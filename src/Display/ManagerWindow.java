import javax.swing.*;

public class ManagerWindow {
    private JLabel accountNameLabel;
    private JLabel sellStocksLabel;
    private JButton otherButton;
    private JButton openButton;
    private JTable stockTable;
    private JComboBox portfolioComboBox;
    private JButton loadButton;
    private JButton saveButton;
    public JPanel panel;
    private JButton addRowButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManagerWindow");
        frame.setContentPane(new ManagerWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ManagerWindow(){
        initComponents();
    }

    private void initComponents() {
        loadButton.addActionListener(a->{
            update();
        });
        saveButton.addActionListener(a->{
            save();
        });
        addRowButton.addActionListener(a->{
            ((StockManagerTableModel)stockTable.getModel()).newStock();
        });
        openButton.addActionListener(a->{
            openCustomer();
        });
        otherButton.addActionListener(a->{
            openOtherOptions();
        });

        portfolioComboBox.setModel(new DefaultComboBoxModel(DisplayFacade.getAllCustomers()));

        update();
    }

    private void openOtherOptions() {
        //more functionality here
        JOptionPane.showMessageDialog(panel.getParent(), "Additional features would be listed here.");
    }

    private void openCustomer() {
        Customer c = (Customer) portfolioComboBox.getSelectedItem();
        //TODO open customer window
    }

    private void save() {
        //TODO
    }

    private void update() {
        stockTable.setModel(new StockManagerTableModel(DisplayFacade.getStockFile(), DisplayFacade.getStockFileHeader()));
    }
}
