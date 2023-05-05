import javax.swing.*;
import java.awt.*;

public class BuyWindow {
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JButton cancelButton;
    private JButton buyButton;
    JPanel panel;
    private JTable stockTable;
    private JButton refreshTableButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("BuyWindow");
        frame.setContentPane(new BuyWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public BuyWindow(){
        initComponents();
        panel.setPreferredSize(new Dimension(500,400));
    }

    private void initComponents() {
        cancelButton.addActionListener(e -> {
            GUI.hideBuyWindow();
        });
        buyButton.addActionListener(e -> {
            buy();
        });

        stockTable.setModel(new DisplayTableModel(DisplayFacade.getTableData(), DisplayFacade.getTableHeader()));

        comboBox1.setModel(new DefaultComboBoxModel(DisplayFacade.getAllStockNames()));
    }

    private void buy() {
        GUI.hideBuyWindow();

        if(DisplayFacade.buy(comboBox1.getSelectedIndex(), (Integer)spinner1.getValue())){
            //nothing
        } else {
            GUI.invalidPopup("Buy Failed");
        }
    }
}
