import java.util.List;

public class DisplayAdaptor {
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
        if (DisplayAdaptor.c != null) return c.getName();
        return "ACCOUNT NOT FOUND";
    }

    public static String getTotal() {
        if (DisplayAdaptor.c != null) return Double.toString(c.getNetValue());
        return "0$";
    }

    public static String getLiquid() {
        if (DisplayAdaptor.c != null) return Double.toString(c.getMoney());
        return "0$";
    }

    //Doesn't have to be STRING. Could be int of a stock id, or however stocks are internally represented.
    public static String[] getOwnedStockNames(){
        if (c == null) return new String[]{};
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
        DisplayAdaptor.c = c;
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
        return c.getPortfolio().getStockArray();
    }

    public static Stock[] getAllStocks() {
        return StockMarket.getAllStockArray();
    }

    public static boolean deposit(Double d){
        if (c == null) return false;
        return MarketSystemFacade.depositMoney(c, d);
    }

    public static boolean withdraw(Double d){
        if (c == null) return false;
        return MarketSystemFacade.withdrawMoney(c, d);
    }

    public static Customer[] getAllCustomers(){
        return FileHandler.loadCustomers().toArray(new Customer[0]);
    }

    public static List<String[]> getStockFile(){
        return FileHandler.getStockFile();
    }
    public static boolean setStockFile(List<String[]> values){
        return FileHandler.setStockFile(values);
    }

    public static String getDetailedInfo() {
        //TODO
        if (DisplayAdaptor.c == null) return "ACCOUNT NOT FOUND";
        return "Account Value: " + getTotal()
                + "\nLiquid Assets: " + getLiquid()
                + "\nUnrealized gains: " + 0
                + "\nTotal change: " + 0
                + "\nDerivative Available?: " + "Y";//TODO
    }

    public static String[] getStockFileHeader() {
        return new String[]{"Stock Name", "Stock Price", "Quantity"};
    }

    public static boolean hasCustomer(){return c != null;}
}