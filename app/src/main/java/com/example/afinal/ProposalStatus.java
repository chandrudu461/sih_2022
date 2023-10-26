package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ProposalStatus extends AppCompatActivity {

    TextView fundingAgencyName,proposalStatus,declineReason;
    HeiProposalModel heiProposalModel;
    DatabaseReference databaseReference;
    FundingAgencyPSPostModel fundingAgencyPSPostModel;

    String status,reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_status);

        fundingAgencyName = findViewById(R.id.funding_agency_name_proposal_status);
        proposalStatus = findViewById(R.id.proposal_status);
        declineReason = findViewById(R.id.decline_reason);

        heiProposalModel = (HeiProposalModel) getIntent().getSerializableExtra("presentProposal");

        if(heiProposalModel.status.equals("accepted")){
            Intent intent = new Intent(ProposalStatus.this,MilestonePage.class);
            intent.putExtra("presentProposal",heiProposalModel);
            startActivity(intent);
        }
        if(heiProposalModel.status.equals("declined")){
            proposalStatus.setText("declined");
//            declineReason.setText(heiProposalModel.declinedReason);
        }

//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                fundingAgencyPSPostModel = snapshot.getValue(FundingAgencyPSPostModel.class);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(heiProposalModel.psId);
//
//        databaseReference.child("status").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                status = snapshot.getValue(String.class);
//                Toast.makeText(ProposalStatus.this, "ondatachange", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(ProposalStatus.this, "oncancelled"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        databaseReference.child("declineReason").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                reason = snapshot.getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//        if(status.equals("accepted")){
//            startActivity(new Intent(ProposalStatus.this,MilestonePage.class));
//        }
//
//        if(fundingAgencyPSPostModel.status.equals("declined")){
//            declineReason.setText(reason);
//        }

//    }
}