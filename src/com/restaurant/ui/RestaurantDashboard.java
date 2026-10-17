package com.restaurant.ui;

import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.BillingService;
import com.restaurant.model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantDashboard extends JFrame { // Window banane ke liye JFrame use kiya
    private MenuService menuService;
    private OrderService orderService;
    private BillingService billingService;
    private Order currentOrder;

    public RestaurantDashboard() {
        // Services Initialize ki
        menuService = new MenuService();
        orderService = new OrderService();
        billingService = new BillingService();

        // Window (JFrame) ki basic settings
        setTitle("Restaurant Order Management System");
        setSize(400, 400);
        setLayout(null); // Absolute positioning ke liye
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Button: View Menu
        JButton btnViewMenu = new JButton("View Menu");
        btnViewMenu.setBounds(100, 50, 200, 40);
        add(btnViewMenu);

        // 2. Button: Create Order
        JButton btnCreateOrder = new JButton("Place New Order");
        btnCreateOrder.setBounds(100, 120, 200, 40);
        add(btnCreateOrder);

        // 3. Button: Track Order
        JButton btnTrackOrder = new JButton("Track Live Order");
        btnTrackOrder.setBounds(100, 190, 200, 40);
        add(btnTrackOrder);

        // 4. Button: Generate Bill
        JButton btnBill = new JButton("Generate Bill");
        btnBill.setBounds(100, 260, 200, 40);
        add(btnBill);

        // --- BUTTON ACTIONS (Event Handling) ---

        // View Menu click hone par kya hoga
        btnViewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Console ki jagah Swing me hum Pop-up Dialog (JOptionPane) use karte hain
                JOptionPane.showMessageDialog(null, "Opening Menu... (Check your IDE console for full layout for now)");
                menuService.displayMenu(); 
            }
        });

        // Place Order click hone par kya hoga
        btnCreateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentOrder = orderService.createNewOrder();
                // User se Input lene ke liye dialog box
                String inputId = JOptionPane.showInputDialog("Enter Item ID to add (e.g. 1 for Burger, 2 for Pizza):");
                if (inputId != null && !inputId.isEmpty()) {
                    int id = Integer.parseInt(inputId);
                    var item = menuService.findItemById(id);
                    if (item != null) {
                        currentOrder.addItem(item);
                        JOptionPane.showMessageDialog(null, item.getName() + " added to cart! Sending to Kitchen.");
                        orderService.sendToKitchen(currentOrder); // Background Multithreading trigger ho gayi!
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Item ID!");
                    }
                }
            }
        });

        // Track Order click hone par kya hoga
        btnTrackOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentOrder == null) {
                    JOptionPane.showMessageDialog(null, "No active order found!");
                } else {
                    // Live tracking status show karega pop-up me
                    JOptionPane.showMessageDialog(null, "Order ID: " + currentOrder.getOrderId() + "\nLive Status: [" + currentOrder.getStatus() + "]");
                }
            }
        });

        // Bill click hone par kya hoga
        btnBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentOrder == null) {
                    JOptionPane.showMessageDialog(null, "No active order found!");
                } else {
                    billingService.generateAndSaveBill(currentOrder);
                    JOptionPane.showMessageDialog(null, "Bill Generated! Saved to 'restaurant_bills.txt'.");
                    currentOrder = null; // Reset
                }
            }
        });
    }

    public static void main(String[] args) {
        // GUI Window ko launch karne ke liye
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RestaurantDashboard().setVisible(true);
            }
        });
    }
}