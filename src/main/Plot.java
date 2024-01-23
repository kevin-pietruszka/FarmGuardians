package src.main;

import javafx.scene.paint.Color;

public class Plot {

    // There are four stages of growth:
    // -1: dead
    // 0: seed
    // 1: immature
    // 2: mature
    private int stageOfGrowth;
    private Crop crop; // If null there is no seed currently
    private int lastWatered;
    // should water at least every day
    private static final int WATER_MIN = 1;
    // should not water more than 5 times a day
    private static final int WATER_MAX = 5;

    private int daysInState;

    public Plot(int stageOfGrowth, Crop crop) {

        this.stageOfGrowth = stageOfGrowth;
        this.crop = crop;
        this.lastWatered = 0;
        this.daysInState = 1;

    }

    public void water() {

        if (this.stageOfGrowth >= 0) {
            lastWatered++;
        }

        getWaterLevelWidth();

    }

    public void drought() {

        lastWatered = lastWatered == 0 ? 0 : lastWatered - 1;
        if (lastWatered < WATER_MIN) {
            setStageOfGrowth(-1);
        }

    }

    private void resetDaysInState() {
        daysInState = 1;
    }

    public void grow() {

        if (crop == null) {
            return;
        }

        if (lastWatered < WATER_MIN || lastWatered > WATER_MAX) {
            setStageOfGrowth(-1);
        }


        if (stageOfGrowth == 0) {

            if (daysInState < 7) {

                if (crop.useFertilizer()) {
                    daysInState += 3;
                } else {
                    daysInState += 1;
                }

            } else {
                setStageOfGrowth(1);
            }

        } else if (stageOfGrowth == 1) {

            if (daysInState < 10) {

                if (crop.useFertilizer()) {
                    daysInState += 3;
                } else {
                    daysInState += 1;
                }

            } else {
                setStageOfGrowth(2);
            }

        }

        resetLastWatered();

    }

    public int getStageOfGrowth() {

        return stageOfGrowth;

    }

    public void setStageOfGrowth(int stageOfGrowth) {
        this.stageOfGrowth = stageOfGrowth;
        this.daysInState = 1;
        if (stageOfGrowth == -1) {
            setCrop(new Crop("Dead Plot"));
            lastWatered = 0;
        }
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Crop getCrop() {
        return crop;
    }

    public void resetLastWatered() {
        lastWatered = stageOfGrowth == -1 ? 0 : (lastWatered == 0 ? 0 : lastWatered - 1);
    }


    public Color getColor() {

        // If no seed is in the plot then display as black rectangle
        if (crop == null) {

            return Color.BLACK;

        }

        if (stageOfGrowth == 0) {

            return Color.SIENNA;

        } else if (stageOfGrowth == 1) {

            return Color.LIMEGREEN;

        } else if (stageOfGrowth == 2) {

            return Color.GREEN;

        } else if (stageOfGrowth == -1) {
            return Color.DARKGREY;
        }

        return null; // Should never get here

    }

    public double getWaterLevelWidth() {
        if (crop == null || lastWatered == 0) {
            return 0;
        } else if (lastWatered > WATER_MAX) {
            setStageOfGrowth(-1);
            return 150;
        } else if (lastWatered == WATER_MAX) {
            return 150;
        } else {
            return (lastWatered / 5.0) * 150;
        }
    }

    public Color getWaterLevelColor() {
        if (stageOfGrowth == -1) {
            return Color.RED;
        } else {
            return Color.LIGHTBLUE;
        }
    }



    public String getWaterLevel() {

        return " " + lastWatered;

    }

    public int getLastWatered() {
        return lastWatered;
    }


    public void sprayPesticide() {
        crop.sprayPesticide();
    }

    public void fertilize() {
        crop.fertilize();
    }
}
