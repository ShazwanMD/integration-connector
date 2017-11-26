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
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.StaffPayload;

/**
 */
@Component
public class DepartmentMapper {
    private static final Logger LOG = LoggerFactory.getLogger(DepartmentMapper.class);

    public DepartmentMapper() {
    }

    public List<FacultyCodePayload> process(List<Map<String, Object>> result) {
        List<FacultyCodePayload> list = new ArrayList<FacultyCodePayload>();

        for (Map<String, Object> map : result) {
            String code = ((String) map.get("DM_DEPT_CODE"));
            String description = ((String) map.get("DM_DEPT_DESC"));
            String prefix = ((String) map.get("DM_ID_PREFIX"));
            
            FacultyCodePayload model = new FacultyCodePayload();
            model.setCode(code);
            model.setDescription(description);
            model.setPrefix(prefix);
            
            list.add(model);
        }
        return list;
    }
}
