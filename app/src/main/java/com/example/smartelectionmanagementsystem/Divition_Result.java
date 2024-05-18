package com.example.smartelectionmanagementsystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Divition_Result extends Fragment {


    RecyclerView recyclerview;

   TextView textView;
   ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
   HashMap<String,String>hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



      View view=  inflater.inflate(R.layout.fragment_divition__result, container, false);
        recyclerview=view.findViewById(R.id.recyclerview);

        String x=getArguments().getString("ID");



        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://carefree-straps.000webhostapp.com/demo/ResultForElection.json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONArray jsonArray=response.getJSONArray(x);
                    for(int x=0;x<jsonArray.length();x++){
                        JSONObject jsonObject=jsonArray.getJSONObject(x);
                        String sit=jsonObject.getString("sit");
                        String name=jsonObject.getString("name");
                        String pic=jsonObject.getString("title");




                        HashMap<String,String>hashMap=new HashMap<>();
                        hashMap.put("sit",sit);
                        hashMap.put("name",name);
                        hashMap.put("pic",pic);

                        arrayList.add(hashMap);

                    }


                    recyclerview.setAdapter(new MyAdapter1());
                    recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());

        queue.add(jsonObjectRequest);

        return view;
    }

    public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.Holder1>{

        public  class Holder1 extends RecyclerView.ViewHolder{

            TextView tvSit,tvName;
            ImageView tvCover;


            public Holder1(@NonNull View itemView) {
                super(itemView);

                tvSit=itemView.findViewById(R.id.tvSit);
                tvName=itemView.findViewById(R.id.tvName);
                tvCover=itemView.findViewById(R.id.tvCover);
            }
        }

        @NonNull
        @Override
        public Holder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.result,parent,false);

            return new Holder1(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder1 holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);


            String tvname=hashMap.get("name");
            String tvsit=hashMap.get("sit");
            String tvpic=hashMap.get("pic");


           holder.tvName.setText(tvname);
           holder.tvSit.setText(tvsit);

            Picasso.get().load(tvpic).into(holder.tvCover);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }



    }


}