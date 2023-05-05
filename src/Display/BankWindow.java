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
        depositButton.addActionListener(e -> {
            deposit();
        });
        withdrawButton.addActionListener(e -> {
            withdraw();
        });
    }

    private void withdraw() {
        GUI.hideBankWindow();

        if (DisplayFacade.withdraw((Double)amountSpinner.getValue())){
            //success
        } else {
            GUI.invalidPopup("Cannot Withdraw amount");
        }
    }

    private void deposit() {
        GUI.hideBankWindow();

        if (DisplayFacade.deposit((Double)amountSpinner.getValue())){
            //success
        } else {
            GUI.invalidPopup("Cannot Deposit amount");
        }
    }

    private void update() {
        amountLabel.setText(DisplayFacade.getLiquid());
    }
}
