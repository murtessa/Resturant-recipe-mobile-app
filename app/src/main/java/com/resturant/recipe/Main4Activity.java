package com.resturant.recipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Main4Activity extends AppCompatActivity {
    CheckBox kitfo, shiro, dorowat, salata, asa;
    Button placeOrder, viewTables;
    Intent getDataForOrdersIntent, viewTablesIntent;
    Intent placeOrderIntent;
    ScrollingActivity scrollingActivity;
    DatabaseHelper myDb;
    boolean isCheckedKitfo=false;
    boolean isCheckedShiro=false;
    boolean isCheckedDorowat=false;
    boolean isCheckedSalata=false;
    boolean isCheckedAsa=false;
    String tableNo, cID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        kitfo=findViewById(R.id.kitfo);
        shiro=findViewById(R.id.shiro);
        dorowat = findViewById(R.id.dorowat);
        salata = findViewById(R.id.salata);
        asa = findViewById(R.id.asa);
        placeOrder = findViewById(R.id.orderButton);
        viewTables = findViewById(R.id.viewTables);
        viewTablesIntent = new Intent(Main4Activity.this, ScrollingActivity.class);
        myDb = new DatabaseHelper(this);
        getDataForOrdersIntent = getIntent();
        scrollingActivity = new ScrollingActivity();
        tableNo = getDataForOrdersIntent.getStringExtra("Table Number");
        cID = getDataForOrdersIntent.getStringExtra("CID");
        placeOrderIntent = new Intent(Main4Activity.this, Main5Activity.class);
        viewTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //scrollingActivity.tableSpinner.setSelection(2);
                startActivity(viewTablesIntent);
            }
        });
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kitfo.isChecked())
                {
                    isCheckedKitfo = true;
                }
                if(shiro.isChecked())
                {
                    isCheckedShiro = true;
                }
                if(dorowat.isChecked())
                {
                    isCheckedDorowat = true;
                }
                if(salata.isChecked())
                {
                    isCheckedSalata = true;
                }
                if(asa.isChecked())
                {
                    isCheckedAsa = true;
                }
                myDb.setOrders(isCheckedKitfo, isCheckedShiro, isCheckedDorowat, isCheckedSalata, isCheckedAsa, tableNo, cID);
                placeOrderIntent.putExtra("Kitfo", isCheckedKitfo);
                placeOrderIntent.putExtra("Shiro", isCheckedShiro);
                placeOrderIntent.putExtra("Doro Wat", isCheckedDorowat);
                placeOrderIntent.putExtra("Salata", isCheckedSalata);
                placeOrderIntent.putExtra("Asa", isCheckedAsa);
                placeOrderIntent.putExtra("CID", cID);
                placeOrderIntent.putExtra("Table Number", tableNo);
                startActivity(placeOrderIntent);
            }
        });
    }
}
