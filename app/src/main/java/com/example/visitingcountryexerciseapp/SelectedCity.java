package com.example.visitingcountryexerciseapp;

public class SelectedCity {
    private String cityName;
    private int days;
    private double total;
    private int person;

    public SelectedCity(String cityName, int days, double total, int person) {
        this.cityName = cityName;
        this.days = days;
        this.total = total;
        this.person = person;
    }

    public String getCityName() {
        return cityName;
    }

    public int getDays() {
        return days;
    }

    public double getTotal() {
        return total;
    }

    public int getPerson() {
        return person;
    }
}
