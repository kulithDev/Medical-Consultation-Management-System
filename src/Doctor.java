import java.time.LocalDate;

public class Doctor extends Person{

    private int licenceNum;
    private String specialisation;
    private String availability;

    public Doctor(String title, String firstname, String surname, LocalDate birthDay, String phoneNum, int licenceNum, String specialisation, String availability) {
        super(title, firstname, surname, birthDay, phoneNum);
        this.licenceNum = licenceNum;
        this.specialisation = specialisation;
        this.availability = availability;
    }

    public int getLicenceNum() {
        return licenceNum;
    }

    public void setLicenceNum(int licenceNum) {
        this.licenceNum = licenceNum;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
