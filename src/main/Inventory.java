package src.main;

import java.util.ArrayList;
import java.util.List;

public class Inventory<E> {

    private List<E> inventory;
    private int maxItems;

    public Inventory(int maxItems) {

        this.inventory = new ArrayList<>();
        this.maxItems = maxItems;

    }

    public Inventory() {

        this(20);

    }

    /**
     * Adds item to inventory
     * @param item Item to add
     * @return True if the item was successfully added, false otherwise
     */
    public boolean addItem(E item) {

        if (inventory.size() == maxItems) {

            return false;

        } else {

            inventory.add(item);
            return true;

        }

    }

    public List<E> getItems() {

        return inventory;

    }

    public void removeItem(E item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(item)) {
                inventory.remove(i);
                break;
            }
        }
    }

    public boolean findPesticide() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals("Pesticide")) {
                return true;
            }
        }
        return false;
    }

    public boolean findTractor() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals("Tractor")) {
                return true;
            }
        }
        return false;
    }

    public boolean findIrrigation() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals("Irrigation")) {
                return true;
            }
        }
        return false;
    }

}
