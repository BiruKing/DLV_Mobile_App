package com.ethiop.drivinglicencevalidation.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
        password =  findViewById(R.id.Password);

        //initialize intent


    }

    public void Authenticate(View view) {

        //TODO: Response 404 200 500 if else

        jsonParse();


        /*Intent intent=new Intent(this,Navigation.class);

        startActivity(intent);*/
    }

    private void jsonParse() {


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

                        try {

                            if (response.length()<=0) {

                                Log.e(TAG, response.getString("msg"));


                            } else {


                                Log.e(TAG, String.valueOf(response));
                                //Log.e(TAG, response.getString("First_Name"));

                                /*
                                Bundle bundle = new Bundle();
                                bundle.putString("First_Name", response.getString("First_Name"));


                                // set Home Arguments
                                Home home = new Home();
                                home.setArguments(bundle);

                                //Call Fragment

                                Fragment fragment = new Home();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.home, fragment, fragment.getClass().getSimpleName())
                                        .addToBackStack(null)
                                        .commit();
*/
                                /*Bundle bundle = new Bundle();
                                bundle.putString("First_Name", response.getString("First_Name"));
                                setNext(bundle);*/
                                ///setNext();
                                next = true; //next page

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

            }
        });

        queue.add(request);

        if (next) {
            Log.e(TAG, "Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+next);

            Intent intent = new Intent(this, Navigation.class);
            startActivity(intent);
        }


    }

}