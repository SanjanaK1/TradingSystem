import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StockManagerTableModel extends DefaultTableModel {
    public StockManagerTableModel(String[][] cells, String[] headers) {
        super(cells, headers);
    }
    public StockManagerTableModel(List<String[]> cells, String[] headers) {
        this(cells.stream().toArray(String[][]::new), headers);
    }

    public void newStock() {
        addRow(new String[]{"Name", "0", "0"});
    }
}
