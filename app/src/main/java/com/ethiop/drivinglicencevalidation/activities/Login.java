package com.ethiop.drivinglicencevalidation.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ethiop.drivinglicencevalidation.Navigation;
import com.ethiop.drivinglicencevalidation.R;
import com.ethiop.drivinglicencevalidation.fragments.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity {


    boolean next = false;  // next page
    // Intent intent = new Intent(this, Navigation.class);


    EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userName = (EditText) findViewById(R.id.UserName); //---
        password = (EditText) findViewById(R.id.Password);

        //initialize intent


    }

    public void Authenticate(View view) {

        //TODO: Response 404 200 500 if else

        jsonParse();

/*try {

    Intent intent=new Intent(this,Navigation.class);

    startActivity(intent);
}catch (Exception e){
    Log.e(TAG, e.toString());

}*/
    }

    private void jsonParse() {

        Intent intent = new Intent(this, Navigation.class);


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        Log.e(TAG, userName.getText().toString());
        Log.e(TAG, password.getText().toString());


        HashMap<String, Object> params = new HashMap<>();
        params.put("username", userName.getText().toString());
        params.put("password", password.getText().toString());


        Log.e(TAG, (String) params.get("username"));
        Log.e(TAG, (String) params.get("password"));

        //---------


        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();
        String url = "http://10.18.197.116:300/login";

        Log.e(TAG, url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog.dismiss();

                        try {

                            if (response.length() <= 0) {

                                Log.e(TAG, response.getString("msg"));


                            } else {

                   try {
                       Log.e(TAG, String.valueOf(response));
                       //Log.e(TAG, response.getString("First_Name"));

                       Bundle bundle = new Bundle();

                       String name = response.getString("First_Name")+"  "+response.getString("Middle_Name")+" "+response.getString("Last_Name");

                       bundle.putString("Name", name);
                       bundle.putString("Phone_Num", response.getString("Phone_Num"));
                       bundle.putString("User_Name", response.getString("User_Name"));
                       bundle.putString("Traffic_Police_Id", response.getString("Traffic_Police_Id"));
                       bundle.putString("City", response.getString("City"));


                       //Send Activity
                       intent.putExtra("data",bundle);
                       startActivity(intent);

                       //  next = true; //next page
                   }catch (Exception e){
                       Log.e(TAG, e.toString());

                   }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e(TAG, error.toString());
                progressDialog.dismiss();
            }

        });

        /*if (next) {
            Log.e(TAG, "Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" + next);

            try {
                Intent intent = new Intent(this, Navigation.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, e.toString());


            }
        }*/

        queue.add(request);


    }

}