import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class BasePortfolio implements Portfolio {

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
            if (isStockInList(s.getName())) {
                Stock inList = getStockInList(s.getName());
                this.stockList.put(inList, this.stockList.get(inList) + quantityAdded);
            } else {
                // add observing when stock is not in stockList
                s.addObserver(this);
                this.stockList.put(s, quantityAdded);
            }
        }
    }

    public void removeStock(Stock s, int quantityRemoved) {
        if (this.stockList.get(getStockByName(s.getName())) != null && quantityRemoved > 0) {
            int currentQuantity = this.stockList.get(getStockByName(s.getName()));
            if (currentQuantity > quantityRemoved)
                this.stockList.replace(getStockByName(s.getName()), currentQuantity - quantityRemoved);
            else if (this.stockList.get(getStockByName(s.getName())) != null && currentQuantity == quantityRemoved){
                s.deleteObserver(this);
                this.stockList.remove(getStockByName(s.getName()));

            }
        }
    }
    public Map<Stock, Integer> getStockList() {
        return this.stockList;
    }

    public int getStockQuantity(Stock s) {
        int quantity = -1; // in case stock is not found in portfolio, not owned.
        if (this.stockList.get(getStockByName(s.getName())) != null) {
            quantity = this.stockList.get(getStockByName(s.getName()));
        }
        return quantity;
    }

    public void setStockQuantity(Stock s, int quantity) {
        if (this.stockList.get(getStockByName(s.getName())) == null) {
            this.stockList.replace(getStockByName(s.getName()), quantity);
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
            // value accounts for quantity times current stock price.
                value += stockArray[i].getCurrentPrice() * this.stockList.get(stockArray[i]);
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
        Stock s = getStockByName(changedStock.getName()); // gets Stock in BasePortfolio (may be null).
        if (s != null) {
            s.setCurrentPrice(changedStock.getCurrentPrice());
            this.stockList.put(getStockByName(s.getName()), this.stockList.get(getStockByName(s.getName())));
        }
    }

    public Stock getStockByName(String stockName) {
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

    @Override
    public double getUnrealizedGains() {
        double unrealizedGains = 0;
        Stock[] stockArray = getStockArray();
        for (int i = 0; i < stockArray.length; i++) {
            unrealizedGains += stockArray[i].getNetChange() * getStockQuantity(stockArray[i]);
        }
        return unrealizedGains;
    }

    public int[] getStockAmountsOwned() {
        Stock[] stockArray = getStockArray();
        int[] stockAmountsOwned = new int[stockArray.length];
        for (int i = 0; i < stockAmountsOwned.length; i++) {
            stockAmountsOwned[i] = stockList.get(stockArray[i]);
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
            if (i != 0) {
                display += ";";
            }
            String name = stockArray[i].getName();
            int quantity = stockList.get(stockArray[i]);
            String datePurchased = stockArray[i].getDatePurchased().toString();
            double boughtPrice = stockArray[i].getBoughtPrice();
            display += "(" + name + "," +  quantity + "," + datePurchased + "," + boughtPrice + ")";
        }
        display += "]";
        return display;
    }
}