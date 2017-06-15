package com.example.amr.i_see;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    DBHelper mydb;
    private ListView obj;
    ArrayAdapter arrayAdapter;
    ArrayList array_list_numbrs, array_list_ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        mydb = new DBHelper(this);

        array_list_ids = mydb.getAllIDs();
        array_list_numbrs = mydb.getAllNumbers();

        arrayAdapter = new ArrayAdapter(FirstActivity.this, android.R.layout.simple_list_item_1, array_list_numbrs);

        obj = (ListView) findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);

        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

                // TODO Auto-generated method stub
                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", array_list_ids.get(pos).toString());
                dataBundle.putString("number", array_list_numbrs.get(pos).toString());

                Intent intent = new Intent(FirstActivity.this, MapsActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
