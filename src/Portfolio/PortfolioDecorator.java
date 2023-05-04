public abstract class PortfolioDecorator implements Portfolio {

    private Portfolio portfolio;

    public PortfolioDecorator(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String makePortfolio() {
        return portfolio.makePortfolio();
    }


}
