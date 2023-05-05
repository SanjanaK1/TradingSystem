public class DisplayFacade {

    private static Customer c;
    public static int numAttributesToDisplay = 6;
    public static String[] tableHeaders = new String[]{"Stock Name", "Current Value ($)", "Number Owned", "Total Value ($)", "Bought Value ($)", "Delta ($)"};

    public static String[] getTableHeader(){
        return tableHeaders;
    }
    public static String[][] getTableData(){
        return new String[][]{{"Stock Example", "2$", "4", "8$"},{"B1", "B2"}}; //TODO
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
        return new String[]{"Example 1", "Example 2"}; //TODO
    }
    public static int[] getOwnedStockAmounts(){
        return new int[]{1, 2}; //TODO
    }


    public static String[] getAllStockNames() {
        return new String[]{"Example 1", "Example 2"}; //TODO
    }

    public static String[] getAllStockPrices() {
        return new String[]{"$Example 1", "$Example 2"}; //TODO
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
        return new Stock[] {new Stock("Example 1", 1), new Stock("Example 2", 2)}; //TODO
    }


    public static Stock[] getAllStocks() {
        return new Stock[] {new Stock("Example 1", 1), new Stock("Example 2", 2)}; //TODO
    }
}