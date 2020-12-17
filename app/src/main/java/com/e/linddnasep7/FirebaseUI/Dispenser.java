package com.e.linddnasep7.FirebaseUI;

public class Dispenser {

    private String title;
    private String description;
    private int gel;
    private int battery;


    public Dispenser() {
        //empty constructor needed
    }

    public Dispenser (String title, String description, int gel, int battery) {
        this.title = title;
        this.description = description;
        this.gel = gel;
        this.battery=battery;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getGel() {
        return gel;
    }

    public void setGel(int gel) {
        this.gel = gel;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }
}
