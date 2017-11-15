package my.edu.umk.pams.connector.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 */
public class ProgramCodePayload {
    private String code;
    private String descriptionMs;
    private String descriptionEn;
    private FacultyCodePayload facultyCode;
    private ProgramLevelPayload programLevel;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescriptionMs() {
		return descriptionMs;
	}

	public void setDescriptionMs(String descriptionMs) {
		this.descriptionMs = descriptionMs;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public FacultyCodePayload getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(FacultyCodePayload facultyCode) {
        this.facultyCode = facultyCode;
    }

    public ProgramLevelPayload getProgramLevel() {
        return programLevel;
    }

    public void setProgramLevel(ProgramLevelPayload programLevel) {
        this.programLevel = programLevel;
    }

    @JsonCreator
    public static ProgramCodePayload create(String jsonString) {
        ProgramCodePayload o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ProgramCodePayload.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
