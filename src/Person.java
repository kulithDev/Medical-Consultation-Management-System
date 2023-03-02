import java.time.LocalDate;

public class Person {

    private String title;
    private String firstname;
    private String surname;
    private LocalDate birthDay;
    private String phoneNum;

    public Person(String title, String firstname, String surname, LocalDate birthDay, String phoneNum) {
        this.title = title;
        this.firstname = firstname;
        this.surname = surname;
        this.birthDay = birthDay;
        this.phoneNum = phoneNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
