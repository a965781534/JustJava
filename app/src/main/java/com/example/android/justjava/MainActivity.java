package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String name = getName();
        String title = getString(R.string.email_title) + name;
        String hasWhippedCream = isWhippedCreamChecked();
        if (hasWhippedCream == "true") {
            hasWhippedCream = getString(R.string.is_true);
        } else {
            hasWhippedCream = getString(R.string.is_false);
        }
        String hasChocolate = isChocolateChecked();
        if (hasChocolate == "true") {
            hasChocolate = getString(R.string.is_true);
        } else {
            hasChocolate = getString(R.string.is_false);
        }
        int price = calculatePrice();
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        composeEmail(title, message);
    }

    public void increment(View view){
        if (quantity == 100){
            Toast.makeText(this, getString(R.string.increment_hint), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if (quantity == 1){
            Toast.makeText(this, getString(R.string.decrement_hint), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
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
     * Calculates the price of the order.
     * @return total price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        if (isWhippedCreamChecked() == "true") {
            price += quantity * 1;
        }
        if (isChocolateChecked() == "true") {
            price += quantity * 2;
        }
        return price;
    }

    /**
     *
     * @param price
     * @return order summary
     */
    private String createOrderSummary(int price, String hasWhippedCream, String hasChocolate, String name){
        String message = getString(R.string.order_summary_name) + name + "\n";
        message += getString(R.string.order_summary_whipped_cream) + hasWhippedCream + "\n";
        message += getString(R.string.order_summary_chocolate) + hasChocolate + "\n";
        message += getString(R.string.order_summary_quantity) + quantity + "\n";
        message += getString(R.string.order_summary_price) + price + "\n";
        message += getString(R.string.thank_you);
        return  message;
    }

    public String isWhippedCreamChecked(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_box1);
        boolean ischecked = checkBox.isChecked();
        return String.valueOf(ischecked);
    }

    public String isChocolateChecked(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_box2);
        boolean ischecked = checkBox.isChecked();
        return String.valueOf(ischecked);
    }

    public String getName(){
        EditText editText = (EditText) findViewById(R.id.your_name);
        String name = editText.getText().toString();
        return name;
    }

    /**
     * 调用电子邮件
     * @param subject 电子邮件主题
     * @param text 电子邮件正文
     */
    public void composeEmail(String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("plain/text");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
