package com.example.afinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
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

public class ViewPSHei extends AppCompatActivity {

    RecyclerView recyclerPSHei;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterPSFundingAgency recyclerAdapter;

    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_p_s_hei);

        mAuth = FirebaseAuth.getInstance();
        // change the reference to ps posts path
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency");

        recyclerPSHei = findViewById(R.id.recycler_ps_hei);
        layoutManager=new LinearLayoutManager(getApplicationContext());


        List<FundingAgencyPSPostModel> fundingAgencyPSList = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fundingAgencyPSList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    FundingAgencyPSPostModel agency = postSnapshot.getValue(FundingAgencyPSPostModel.class);
                    fundingAgencyPSList.add(agency);

                    // here you can access to name property like university.name

                }
                recyclerAdapter = new RecyclerAdapterPSFundingAgency(getApplicationContext(),fundingAgencyPSList);
                recyclerPSHei.setAdapter(recyclerAdapter);
                recyclerPSHei.setLayoutManager(layoutManager);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getMessage());
            }


        });
    }
}