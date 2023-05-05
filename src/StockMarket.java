;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StockMarket {

    // mapping between Stock and quantities available
    private static Map<Stock, Integer> stockListOnMarket;

    public static void main(String args[])
    {
        addStocksToMarket(stockListOnMarket);
        printStocks();
    }

    public static boolean purchase(Stock s, int quantityBought) {
        boolean isSuccessfulPurchase = false;
        boolean isStockInMarket = isStockInMarket(s);
        if (isStockInMarket && stockListOnMarket.get(s) >= quantityBought) {
            int currentStockQuantity =  stockListOnMarket.get(s);
            stockListOnMarket.replace(s, currentStockQuantity - quantityBought);
            isSuccessfulPurchase = true;
        }
        return isSuccessfulPurchase;
    }

    public static boolean sell(Stock s, int quantitySold) {
        boolean isSuccessfulSell = false;
        boolean isStockInMarket = isStockInMarket(s);
        if (isStockInMarket) {
            int currentStockQuantity =  stockListOnMarket.get(s);
            stockListOnMarket.replace(s, currentStockQuantity + quantitySold);
        } else {
            stockListOnMarket.put(s, quantitySold);
        }

        return isSuccessfulSell;
    }

    public static int getQuantityAvailable(Stock s) {
        if (isStockInMarket(s)) {
            return stockListOnMarket.get(s);
        } else {
            return -1;
        }
    }

    private static Map<Stock, Integer> getStocks() {
        return stockListOnMarket;
    }

    public static boolean isStockInMarket(Stock s) {
        boolean isStockInMarket = false;
        if (stockListOnMarket != null) {
            isStockInMarket = (stockListOnMarket.get(s) != null);
        }
        return isStockInMarket;
    }

    public static Map<Stock, Integer> addStocksToMarket( Map<Stock, Integer> stockListOnMarket)
    {
        stockListOnMarket = new HashMap<Stock, Integer>();
        //read from stocks.txt
        String fileName = "C:\\Users\\17322\\HighSchool\\TradingSystem\\src\\stocks.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                String name = tokens[0];
                Double price = Double.parseDouble(tokens[1]);
                Stock stock = new Stock(name,price);
                Integer amountOfStock = Integer.parseInt(tokens[2]);
                stockListOnMarket.put(stock, amountOfStock);
            }
            return stockListOnMarket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printStocks()
    {
        for (Map.Entry<Stock, Integer> entry : stockListOnMarket.entrySet()) {
            Stock key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value);
        }
    }

    public static int findGreatestQuantityAvailableForPurchase(Stock s, double currentMoney) {
        int quantityCurrentlyAvailable = getQuantityAvailable(s);
        int highestQuantityAvailableForPurchase = 0;
        if (quantityCurrentlyAvailable > 0) {
            for (int i = quantityCurrentlyAvailable; i > 0; i--) {
                if (i * s.getCurrentPrice() <= currentMoney) {
                    highestQuantityAvailableForPurchase = i;
                    break;
                }
            }
        }
        return highestQuantityAvailableForPurchase;
    }
}
