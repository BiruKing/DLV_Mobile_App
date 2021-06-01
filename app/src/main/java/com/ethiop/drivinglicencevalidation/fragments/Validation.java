package com.ethiop.drivinglicencevalidation.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ethiop.drivinglicencevalidation.Navigation;
import com.ethiop.drivinglicencevalidation.R;
import com.ethiop.drivinglicencevalidation.activities.Status;
import com.google.android.material.tabs.TabLayout;

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
        // Send scan result to activity
        goToAttract(rawResult.getContents());
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    public void goToAttract(String licenceNumber)
    {
        Intent intent = new Intent(getActivity(), Status.class);
        intent.putExtra("licenceNumber",licenceNumber);
        startActivity(intent);
    }
}