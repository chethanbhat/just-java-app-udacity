package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    // Number of cups of coffee ordered
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price =  calculatePrice();
        displayMessage(createOrderSummary(price));
    }

    /**
     * Calculates the price of the order.
     * @return returns total price of the order
     *
     */
    private int calculatePrice() {
        int pricePerCup = 5;
        return quantity * pricePerCup;
    }

    /**
     * Calculates the price of the order.
     * @param price is the total price of order
     */
    private String createOrderSummary(int price) {
        String orderSummary = "Name: Chethan Bhat";
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: $" + price;
        orderSummary += "\nThank You!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummarytextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummarytextView.setText(message);
    }
}
