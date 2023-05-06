import javax.swing.table.DefaultTableModel;

public class StockManagerTableModel extends DefaultTableModel {
    public StockManagerTableModel(String[][] cells, String[] headers) {
        super(cells, headers);
    }

    public void newStock() {
        addRow(new String[]{"Name", "0", "0"});
    }
}
