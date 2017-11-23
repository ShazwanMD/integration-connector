package my.edu.umk.pams.connector.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.payload.StaffPayload;

/**
 */
@Component
public class StaffMapper {
    private static final Logger LOG = LoggerFactory.getLogger(StaffMapper.class);

    public StaffMapper() {
    }

    public List<StaffPayload> process(List<Map<String, Object>> result) {
        List<StaffPayload> list = new ArrayList<StaffPayload>();

        for (Map<String, Object> map : result) {
            String staffId = ((String) map.get("SM_STAFF_ID"));
            String staffName = ((String) map.get("SM_STAFF_NAME"));
            System.out.println("staffId {}"+staffId);
            System.out.println("staffName {}"+staffName);
            StaffPayload model = new StaffPayload();
            model.setName(staffName);
            model.setStaffId(staffId);
            list.add(model);
        }
        return list;
    }
}
