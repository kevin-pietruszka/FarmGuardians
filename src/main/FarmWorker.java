package main;


public class FarmWorker {

    private int skill;
    private double wage;

    public FarmWorker(int s, double w) {

        skill = s;
        wage = w;

    }

    public int getSkill() {
        return skill;
    }

    public double getWage() {
        return wage;
    }
}
