package src.main;

public class GameState {

    private int precipitationFrequency;
    private double precipitationAmount;
    private double temperature;
    private String season;
    private int day;

    public GameState(int frequency, double amount, double temperature, String season, int day) {
        this.precipitationFrequency = frequency;
        this.precipitationAmount = amount;
        this.temperature = temperature;
        this.season = season;
        this.day = day;
    }

    public int getPrecipitationFrequency() {
        return precipitationFrequency;
    }

    public void setPrecipitationFrequency(int frequency) {
        this.precipitationFrequency = frequency;
    }

    public double getPrecipitationAmount() {
        return precipitationAmount;
    }

    public void setPrecipitationAmount(double amount) {
        this.precipitationAmount = amount;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
