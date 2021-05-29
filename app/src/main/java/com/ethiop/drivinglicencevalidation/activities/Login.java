package com.ethiop.drivinglicencevalidation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ethiop.drivinglicencevalidation.Navigation;
import com.ethiop.drivinglicencevalidation.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void Authenticate(View view) {
        Intent intent = new Intent(this, Navigation.class);
        startActivity(intent);
            }

    public void gotoFretPassword(View view) {
    }
}