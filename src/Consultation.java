public class Consultation {

    Patient patient;
    private float cost;
    private String notes;
    private String doctorLicenseNum;
    private String appointmentDate;


    public Consultation(Patient patient, float cost, String notes, String doctorLicenseNum, String appointmentDate) {
        this.patient = patient;
        this.cost = cost;
        this.notes = notes;
        this.doctorLicenseNum = doctorLicenseNum;
        this.appointmentDate = appointmentDate;

    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
