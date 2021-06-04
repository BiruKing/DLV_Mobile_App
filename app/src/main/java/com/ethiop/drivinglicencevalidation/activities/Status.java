package com.ethiop.drivinglicencevalidation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ethiop.drivinglicencevalidation.R;

import static android.content.ContentValues.TAG;

public class Status extends AppCompatActivity {

    TextView name,lIcence_Num,Driving_Schhol,City,date,Natonality,Level,LastCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.status);


        try {
       //find
       name = (TextView) findViewById(R.id.name);
       lIcence_Num = (TextView) findViewById(R.id.liceneNo);
       City = (TextView) findViewById(R.id.Dcity);
       date = (TextView) findViewById(R.id.expDate);
       Natonality = (TextView) findViewById(R.id.Dnationality);
       Driving_Schhol = (TextView) findViewById(R.id.schoolName);
       Level = (TextView) findViewById(R.id.level);
       LastCheck = (TextView) findViewById(R.id.checkDate);


       if(getIntent().getBundleExtra("data")!= null){
           //getBundle
           Bundle extras = getIntent().getBundleExtra("data");


           String Licence;

           if (extras != null) {
               Licence = extras.getString("LIcence_Num");
               Log.e(TAG, Licence);
               Toast.makeText(this, "Contents = " + Licence, Toast.LENGTH_SHORT).show();
//--------
               name.setText(extras.getString("Name"));
               lIcence_Num.setText(extras.getString("LIcence_Num"));
               Driving_Schhol.setText(extras.getString("Driving_Schhol_N"));
               City.setText(extras.getString("City"));
               date.setText(extras.getString("Date"));
               Natonality.setText(extras.getString("Natonality"));
               Level.setText(extras.getString("Level"));
               LastCheck.setText(extras.getString("LastCheck"));

           }
       }
       else {
           Log.e(TAG, "Empity bundel QECCVV");

       }
   }catch (Exception e){

        Log.e(TAG, e.toString());
        System.out.println(e.toString());
   }

    }

    public void goToPenality(View view) {

    }

    public void goToPenalityHistory(View view) {
    }
}