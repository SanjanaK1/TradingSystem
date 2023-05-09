import java.util.Map;
import java.util.Observer;

public interface Portfolio extends Observer {

    String makePortfolio();

    void addStock(Stock s, int quantityBought);

    void removeStock(Stock s, int quantitySold);

    int getStockQuantity(Stock s);

    double getStockListValue();

    int getStockListCount();

    double getUnrealizedGains();

    Stock[] getStockArray();

    int[] getStockAmountsOwned();

    String[] getStockNames();

    String displayStockList();

    void setStockList(Map<Stock, Integer> stockList);
}