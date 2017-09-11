package my.edu.umk.pams.connector.payload;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StaffPayload {
	String staffId;
	String staffName;
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	@JsonCreator
    public static StaffPayload create(String jsonString) {
		StaffPayload o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, StaffPayload.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}