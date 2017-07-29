package my.edu.umk.pams.connector.payload;

/**
 */
public class AdmissionPayload {

    private AcademicSessionCodePayload academicSession;
    private StudentPayload student;
    private CohortCodePayload cohortCode;
    private StudyModePayload studyMode;
    private Integer ordinal;

    public AcademicSessionCodePayload getAcademicSession() {
        return academicSession;
    }

    public void setAcademicSession(AcademicSessionCodePayload academicSession) {
        this.academicSession = academicSession;
    }

    public StudentPayload getStudent() {
        return student;
    }

    public void setStudent(StudentPayload student) {
        this.student = student;
    }

    public CohortCodePayload getCohortCode() {
        return cohortCode;
    }

    public void setCohortCode(CohortCodePayload cohortCode) {
        this.cohortCode = cohortCode;
    }

    public StudyModePayload getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(StudyModePayload studyMode) {
        this.studyMode = studyMode;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}
