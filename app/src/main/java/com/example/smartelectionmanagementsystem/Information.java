package com.example.smartelectionmanagementsystem;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Information extends Fragment {


    CardView glance,parties,noticeBoard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_information, container, false);


        glance=view.findViewById(R.id.glance);
        parties=view.findViewById(R.id.parties);
        noticeBoard=view.findViewById(R.id.noticeBoard);


        glance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager= requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frameLayout,new Glance());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });




        parties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager= requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frameLayout,new Partices());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return view;
    }
}