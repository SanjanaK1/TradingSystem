public class DisplayFacade {

    private static Customer c;
    public static int numAttributesToDisplay = 6;
    public static String[] tableHeaders = new String[]{"Stock Name", "Current Value ($)", "Number Owned", "Total Value ($)", "Bought Value ($)", "Delta ($)"};

    public static String[] getTableHeader(){
        return tableHeaders;
    }

    public static String[][] getTableData(){
        return c.getStocksListInformation();
    }

    public static String getUserName(){
        if (DisplayFacade.c != null) return c.getName();
        return "ACCOUNT NOT FOUND";
    }

    public static String getTotal() {
        if (DisplayFacade.c != null) return Double.toString(c.getNetValue());
        return "0$";
    }

    public static String getLiquid() {
        if (DisplayFacade.c != null) return Double.toString(c.getMoney());
        return "0$";
    }

    //Doesn't have to be STRING. Could be int of a stock id, or however stocks are internally represented.
    public static String[] getOwnedStockNames(){
        return c.getPortfolio().getStockNames();
    }

    public static int[] getOwnedStockAmounts(){
        return c.getPortfolio().getStockAmountsOwned();
    }


    public static String[] getAllStockNames() {
        return StockMarket.getAllStockNames();
    }

    public static String[] getAllStockPrices() {
        return StockMarket.getAllStockPricesArray();
    }

    public static void setCustomer(Customer c) {
        DisplayFacade.c = c;
    }

    public static boolean buy(Stock s, int quantity) {
        if (c == null) return false;
        return MarketSystemFacade.buyStock(c, s, quantity);
    }

    public static boolean sell(Stock s, int quantity) {
        if (c == null) return false;
        return MarketSystemFacade.sellStock(c, s, quantity);
    }

    public static boolean sell(int index, int quantity) {
        if (c == null) return false;
        return MarketSystemFacade.sellStock(c, getOwnedStocks()[index], quantity);
    }

    public static boolean buy(int index, int quantity) {
        if (c == null) return false;
        return MarketSystemFacade.buyStock(c, getAllStocks()[index], quantity);
    }

    public static Stock[] getOwnedStocks() {
        return c.getPortfolio().getStockArray(); //TODO
    }


    public static Stock[] getAllStocks() {
        return StockMarket.getAllStockArray();
    }
}