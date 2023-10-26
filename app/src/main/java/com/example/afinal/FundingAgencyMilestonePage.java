package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FundingAgencyMilestonePage extends AppCompatActivity {

    MilestonePostModel milestonePostModel;
    DatabaseReference databaseReference,ref;
    HeiProposalModel heiProposalModel;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterMilestone recyclerAdapterMilestone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_agency_milestone_page);

        heiProposalModel = (HeiProposalModel) getIntent().getSerializableExtra("presentProposal");
        databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(heiProposalModel.psId).child("milestones");

        List<MilestonePostModel> listdata = new ArrayList<>();
        recyclerView = findViewById(R.id.funding_agency_milestones);
        layoutManager = new LinearLayoutManager(this);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listdata.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    MilestonePostModel agency = (MilestonePostModel) postSnapshot.getValue(MilestonePostModel.class);
                    listdata.add(agency);
                }
                recyclerAdapterMilestone = new RecyclerAdapterMilestone(FundingAgencyMilestonePage.this,listdata);
                recyclerView.setAdapter(recyclerAdapterMilestone);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}