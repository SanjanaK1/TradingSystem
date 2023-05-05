import javax.swing.table.DefaultTableModel;

public class DisplayTableModel extends DefaultTableModel {
    public DisplayTableModel (String[][] cells, String[] headers) {
        super(cells, headers);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void test(){
        addRow(new String[] {"Test Row", "1", "2", "3"});
    }

    public void truncate(int i) {
    }
}
