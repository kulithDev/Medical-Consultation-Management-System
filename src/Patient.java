import java.time.LocalDate;

public class Patient extends Person{

    private String patientId;

    public Patient(String title, String firstname, String surname, LocalDate birthDay, String phoneNum, String patientId) {
        super(title, firstname, surname, birthDay, phoneNum);
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
