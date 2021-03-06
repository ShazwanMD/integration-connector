package my.edu.umk.pams.connector.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 */
public class FacultyCodePayload {
    private String code;
    private String description;
    private String prefix;

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

    public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@JsonCreator
    public static FacultyCodePayload create(String jsonString) {
        FacultyCodePayload o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, FacultyCodePayload.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
