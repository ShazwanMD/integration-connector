package my.edu.umk.pams.connector.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 */
public class CohortCodePayload {
    private String code;
    private String description;
    private ProgramCodePayload programCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProgramCodePayload getProgramCode() {
        return programCode;
    }

    public void setProgramCode(ProgramCodePayload programCode) {
        this.programCode = programCode;
    }

    @JsonCreator
    public static CohortCodePayload create(String jsonString) {
        CohortCodePayload o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, CohortCodePayload.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
