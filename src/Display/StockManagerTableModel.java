import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    public List<String[]> getCells() {
        List<String[]> stockList = new ArrayList<>();
        for (int row = 0; row < getRowCount(); row++) {
            String[] stock = new String[getColumnCount()];
            boolean valid = true;
            for (int col = 0; col < getColumnCount(); col++) {
                if (getValueAt(row, col) == null) continue;
                String value = getValueAt(row, col).toString();
                if (value.length() == 0) {
                    valid = false;
                    break;
                }
                stock[col] = value;
            }
            if(valid) stockList.add(stock);
        }
        return stockList;
    }
}
