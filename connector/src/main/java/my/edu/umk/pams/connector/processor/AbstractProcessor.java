package my.edu.umk.pams.connector.processor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.activation.UnsupportedDataTypeException;
import javax.annotation.PostConstruct;

public abstract class AbstractProcessor {

    protected ObjectMapper mapper;

    @PostConstruct
    protected void postInit() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        mapper.setDateFormat(df);

    }

    protected <X> X convertPayload(String payloadString, Class<X> clazz) {
        try {
            return mapper.readValue(payloadString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String resolveColumns(Class clasz) {
        Field[] declaredFields = clasz.getDeclaredFields();
        String name = "";
        int len = countNumberOfFields(clasz);
        for (int i = 0; i < len; i++) {
            if (i == len - 1)
                name = name + declaredFields[i].getName();
            else
                name = name + declaredFields[i].getName() + ",";
        }
        return name;
    }

    protected String resolveParameter(Class clasz) {
        String name = "";

        int len = countNumberOfFields(clasz);
        for (int i = 0; i < len; i++) {
            if (i == len - 1)
                name = name + "?";
            else
                name = name + "?,";
        }
        return name;
    }

    protected void resolveParameterValue(PreparedStatement stmt, Object object) throws IllegalAccessException, SQLException, UnsupportedDataTypeException {
        Field[] declaredFields = object.getClass().getDeclaredFields();

        int len = countNumberOfFields(object.getClass());

        for (int i = 0; i < len; i++) {
            Field field = declaredFields[i];
            Class<?> type = field.getType();
            Object value = field.get(object);
            int index = i + 1;
            if (type.getName().equals(java.util.Date.class.getName())) {
                if (null == value)
                    stmt.setNull(index, Types.DATE);
                else
                    stmt.setDate(index, new Date(((java.util.Date) value).getTime()));
            } else if (type.getName().equals(object.getClass().getEnclosingClass().getName())) {
                continue;
            } else {
                stmt.setObject(index, value);
            }
        }
    }

    private int countNumberOfFields(Class clasz) {
        Field[] declaredFields = clasz.getDeclaredFields();
        String s1 = "";
        boolean isHasEnclosingClass = false;
        for (Field declaredField : declaredFields) {
            if (declaredField.getType().getName().equals(clasz.getEnclosingClass().getName()))
                isHasEnclosingClass = true;
        }
        return isHasEnclosingClass ? declaredFields.length - 1 : declaredFields.length;
    }

}
