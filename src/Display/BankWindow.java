import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class BankWindow {
    private JSpinner amountSpinner;
    private JButton depositButton;
    private JButton withdrawButton;
    JPanel panel;
    private JLabel amountLabel;
    private JButton cancelButton;
    private JTextField amountField;

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
        depositButton.addActionListener(e -> {
            deposit();
        });
        withdrawButton.addActionListener(e -> {
            withdraw();
        });

        update();
    }

    private void withdraw() {
        GUI.hideBankWindow();

        double d = 0;
        try {
            d = Double.parseDouble(amountField.getText());
        }
        catch (Exception e) {
            GUI.invalidPopup("Not a valid amount");
            return;
        }

        if (d<0) GUI.invalidPopup("Not a valid amount");

        if (DisplayAdaptor.withdraw(d)) {
            //success
        } else {
            GUI.invalidPopup("Cannot Withdraw amount");
        }
    }

    private void deposit() {
        GUI.hideBankWindow();
        double d = 0;
        try {
            d = Double.parseDouble(amountField.getText());
        }
        catch (Exception e) {
            GUI.invalidPopup("Not a valid amount");
            return;
        }

        if (d<0) GUI.invalidPopup("Not a valid amount");

        if (DisplayAdaptor.deposit(d)) {
            //success
        } else {
            GUI.invalidPopup("Cannot Deposit amount");
        }
    }

    private void update() {
        amountLabel.setText(DisplayAdaptor.getLiquid());
    }
}
