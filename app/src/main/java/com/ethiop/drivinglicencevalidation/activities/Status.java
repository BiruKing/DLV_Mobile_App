package com.ethiop.drivinglicencevalidation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ethiop.drivinglicencevalidation.R;

import static android.content.ContentValues.TAG;

public class Status extends AppCompatActivity {

    //getBundle
    Bundle extras = getIntent().getExtras();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userName;

        if (extras != null) {
            userName = extras.getString("licenceNumber");
            Log.e(TAG, userName);
            Toast.makeText(this, "Contents = " + userName, Toast.LENGTH_SHORT).show();

        }
        setContentView(R.layout.status);


    }

    public void goToPenality(View view) {

    }

    public void goToPenalityHistory(View view) {
    }
}