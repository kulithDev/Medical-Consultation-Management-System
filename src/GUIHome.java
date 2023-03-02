import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIHome extends JFrame implements ActionListener {

    JLabel homeHeading = new JLabel("Westminster Skin Consultation");
    JButton addConsultation = new JButton("Add a Consultation");
    JButton viewDoctors = new JButton("View list of Doctors");

    Color bgColor = new Color(237,224,212);


    public GUIHome(){
        setLocationRelativeTo(null);
        setLayout(new GridLayout());


        homeHeading.setFont(new Font("SansSerif", Font.BOLD, 45));
        addConsultation.setFont(new Font("SansSerif", Font.PLAIN, 16));
        viewDoctors.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JPanel headerPnl = new JPanel();
        headerPnl.setLayout(null);
        headerPnl.add(homeHeading).setBounds(210,200,800,50);
        headerPnl.add(addConsultation).setBounds(410,350,200,30);
        headerPnl.add(viewDoctors).setBounds(410,430,200,30);

        addConsultation.addActionListener(this);
        viewDoctors.addActionListener(this);



        setTitle("Westminster Skin Consultation");
        headerPnl.setBackground(bgColor);
        add(headerPnl);

        setBounds(250, 30, 1060, 700);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addConsultation){
            this.setVisible(false);
            AddConsultationGUI addConsultation = new AddConsultationGUI();
            addConsultation.setVisible(true);
        }else if (e.getSource() == viewDoctors){
            JFrame docTableFrame = new JFrame("Westminster Skin Consultation");
            DoctorTable doctorTable = new DoctorTable(WestminsterSkinConsultationManager.docList);
            TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(bgColor), "Details of Doctors");
            title.setTitleJustification(TitledBorder.CENTER);
            title.setTitleFont(new Font("Arial", Font.BOLD, 20));
            doctorTable.setBorder(title);
            docTableFrame.add(doctorTable);

            docTableFrame.setBounds(250,100,1060, 480);
            docTableFrame.setBackground(bgColor);
            docTableFrame.setVisible(true);
        }
    }
}
