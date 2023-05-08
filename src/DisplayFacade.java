import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        if (c == null) return false;
        return MarketSystemFacade.depositMoney(c, d);
    }

    public static boolean withdraw(Double d){
        if (c == null) return false;
        return MarketSystemFacade.withdrawMoney(c, d);
    }

    public static Customer[] getAllCustomers(){
        //TODO from file
        return new Customer[]{};
    }

    public static List<String[]> getStockFile(){
        //TODO move into fileHandler class
        String fileName = "src/TXT Files/stocks.txt";
        List<String[]> stockList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                stockList.add(tokens);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stockList;
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

    public static String[] getStockFileHeader() {
        return new String[]{"Stock Name", "Stock Price", "Quantity"};
    }
}