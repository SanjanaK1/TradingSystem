package src;

public class Stock {

    double price;
    String name;
    boolean tradable;


    public double getPrice()
    {
        return price;
    }

    public void setPrice(double newPrice){
        price = newPrice;

    }

    public String getName()
    {
        return name;
    }

    public boolean getTradable()
    {
        return tradable;
    }

    public void setTradable(boolean isTradable){
        tradable = isTradable;
    }

}
