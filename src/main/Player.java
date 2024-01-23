package src.main;

import main.FarmWorker;

import java.util.ArrayList;
import java.util.List;



/**
 * Represents a player and their associated state.
 */
public class Player {

    private String name;
    private String season;
    private double money;
    private List<Animal> animals;
    private List<MarketItems> owned;
    private Inventory<String> inventory;
    private FarmWorker worker;

    // TODO lengthy constructor, split out into some other way of doing it
    public Player(String name, String season, double money,
                  List<Animal> animals, List<MarketItems> owned) {

        this.name = name;
        this.season = season;
        this.money = money;
        this.animals = animals;
        this.owned = owned;
        this.inventory = new Inventory<>();
        this.worker = null;
    }

    public Player() {

        this("", "", 0, new ArrayList<>(), new ArrayList<>());

    }

    public void setStartingSeed(String seed) {

        inventory.addItem(seed);

    }

    /**
     * Harvests a crop
     * @param crop Crop to harvest
     */
    public void harvest(Crop crop) {
        if (crop.getPesticide()) {
            inventory.addItem(crop.getType() + ": Pesticide Protected");
        } else {
            inventory.addItem(crop.getType());
        }

    }

    /**
     * Buys from a marketPlace
     * @param marketPlace Marketplace to buy from
     */
    public void buy(MarketPlace marketPlace) {



    }

    public String getName() {

        return name;

    }

    public String getSeason() {

        return season;

    }

    public double getMoney() {

        return money;

    }

    public Inventory<String> getInventory() {

        return inventory;

    }

    public FarmWorker getWorker() {
        return worker;
    }

    public void setName(String name) {

        this.name = name;

    }

    public void setSeason(String season) {

        this.season = season;

    }

    public void setMoney(double money) {

        this.money = money;

    }

    public void setWorker(FarmWorker worker) {
        this.worker = worker;
    }

}
