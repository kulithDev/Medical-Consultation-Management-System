import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{

    // maintains the list of the doctors and provides all the methods for the system manager

    static ArrayList<Doctor> docList = new ArrayList<>();
    static ArrayList<Patient> patientsList = new ArrayList<>();
    static ArrayList<Consultation> consultationsList = new ArrayList<>();

    public static Scanner input = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
        WestminsterSkinConsultationManager consultManager = new WestminsterSkinConsultationManager();
        consultManager.loadfile("doctorDetails.csv"); //load details of doctors from the file
        boolean returnMenu = true;
        while (returnMenu) {
            try {
                System.out.print("""
                        \nWelcome to the Westminster Skin Consultation Manager console menu
                                
                        Enter the relevant number to access the option :
                            1- Add a new doctor
                            2- Delete a doctor
                            3- Print the list of doctors
                            4- Save to the file
                            5- Graphical User Interface (GUI)
                            6- Quit
                                
                        Option : """);

                int option = input.nextInt();
                switch (option) {
                    case 1:
                        //add a new doctor to
                        consultManager.addNewDoctor();
                        break;
                    case 2:
                        //delete an existing doctor
                        if(docList.size()==0){
                            System.out.println("\nThe list of doctors is empty");
                        }
                        else {
                            consultManager.deleteDoctor();
                        }
                        break;
                    case 3:
                        //print the list of doctors
                        consultManager.printDoctors();
                        break;
                    case 4:
                        //save in a file all the information
                        consultManager.saveToFile();
                        break;
                    case 5:
                        //enter to the GUI;
                        GUIHome guiHome = new GUIHome();
                        guiHome.setVisible(true);
                        break;
                    case 6:
                        //Quit
                        System.out.println("You quit the program");
                        returnMenu = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
        }
    }


    @Override
    public void addNewDoctor() {
        if (docList.size()<10) {
            System.out.print("Enter the title (Mr/Ms):");
            String title = input.next();

            System.out.print("Enter the first name :");
            String firstname = input.next();

            System.out.print("Enter the surname :");
            String surname = input.next();

            String dob = null;
            LocalDate birthDay = null;
            while (dob == null) {
                System.out.println("Enter the date of birth (yyyy-mm-dd):");
                try {
                    dob = input.next();
                    birthDay = LocalDate.parse(dob, DateTimeFormatter.ISO_DATE);
                } catch (Exception e) {
                    System.out.println("Invalid date. Please check the format.");
                    dob = null;
                }
            }

            String phnNUm = null;
            while (phnNUm == null) {
                try {
                    System.out.print("Enter the phone number :");
                    phnNUm = input.next();
                    if (phnNUm.length() == 10) {
                        String phoneNum = phnNUm;
                    } else {
                        System.out.println("Invalid phone number");
                        phnNUm = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid phone number");
                    phnNUm = null;
                }
            }

            int licenceNum = 0;
            while (licenceNum==0) {
                try {
                    System.out.print("Enter the medical licence number :");
                    int licenceNumber = Integer.parseInt(input.next());
                    licenceNum =licenceNumber;
                }catch (Exception e){
                    System.out.println("Invalid license number");
                    licenceNum = 0;
                }
            }

            System.out.print("Enter the specialisation :");
            String specialisation = input.next();

            System.out.println("Enter available days (Sunday/ Monday/ etc.) :");
            String availableDates = input.next();

            Doctor newDoctor = new Doctor(title, firstname, surname, birthDay, phnNUm, licenceNum, specialisation, availableDates);
            docList.add(newDoctor);
            System.out.println("\nDetails of the new doctor have been added successfully");
        }else {
            System.out.println("\nYou can't add a new doctor. The maximum count has reached");
        }
    }

    @Override
    public void deleteDoctor() {
        try {
            System.out.print("\nEnter the medical licence number of the doctor to be deleted : ");
            int licenceNum = input.nextInt();
            String validLicenceNum = "false";

            for (Doctor doctor : docList) {         //check the list of doctors
                if (doctor.getLicenceNum() == licenceNum) {
                    //details of the removed doctor
                    System.out.println("\nThe doctor has been removed successfully. Details as follows.\n");
                    System.out.println("Name : " + doctor.getTitle() + " " + doctor.getFirstname() + " " + doctor.getSurname());
                    System.out.println("Medical licence number : " + doctor.getLicenceNum());
                    System.out.println("Specialisation : " + doctor.getSpecialisation());
                    System.out.println("Birthday : " + doctor.getBirthDay());
                    System.out.println("Contact number : " + doctor.getPhoneNum());
                    System.out.println("Availability : " + doctor.getAvailability());

                    docList.remove(doctor);       //remove the relevant doctor and print the details
                    System.out.println("\nTotal number of doctors in the center : " + docList.size());

                    validLicenceNum = "true";
                    break;
                }
            }
            if (validLicenceNum.equals("false")) {      //if the entered licence number is not in the list
                System.out.println("\nInvalid medical licence number");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\nThe list of doctors is empty");
        }
    }

    @Override
    public void printDoctors() {
        if (docList.size()==0){
            System.out.println("The list of doctors is empty");
        }else {
            docList.sort(Comparator.comparing(Doctor::getSurname));   //sort the list of doctors using the Surname
            System.out.println("\nThe list of doctors in the center as follows");
            //print the sorted list of doctors
            for (Doctor doctor : docList) {
                System.out.println("\nName : " + doctor.getTitle() + " " + doctor.getFirstname() + " " + doctor.getSurname());
                System.out.println("Medical licence number : " + doctor.getLicenceNum());
                System.out.println("Specialisation : " + doctor.getSpecialisation());
                System.out.println("Birthday : " + doctor.getBirthDay());
                System.out.println("Contact number : " + doctor.getPhoneNum());
                System.out.println("Available days : " + doctor.getAvailability());
            }
        }
    }

    @Override
    public void saveToFile() {
        File file = new File("doctorDetails.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            for(Doctor doctor: docList){
                fileWriter.write(doctor.getTitle());
                fileWriter.write(","+ doctor.getFirstname());
                fileWriter.write(","+ doctor.getSurname());
                fileWriter.write(","+ doctor.getLicenceNum());
                fileWriter.write(","+ doctor.getSpecialisation());
                fileWriter.write(","+ doctor.getBirthDay());
                fileWriter.write(","+ doctor.getPhoneNum());
                fileWriter.write(","+ doctor.getAvailability()+"\n");
            }
            fileWriter.close();
            System.out.println("Details of doctors have been added to the document");
        }
        catch (IOException e)
        {
            System.out.println("Error occured while writing to the file. Please try again");
        }
    }

    public void loadfile(String fileName){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String docline;
            while ((docline = bufferedReader.readLine()) != null){
                // getting data from the csv file
                String[] docDetails = docline.split(",");

                String title = docDetails[0];
                String firstname = docDetails[1];
                String surname = docDetails[2];
                int licenceNum = Integer.parseInt(docDetails [3]);
                String Specialisation = docDetails [4];
                LocalDate dob = LocalDate.parse(docDetails[5]);
                String phoneNum = docDetails [6];
                String availability = docDetails[7];

                //adding the data to the arraylist
                docList.add(new Doctor(title, firstname, surname, dob, phoneNum,licenceNum,Specialisation,availability));
            }
        } catch (IOException ioe) {
            System.out.println("Error occured while loading the file. Please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File containing details of doctors is empty");
        }
        catch (DateTimeParseException e) {
            System.out.println("Error Ocuured while reading");
        }


    }
}

