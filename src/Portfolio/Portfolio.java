public interface Portfolio {

    String makePortfolio();

    void addStock(Stock s, int quantityBought);

    void removeStock(Stock s, int quantitySold);

    int getStockQuantity(Stock s);

    double getStockListValue();
}