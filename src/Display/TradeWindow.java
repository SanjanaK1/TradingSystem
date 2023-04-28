import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradeWindow {
    private JButton buyButton;
    private JButton sellButton;
    private JLabel accountNameLabel;
    private JLabel buyStocksLabel;
    private JLabel sellStocksLabel;
    private JTable table1;
    JPanel panel;
    private JButton detailsButton;
    private JButton bankButton;
    private JButton refreshTableButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TradeWindow");
        frame.setContentPane(new TradeWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TradeWindow(){
        initComponents();

        panel.setPreferredSize(new Dimension(600,400));
    }

    private void initComponents() {
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buy();
            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sell();
            }
        });
        bankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bank();
            }
        });
    }

    private void bank() {
        GUI.showBankWindow();
    }

    private void sell() {
        GUI.showSellWindow();
    }

    private void buy() {
        GUI.showBuyWindow();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
