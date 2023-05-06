import java.awt.*;
import java.io.IOException;

public class TradingSystem {

    public static void main(String args[]) throws IOException {
        PortfolioManager pm = PortfolioManager.getPortfolioManagerInstance();
        FileHandler.loadAllFiles(pm); // loads customers, users, and stockMarket from db (txt files).
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }
}