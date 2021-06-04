package com.ethiop.drivinglicencevalidation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.ui.NavigationUI;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static android.content.ContentValues.TAG;


public class Navigation extends AppCompatActivity {

    //For storing traffic profile

    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("msg", "I'm in");

        setContentView(R.layout.navigation);

        Log.i("msg", "next");


        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = androidx.navigation.Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        final TextView textTitle = (TextView) findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());
            }
        });


        ///------
        Bundle extras = getIntent().getExtras().getBundle("data");

        if (extras != null) {


            try {

                //data.getString("First_Name",extras.getString("First_Name"));

                Log.e(TAG, extras.getString("Name"));


                //Bundle dataGet = new Bundle();
                //dataGet.putString("First_Name", extras.getString("First_Name"));

                //Set data
                //todo: photo
                setBundleData(getIntent().getExtras().getBundle("data"));


                //Log.e(TAG, data.getString("First_Name"));
                Log.e(TAG, extras.getString("Name"));

                Toast.makeText(this, "Contents = " + extras.getString("Name"), Toast.LENGTH_SHORT).show();


                //Initialize navigation header


                Fragment frag = getFragmentManager().findFragmentById(R.id.navigationHeader);
                // name
                ((TextView) frag.getView().findViewById(R.id.profileName)).setText(extras.getString("Name"));
                //todo: photo

            } catch (Exception e) {
                Log.e(TAG, e.toString());

            }
        } else {
            Toast.makeText(this, "Nooo", Toast.LENGTH_SHORT).show();

        }

    }

    public Bundle getBundleData() {

        return data;
    }

    public void setBundleData(Bundle bundleData) {

        data = bundleData;

    }
}