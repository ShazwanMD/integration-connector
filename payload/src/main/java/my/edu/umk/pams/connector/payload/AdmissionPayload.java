package my.edu.umk.pams.connector.payload;

/**
 */
public class AdmissionPayload {

    private AcademicSessionCodePayload academicSession;
    private StudentPayload student;
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

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}
