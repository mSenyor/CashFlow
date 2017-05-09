package com.moransenyor.cashflow;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    DBAdapter myDb;
    ListView lvReceipts;

    public static String EXTRA_RECEIPT_ID = "com.moransenyor.cashflow.MainActivity.EXTRA_TASK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvReceipts = (ListView)findViewById(R.id.list_view_receipts);
        openDB();
        populateListView();
        //listItemLongClick();
        //listItemClick();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewReceipt();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    public void populateListView(){
        Cursor cursor = myDb.getAllRows();
        String[] fromFieldNames = new String[] {
                DBAdapter.KEY_PRICE,
                DBAdapter.KEY_CURRENCY_ID,
                DBAdapter.KEY_DATE,
                DBAdapter.KEY_PRODUCT_ID,
                DBAdapter.KEY_VENDOR_ID};

        int[] toViewIDs = new int[] {R.id.item_price, R.id.item_currency, R.id.item_date, R.id.item_product, R.id.item_vendor};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.receipts_list_item, cursor, fromFieldNames, toViewIDs,0);
        lvReceipts.setAdapter(myCursorAdapter);
    }

    public void addNewReceipt(){
        Intent intent = new Intent(this, AddNewReceipt.class);
        startActivity(intent);
    }
}
