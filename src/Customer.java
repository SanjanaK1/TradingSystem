public class Customer extends Person {

    private boolean approved = false ; //0 = not approved
    private double money;
    private BasePortfolio basePortfolio = new BasePortfolio(null);

    public Customer(String name, boolean isApproved, double money, BasePortfolio p) {
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

    public BasePortfolio getPortfolio() {
        return basePortfolio;
    }

    public void setPortfolio(BasePortfolio basePortfolio) {
        this.basePortfolio = basePortfolio;
    }

    public double getNetValue() {
        return this.getMoney() + this.getPortfolio().getStockListValue();
    }

    public boolean buyStock(Stock s, int quantityBought) {
        boolean bought = false;

        if (s != null && StockMarket.isStockInMarket(s) && this.money >= quantityBought * s.getCurrentPrice()) {
            double stockCost = quantityBought * s.getCurrentPrice();
            boolean isSuccessfulPurchase = StockMarket.purchase(s, quantityBought);
            if (isSuccessfulPurchase) {
                basePortfolio.addStock(s, quantityBought);
                setMoney(this.money - stockCost);
                bought = true;
            }
        }

        return bought;
    }

    public boolean sellStock(Stock s, int quantitySold) {
        boolean sold = false;
        if (s != null && basePortfolio.getStockQuantity(s) > 0) {
            basePortfolio.removeStock(s, quantitySold);
            double netChange = s.getNetChange() * quantitySold;
            setMoney(netChange);
            sold = true;
        }
        //calculate realized gains here

        return sold;
    }
}
