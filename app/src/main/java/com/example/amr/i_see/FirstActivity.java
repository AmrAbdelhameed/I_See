package com.example.amr.i_see;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    DBHelper mydb;
    ListView obj;
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
                finish();
                overridePendingTransition(0, 0);
                Intent i = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
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

                finish();
                overridePendingTransition(0, 0);
                Intent intent = new Intent(FirstActivity.this, MapsActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        obj.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           final int index, long arg3) {

                AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this);
                builder.setMessage("Do you want to delete " + array_list_numbrs.get(index) + " ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                mydb.deleteNumber(array_list_ids.get(index).toString());
                                Toast.makeText(FirstActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Nothing
                    }
                });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();
                return true;
            }
        });
    }
}
