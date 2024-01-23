package src.main;

public class MarketItems {

    protected String type;
    protected double price;

    public MarketItems(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public void setPrice(double price) {

        this.price = price;

    }

    public double getPrice() {

        return price;

    }

    public String getType() {

        return type;

    }

    public String toString() {
        return type + ": " + Math.round(price * 100.0) / 100.0;
    }
}
