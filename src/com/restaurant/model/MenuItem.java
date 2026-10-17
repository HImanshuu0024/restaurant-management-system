package com.restaurant.model;

public class MenuItem {
    // Encapsulation: Variables ko private rakha hai
    private int id;
    private String name;
    private double price;
    private String category; // e.g., "Veg", "Non-Veg", "Beverage"

    // Constructor: Naya item create karte hi values initialize karne ke liye
    public MenuItem(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters: Private data ko safely access/modify karne ke liye
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString method: Jab hum item ko print karein, toh wo achhi tarah se dikhe
    @Override
    public String toString() {
        return String.format("ID: %d | %-25s | Category: %-10s | Price: %.2f INR", id, name, category, price);
    }
}