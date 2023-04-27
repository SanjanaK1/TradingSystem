import javax.swing.*;

public class TradeWindow {
    private JButton goButton;
    private JButton goButton1;
    private JLabel accountNameLabel;
    private JLabel buyStocksLabel;
    private JLabel sellStocksLabel;
    private JTable table1;
    private JPanel panel;
    private JButton openButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TradeWindow");
        frame.setContentPane(new TradeWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TradeWindow(){
        initComponents();
    }

    private void initComponents() {
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
