package com.moransenyor.cashflow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewReceipt extends AppCompatActivity {

    DBAdapter myDb;
    Time today = new Time(Time.getCurrentTimezone());

    EditText editText_price, editText_currency,
            editText_day, editText_month, editText_year,
            editText_product, editText_vendor,
            editText_category,  editText_behalf,
            editText_image,
            editText_tags,
            editText_notes;

    Button btnToday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_receipt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText_price = (EditText)findViewById(R.id.new_receipt_price);
        editText_currency = (EditText)findViewById(R.id.new_receipt_currency);

        editText_day = (EditText)findViewById(R.id.new_receipt_day);
        editText_month = (EditText)findViewById(R.id.new_receipt_month);
        editText_year = (EditText)findViewById(R.id.new_receipt_year);

        editText_product = (EditText)findViewById(R.id.new_receipt_product);
        editText_vendor = (EditText)findViewById(R.id.new_receipt_vendor);

        editText_category = (EditText)findViewById(R.id.new_receipt_category);
        editText_behalf = (EditText)findViewById(R.id.new_receipt_behalf);

        editText_image = (EditText)findViewById(R.id.new_receipt_image);

        editText_tags = (EditText)findViewById(R.id.new_receipt_tags);

        editText_notes = (EditText)findViewById(R.id.new_receipt_notes);

        btnToday = (Button)findViewById(R.id.new_receipt_today_button);



        openDB();

        setToday();

        resetToday();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(editText_image.getText().toString())){
                    myDb.insertRow(editText_price.getText().toString(),
                            editText_image.getText().toString(),
                            getDateFromFields(),
                            editText_notes.getText().toString(),
                            editText_currency.getText().toString(),
                            editText_product.getText().toString(),
                            editText_vendor.getText().toString(),
                            editText_category.getText().toString(),
                            editText_behalf.getText().toString());

                    Toast.makeText(getApplicationContext(), "Receipt added", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "FAILED to add Receipt!", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    private void setToday(){
        // TODO: 08/05/17 - make this update the date values
        editText_day.setText(today.format("%d"));
        editText_month.setText(today.format("%m"));
        editText_year.setText(today.format("%Y"));
    }

    private String getDateFromFields(){
        String date = editText_day.getText().toString() + "/" + editText_month.getText().toString() + "/" + editText_year.getText().toString();
        return date;
    }

    private void resetToday(){
        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToday();
            }
        });
    }

    public String makeTimestamp(){
        today.setToNow();
        return today.format("%d/%m/%Y");
    }



}
