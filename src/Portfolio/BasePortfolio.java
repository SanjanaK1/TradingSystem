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
                this.stockList.put(s, quantityAdded);
            }
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
        if (this.stockList.get(s) != null) {
            quantity = this.stockList.get(s);
        }
        return quantity;
    }

    public void setStockQuantity(Stock s, int quantity) {
        if (this.stockList.get(s) == null) {
            this.stockList.replace(s, quantity);
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
            value += stockArray[i].getNetChange() * this.stockList.get(stockArray[i]);
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

    public int getStockListCount() {
        return this.stockList.size();
    }

    public int[] getStockAmountsOwned() {
        Stock[] stockArray = getStockArray();
        int[] stockAmountsOwned = new int[stockArray.length];
        for (int i = 0; i < stockAmountsOwned.length; i++) {
            stockAmountsOwned[i] = stockArray[i].getQuantity();
        }
        return stockAmountsOwned;
    }

    public String[] getStockNames() {
        Stock[] stockArray = getStockArray();
        String[] stockNames = new String[stockArray.length];
        for (int i = 0; i < stockNames.length; i++) {
            stockNames[i] = stockArray[i].getName();
        }
        return stockNames;
    }

    @Override
    public String displayStockList() {
        String display = "[";
        Stock[] stockArray = getStockArray();
        for (int i = 0; i < stockArray.length; i++) {
            if (i != 0 && i !=  stockArray.length -1) {
                display += ";";
            }
            String name = stockArray[i].getName();
            int quantity = stockList.get(stockArray[i]);
            String datePurchased = stockArray[i].getDatePurchased().toString();
            double boughtPrice = stockArray[i].getBoughtPrice();
            display += "(" + name + "," +  quantity + "," + datePurchased + "," + boughtPrice + ")";
        }
        display += "]";
        return display; //todo
    }
}