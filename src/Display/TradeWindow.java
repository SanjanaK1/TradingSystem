import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
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
        //populate labels
        accountNameLabel.setText(DisplayFacade.getUserName());
        totalAssetLabel.setText(DisplayFacade.getTotal());
        liquidAssetLabel.setText(DisplayFacade.getLiquid());
        stockTable.setModel(new DisplayTableModel(DisplayFacade.getTableData(), DisplayFacade.getTableHeader()));



        //Add action listeners to buttons
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

        refreshTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
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

    public void update() {
        //accountNameLabel.setText(DisplayFacade.getUserName());
        totalAssetLabel.setText(DisplayFacade.getTotal());
        liquidAssetLabel.setText(DisplayFacade.getLiquid());
        //stockTable.setModel(new DisplayTableModel(DisplayFacade.getTableData(), DisplayFacade.getTableHeader()));
        ((DisplayTableModel) stockTable.getModel()).test();
    }
}
