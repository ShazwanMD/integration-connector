package my.edu.umk.pams.connector.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;

/**
 */
public class CandidatePayload {
    private String name;
    private String gender;
    private String martialStatus;
    private String religion;
    private String race;
    private String matricNo;
    private String email;
    private String phone;
    private String mobile;
    private String fax;
    private String programCodeDescriptionMs;
    private String facultyCodeDescriptionMs;
    private String programLevel;
    private String ResearchTitle;

    // todo: use the object version
    private String cohortCode;
    private String programCode;
    private String facultyCode;
    

    
    private BigDecimal balance;
    private boolean outstanding;
    
    private StudyModePayload studyMode;
    private StudyCenterPayload studyCenter;
    private NationalityCodePayload nationalityCode;
    private UserPayload userPayload;

    //Address
    private AddressPayload primaryAddress;
    private AddressPayload secondaryAddress;
    
    private String supervisorCode;
       
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getProgramLevel() {
		return programLevel;
	}

	public void setProgramLevel(String programLevel) {
		this.programLevel = programLevel;
	}

	public String getFacultyCodeDescriptionMs() {
		return facultyCodeDescriptionMs;
	}

	public void setFacultyCodeDescriptionMs(String facultyCodeDescriptionMs) {
		this.facultyCodeDescriptionMs = facultyCodeDescriptionMs;
	}

	public String getProgramCodeDescriptionMs() {
		return programCodeDescriptionMs;
	}

	public void setProgramCodeDescriptionMs(String programCodeDescriptionMs) {
		this.programCodeDescriptionMs = programCodeDescriptionMs;
	}

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
		
	public StudyCenterPayload getStudyCenter() {
		return studyCenter;
	}

	public void setStudyCenter(StudyCenterPayload studyCenter) {
		this.studyCenter = studyCenter;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public boolean isOutstanding() {
		return outstanding;
	}

	public void setOutstanding(boolean outstanding) {
		this.outstanding = outstanding;
	}

    public NationalityCodePayload getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(NationalityCodePayload nationalityCode) {
        this.nationalityCode = nationalityCode;
    }
    
    public UserPayload getUserPayload() {
		return userPayload;
	}

	public void setUserPayload(UserPayload userPayload) {
		this.userPayload = userPayload;
	}
	
	public String getResearchTitle() {
		return ResearchTitle;
	}

	public void setResearchTitle(String researchTitle) {
		ResearchTitle = researchTitle;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
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

