package com.restaurant.main;

import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.service.BillingService;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Saari services ke objects initialize kiye
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();
        BillingService billingService = new BillingService();
        
        Scanner sc = new Scanner(System.in);
        // Active order track karne ke liye variable (Shuru me koi order nahi hai yani null)
        Order currentOrder = null; 

        System.out.println("=================================================");
        System.out.println("  WELCOME TO THE RESTAURANT MANAGEMENT SYSTEM   ");
        System.out.println("=================================================");

        // Infinite loop jab tak user exit na kare
        while (true) {
            System.out.println("\n👉 MAIN MENU:");
            System.out.println("1. View Menu Card");
            System.out.println("2. Place New Order");
            System.out.println("3. Track Order Status (Live Simulation)");
            System.out.println("4. Generate Bill & Checkout");
            System.out.println("5. Admin Panel (Add Item to Menu)");
            System.out.println("6. Exit Application");
            System.out.print("Enter your choice (1-6): ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Scanner buffer clear karne ke liye

                switch (choice) {
                    case 1:
                        // Feature: Menu Management (Display Menu)
                        menuService.displayMenu();
                        break;

                    case 2:
                        // Feature: Place Order
                        currentOrder = orderService.createNewOrder();
                        System.out.println("\n✅ New Order Created! (Order ID: " + currentOrder.getOrderId() + ")");
                        
                        while (true) {
                            menuService.displayMenu();
                            System.out.print("Enter Item ID to add to cart (or press 0 to finish selection): ");
                            int itemId = sc.nextInt();
                            
                            if (itemId == 0) {
                                break; // Order selection poori hui
                            }

                            MenuItem selectedItem = menuService.findItemById(itemId);
                            if (selectedItem != null) {
                                currentOrder.addItem(selectedItem);
                                System.out.println("➕ Added: " + selectedItem.getName() + " to your cart.");
                            } else {
                                System.out.println("❌ Invalid Item ID! Please try again.");
                            }
                        }

                        if (!currentOrder.getItems().isEmpty()) {
                            System.out.println("\n🍳 Sending your order to the kitchen...");
                            orderService.sendToKitchen(currentOrder); // Background thread trigger hoga
                        } else {
                            System.out.println("⚠️ Cart was empty. Order cancelled.");
                            currentOrder = null;
                        }
                        break;

                    case 3:
                        // Feature: Order Tracking (Live Status)
                        if (currentOrder == null) {
                            System.out.println("⚠️ No active order found! Please place an order first.");
                        } else {
                            System.out.println("\n📊 ORDER TRACKING REPORT:");
                            System.out.println("---------------------------------------");
                            System.out.println("Order ID: " + currentOrder.getOrderId());
                            System.out.println("Current Live Status: ⏳ [" + currentOrder.getStatus() + "]");
                            System.out.println("---------------------------------------");
                        }
                        break;

                    case 4:
                        // Feature: Generate Bill & Save to File
                        if (currentOrder == null) {
                            System.out.println("⚠️ No active order found to generate bill!");
                        } else {
                            billingService.generateAndSaveBill(currentOrder);
                            currentOrder = null; // Billing ke baad order clear kar diya
                        }
                        break;

                    case 5:
                        // Feature: Admin Corner (Dynamic Menu Update)
                        System.out.println("\n--- ADMIN PANEL ---");
                        System.out.print("Enter New Item ID: ");
                        int newId = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("Enter New Item Name: ");
                        String newName = sc.nextLine();
                        
                        System.out.print("Enter Price (INR): ");
                        double newPrice = sc.nextDouble();
                        sc.nextLine();
                        
                        System.out.print("Enter Category (Veg/Non-Veg/Beverage): ");
                        String newCat = sc.nextLine();

                        MenuItem newItem = new MenuItem(newId, newName, newPrice, newCat);
                        menuService.addMenuItem(newItem);
                        break;

                    case 6:
                        // Exit
                        System.out.println("\nThank you for using our system. Goodbye! 👋");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("❌ Invalid Choice! Please select a number between 1 and 6.");
                }

            } catch (InputMismatchException e) {
                // Exception Handling: Agar user integer ki jagah kuch aur input kar de
                System.out.println("❌ Error: Invalid Input type! Please enter dig its only.");
                sc.nextLine(); // Scanner buffer clear karna zaroori hai loop chalane ke liye
            }
        }
    }
}