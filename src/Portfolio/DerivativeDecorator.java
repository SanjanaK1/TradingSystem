public class DerivativeDecorator extends PortfolioDecorator{


    public DerivativeDecorator(Portfolio portfolio) {
        super(portfolio);
    }

    @Override
    public String makePortfolio() {
        return super.makePortfolio() + "\n" + addDerivative();
    }

    @Override
    public void addStock(Stock s, int quantityBought) {

    }

    @Override
    public void removeStock(Stock s, int quantitySold) {

    }

    @Override
    public int getStockQuantity(Stock s) {
        return 0;
    }

    @Override
    public double getStockListValue() {
        return 0;
    }

    @Override
    public int getStockListCount() {
        return 0;
    }

    @Override
    public Stock[] getStockArray() {
        return new Stock[0];
    }

    private String addDerivative() {
        return "Derivative Account";
    }
}
