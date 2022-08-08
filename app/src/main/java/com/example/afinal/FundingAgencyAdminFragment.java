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

public class FundingAgencyAdminFragment extends Fragment {
    RecyclerView recyclerAdmin;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterFundingAgency recyclerAdapter;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_funding_agency_admin, container, false);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency");

        recyclerAdmin = view.findViewById(R.id.recyclerView_Allevents);
        layoutManager=new LinearLayoutManager(getContext());

        List<FundingAgencyPostModel> fundingAgencyList = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                fundingAgencyList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    FundingAgencyPostModel agency = postSnapshot.getValue(FundingAgencyPostModel.class);
                    fundingAgencyList.add(agency);

                    // here you can access to name property like university.name

                }

                recyclerAdapter = new RecyclerAdapterFundingAgency(getContext(),fundingAgencyList);
                recyclerAdmin.setAdapter(recyclerAdapter);
                recyclerAdmin.setLayoutManager(layoutManager);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });



        return view;
    }
}