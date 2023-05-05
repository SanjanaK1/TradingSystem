public abstract class DerivativeDecorator extends PortfolioDecorator {


    public DerivativeDecorator(Portfolio portfolio) {
        super(portfolio);
    }

    @Override
    public String makePortfolio() {
        return super.makePortfolio() + "\n" + addDerivative();
    }

    private String addDerivative() {
        return "Derivative Account";
    }
}
