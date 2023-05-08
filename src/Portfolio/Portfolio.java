public interface Portfolio {

    String makePortfolio();

    void addStock(Stock s, int quantityBought);

    void removeStock(Stock s, int quantitySold);

    int getStockQuantity(Stock s);

    double getStockListValue();

    int getStockListCount();

    Stock[] getStockArray();

    int[] getStockAmountsOwned();

    String[] getStockNames();

    String displayStockList();
}