package com.restaurant.service;

import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BillingService {
    // Constant: 5% GST standard lagate hain food par
    private static final double GST_PERCENTAGE = 5.0;

    // Method: Bill calculate aur print karne ke liye, plus file me save karne ke liye
    public void generateAndSaveBill(Order order) {
        double subTotal = order.getTotalAmount();
        double gstAmount = (subTotal * GST_PERCENTAGE) / 100;
        double finalTotal = subTotal + gstAmount;

        // 1. Pehle hum StringBuilder me poora bill format design karenge
        StringBuilder billData = new StringBuilder();
        billData.append("\n=============================================\n");
        billData.append("            RESTAURANT RECEIPT               \n");
        billData.append("=============================================\n");
        billData.append(String.format("Order ID: %d\n", order.getOrderId()));
        billData.append(String.format("Order Status: %s\n", order.getStatus()));
        billData.append("---------------------------------------------\n");
        billData.append(String.format("%-25s %-5s %-10s\n", "Item Name", "Qty", "Price"));
        billData.append("---------------------------------------------\n");

        // Loop chalakar saare items bill me add kiye
        for (MenuItem item : order.getItems()) {
            billData.append(String.format("%-25s %-5d %.2f INR\n", item.getName(), 1, item.getPrice()));
        }

        billData.append("---------------------------------------------\n");
        billData.append(String.format("Sub-Total:                 %.2f INR\n", subTotal));
        billData.append(String.format("GST (5%%):                  %.2f INR\n", gstAmount));
        billData.append("---------------------------------------------\n");
        billData.append(String.format("FINAL TOTAL:               %.2f INR\n", finalTotal));
        billData.append("=============================================\n");

        // 2. Bill ko console par print karo
        System.out.print(billData.toString());

        // 3. File Handling: Bill ko text file me save karo (Persistence)
        saveBillToFile(billData.toString());
    }

    // Private Helper Method: File me data write karne ke liye
    private void saveBillToFile(String billContent) {
        // try-with-resources use kiya hai, isse file automatic close ho jati hai
        try (FileWriter fw = new FileWriter("restaurant_bills.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            bw.write(billContent);
            bw.newLine(); // Har bill ke baad ek line ka space
            System.out.println("ℹ️ Bill successfully saved to 'restaurant_bills.txt'!");
            
        } catch (IOException e) {
            // Exception Handling: Agar file write karne me koi error aaye
            System.out.println("❌ File Error: Unable to save bill. " + e.getMessage());
        }
    }
}