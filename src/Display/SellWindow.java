import javax.swing.*;
import java.awt.*;

public class SellWindow {
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JButton sellButton;
    private JButton cancelButton;
    JPanel panel;
    private JTable stockTable;
    private JButton refreshTableButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SellWindow");
        frame.setContentPane(new SellWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public SellWindow(){
        initComponents();
        panel.setPreferredSize(new Dimension(500,400));
    }

    private void initComponents() {
        cancelButton.addActionListener(e -> {
            GUI.hideSellWindow();
        });
        sellButton.addActionListener(e -> {
            sell();
        });

        stockTable.setModel(new DisplayTableModel(DisplayFacade.getTableData(), DisplayFacade.getTableHeader()));

        comboBox1.setModel(new DefaultComboBoxModel(DisplayFacade.getOwnedStockNames()));
    }

    private void sell() {
        GUI.hideSellWindow();

        if(DisplayFacade.sell(comboBox1.getSelectedIndex(), (int)spinner1.getValue())){
            //nothing
        } else {
            GUI.invalidPopup("Sell Failed");
        }
    }
}
