import java.awt.*;

public class TradingSystem {

    public static void main(String args[]){
        StockMarket.addStocksToMarket();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }
}