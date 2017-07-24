package my.edu.umk.pams.connector.model;

public class Candidate {
    private String matricNo;
    private String name;
    private String credentialNo;


    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "matricNo='" + matricNo + '\'' +
                ", name='" + name + '\'' +
                ", credentialNo='" + credentialNo + '\'' +
                '}';
    }
}
