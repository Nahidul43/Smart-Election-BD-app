package com.example.smartelectionmanagementsystem;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


public class ShowDetails extends Fragment {

    TextView tvSit,tvVoterNO,tvSerisalNo,tvCenter,tvAddress;
    DatabaseHelper helper;
    RecyclerView recyclerView;
    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    String sit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


    View view=inflater.inflate(R.layout.fragment_show_details, container, false);
        tvSit=view.findViewById(R.id.tvSit);
        tvVoterNO=view.findViewById(R.id.tvVoterNO);
        tvSerisalNo=view.findViewById(R.id.tvSerisalNo);
        tvCenter=view.findViewById(R.id.tvCenter);
        tvAddress=view.findViewById(R.id.tvAddress);
        recyclerView=view.findViewById(R.id.recyclerview);


        String result=getArguments().getString("ID");

           helper=new DatabaseHelper(getContext());

        Cursor cursor=helper.getData(result);



        if(cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
                String nid=cursor.getString(0);
                String vot=cursor.getString(1);
                String serial=cursor.getString(2);
                String center=cursor.getString(3);
                String address=cursor.getString(4);
                sit=cursor.getString(5);


                tvSit.append(sit);
                tvVoterNO.append(vot);
                tvSerisalNo.append(serial);
                tvCenter.append(center);
                tvAddress.append(address);

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://carefree-straps.000webhostapp.com/demo/recoad.json", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray(sit);
                            for(int x=0;x<jsonArray.length();x++){
                                JSONObject jsonObject=jsonArray.getJSONObject(x);
                                String name=jsonObject.getString("name");
                                String title=jsonObject.getString("tilte");
                                String pic=jsonObject.getString("pic");

                                hashMap=new HashMap<>();
                                hashMap.put("name",name);
                                hashMap.put("title",title);
                                hashMap.put("pic",pic);
                                arrayList.add(hashMap);


                            }


                            recyclerView.setAdapter(new MyAdapter());
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                });
                RequestQueue queue = Volley.newRequestQueue(getActivity());

                queue.add(jsonObjectRequest);


            }
        }else {
            Toast.makeText(getActivity(),"Please Check Your NID Card!",Toast.LENGTH_LONG).show();
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frameLayout, new DashBoardActivity());
            transaction.addToBackStack(null);
            transaction.commit();

        }





        return view;
    }

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder>{



    public class Holder extends RecyclerView.ViewHolder{

         TextView tvName,tvTitle;
         ImageView image;
        public Holder(@NonNull View itemView) {
            super(itemView);
         tvName=itemView.findViewById(R.id.tvName);
         tvTitle=itemView.findViewById(R.id.tvTitle);
            image=itemView.findViewById(R.id.image);



        }
    }


    @NonNull
    @Override
    public MyAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=getLayoutInflater();
        View MyView=inflater.inflate(R.layout.item,parent,false);


        return new Holder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Holder holder, int position) {


        HashMap<String, String> hashMap = arrayList.get(position);

        String name = hashMap.get("name");
        String title = hashMap.get("title");
        String cover=hashMap.get("pic");


        holder.tvName.setText(name);
        holder.tvTitle.setText(title);

        Picasso.get().load(cover).into(holder.image);




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}








}