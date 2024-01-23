package src.main;

public class Currency {

    private String type;
    private double amount;

    public Currency(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void addAmount(double value) {
        this.amount += value;
    }

    public void subtractAmount(double value) throws IllegalArgumentException {
        double newAmount = amount - value;
        if (newAmount < 0) {
            throw new IllegalArgumentException("Tried to remove more than what you have");
        } else {
            amount = newAmount;
        }
    }
}
