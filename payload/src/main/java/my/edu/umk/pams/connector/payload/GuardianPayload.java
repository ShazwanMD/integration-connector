package my.edu.umk.pams.connector.payload;

public class GuardianPayload {
	
	private String identityNo;
	
	private String name;
	
	private String phone;
	
	private StudentPayload studentPayload;
	
	private GuardianTypePayload type;
	
	

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public StudentPayload getStudentPayload() {
		return studentPayload;
	}

	public void setStudentPayload(StudentPayload studentPayload) {
		this.studentPayload = studentPayload;
	}

	public GuardianTypePayload getType() {
		return type;
	}

	public void setType(GuardianTypePayload type) {
		this.type = type;
	}


	
	
	
	
	

}
