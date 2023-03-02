import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ConfirmConsultationGUI extends JFrame implements ActionListener {

    JButton confirmbtn = new JButton("Confirm");
    JButton backbtn = new JButton("Back");

    Font bodyFont = new Font("Arial", Font.PLAIN, 14);
    Color bgColor = new Color(237,224,212);

    static String docNumSelected;

    public ConfirmConsultationGUI() {

        setLayout(null);
        int rowNum = DoctorTable.docTable.getSelectedRow();
        TableModel model = DoctorTable.docTable.getModel();
        String docAvailableSelected = model.getValueAt(rowNum, 6).toString();

        //panel to show consultation details
        JPanel consultationDetails = new JPanel();
        consultationDetails.setBackground(bgColor);
        consultationDetails.setLayout(null);

        if ((docAvailableSelected.toLowerCase()).contains(AddConsultationGUI.appointmentDate.toLowerCase())){
            //details of the selected doctor
            docNumSelected =  model.getValueAt(rowNum, 0).toString();
            String docNameSelected =  model.getValueAt(rowNum,1).toString()+" "+model.getValueAt(rowNum,2).toString()+" "+model.getValueAt(rowNum,3).toString();
            String docSpecSelected = model.getValueAt(rowNum, 5).toString();
            String docPhnNumSelected = model.getValueAt(rowNum,4).toString();

            JLabel docName = new JLabel("Name of the doctor : " + docNameSelected);
            docName.setFont(bodyFont);
            docName.setBounds(30, 5, 300, 25);
            consultationDetails.add(docName);

            JLabel docSpec = new JLabel("Specialisation of the doctor : " + docSpecSelected);
            docSpec.setFont(bodyFont);
            docSpec.setBounds(30, 35, 300, 25);
            consultationDetails.add(docSpec);

            JLabel docNum = new JLabel("Medical license number of the doctor : " + docNumSelected);
            docNum.setFont(bodyFont);
            docNum.setBounds(30, 65, 300, 25);
            consultationDetails.add(docNum);

            JLabel docAvailability = new JLabel("Availability of the doctor : " + docAvailableSelected);
            docAvailability.setFont(bodyFont);
            docAvailability.setBounds(30, 95, 300, 25);
            consultationDetails.add(docAvailability);

            JLabel docPhnNum = new JLabel("Contact number of the doctor : " + docPhnNumSelected);
            docPhnNum.setFont(bodyFont);
            docPhnNum.setBounds(30, 125, 350, 25);
            consultationDetails.add(docPhnNum);
        }else { //if selected doctor is not available

            //selecting doctors available from the list
            ArrayList<Doctor> availableDoctors = new ArrayList<>();
            for (Doctor doctor : WestminsterSkinConsultationManager.docList){
                if ((doctor.getAvailability().toString().toLowerCase()).contains(AddConsultationGUI.appointmentDate.toLowerCase())){
                    availableDoctors.add(doctor);
                }
            }

            if (!availableDoctors.isEmpty()) {
                //selecting a random doctor available
                Collections.shuffle(availableDoctors);
                Doctor selectedDoc = availableDoctors.get(0);

                //details of the selected doctor
                docNumSelected = String.valueOf(selectedDoc.getLicenceNum());
                String docNameSelected = selectedDoc.getTitle() + " " + selectedDoc.getFirstname() + " " + selectedDoc.getSurname();
                String docSpecSelected = selectedDoc.getSpecialisation();
                String docPhnNumSelected = selectedDoc.getPhoneNum();

                JLabel warningmsg = new JLabel("*The selected doctor is unavailable.We suggest the below doctor for your treatments.");
                warningmsg.setFont(bodyFont);
                warningmsg.setBounds(30, 05, 600, 25);
                consultationDetails.add(warningmsg);

                JLabel docName = new JLabel("Name of the doctor : " + docNameSelected);
                docName.setFont(bodyFont);
                docName.setBounds(30, 35, 300, 25);
                consultationDetails.add(docName);

                JLabel docSpec = new JLabel("Specialisation of the doctor : " + docSpecSelected);
                docSpec.setFont(bodyFont);
                docSpec.setBounds(30, 65, 300, 25);
                consultationDetails.add(docSpec);

                JLabel docNum = new JLabel("Medical license number of the doctor : " + docNumSelected);
                docNum.setFont(bodyFont);
                docNum.setBounds(30, 95, 300, 25);
                consultationDetails.add(docNum);

                JLabel docAvailability = new JLabel("Availability of the doctor : " + selectedDoc.getAvailability());
                docAvailability.setFont(bodyFont);
                docAvailability.setBounds(30, 125, 300, 25);
                consultationDetails.add(docAvailability);

                JLabel docPhnNum = new JLabel("Contact number of the doctor : " + docPhnNumSelected);
                docPhnNum.setFont(bodyFont);
                docPhnNum.setBounds(30, 155, 350, 25);
                consultationDetails.add(docPhnNum);
            }else{
                JLabel warningmsg = new JLabel("*All the doctors are unavailable on the selected date.");
                warningmsg.setFont(bodyFont);
                warningmsg.setBounds(30, 05, 600, 25);
                consultationDetails.add(warningmsg);
            }

        }
        backbtn.setBounds(30, 175, 100, 25);
        backbtn.setFont(bodyFont);
        backbtn.addActionListener(this);
        consultationDetails.add(backbtn);

        confirmbtn.setBounds(150, 175, 100, 25);
        confirmbtn.setFont(bodyFont);
        confirmbtn.addActionListener(this);
        consultationDetails.add(confirmbtn);

        add(consultationDetails).setBounds(0, 300, 600, 210);
        setBounds(400, 150, 600, 550);
        setBackground(bgColor);
        setTitle("Appointment Confirmation");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == confirmbtn) {
            //saving the consultation details
            WestminsterSkinConsultationManager.consultationsList.add(AddConsultationGUI.consultation);
            this.setVisible(false);
            GUIHome guiHome = new GUIHome();
            guiHome.setVisible(true);
            JOptionPane.showMessageDialog(this, "Consultation is added successfully!");
        }else if (event.getSource() == backbtn){
            this.setVisible(false);
            AddConsultationGUI addConsultationGUI = new AddConsultationGUI();
            addConsultationGUI.setVisible(true);
        }
    }
}