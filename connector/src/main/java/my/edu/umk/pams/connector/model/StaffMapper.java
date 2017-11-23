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
            String staffName = ((String) map.get("NAMA"));
            String staffEmail = ((String) map.get("SM_EMAIL_ADDR"));
            String staffDeptCode = ((String) map.get("SM_DEPT_CODE"));
            String staffTelNo = ((String) map.get("SM_TELNO_WORK"));
            String staffGred= ((String) map.get("SS_SALARY_GRADE"));
            String staffCategory = ((String) map.get("SOG_GROUP_CODE"));
            
            StaffPayload model = new StaffPayload();
            model.setStaffName(staffName);
            model.setStaffId(staffId);
            model.setStaffCategory(staffCategory);
            model.setStaffDepartmentCode(staffDeptCode);
            model.setStaffPhoneNo(staffTelNo);
            model.setStaffEmail(staffEmail);
            
            list.add(model);
        }
        return list;
    }
}
