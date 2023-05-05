import javax.swing.*;
import java.awt.*;

public class GUI {
    private static JFrame buyWindow, tradeWindow, sellWindow, bankWindow;

    public static void tradeWindow() {
        JFrame frame = new JFrame("TradeWindow");
        frame.setContentPane(new TradeWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void showBuyWindow() {
        if (buyWindow == null) {
            buyWindow = new JFrame("BuyWindow");
            buyWindow.setContentPane(new BuyWindow().panel);
            buyWindow.pack();
            buyWindow.setLocationRelativeTo(null);
        }

        buyWindow.setVisible(true);
    }

    public static void hideBuyWindow() {
        if (buyWindow == null) return;
        buyWindow.setVisible(false);
    }

    public static void showSellWindow() {
        if (sellWindow == null) {
            sellWindow = new JFrame("SellWindow");
            sellWindow.setContentPane(new SellWindow().panel);
            sellWindow.pack();
            sellWindow.setLocationRelativeTo(null);
        }

        sellWindow.setVisible(true);
    }

    public static void hideSellWindow() {
        if (sellWindow == null) return;
        sellWindow.setVisible(false);
    }

    public static void showBankWindow() {
        if (bankWindow == null) {
            bankWindow = new JFrame("BankWindow");
            bankWindow.setContentPane(new BankWindow().panel);
            bankWindow.pack();
            bankWindow.setLocationRelativeTo(null);
        }

        bankWindow.setVisible(true);
    }

    public static void hideBankWindow() {
        if (bankWindow == null) return;
        bankWindow.setVisible(false);
    }

    public static void invalidPopup() {
        InvalidPopup dialog = new InvalidPopup("Test invalid");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
