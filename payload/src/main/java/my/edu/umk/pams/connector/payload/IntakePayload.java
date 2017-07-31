package my.edu.umk.pams.connector.payload;

import java.util.List;

/**
 * Created by User on 7/30/2017.
 */
public class IntakePayload {

    private IntakeSessionCodePayload intakeSession;
    private List<ProgramCodePayload> offeredProgramCodes;
    private String cohort;

    public IntakeSessionCodePayload getIntakeSession() {
        return intakeSession;
    }

    public void setIntakeSession(IntakeSessionCodePayload intakeSession) {
        this.intakeSession = intakeSession;
    }

    public List<ProgramCodePayload> getOfferedProgramCodes() {
        return offeredProgramCodes;
    }

    public void setOfferedProgramCodes(List<ProgramCodePayload> offeredProgramCodes) {
        this.offeredProgramCodes = offeredProgramCodes;
    }

	public String getCohort() {
		return cohort;
	}

	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
    
}
