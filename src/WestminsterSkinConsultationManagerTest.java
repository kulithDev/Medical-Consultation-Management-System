import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {

    WestminsterSkinConsultationManager system = new WestminsterSkinConsultationManager();
    LocalDate testDate = LocalDate.parse("1988-12-05");
    Doctor testDoctor1 = new Doctor("Mr","Alan","Jeff",testDate,"0712345689",1000,"cosmetic dermatology","Sunday/Monday/Friday");
    Doctor testDoctor2 = new Doctor("Ms","Ann","Cox",testDate,"0712345689",1001,"medical dermatology","Sunday/Friday");
    Doctor testDoctor3 = new Doctor("Mr","Ben","Erick",testDate,"0712345689",1003,"cosmetic dermatology","Sunday/Monday");

    @Test
    void loadDocDetails() {
        system.loadfile("doctorDetails.csv");
        assertDoesNotThrow(()->{});
    }
    @Test
    void addDOB() {
        testDoctor1.setBirthDay(LocalDate.parse("1987-04-09"));
        assertDoesNotThrow(()->{});
    }

    @Test
    void addLicenseNum() {
        testDoctor1.setLicenceNum(1001);
        assertDoesNotThrow(()->{});
    }

    @Test
    void addDoctorToTheArrayList() {
        WestminsterSkinConsultationManager.docList.add(testDoctor1);
        assertNotNull(WestminsterSkinConsultationManager.docList);
    }

    @Test
    void deleteDoctorFromTheList() {
        WestminsterSkinConsultationManager.docList.add(testDoctor1);
        WestminsterSkinConsultationManager.docList.add(testDoctor2);
        WestminsterSkinConsultationManager.docList.remove(0);
        assertEquals(1,WestminsterSkinConsultationManager.docList.size());
    }

    @Test
    void printDoctors() {
        WestminsterSkinConsultationManager.docList.add(testDoctor1);
        WestminsterSkinConsultationManager.docList.add(testDoctor2);
        WestminsterSkinConsultationManager.docList.add(testDoctor3);
        system.printDoctors();
        assertEquals("Cox",WestminsterSkinConsultationManager.docList.get(0).getSurname());
    }

    @Test
    void saveToFile() {
        WestminsterSkinConsultationManager.docList.add(testDoctor1);
        WestminsterSkinConsultationManager.docList.add(testDoctor2);
        WestminsterSkinConsultationManager.docList.add(testDoctor3);
        system.saveToFile();
        assertDoesNotThrow(()->{});
    }
}