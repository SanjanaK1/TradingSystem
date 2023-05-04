;
import java.util.Map;

public class StockMarket {

    // mapping between Stock and quantities available
    private static Map<Stock, Integer> stockListOnMarket;

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

    private Map<Stock, Integer> getStocks() {
        return stockListOnMarket;
    }

    public static boolean isStockInMarket(Stock s) {
        boolean isStockInMarket = false;
        if (stockListOnMarket != null) {
            isStockInMarket = (stockListOnMarket.get(s) != null);
        }
        return isStockInMarket;
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
