package com.example.amr.i_see;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
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

public class EditMainActivity extends AppCompatActivity {

    EditText mobilenumber;
    Button bTnSave;
    DBHelper mydb;
    String idd = "";
    String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main);

        mydb = new DBHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mobilenumber = (EditText) findViewById(R.id.editmobilenumber);
        bTnSave = (Button) findViewById(R.id.button2);

        Bundle extras = getIntent().getExtras();
        idd = extras.getString("id");
        number = extras.getString("number");

        mobilenumber.setText(number);

        bTnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobilenumber.getText().toString().isEmpty()) {
                    Toast.makeText(EditMainActivity.this, "Enter device's mobile number", Toast.LENGTH_SHORT).show();
                } else if (mobilenumber.getText().toString().equals(number)) {
                    Toast.makeText(EditMainActivity.this, "Nothing New", Toast.LENGTH_SHORT).show();

                    Bundle dataBundle = new Bundle();
                    dataBundle.putString("id", idd);
                    dataBundle.putString("number", number);

                    finish();
                    overridePendingTransition(0, 0);
                    Intent intent = new Intent(EditMainActivity.this, MapsActivity.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                } else {
                    if (mydb.updateNumber(idd, mobilenumber.getText().toString())) {
                        Toast.makeText(EditMainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditMainActivity.this, "Not Updated ...", Toast.LENGTH_SHORT).show();
                    }
                    Bundle dataBundle = new Bundle();
                    dataBundle.putString("id", idd);
                    dataBundle.putString("number", mobilenumber.getText().toString());

                    finish();
                    overridePendingTransition(0, 0);
                    Intent intent = new Intent(EditMainActivity.this, MapsActivity.class);
                    intent.putExtras(dataBundle);
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

            Bundle dataBundle = new Bundle();
            dataBundle.putString("id", idd);
            dataBundle.putString("number", number);

            finish();
            overridePendingTransition(0, 0);
            Intent intent = new Intent(EditMainActivity.this, MapsActivity.class);
            intent.putExtras(dataBundle);
            startActivity(intent);
            overridePendingTransition(0, 0);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {

            Bundle dataBundle = new Bundle();
            dataBundle.putString("id", idd);
            dataBundle.putString("number", number);

            finish();
            overridePendingTransition(0, 0);
            Intent intent = new Intent(EditMainActivity.this, MapsActivity.class);
            intent.putExtras(dataBundle);
            startActivity(intent);
            overridePendingTransition(0, 0);

        }
        return super.onKeyDown(keycode, event);
    }
}
