package com.example.amr.i_see;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMainActivity extends AppCompatActivity {

    EditText mobilenumber;
    Button bTnSave;
    String u = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mobilenumber = (EditText) findViewById(R.id.editmobilenumber);
        bTnSave = (Button) findViewById(R.id.button2);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        u = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "Not Available");

        mobilenumber.setText(u);

        bTnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobilenumber.getText().toString().isEmpty()) {
                    Toast.makeText(EditMainActivity.this, "Enter device's mobile number", Toast.LENGTH_SHORT).show();
                } else if (mobilenumber.getText().toString().equals(u)) {
                    Toast.makeText(EditMainActivity.this, "Nothing New", Toast.LENGTH_SHORT).show();
                    finish();
                } else {

                    SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                    editor.putString(Config.EMAIL_SHARED_PREF, "");

                    editor.commit();

                    SharedPreferences sharedPreferences = EditMainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor2 = sharedPreferences.edit();

                    editor2.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                    editor2.putString(Config.EMAIL_SHARED_PREF, mobilenumber.getText().toString());

                    editor2.commit();

                    Toast.makeText(EditMainActivity.this, "Modification Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EditMainActivity.this, MapsActivity.class);
                    startActivity(i);
                    finish();
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

            Intent i = new Intent(EditMainActivity.this, MapsActivity.class);
            startActivity(i);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
