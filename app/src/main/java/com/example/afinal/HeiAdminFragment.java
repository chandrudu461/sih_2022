package com.example.afinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HeiAdminFragment extends Fragment {

    RecyclerView recyclerAdmin;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterHei recyclerAdapter;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hei_admin, container, false);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child("Hei");

        recyclerAdmin = view.findViewById(R.id.recyclerViewHei_Allevents);
        layoutManager = new LinearLayoutManager(getContext());

        List<HeiPostModel> heiList = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange( DataSnapshot snapshot) {
                heiList.clear();

                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    HeiPostModel agency = postSnapshot.getValue(HeiPostModel.class);
                    heiList.add(agency);
                }

                recyclerAdapter = new RecyclerAdapterHei(getContext(),heiList);
                recyclerAdmin.setAdapter(recyclerAdapter);
                recyclerAdmin.setLayoutManager(layoutManager);

            }

            public void onCancelled( DatabaseError error) {
                System.out.println("The thread Failed: " + error.getMessage());
            }
        });



        return view;
    }
}