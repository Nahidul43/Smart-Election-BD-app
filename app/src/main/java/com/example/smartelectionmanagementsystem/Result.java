package com.example.smartelectionmanagementsystem;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class Result extends Fragment {

    RecyclerView recyclerView;
    TextView edSearch, bottonVerify;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        edSearch = view.findViewById(R.id.edSearch);
        bottonVerify = view.findViewById(R.id.bottonVerify);

        createTable();

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        return view;
    }

    public void createTable() {
        arrayList.clear(); // Clear the arraylist to avoid duplicates

        hashMap = new HashMap<>();
        hashMap.put("name", "Barishal");
        hashMap.put("title", "Rajshahi");
        arrayList.add(new HashMap<>(hashMap));

        hashMap = new HashMap<>();
        hashMap.put("name", "Chittagong");
        hashMap.put("title", "Rangpur");
        arrayList.add(new HashMap<>(hashMap));

        hashMap = new HashMap<>();
        hashMap.put("name", "Dhaka");
        hashMap.put("title", "Khulna");
        arrayList.add(new HashMap<>(hashMap));

        hashMap = new HashMap<>();
        hashMap.put("name", "Mymensingh");
        hashMap.put("title", "Sylhet");
        arrayList.add(new HashMap<>(hashMap));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

        public class Holder extends RecyclerView.ViewHolder {
            TextView tvDivition, tvDivition2;

            public Holder(@NonNull View itemView) {
                super(itemView);
                tvDivition = itemView.findViewById(R.id.tvDivition);
                tvDivition2 = itemView.findViewById(R.id.tvDivition2);
            }
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item2, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            hashMap = arrayList.get(position);
            String name = hashMap.get("name");
            String title = hashMap.get("title");

            holder.tvDivition.setText(name);
            holder.tvDivition2.setText(title);

            holder.tvDivition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edSearch.setText(name);
                }
            });

            bottonVerify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Divition_Result divitionResult = new Divition_Result();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", name);
                    divitionResult.setArguments(bundle);

                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameLayout, divitionResult);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}
