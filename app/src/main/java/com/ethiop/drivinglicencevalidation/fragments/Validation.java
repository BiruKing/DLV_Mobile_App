package com.ethiop.drivinglicencevalidation.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ethiop.drivinglicencevalidation.activities.Status;

import org.json.JSONObject;

import java.util.HashMap;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Validation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Validation extends Fragment implements ZBarScannerView.ResultHandler {

    //TextView textView = (TextView) findViewById(R.id.tvresult);

    private ZBarScannerView mScannerView;
    //camera permission is needed.

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Validation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Validation.
     */
    // TODO: Rename and change types and number of parameters
    public static Validation newInstance(String param1, String param2) {
        Validation fragment = new Validation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


            //mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
           // setContentView(mScannerView);                // Set the scanner view as the content view
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mScannerView = new ZBarScannerView(getActivity());
        return mScannerView;

        //return inflater.inflate(R.layout.validation, false);


    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getActivity(), "Contents = " + rawResult.getContents() +
                ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();

        // Check for validity
        validate(rawResult.getContents());

        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
         // mScannerView.startCamera();

    }



    public void validate(String code){


        Intent intent = new Intent(getActivity(), Status.class);


        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

                Log.e(TAG, code);

// Json Object

        HashMap<String, Object> params = new HashMap<>();
        params.put("licenseNumber", code);

        Log.e(TAG, (String) params.get("licenseNumber"));


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.start();
        String url = "http://10.18.197.116:300/validate";

        Log.e(TAG, url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog.dismiss();

                        try {

                            if (response.length() <= 0) {


                                onResume();
                               // Log.e(TAG, response.getString("msg"));
                                 Log.e(TAG, "Driver NOt found");

                            } else {

                                try {
                                    Log.e(TAG, String.valueOf(response));
                                    //Log.e(TAG, response.getString("First_Name"));

                                    Bundle bundle = new Bundle();

                                    String name = response.getString("First_Name")+"  "+response.getString("Middle_Name")+" "+response.getString("Last_Name");

                                    bundle.putString("Name", name);
                                    bundle.putString("LIcence_Num", response.getString("LIcence_Num"));
                                    bundle.putString("Driving_Schhol_N", response.getString("Driving_Schhol_N"));
                                    bundle.putString("Date", response.getString("Date"));
                                    bundle.putString("Natonality", response.getString("Natonality"));
                                    bundle.putString("Level", response.getString("Level"));
                                    bundle.putString("LastCheck", response.getString("LastCheck"));
                                    bundle.putString("City", response.getString("City"));

                                    //todo: Photo

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