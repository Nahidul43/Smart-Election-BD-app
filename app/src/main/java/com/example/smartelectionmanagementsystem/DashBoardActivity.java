package com.example.smartelectionmanagementsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class DashBoardActivity extends Fragment {

    EditText edDate, edNumber;
    TextView bottonVerify,election_Info,election_Result;

    String date, number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dash_board_activity, container, false);

        edDate = view.findViewById(R.id.edDate);
        edNumber = view.findViewById(R.id.edNumber);
        bottonVerify = view.findViewById(R.id.bottonVerify);
        election_Info = view.findViewById(R.id.election_Info);
        election_Result = view.findViewById(R.id.election_Result);


        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showdate();
            }
        });

        bottonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateOfBirth = edDate.getText().toString().trim();
                String number = edNumber.getText().toString().trim();

                if(!TextUtils.isEmpty(dateOfBirth) && !TextUtils.isEmpty(number)){
                    ShowDetails showDetails = new ShowDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", number);
                    showDetails.setArguments(bundle);

                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameLayout, showDetails);

                    transaction.commit();
                } else {
                    if (TextUtils.isEmpty(dateOfBirth)) {
                        edDate.setError("Please Enter Your Date Of Birth");
                    }
                    if (TextUtils.isEmpty(number)) {
                        edNumber.setError("Please Enter Your NID Number");
                    }
                }
            }
        });



        election_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager= requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frameLayout,new Information());
                transaction.addToBackStack(null);
                transaction.commit();






            }
        });

        election_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager= requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frameLayout,new Result());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });





        return view;
    }

    //#################################################################################################
    public void Showdate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date = i + "-" + (i1 + 1) + "-" + i2;
                edDate.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    //#################################################################################################
}
