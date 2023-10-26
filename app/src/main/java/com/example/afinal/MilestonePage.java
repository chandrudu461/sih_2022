package com.example.afinal;

import static androidx.recyclerview.widget.RecyclerView.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.AlignmentSpan;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MilestonePage extends AppCompatActivity {

//    MilestonePostModel milestonePostModel;
//    int requestCodeCounter = 0;
    DatabaseReference databaseReference,ref;
    HeiProposalModel heiProposalModel;
    RecyclerView recyclerView;
    LayoutManager layoutManager;
    RecyclerAdapterMilestone recyclerAdapterMilestone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone_page);

        heiProposalModel = (HeiProposalModel) getIntent().getSerializableExtra("presentProposal");
        databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(heiProposalModel.psId).child("milestones");

        List<MilestonePostModel> listdata = new ArrayList<>();
        recyclerView = findViewById(R.id.milestones);
        layoutManager = new LinearLayoutManager(this);


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listdata.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    MilestonePostModel agency = (MilestonePostModel) postSnapshot.getValue(MilestonePostModel.class);
                    listdata.add(agency);
                }
                recyclerAdapterMilestone = new RecyclerAdapterMilestone(MilestonePage.this,listdata);
                recyclerView.setAdapter(recyclerAdapterMilestone);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == requestCodeCounter  && resultCode == RESULT_OK){
//            milestonePostModel.setDeliverableUri(data.getData().toString());
//        }
//        requestCodeCounter++;
//    }
}