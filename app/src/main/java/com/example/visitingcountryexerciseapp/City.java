package com.example.visitingcountryexerciseapp;

public class City {
    private String country;
    private String city;
    private double expense;

    public City(String country, String city, double expense) {
        this.country = country;
        this.city = city;
        this.expense = expense;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getExpense() {
        return expense;
    }
}

