package my.edu.umk.pams.connector.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 */
public class CandidatePayload {
    private String name;
    private String matricNo;
    private String email;
    private String phone;
    private String mobile;
    private String fax;

    // todo: use the object version
    private String cohortCode;
    private String programCode;
    private String facultyCode;
    private StudyModePayload studyMode;
    private ResidencyCodePayload residencyCode;

    //Address
    private AddressPayload primaryAddress;
    private AddressPayload secondaryAddress;
    
    private String supervisorCode;
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCohortCode() {
        return cohortCode;
    }

    public void setCohortCode(String cohortCode) {
        this.cohortCode = cohortCode;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public AddressPayload getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(AddressPayload primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public AddressPayload getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(AddressPayload secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getSupervisorCode() {
		return supervisorCode;
	}

	public void setSupervisorCode(String supervisorCode) {
		this.supervisorCode = supervisorCode;
	}

	public StudyModePayload getStudyMode() {
		return studyMode;
	}

	public void setStudyMode(StudyModePayload studyMode) {
		this.studyMode = studyMode;
	}

    public ResidencyCodePayload getResidencyCode() {
        return residencyCode;
    }

    public void setResidencyCode(ResidencyCodePayload residencyCode) {
        this.residencyCode = residencyCode;
    }

    @JsonCreator
    public static CandidatePayload create(String jsonString) {
        CandidatePayload o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, CandidatePayload.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}

