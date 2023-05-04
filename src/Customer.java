public class Customer extends Person {

    private boolean approved = false ; //0 = not approved
    private double money;
    private Portfolio portfolio = new BasePortfolio(null);

    public Customer(String name, boolean isApproved, double money, Portfolio p) {
        super(name);
        setApproved(isApproved);
        setMoney(money);
        setPortfolio(p);
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
            sold = true;
        }
        //calculate realized gains here

        return sold;
    }

    public boolean isStockInPortfolio(Stock s) {
        return false; // todo
    }

    public boolean hasMoneyMoreThan(double amount) {
        return this.getMoney() >= amount;
    }
}
