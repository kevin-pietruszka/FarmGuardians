package src.main;

import java.util.ArrayList;
import java.util.Collection;


public class MarketPlace {

    private ArrayList<MarketItems> items;

    public MarketPlace() {
        this.items = new ArrayList<>();
    }

    public MarketPlace(Collection<MarketItems> items) {
        this.items = new ArrayList<>(items);
    }

    public void addItem(MarketItems item) {
        this.items.add(item);
    }

    public MarketItems removeItem(int index) {
        return this.items.remove(index);

    }

    public MarketItems getItem(int index) {
        return this.items.get(index);
    }

    public double findItemPrice(String item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType().equals(item)) {
                return items.get(i).getPrice();
            }
        }
        return 0;
    }

    public ArrayList<MarketItems> getItems() {
        return items;
    }

}
