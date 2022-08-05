package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminRelated extends AppCompatActivity {
        RecyclerView recyclerAdmin;
        RecyclerView.LayoutManager layoutManager;
        RecyclerAdapterFundingAgency recyclerAdapter;
        FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_related);

            mAuth = FirebaseAuth.getInstance();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency");



            recyclerAdmin = findViewById(R.id.recyclerView_Allevents);
            layoutManager=new LinearLayoutManager(this);

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

                    recyclerAdapter = new RecyclerAdapterFundingAgency(getApplicationContext(),fundingAgencyList);
                    recyclerAdmin.setAdapter(recyclerAdapter);
                    recyclerAdmin.setLayoutManager(layoutManager);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });



        }
}