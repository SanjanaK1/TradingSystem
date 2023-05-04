import javax.swing.*;
import java.awt.*;

public class BankWindow {
    private JSpinner amountSpinner;
    private JButton depositButton;
    private JButton withdrawButton;
    JPanel panel;
    private JLabel amountLabel;
    private JButton cancelButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("BankWindow");
        frame.setContentPane(new BankWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public BankWindow(){
        initComponents();
        panel.setPreferredSize(new Dimension(500,400));
    }

    private void initComponents() {
        cancelButton.addActionListener(e -> {
            GUI.hideBankWindow();
        });
    }
}
