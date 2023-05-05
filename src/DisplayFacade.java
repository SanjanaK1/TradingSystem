public class DisplayFacade {
    private static Customer c;
    public static int numAttributesToDisplay = 6;
    public static String[] tableHeaders = new String[]{"Stock Name", "Current Value ($)", "Number Owned", "Total Value ($)", "Bought Value ($)", "Delta ($)"};

    public static String[] getTableHeader(){
        return tableHeaders;
    }

    public static String[][] getTableData(){
        if (c == null) return null;
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

    public static boolean deposit(Double d){
        return false;
    }

    public static boolean withdraw(Double d){
        return false;
    }

    public static Customer[] getAllCustomers(){
        //TODO from file
        return new Customer[]{};
    }

    public static String[][] getStockFile(){
        //TODO from file
        return new String[][]{{"AMAZON", "20.23"},{"Example 2", "21"}};
    }
    public static void setStockFile(){
        //TODO from file
    }

    public static String getDetailedInfo() {
        //TODO
        if (DisplayFacade.c == null) return "ACCOUNT NOT FOUND";
        return "Account Value: " + getTotal()
                + "\nLiquid Assets: " + getLiquid()
                + "\nUnrealized gains: " + 0 //TODO Delta Here
                + "\n...";
    }
}