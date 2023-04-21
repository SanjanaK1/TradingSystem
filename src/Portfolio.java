package src.src;

import java.util.Map;

public class Portfolio {

    private Map<Stock, Integer> stockList; //mapping between Stocks and their amounts.

    public Portfolio(Map<Stock, Integer> stockList) {
        setStockList(stockList);
    }

    public void setStockList(Map<Stock, Integer> stockList) {
        this.stockList = stockList;
    }

    public void addStock(Stock s, int quantityAdded) {
        if (quantityAdded > 0) {
            this.stockList.putIfAbsent(s, quantityAdded);
        }
    }

    public void removeStock(Stock s, int quantityRemoved) {
        if (this.stockList.get(s) != null && quantityRemoved > 0) {
            int currentQuantity = this.stockList.get(s);
            if (currentQuantity >= quantityRemoved)
                this.stockList.replace(s, currentQuantity - quantityRemoved);
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
        //TODO
        return 0;
    }

    public double getUnrealizedAmount(Stock s) {
        //TODO
        return 0;
    }

    public double getStockListValue() {
        double value = 0;
        Stock[] stockArray = this.stockList.keySet().toArray(new Stock[0]);
        for (int i = 0; i < stockArray.length; i++) {
            value += getUnrealizedAmount(stockArray[i]); //TODO: check formula for calculating value of stocks.
        }
        return value;
    }



}
