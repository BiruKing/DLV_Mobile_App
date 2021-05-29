package com.ethiop.drivinglicencevalidation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ethiop.drivinglicencevalidation.Navigation;
import com.ethiop.drivinglicencevalidation.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText userName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        findViewById(R.id.lUserName);
        findViewById(R.id.lPassword);

    }

    final String url = "http://10.18.197.67:300/login";
    JSONArray jsonArray = new JSONArray();

    RequestQueue requestQueue;

    public void Authenticate(View view) {

        //TODO: Response 404 200 500 if else


        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("username", userName.getText().toString());
        postParam.put("password", password.getText().toString());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, (JSONArray) postParam, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                System.out.println(response);



                JSONArray jsonArray = response;
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String albumname = jsonObject.getString("title");
                        String albumimageurl = jsonObject.getString("image");
                        // alist.add(new Albums(albumname,albumimageurl));
                        System.out.println("Hi King It's me Java");
                    }
                    //  adapter.notifyDataSetChanged();//To prevent app from crashing when updating
                    //UI through background Thread
                } catch (Exception w) {
                    Toast.makeText(Login.this, w.getMessage(), Toast.LENGTH_LONG).show();
                    System.out.println(w.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

        Intent intent = new Intent(this, Navigation.class);



        startActivity(intent);

    }

    public void gotoFretPassword(View view) {
    }

}