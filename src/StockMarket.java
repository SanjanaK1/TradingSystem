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
    private static Map<Stock, Integer> stockListOnMarket = new HashMap<>();

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

    public static Stock[] getAllStockArray() {
        return stockListOnMarket.keySet().toArray(new Stock[0]);
    }

    public static void printStocks() {
        for (Map.Entry<Stock, Integer> entry : stockListOnMarket.entrySet()) {
            Stock key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value);
        }
    }

    public static String[] getAllStockPricesArray() {
        Stock[] stockArray = getAllStockArray();
        String[] stockPrices = new String[stockArray.length];
        for (int i = 0; i < stockArray.length; i++) {
            stockPrices[i] = String.valueOf(stockArray[i].getCurrentPrice());
        }
        return stockPrices;
    }

    public static String[] getAllStockNames() {
        Stock[] stockArray = getAllStockArray();
        String[] stockNames = new String[stockArray.length];
        for (int i = 0; i < stockArray.length; i++) {
            stockNames[i] = stockArray[i].getName();
        }
        return stockNames;
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

    public static void addStock(Stock stock, int amountOfStock) {
        assert stock != null && amountOfStock >= 0;
        stockListOnMarket.put(stock, amountOfStock);
    }

    public static Stock getStockByName(String name) {
        Stock toReturn = null;
        Stock[] stockArray = getAllStockArray();
        for (int i = 0; i < stockArray.length; i++) {
            if (stockArray[i].getName().equals(name)) {
                toReturn = stockArray[i];
            }
        }
        return toReturn;
    }
}