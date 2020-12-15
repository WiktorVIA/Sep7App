package com.e.linddnasep7.FirebaseUI;

public class Dispenser {

    private String title;
    private String description;
    private int gel;


    public Dispenser() {
        //empty constructor needed
    }

    public Dispenser (String title, String description, int gel) {
        this.title = title;
        this.description = description;
        this.gel = gel;
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
}
