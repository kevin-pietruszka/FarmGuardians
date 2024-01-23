package src.main;

public class Crop {

    private String type;
    private boolean pesticide;
    private int fertilizeLevel;

    public Crop(String type) {

        this.type = type;
        this.pesticide = false;
        this.fertilizeLevel = 0;
    }

    public String getType() {

        return type;

    }

    public void sprayPesticide() {
        this.pesticide = true;
    }

    public void fertilize() {
        fertilizeLevel++;
    }

    public boolean useFertilizer() {
        if (fertilizeLevel > 0) {
            fertilizeLevel -= 1;
            return true;
        }
        return false;
    }

    public boolean getPesticide() {
        return this.pesticide;
    }

    public int getFertilizeLevel() {
        return fertilizeLevel;
    }
}
