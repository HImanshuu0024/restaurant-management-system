package com.restaurant.service;

import com.restaurant.model.Order;
import java.util.HashMap;

public class OrderService {
    // Collections Framework: Saare active orders ko unki ID ke sath store karne ke liye HashMap
    private HashMap<Integer, Order> orderMap;
    private int orderIdCounter;

    public OrderService() {
        this.orderMap = new HashMap<>();
        this.orderIdCounter = 1; // Pehla order ID 1 se shuru hoga
    }

    // Method 1: Naya Order generate karne ke liye
    public Order createNewOrder() {
        Order newOrder = new Order(orderIdCounter);
        orderMap.put(orderIdCounter, newOrder);
        orderIdCounter++; // Agle order ke liye ID badha di
        return newOrder;
    }

    // Method 2: ID se active order ka status check karne ke liye (Order Tracking)
    public Order getOrderById(int id) {
        return orderMap.get(id);
    }

    // Method 3: Multithreading - Kitchen me khana pakne ka simulation 
    public void sendToKitchen(Order order) {
        // Hum ek naya Thread bana rahe hain jo background me chalega
        Thread kitchenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 1. Order queue me gaya
                    order.setStatus("Cooking");
                    
                    // 2. Simulate cooking time: 5 seconds ke liye thread ko rok diya
                    Thread.sleep(5000); 
                    
                    // 3. Khana taiyar ho gaya
                    order.setStatus("Ready");
                    
                } catch (InterruptedException e) {
                    System.out.println("Kitchen Error: " + e.getMessage());
                }
            }
        });

        // Thread ko start kiya. Isse main program ruka nahi rahega, background me cooking chalti rahegi!
        kitchenThread.start();
    }
}