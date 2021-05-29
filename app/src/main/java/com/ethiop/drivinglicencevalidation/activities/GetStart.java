package com.ethiop.drivinglicencevalidation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ethiop.drivinglicencevalidation.R;

public class GetStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_start);

    }

    public void onClickHandler(View view) {
        Log.i("info","I am here");


        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }
}