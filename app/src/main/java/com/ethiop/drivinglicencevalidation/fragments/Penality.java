package com.ethiop.drivinglicencevalidation.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        // Inflate the layout for this fragment
        return view;

    }

    public void makeReport(View view) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

// Json Object

        HashMap<String, Object> params = new HashMap<>();
        params.put("P_Type", penaltyType);
        params.put("Datee", date);
        params.put("Timee", time);
        params.put("City", City);
        params.put("Road_name", Road_name);
        params.put("Ticket_Num", Ticket_Num);
        params.put("Licence_num", Licence_num);
        params.put("Cause", Cause);
        params.put("Trafic_Name", Trafic_Name);
        params.put("P_Balance", P_Balance);
        params.put("Report", Report);

        String name[]=Driver_Name.toString().split(" ");

        params.put("DF_Name", name[0]);
        params.put("DM_Name", name[1]);
        params.put("DL_Name", name[2]);




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

                            if (response.getString("response").equalsIgnoreCase("Error")) {
                                //response.length() <= 0

                                Log.e(TAG, "Penality error");

                            } else if(response.getString("response").equalsIgnoreCase("Ok")) {

                                //Succsus Dialog

                                Log.e(TAG, "Penality OK");


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


        queue.add(request);
    }
}