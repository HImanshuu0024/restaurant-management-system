# Restaurant Management System

A restaurant management application written in core Java. You can browse the menu, place orders, track an order while it is being "cooked", and generate a bill. It runs in the console and also has a Java Swing window.

I built this to practise OOP, collections, multithreading and file handling in one project instead of separate small programs.

## Features

- View the menu card with item categories and prices
- Place a new order and see the running total as items are added
- Track order status. The cooking time is simulated on a separate thread, so the rest of the program keeps working
- Generate a bill with subtotal and 5% GST, and save the invoice to `restaurant_bills.txt`
- Admin panel to add new items to the menu while the program is running
- Works from the console, and also has a simple Swing GUI

## Console menu

```
1. View Menu Card
2. Place New Order
3. Track Order Status (Live Simulation)
4. Generate Bill & Checkout
5. Admin Panel (Add Item to Menu)
6. Exit Application
```

<!-- Add your screenshot here, for example: ![Main menu](screenshots/main-menu.png) -->

## What I used

- **Core Java and OOP:** classes for menu items and orders, with encapsulation and inheritance
- **Collections:** `ArrayList<MenuItem>` for the menu and cart, `HashMap<Integer, Order>` for active orders
- **Multithreading:** `Thread` and `Runnable` with `Thread.sleep` to simulate cooking time
- **File handling:** `FileWriter` and `BufferedWriter` with try-with-resources to write invoices
- **Exception handling:** `try-catch` for invalid input (`InputMismatchException`) and for `IOException` and `InterruptedException`
- **Java Swing:** `JFrame`, `JButton` and `JOptionPane` for the GUI

## Project structure

```text
src/
└── com/
    └── restaurant/
        ├── model/
        │   ├── MenuItem.java        # menu item data
        │   └── Order.java           # order data and status
        ├── service/
        │   ├── MenuService.java     # menu operations
        │   ├── OrderService.java    # creates orders, runs the cooking thread
        │   └── BillingService.java  # bill calculation and writing to file
        ├── ui/
        │   └── RestaurantDashboard.java   # Swing GUI
        └── main/
            └── Main.java            # console entry point
```

## How to run

You need JDK 8 or higher.

From the project folder:

```
javac -d bin src/com/restaurant/model/*.java src/com/restaurant/service/*.java src/com/restaurant/ui/*.java src/com/restaurant/main/*.java
java -cp bin com.restaurant.main.Main
```

Or open the project in VS Code / IntelliJ and run `Main.java`. To open the GUI, run `RestaurantDashboard.java`.

## What I learned

- Updating order status in a background thread without blocking the main program or the UI, and keeping that shared data consistent
- Keeping the code organised by splitting it into `model`, `service`, `ui` and `main` packages, each with its own job
- Handling files safely with try-with-resources, so files are always closed and errors do not crash the program

## Possible improvements

- Save the menu to a file so added items are not lost when the program closes
- Move from text files to a database such as MySQL
