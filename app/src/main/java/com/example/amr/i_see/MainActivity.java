package com.example.amr.i_see;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mobilenumber;
    Button bTnSave;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mydb = new DBHelper(this);

        mobilenumber = (EditText) findViewById(R.id.mobile);
        bTnSave = (Button) findViewById(R.id.button1);

        bTnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobilenumber.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter device's mobile number", Toast.LENGTH_SHORT).show();
                } else {
                    if (mydb.insertNumber(mobilenumber.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Not Added ...", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                    overridePendingTransition(0, 0);
                    Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();
            overridePendingTransition(0, 0);
            Intent i = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {

            finish();
            overridePendingTransition(0, 0);
            Intent i = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);

        }
        return super.onKeyDown(keycode, event);
    }
}
