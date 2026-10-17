package com.restaurant.model;

import java.util.ArrayList;

public class Order {
    private int orderId;
    // Collections Framework: Ordered items ko store karne ke liye ArrayList
    private ArrayList<MenuItem> items;
    private double totalAmount;
    private String status; // "Pending", "Cooking", "Ready", "Delivered"

    // Constructor
    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>(); // Empty list initialize ki
        this.totalAmount = 0.0;
        this.status = "Pending"; // Shuru me order pending rahega
    }

    // Method: Order me naya food item add karne ke liye
    public void addItem(MenuItem item) {
        this.items.add(item);
        this.totalAmount += item.getPrice(); // Total bill price badhta jayega
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status; // Iska use hum Multithreading wale step me karenge status badalne ke liye
    }
}