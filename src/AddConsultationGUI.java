import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AddConsultationGUI extends JFrame implements ActionListener {
    //labels of the form
    JLabel patientDetailsHeading = new JLabel("Add patient details here.");
    JLabel titleLabel = new JLabel("Title (Mr/Ms)");
    JLabel firstnameLabel = new JLabel("First Name");
    JLabel surnameLabel = new JLabel("Last Name");
    JLabel dobLabel = new JLabel("Date of Birth (yyyy-mm-dd)");
    JLabel contactNumlbl = new JLabel("Contact Number");
    JLabel patientIdlbl = new JLabel("Patient ID Number");
    JLabel appointmentDatelbl = new JLabel("Date of Appointment");
    JLabel costlbl = new JLabel("Cost for the Appointment");
    JLabel noteslbl = new JLabel("Additional Notes about the Skin");

    //text fields of the form
    JTextField titlefld = new JTextField();
    JTextField firstnamefld = new JTextField();
    JTextField surnamefld = new JTextField();
    JTextField dobfld = new JTextField();
    JTextField contactnumfld = new JTextField();
    JTextField patientIdfld = new JTextField();
    JTextField appointmentDatefld = new JTextField();
    JTextField costfld = new JTextField();
    JTextArea notesfld = new JTextArea();

    //buttons
    JButton verifybtn = new JButton("Verify");
    JButton nextbtn = new JButton("Next");
    JButton resetbtn = new JButton("Reset");
    JButton backbtn = new JButton("Back");

    //panel to enter patient details
    JPanel patientDetailsPanel = new JPanel();

    Font bodyFont = new Font("Arial", Font.PLAIN, 14);
    Color bgColor = new Color(230,204,178);

    public AddConsultationGUI() {
        //set the title of the frame
        super("Westminster Skin Consultation");

        //patient details panel
        TitledBorder title = BorderFactory.createTitledBorder("Patient Details");
        title.setTitleFont(new Font("SansSerif", Font.BOLD, 20));
        patientDetailsPanel.setBorder(title);
        patientDetailsPanel.setBackground(bgColor);
        patientDetailsPanel.setLayout(null);

        patientIdlbl.setBounds(40,50, 250,25);
        patientIdlbl.setFont(bodyFont);
        patientDetailsPanel.add(patientIdlbl);

        patientIdfld.setBounds(240,50, 250,25);
        patientIdfld.setFont(bodyFont);
        patientDetailsPanel.add(patientIdfld);

        verifybtn.setBounds(390,100, 100,25);
        verifybtn.setFont(bodyFont);
        verifybtn.addActionListener(this);
        patientDetailsPanel.add(verifybtn);

        titleLabel.setBounds(40,150, 100,25);
        titleLabel.setFont(bodyFont);
        patientDetailsPanel.add(titleLabel);

        firstnameLabel.setBounds(40,200, 100,25);
        firstnameLabel.setFont(bodyFont);
        patientDetailsPanel.add(firstnameLabel);

        surnameLabel.setBounds(40,250, 100,25);
        surnameLabel.setFont(bodyFont);
        patientDetailsPanel.add(surnameLabel);

        dobLabel.setBounds(40,300, 200,25);
        dobLabel.setFont(bodyFont);
        patientDetailsPanel.add(dobLabel);

        contactNumlbl.setBounds(40,350, 200,25);
        contactNumlbl.setFont(bodyFont);
        patientDetailsPanel.add(contactNumlbl);

        appointmentDatelbl.setFont(bodyFont);
        appointmentDatelbl.setBounds(40,400,200,25);
        patientDetailsPanel.add(appointmentDatelbl);

        appointmentDatefld.setFont(bodyFont);
        appointmentDatefld.setBounds(240,400,250,25);
        patientDetailsPanel.add(appointmentDatefld);

        noteslbl.setFont(bodyFont);
        noteslbl.setBounds(40,450,200,25);
        patientDetailsPanel.add(noteslbl);

        notesfld.setFont(bodyFont);
        notesfld.setBounds(250,450,470,75);
        patientDetailsPanel.add(notesfld);


        //panel to add table of doctors
        DoctorTable doctorTable = new DoctorTable(WestminsterSkinConsultationManager.docList);
        title = BorderFactory.createTitledBorder("Details of Doctors");
        title.setTitleFont(new Font("Arial", Font.BOLD, 20));
        doctorTable.setBorder(title);
        DoctorTable.instruction.setTitle("Select the preferred doctor");

        //panel to show consultation details
        JPanel consultationDetails = new JPanel();
        consultationDetails.setBackground(bgColor);
        consultationDetails.setLayout(null);



        costlbl.setFont(bodyFont);
        costlbl.setBounds(40,95,200,25);
        consultationDetails.add(costlbl);

        costfld.setFont(bodyFont);
        costfld.setBounds(240,95,250,25);
        consultationDetails.add(costfld);


        backbtn.setBounds(40,185, 100,25);
        backbtn.setFont(bodyFont);
        backbtn.addActionListener(this);
        consultationDetails.add(backbtn);

        resetbtn.setBounds(770,185, 100,25);
        resetbtn.setFont(bodyFont);
        resetbtn.addActionListener(this);
        consultationDetails.add(resetbtn);

        nextbtn.setBounds(890,185, 100,25);
        nextbtn.setFont(bodyFont);
        nextbtn.addActionListener(this);
        consultationDetails.add(nextbtn);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgColor);
        mainPanel.setPreferredSize(new Dimension(1080,1140));
        mainPanel.setLayout(null);

        //add panel to the main panel
        mainPanel.add(patientDetailsPanel).setBounds(10,10,1060,600);
        mainPanel.add(doctorTable).setBounds(10,610,1060,280);
        mainPanel.add(consultationDetails).setBounds(10,890,1060,240);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setPreferredSize(new Dimension(1080,800));
        scrollPane.setBackground(bgColor);

        JPanel container = new JPanel();
        container.setBackground(bgColor);
        container.add(scrollPane, BorderLayout.CENTER);

        //make dimensions of the frame
        getContentPane().add(container);
        setBounds(250,5,1110,800);
        setBackground(bgColor);
        setVisible(true);
    }

    static String appointmentDate;
    static Consultation consultation;
    boolean newPatient = true;

    //verify the patient ID
    private void verifyPatient(){
        for (Patient patient: WestminsterSkinConsultationManager.patientsList){
            if (Objects.equals(patientIdfld.getText(), patient.getPatientId())){
                JLabel patientStatus = new JLabel("Existing patient");
                patientStatus.setBounds(40,100, 200,25);
                patientStatus.setFont(new Font("Arial", Font.ITALIC, 16));
                patientDetailsPanel.add(patientStatus);

                titlefld.setBounds(240,150, 250,25);
                titlefld.setText(patient.getTitle());
                titlefld.setEditable(false);
                titleLabel.setFont(bodyFont);
                patientDetailsPanel.add(titlefld);

                firstnamefld.setBounds(240,200, 250,25);
                firstnamefld.setText(patient.getFirstname());
                firstnamefld.setEditable(false);
                firstnamefld.setFont(bodyFont);
                patientDetailsPanel.add(firstnamefld);

                surnamefld.setBounds(240,250, 250,25);
                surnamefld.setText(patient.getSurname());
                surnamefld.setEditable(false);
                surnamefld.setFont(bodyFont);
                patientDetailsPanel.add(surnamefld);

                dobfld.setBounds(240,300, 250,25);
                dobfld.setText(patient.getBirthDay().toString());
                dobfld.setEditable(false);
                dobfld.setFont(bodyFont);
                patientDetailsPanel.add(dobfld);

                contactnumfld.setBounds(240,350, 250,25);
                contactnumfld.setText(patient.getPhoneNum());
                contactnumfld.setEditable(false);
                contactnumfld.setFont(bodyFont);
                patientDetailsPanel.add(contactnumfld);

                costfld.setBounds(240,95,250,25);
                costfld.setText("25.00");

                newPatient = false;
            }
        }
        if (newPatient){
            JLabel patientStatus = new JLabel("New patient");
            patientStatus.setBounds(40,100, 200,25);
            patientStatus.setFont(new Font("Arial", Font.ITALIC, 16));
            patientDetailsPanel.add(patientStatus);

            titlefld.setBounds(240,150, 250,25);
            titleLabel.setFont(bodyFont);
            patientDetailsPanel.add(titlefld);
            titlefld.setVisible(true);

            firstnamefld.setBounds(240,200, 250,25);
            firstnamefld.setFont(bodyFont);
            patientDetailsPanel.add(firstnamefld);

            surnamefld.setBounds(240,250, 250,25);
            surnamefld.setFont(bodyFont);
            patientDetailsPanel.add(surnamefld);

            dobfld.setBounds(240,300, 250,25);
            dobfld.setFont(bodyFont);
            patientDetailsPanel.add(dobfld);

            contactnumfld.setBounds(240,350, 250,25);
            contactnumfld.setFont(bodyFont);
            patientDetailsPanel.add(contactnumfld);

            costfld.setBounds(240,95,250,25);
            costfld.setText("15.00");
        }
        patientDetailsPanel.revalidate();
        patientDetailsPanel.repaint();
    }

    //view and save the patient details
    private void patientDetails(){
        String title = titlefld.getText();
        String firstname = firstnamefld.getText();
        String surname = surnamefld.getText();
        LocalDate birthDay = LocalDate.parse(dobfld.getText());
        String phnNUm = contactnumfld.getText();
        String patientId = patientIdfld.getText();
        appointmentDate = (LocalDate.parse(appointmentDatefld.getText(), DateTimeFormatter.ISO_DATE)).getDayOfWeek().name();
        float cost = Float.parseFloat(costfld.getText());
        String encryptedNotes = encryptData(notesfld.getText());

        Patient addPatient = new Patient(title, firstname, surname, birthDay, phnNUm, patientId);
        if (newPatient) {  //add the patient to the list if that is a new patient
            WestminsterSkinConsultationManager.patientsList.add(addPatient);
        }

        //add details to the consultation list
        consultation = new Consultation(addPatient,cost,encryptedNotes,ConfirmConsultationGUI.docNumSelected,appointmentDate);

        JPanel addedPatient = new JPanel();
        addedPatient.setBackground(bgColor);
        addedPatient.setLayout(null);
        JLabel heading = new JLabel("Appointment Details");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(30,25,400,50);
        addedPatient.add(heading);

        JLabel addedName = new JLabel("Name : "+title+" "+firstname+" "+surname);
        addedName.setBounds(30,55,200,50);
        addedName.setFont(bodyFont);
        addedPatient.add(addedName);

        JLabel addedDob = new JLabel("Birth Date : "+birthDay);
        addedDob.setBounds(30,85,200,50);
        addedDob.setFont(bodyFont);
        addedPatient.add(addedDob);

        JLabel addedPhnNum = new JLabel("Contact Number : "+phnNUm);
        addedPhnNum.setBounds(30,115,200,50);
        addedPhnNum.setFont(bodyFont);
        addedPatient.add(addedPhnNum);

        JLabel addedId = new JLabel("Patient ID : "+patientId);
        addedId.setBounds(30,145,200,50);
        addedId.setFont(bodyFont);
        addedPatient.add(addedId);

        JLabel addedAppointmentDate = new JLabel("Appointment Date : "+appointmentDatefld.getText()+"-"+appointmentDate);
        addedAppointmentDate.setBounds(30,175,300,50);
        addedAppointmentDate.setFont(bodyFont);
        addedPatient.add(addedAppointmentDate);

        JLabel addedCost = new JLabel("Gross Payment ($) : "+cost);
        addedCost.setBounds(30,205,200,50);
        addedCost.setFont(bodyFont);
        addedPatient.add(addedCost);

        JTextArea addedNotes = new JTextArea("Notes : "+ encryptedNotes);
        addedNotes.setEditable(false);
        addedNotes.setBounds(30,245,500,100);
        addedNotes.setFont(bodyFont);
        addedPatient.add(addedNotes);

        ConfirmConsultationGUI confirmConsultationGUI = new ConfirmConsultationGUI();
        confirmConsultationGUI.add(addedPatient).setBounds(0, 0, 600, 385);
        //make dimensions of the frame
        confirmConsultationGUI.setVisible(true);
        this.setVisible(false);

        titlefld.setText("");
        firstnamefld.setText("");
        surnamefld.setText("");
        dobfld.setText("");
        contactnumfld.setText("");
        patientIdfld.setText("");
        appointmentDatefld.setText("");
        costfld.setText("");
    }

    //encrypting data
    public static String encryptData(String passKey) {
        String encryptedData = "";
        int keyLength = passKey.length();
        char character;
        for(int i = 0; i < keyLength; i++){
            character = passKey.charAt(i);
            character += 5; //change the character
            encryptedData += character;
        }
        return encryptedData;
    }
    //decrypting data
    public static String decryptData(String passKey) {
        String decryptData = "";
        int keyLength = passKey.length();
        char character;
        for(int i = 0; i < keyLength; i++){
            character = passKey.charAt(i);
            character -= 5;
            decryptData += character;
        }
        return decryptData;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == verifybtn){
            verifyPatient();
        }

        if (event.getSource() == nextbtn){
            try {
                patientDetails();
            }catch (DateTimeParseException dateTimeParseException){
                JOptionPane.showMessageDialog(this, "Add correct data to all the fields");
            }

        }else if (event.getSource() == resetbtn){
            titlefld.setText("");
            firstnamefld.setText("");
            surnamefld.setText("");
            dobfld.setText("");
            contactnumfld.setText("");
            patientIdfld.setText("");
            appointmentDatefld.setText("");
            costfld.setText("");
        }else if (event.getSource() == backbtn){
            this.setVisible(false);
            GUIHome home = new GUIHome();
            home.setVisible(true);
        }
    }
}
