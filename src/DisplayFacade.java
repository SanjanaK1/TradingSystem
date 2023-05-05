public class DisplayFacade {

    private static Customer c;
    public static int numAttributesToDisplay = 6;
    public static String[] tableHeaders = new String[]{"Stock Name", "Current Value ($)", "Number Owned", "Total Value ($)", "Bought Value ($)", "Delta ($)"};

    public static String[] getTableHeader(){
        return tableHeaders;
    }
    public static String[][] getTableData(){
        return new String[][]{{"Stock Example", "2$", "4", "8$"},{"B1", "B2"}};
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
    public static String[] getOwnedStockNames(){return null;}
    public static int[] getOwnedStockAmounts(){return null;}


    public static String[] getAllStockNames() {
        return null;
    }

    public static String[] getAllStockPrices() {
        return null;
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

}