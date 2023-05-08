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
    private JTable stockTable;
    JPanel panel;
    private JButton detailsButton;
    private JButton bankButton;
    private JButton refreshTableButton;
    private JLabel totalAssetLabel;
    private JLabel liquidAssetLabel;
    private JLabel moneyLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TradeWindow");
        frame.setContentPane(new TradeWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TradeWindow(){
        initComponents();

        panel.setPreferredSize(new Dimension(800,600));
    }

    private void initComponents() {
        //populate labels
        accountNameLabel.setText(DisplayAdaptor.getUserName());
        totalAssetLabel.setText(DisplayAdaptor.getTotal());
        liquidAssetLabel.setText(DisplayAdaptor.getLiquid());
        stockTable.setModel(new DisplayTableModel(DisplayAdaptor.getTableData(), DisplayAdaptor.getTableHeader()));

        moneyLabel.setIcon(IconCreator.createImageIcon("rsc/money2.gif", "money"));


        //Add action listeners to buttons
        detailsButton.addActionListener(e -> {
            info();
        });
        buyButton.addActionListener(e -> {
            buy();
        });
        sellButton.addActionListener(e -> {
            sell();
        });
        bankButton.addActionListener(e -> {
            bank();
        });
        refreshTableButton.addActionListener(e -> {
            update();
        });

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        timer.start();
    }

    private void info() {
        InfoWindow info = new InfoWindow();
        info.pack();
        info.setLocationRelativeTo(null);
        info.setVisible(true);
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

    public void update() {
        accountNameLabel.setText(DisplayAdaptor.getUserName());
        totalAssetLabel.setText(DisplayAdaptor.getTotal());
        liquidAssetLabel.setText(DisplayAdaptor.getLiquid());
        stockTable.setModel(new DisplayTableModel(DisplayAdaptor.getTableData(), DisplayAdaptor.getTableHeader()));
        DisplayAdaptor.notifyDerivative();
    }

}
