package my.edu.umk.pams.connector.model;

/**
 * Created by User on 7/31/2017.
 */
public class StaffPayload {

    private String staffId;
    private String name;
    
    public StaffPayload() {
		super();
	}

	public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
