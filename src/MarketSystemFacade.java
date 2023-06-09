import java.io.File;
import java.util.Map;

public class MarketSystemFacade {

    private PortfolioManager portfolioManager;
    private Map<Customer, Portfolio> customers;


    public static boolean buyStock(Customer c, Stock s, int quantity) {
        boolean isStockBought = false;
        if (quantity >= 0) {
            boolean isInMarket = StockMarket.isStockInMarket(s);
            boolean isQuantityAvailable = StockMarket.getQuantityAvailable(s) >= quantity;
            double totalPrice = s.getCurrentPrice() * quantity;
            boolean hasEnoughMoney = c.hasMoneyMoreThan(totalPrice);

            // case where stock quantity available in market is enough
            if (isInMarket && isQuantityAvailable && hasEnoughMoney) {
                boolean isSuccessfulPurchase = StockMarket.purchase(s, quantity);
                if (isSuccessfulPurchase) {
                    isStockBought = c.buyStock(s, quantity);
                }
            }
            // case where stock quantity is less than desired for purchase
            // in this case, we find the largest quantity the stock market has available
            else if (isInMarket && !isQuantityAvailable && hasEnoughMoney) {
                int greatestQuantityAvailableForPurchase = StockMarket.findGreatestQuantityAvailableForPurchase(s, c.getMoney());
                if (greatestQuantityAvailableForPurchase > 0) {
                    boolean isSuccessfulPurchase = StockMarket.purchase(s, greatestQuantityAvailableForPurchase);
                    if (isSuccessfulPurchase) {
                        // TODO: consider logging this or adding sign of how much was purchased (compared to intended).
                        isStockBought = c.buyStock(s, greatestQuantityAvailableForPurchase);
                    }
                }
            }
            if (isStockBought) {
                System.out.println(c.getPortfolio().displayStockList());
                FileHandler.updateCustomer(c);
                FileHandler.updateStockMarketQuantity(s, StockMarket.getQuantityAvailable(s));
            }
        }
        return isStockBought;
    }

    public static boolean sellStock(Customer c, Stock s, int quantityToSell) {
        boolean isStockSold = false;
        if (quantityToSell >= 0) {
            boolean hasStockInPortfolio = c.isStockInPortfolio(s);
            boolean isValidSell = hasStockInPortfolio && c.getPortfolio().getStockQuantity(s) >= quantityToSell;
            if (isValidSell) {
                boolean isSuccessfulSell = StockMarket.sell(s, quantityToSell);
                if (isSuccessfulSell) {
                    isStockSold = c.sellStock(s, quantityToSell);
                    FileHandler.updateCustomer(c);
                    if (StockMarket.getStockByName(s.getName()) != null) {
                        FileHandler.updateStockMarketQuantity(s, StockMarket.getQuantityAvailable(s));
                    }

                }
            }
        }
        return isStockSold;
    }

    public static String[][] getCustomerInformation(Customer c) {
        if (c != null) {
            return c.getStocksListInformation();
        }
        return null;
    }

    public static boolean depositMoney(Customer c, double money) {
        if (money >= 0) {
            c.setMoney(c.getMoney() + money);
            FileHandler.updateCustomer(c);
            return true;
        }
        return false;
    }

    public static boolean withdrawMoney(Customer c, double money) {
        if (c.getMoney() >= money) {
            c.setMoney(c.getMoney() - money);
            FileHandler.updateCustomer(c);
            return true;
        }
        return false;
    }
}