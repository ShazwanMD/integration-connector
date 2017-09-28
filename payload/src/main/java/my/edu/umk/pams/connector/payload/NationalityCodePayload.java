package my.edu.umk.pams.connector.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 */
public class NationalityCodePayload {
    private String code;
    private String descriptionMs;
    private String descriptionEn;

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

	@JsonCreator
    public static NationalityCodePayload create(String jsonString) {
        NationalityCodePayload o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, NationalityCodePayload.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
