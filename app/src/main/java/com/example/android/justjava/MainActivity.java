package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if(quantity<100)
        {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        else{
            createToast("Sorry, too much caffeine is bad for health!");
        }

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity>1)
        {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        else{
            createToast("You can't have less than 1 coffee");
        }

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean addWhippedCream = hasWhippedCream();
        boolean addChocolate = hasChocolate();
        int price =  calculatePrice(addWhippedCream,addChocolate);
        String  name = getName();
        createOrderSummary(name,price,addWhippedCream,addChocolate);
    }

    /**
     * Calculates the price of the order.
     * @param addWhippedCream checks whether whipped cream topping is selected
     * @param addChocolate checks whethern chocolate is selected
     * @return returns total price of the order
     *
     */
    private int calculatePrice(Boolean addWhippedCream, Boolean addChocolate) {
        int basePrice = 5;

        if(addWhippedCream){
            basePrice += 1;
        }
        if(addChocolate){
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * Checks if Whipped Cream Topping has been added.
     * @return returns whether whipped cream topping has been added
     *
     */
    private boolean hasWhippedCream() {
        CheckBox whippedCreamBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        return whippedCreamBox.isChecked();
    }

    /**
     * Checks if Chocolate Topping has been added.
     * @return returns whether Chocolate topping has been added
     *
     */
    private boolean hasChocolate() {
        CheckBox chocolateBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        return chocolateBox.isChecked();
    }

    /**
     * Checks if Chocolate Topping has been added.
     * @return returns whether Chocolate topping has been added
     *
     */
    private String getName() {
        EditText userNameBox = (EditText) findViewById(R.id.username_box);

        return userNameBox.getText().toString();
    }

    /**
     * Checks if Chocolate Topping has been added.
     * @param message indicates toast message.
     *
     */
    private void createToast(String message) {

        Context context = this;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

    }


    /**
     * Calculates the price of the order.
     * @param price is the total price of order
     */
    private void createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String orderSummary = "Name: " + name;
        orderSummary += "\nAdd Whipped Cream: " + addWhippedCream;
        orderSummary += "\nAdd Chocolate: " + addChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: $" + price;
        orderSummary += "\nThank You!";

        String emailAddress = "chethanbhat@gmail.com";
        String emailSubject = "Your coffee order - JustJava";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"+emailAddress)); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
//    private void displayMessage(String message) {
//        TextView orderSummarytextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummarytextView.setText(message);
//    }
}
