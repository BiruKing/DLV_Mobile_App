package com.ethiop.drivinglicencevalidation.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ethiop.drivinglicencevalidation.Navigation;
import com.ethiop.drivinglicencevalidation.R;
import com.ethiop.drivinglicencevalidation.activities.ChangePassword;
import com.ethiop.drivinglicencevalidation.activities.*;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

//Set text
    //Photo--

    TextView trafficName, Phone_Num, User_Name, Traffic_Police_Id, City;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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

//find vie by id
        View view = inflater.inflate(R.layout.profile, container, false);

        trafficName = (TextView) view.findViewById(R.id.TrafficName);
        Phone_Num = (TextView) view.findViewById(R.id.phoneNum);
        User_Name = (TextView) view.findViewById(R.id.trafficUserName);
        Traffic_Police_Id = (TextView) view.findViewById(R.id.trafficPoliceId);
        City = (TextView) view.findViewById(R.id.city);

        try {

            Navigation navigation = (Navigation) getActivity();

            Bundle results = navigation.getBundleData();

            Log.e(TAG, "Profile " + results.getString("Name"));


            //Stet profile
//todo: photo
            trafficName.setText(results.getString("Name"));
            Phone_Num.setText(results.getString("Phone_Num"));
            User_Name.setText(results.getString("User_Name"));
            Traffic_Police_Id.setText(results.getString("Traffic_Police_Id"));
            City.setText(results.getString("City"));

        } catch (Exception e) {
            Log.e(TAG, e.toString());

        }


        // Inflate the layout for this fragment
        return view;
    }

    public void changePassword(View view) {
        Intent intent = new Intent(getActivity(), ChangePassword.class);
        startActivity(intent);

    }
}