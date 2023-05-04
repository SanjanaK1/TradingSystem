import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class BasePortfolio implements Portfolio, Observer {

    private Map<Stock, Integer> stockList; //mapping between Stocks and their amounts.

    public BasePortfolio(Map<Stock, Integer> stockList) {
        setStockList(stockList);
    }

    @Override
    public String makePortfolio() {
        return this.toString();
    }

    public void setStockList(Map<Stock, Integer> stockList) {
        this.stockList = stockList;
    }

    public void addStock(Stock s, int quantityAdded) {
        if (quantityAdded > 0) {
            if (!isStockInList(s.getName())) {
                s.addObserver(this);
            }
            this.stockList.putIfAbsent(s, quantityAdded);
        }
    }

    public void removeStock(Stock s, int quantityRemoved) {
        if (this.stockList.get(s) != null && quantityRemoved > 0) {
            int currentQuantity = this.stockList.get(s);
            if (currentQuantity > quantityRemoved)
                this.stockList.replace(s, currentQuantity - quantityRemoved);
            else if (currentQuantity == quantityRemoved){
                s.deleteObserver(this);
                this.stockList.remove(s);

            }
        }
    }
    public Map<Stock, Integer> getStockList() {
        return this.stockList;
    }

    public int getStockQuantity(Stock s) {
        int quantity = -1; // in case stock is not found in portfolio, not owned.
        if (this.stockList.get(s) == null) {
            quantity = this.stockList.get(s);
        }
        return quantity;
    }

    public void setStockQuantity(Stock s, int quantity) {
        if (this.stockList.get(s) == null) {
            this.stockList.replace(s, quantity);
        }
    }

    public double getRealizedAmount(Stock s) {
        //TODO Move change to elsewhere as stock should no longer be in portfolio.
        try {
            return s.getNetChange();
        } catch (Exception e) {
            System.out.println("Stock is null");
            return -1;
        }
    }

    public double getUnrealizedAmount(Stock s) {
        //TODO
        try {
            return s.getNetChange();
        } catch (Exception e) {
            System.out.println("Stock is null");
            return -1;
        }

    }

    public Stock[] getStockArray() {
        return this.stockList.keySet().toArray(new Stock[0]);
    }

    public double getStockListValue() {
        double value = 0;
        Stock[] stockArray = getStockArray();
        for (int i = 0; i < stockArray.length; i++) {
            // value accounts for quantity times stock price net change.
            value += getUnrealizedAmount(stockArray[i]) * this.stockList.get(stockArray[i]);
        }
        return value;
    }


    @Override
    public void update(Observable o, Object arg) {
        Stock changedStock = (Stock) o;
        boolean isInList = isStockInList(changedStock.getName());
        if (isInList) {
            updateStockPrice(changedStock);
        }
    }

    private void updateStockPrice(Stock changedStock) {
        Stock s = getStock(changedStock.getName()); // gets Stock in BasePortfolio (may be null).
        if (s != null) {
            s.setCurrentPrice(changedStock.getCurrentPrice());
            this.stockList.put(s, this.stockList.get(s));
        }
    }

    public Stock getStock(String stockName) {
        Stock s = getStockInList(stockName);
        return s;
    }

    private Stock getStockInList(String stockName) {
        Stock retrieved = null;
        Stock[] stockArray = getStockArray();
        for (int i = 0; i < stockArray.length; i++) {
            if (stockArray[i].getName().equals(stockName)) {
                retrieved = stockArray[i];
                break;
            }
        }
        return retrieved;
    }

    private boolean isStockInList(String stockName) {
        boolean isInList = false;
        Stock[] stockArray = getStockArray();
        for (int i = 0; i < stockArray.length; i++) {
            if (stockArray[i].getName().equals(stockName)) {
                isInList = true;
                break;
            }
        }
        return isInList;
    }
}
