package my.edu.umk.pams.connector.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 */
public class AdmissionChargePayload {
    private String academicSessionCode;
    private String cohortCode;
    private String residencyCode;
    private String studyMode;
    private Integer ordinal;

    public String getAcademicSessionCode() {
        return academicSessionCode;
    }

    public void setAcademicSessionCode(String academicSessionCode) {
        this.academicSessionCode = academicSessionCode;
    }

    public String getCohortCode() {
        return cohortCode;
    }

    public void setCohortCode(String cohortCode) {
        this.cohortCode = cohortCode;
    }

    public String getResidencyCode() {
        return residencyCode;
    }

    public void setResidencyCode(String residencyCode) {
        this.residencyCode = residencyCode;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    @JsonCreator
    public static AdmissionChargePayload create(String jsonString) {
        AdmissionChargePayload o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, AdmissionChargePayload.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
