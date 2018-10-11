package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String hasWhippedCream = isBox1Checked();
        String hasChocolate = isBox2Checked();
        String name = getName();
        int price = calculatePrice();
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(message);

    }

    public void increment(View view){
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        quantity = quantity - 1;
        if (quantity < 0)
            quantity = 0;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     * @return total price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     *
     * @param price
     * @return order summary
     */
    private String createOrderSummary(int price, String hasWhippedCream, String hasChocolate, String name){
        String message = "Name: " + name + "\n";
        message += "Add whipped cream?" + hasWhippedCream + "\n";
        message += "Add chocolate?" + hasChocolate + "\n";
        message += "Quantity: " + quantity + "\n";
        message += "Total: $" + price + "\n";
        message += "Thank you!";
        return  message;
    }

    public String isBox1Checked(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_box1);
        boolean ischecked = checkBox.isChecked();
        return String.valueOf(ischecked);
    }

    public String isBox2Checked(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_box2);
        boolean ischecked = checkBox.isChecked();
        return String.valueOf(ischecked);
    }

    public String getName(){
        EditText editText = (EditText) findViewById(R.id.your_name);
        String name = editText.getText().toString();
        return name;
    }

}
