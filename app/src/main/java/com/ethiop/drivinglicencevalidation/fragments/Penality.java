package com.ethiop.drivinglicencevalidation.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ethiop.drivinglicencevalidation.R;

import org.json.JSONObject;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Penality#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Penality extends Fragment {

    TextView penaltyType,date,time,City,Road_name,Ticket_Num,Licence_num,Cause,Trafic_Name,P_Balance,Driver_Name,Report;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Penality() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Penality.
     */
    // TODO: Rename and change types and number of parameters
    public static Penality newInstance(String param1, String param2) {
        Penality fragment = new Penality();
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

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.penality, container, false);

        penaltyType = (TextView) view.findViewById(R.id.penalityType);
        date = (TextView) view.findViewById(R.id.penalityReportDate);
        time = (TextView) view.findViewById(R.id.penalityReportTime);
        City = (TextView) view.findViewById(R.id.pcity);
        Road_name = (TextView) view.findViewById(R.id.roadName);
        Ticket_Num = (TextView) view.findViewById(R.id.ticketNum);
        Licence_num = (TextView) view.findViewById(R.id.licenceNum);
        Cause = (TextView) view.findViewById(R.id.cause);
        Trafic_Name = (TextView) view.findViewById(R.id.trafficName);
        P_Balance = (TextView) view.findViewById(R.id.amount);
        Driver_Name = (TextView) view.findViewById(R.id.driverName);
        Report = (TextView) view.findViewById(R.id.penalityReport);


        // Click handler

        final Button button = (Button) view.findViewById(R.id.makePenaltyBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    makePenalty();
                }catch (Exception e){
                    Log.e(TAG, e.toString());

                }
            }
        });

        // Inflate the layout for this fragment

        return view;

    }

    public void makePenalty() {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

// Json Object

        HashMap<String, Object> params = new HashMap<>();
        params.put("P_Type", penaltyType.getText().toString());
        params.put("datee", date.getText().toString());
        params.put("timee", time.getText().toString());
        params.put("city", City.getText().toString());
        params.put("Road_name", Road_name.getText().toString());
        params.put("Ticket_Num", Ticket_Num.getText().toString());
        params.put("Licence_num", Licence_num.getText().toString());
        params.put("Cause", Cause.getText().toString());
        params.put("Traffic_Name", Trafic_Name.getText().toString());
        params.put("P_Balance", P_Balance.getText().toString());
        params.put("Report", Report.getText().toString());

        String name[]=Driver_Name.getText().toString().split(" ");

        //
        Log.e(TAG, Driver_Name.getText().toString());

        Log.e(TAG, ""+name.length);

        Log.e(TAG, name[0]+" "+name[1]+" "+name[2]);

        //
        params.put("DF_Name", name[0]);
        params.put("DM_Name", name[1]);
        params.put("DL_Name", name[2]);


       // Log.e(TAG, (String) params.get("licenseNumber"));


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.start();
        String url = "http://10.18.197.116:300/penalty";

        Log.e(TAG, url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog.dismiss();

                        try {

                            if (response.getString("response").equalsIgnoreCase("Error")) {

                                Log.e(TAG, "Penality error");

                                //Dialog box

                                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                                alertDialog.setTitle("Warning!");
                                alertDialog.setMessage("Try again");
                                // Alert dialog button
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Alert dialog action goes here
                                                // onClick button code here
                                                dialog.dismiss();// use dismiss to cancel alert dialog
                                            }
                                        });
                                alertDialog.show();

                            } else if(response.getString("response").equalsIgnoreCase("Ok")) {

                                //Succsus Dialog

                                Log.e(TAG, "Penality OK");

                                //Dialog box

                                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                                alertDialog.setTitle("Success!");
                                alertDialog.setMessage("The penalty has been completed.");
                                // Alert dialog button
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Alert dialog action goes here
                                                // onClick button code here
                                                dialog.dismiss();// use dismiss to cancel alert dialog
                                            }
                                        });
                                alertDialog.show();

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

        //Request Timeout

         int TIMEOUT_MS=10000;        //10 seconds

        request.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //Start request queue

        queue.add(request);
    }
}