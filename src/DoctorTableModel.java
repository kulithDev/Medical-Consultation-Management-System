import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorTableModel extends AbstractTableModel {

    private String[] columnNames = {"Medical licence No.", "Title","First Name", "Surname", "Phone Number",  "Specialisation", "Availability"};
    private ArrayList<Doctor> docList;

    public DoctorTableModel(ArrayList<Doctor> docList) {
        this.docList = docList;
    }

    //show column names in the table
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return docList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object data = null;
        if (columnIndex == 0){
            data = docList.get(rowIndex).getLicenceNum();
        }else if (columnIndex == 1){
            data = docList.get(rowIndex).getTitle();
        }else if (columnIndex == 2){
            data = docList.get(rowIndex).getFirstname();
        }else if (columnIndex == 3){
            data = docList.get(rowIndex).getSurname();
        }else if (columnIndex == 4){
            data = docList.get(rowIndex).getPhoneNum();
        }else if (columnIndex == 5){
            data = docList.get(rowIndex).getSpecialisation();
        }else if (columnIndex == 6){
            data = docList.get(rowIndex).getAvailability();
        }
        return data;
    }
}
