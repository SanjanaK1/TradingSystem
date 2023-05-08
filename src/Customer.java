import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

    private boolean approved = false ; //0 = not approved
    private double money;
    private Portfolio portfolio = new BasePortfolio(null);
    private boolean isEligibleForDerivative = false;

    public Customer(String name, boolean isApproved, double money, Portfolio p, boolean isEligibleForDerivative) {
        super(name);
        setApproved(isApproved);
        setMoney(money);
        setPortfolio(p);
        setEligibleForDerivative(isEligibleForDerivative);
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public double getNetValue() {
        return this.getMoney() + this.getPortfolio().getStockListValue();
    }

    public boolean buyStock(Stock s, int quantityBought) {
        boolean bought = false;
        if (s != null && StockMarket.isStockInMarket(s) && this.money >= quantityBought * s.getCurrentPrice()) {
            double stockCost = quantityBought * s.getCurrentPrice();
            s.setBoughtPrice(s.getCurrentPrice());
            LocalDate currentDate = java.time.LocalDate.now();
            Date d = new Date(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
            s.setDatePurchased(d);
            portfolio.addStock(s, quantityBought);
            setMoney(this.money - stockCost);
            bought = true;
        }
        return bought;
    }

    public boolean sellStock(Stock s, int quantitySold) {
        boolean sold = false;
        if (s != null && portfolio.getStockQuantity(s) > 0) {
            portfolio.removeStock(s, quantitySold);
            double netChange = s.getNetChange() * quantitySold;
            setMoney(netChange);
            LocalDate currentDate = java.time.LocalDate.now();
            Date d = new Date(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth());
            s.setDateSold(d);
            sold = true;
        }
        //calculate realized gains here

        return sold;
    }

    public boolean isStockInPortfolio(Stock s)
    {
        Stock[] stocks = portfolio.getStockArray();
        for(Stock stock: stocks)
        {
            if (stock == s){
                return true;
            }
        }
        return false;
    }

    public boolean hasMoneyMoreThan(double amount) {
        return this.getMoney() >= amount;
    }

    public String[][] getStocksListInformation() {
        int numOfStocks = this.getPortfolio().getStockListCount();
        Stock[] stockArray = this.getPortfolio().getStockArray();
        String[][] stockListInformation =
                new String[numOfStocks][DisplayFacade.numAttributesToDisplay];
        for (int i = 0; i < numOfStocks; i++) {
            stockListInformation[i][0] = stockArray[i].getName(); // Name of Stock
            stockListInformation[i][1] = String.valueOf(stockArray[i].getCurrentPrice()); // Current Price at Market);
            stockListInformation[i][2] = String.valueOf(getPortfolio().getStockQuantity(stockArray[i])); // Quantity Owned
            stockListInformation[i][4] = String.valueOf(stockArray[i].getBoughtPrice()); // Price at which stock was bought
            stockListInformation[i][5] = String.valueOf(Double.parseDouble(stockListInformation[i][1]) - Double.parseDouble(stockListInformation[i][4])); // Delta (Current Price - Bought Price)
            stockListInformation[i][3] = String.valueOf(Integer.parseInt(stockListInformation[i][2]) * stockArray[i].getCurrentPrice()); // Total Price (Delta * Quantity)

        }
        return stockListInformation;
    }

    public boolean isEligibleForDerivative() {
        return PortfolioManager.getPortfolioManagerInstance().approveCustomer(this);
    }

    public void setEligibleForDerivative(boolean eligibleForDerivative) {
        isEligibleForDerivative = eligibleForDerivative;
    }

    @Override
    public String toString() {
        return "Customer: " + getName();
    }
}