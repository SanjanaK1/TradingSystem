import javax.swing.table.DefaultTableModel;

public class DisplayTableModel extends DefaultTableModel {
    public DisplayTableModel (String[][] cells, String[] headers) {
        super(cells, headers);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
