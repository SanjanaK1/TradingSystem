
import java.util.Observable;

public class Stock extends Observable implements Tradeable {

    private double boughtPrice;
    private double currentPrice;
    private Date datePurchased; // todo implement usage
    private Date dateSold; // todo implement usage
    private String name;

    public Stock(String name, double currentPrice, double boughtPrice, Date datePurchased, Date dateSold){
        setName(name);
        setCurrentPrice(currentPrice);
        setBoughtPrice(boughtPrice);
        setDatePurchased(datePurchased);
        setDateSold(dateSold);

    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        if (this.getCurrentPrice() != currentPrice) {
            notifyObservers();
        }
        this.currentPrice = currentPrice;
    }

    public double getNetChange() {
        System.out.println("current Price: " + currentPrice + "\t Bought: "  + boughtPrice);
        return currentPrice - boughtPrice;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Date getDateSold() {
        return dateSold;
    }

    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
    }

    public String toString()
    {
        return name;
    }
}