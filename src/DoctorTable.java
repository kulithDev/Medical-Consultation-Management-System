import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DoctorTable extends JPanel {

    static JTable docTable;

    static TitledBorder instruction;
    Font bodyFont = new Font("Arial", Font.PLAIN, 14);
    Color bgColor = new Color(221,184,146);

    public DoctorTable(ArrayList<Doctor> list) {

        //doctors' table
        DoctorTableModel doctorTableModel = new DoctorTableModel(list);
        docTable = new JTable(doctorTableModel);
        docTable.setFont(bodyFont);
        docTable.setAutoCreateRowSorter(true); //sort the table

        JScrollPane docTableScrollPane = new JScrollPane(docTable);
        docTableScrollPane.setPreferredSize(new Dimension(1000, 200));
        docTableScrollPane.setBackground(bgColor);

        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(bgColor);

        instruction = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "*Click column headers to sort");
        instruction.setTitleFont(bodyFont);
        instruction.setTitlePosition(TitledBorder.ABOVE_TOP);
        tablePanel.setBorder(instruction);
        tablePanel.add(docTableScrollPane);

        //add panels to the main panel
        add(tablePanel, BorderLayout.CENTER);
        setBackground(bgColor);

    }

}
