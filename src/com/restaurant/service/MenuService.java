package com.restaurant.service;

import com.restaurant.model.MenuItem;
import java.util.ArrayList;

public class MenuService {
    // Collections Framework: Saare food items ko store karne ke liye list
    private ArrayList<MenuItem> menuList;

    // Constructor: Jab MenuService banegi, tabhi default menu load ho jayega
    public MenuService() {
        menuList = new ArrayList<>();
        loadDefaultMenu(); // Default items dalne ke liye function call kiya
    }

    // Hardcoded Values: Restaurant ka default menu card design kiya
    private void loadDefaultMenu() {
        menuList.add(new MenuItem(1, "Veg Cheese Burger", 89.00, "Veg"));
        menuList.add(new MenuItem(2, "Margherita Pizza", 199.00, "Veg"));
        menuList.add(new MenuItem(3, "Peri Peri French Fries", 79.00, "Veg"));
        menuList.add(new MenuItem(4, "Chicken Makhani Pizza", 289.00, "Non-Veg"));
        menuList.add(new MenuItem(5, "Chocolate Oreo Shake", 119.00, "Beverage"));
    }

    // Method 1: Poora Menu Card print karne ke liye (Menu Management)
    public void displayMenu() {
        System.out.println("\n==================== MENU CARD ====================");
        if (menuList.isEmpty()) {
            System.out.println("Sorry, Menu is empty right now!");
            return;
        }
        // Loop ka use karke har ek item ko print kar rahe hain
        for (MenuItem item : menuList) {
            System.out.println(item); // Yeh automatic MenuItem ke toString() ko call karega
        }
        System.out.println("===================================================");
    }

    // Method 2: ID se item ko dhoodhne ke liye (Jab customer order karega)
    public MenuItem findItemById(int id) {
        for (MenuItem item : menuList) {
            if (item.getId() == id) {
                return item; // Item mil gaya
            }
        }
        return null; // Agar galat ID dali toh null milega
    }

    // Method 3: Admin Corner - Naya item menu me add karne ke liye
    public void addMenuItem(MenuItem newItem) {
        menuList.add(newItem);
        System.out.println("Success: " + newItem.getName() + " has been added to the menu!");
    }
}