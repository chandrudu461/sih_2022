package com.example.afinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FundingAgencyPost extends Fragment {

    private EditText problemStatementText,descriptionText,budgetText,durationText;
    private EditText deadlineText,eligibilityText,deliverablesText;
    private Button post;
    private EditText psIdText;
    FundingAgencyPostModel fundingAgencyPostModel;

    DocumentReference documentReference;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    private FundingAgencyPSPostModel fundingAgencyPSPostModel;
//    HeiProposalModel heiProposalModel;
//    private ArrayList<HeiProposalModel> proposals;

    public static FundingAgencyPost newInstance(FundingAgencyPostModel fundingAgencyPostModel) {
        FundingAgencyPost fragment = new FundingAgencyPost();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", fundingAgencyPostModel);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_funding_agency_post, container, false);

        fundingAgencyPostModel = (FundingAgencyPostModel) getArguments().getSerializable("user");
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //
//        fundingAgencyPostModel = (FundingAgencyPostModel) getArguments().getSerializable("user");


//        heiProposalModel = new HeiProposalModel();
//        heiProposalModel.setHeiName("demo");
//        heiProposalModel.setExpectedBudget("demo");
//        proposals = new ArrayList<HeiProposalModel>();
//        proposals.add(heiProposalModel);

        problemStatementText = view.findViewById(R.id.problemStatement);
        descriptionText = view.findViewById(R.id.description);
        budgetText = view.findViewById(R.id.budget);
        durationText = view.findViewById(R.id.duration);
        deadlineText = view.findViewById(R.id.deadline);
        eligibilityText = view.findViewById(R.id.eligibility);
        psIdText = view.findViewById(R.id.id);
        deliverablesText = view.findViewById(R.id.deliverables);


        post = view.findViewById(R.id.post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = fundingAgencyPostModel.nameOfFundingAgency;
                String problemStatement = problemStatementText.getText().toString();
                String description = descriptionText.getText().toString();
                String psId = psIdText.getText().toString();
                String budget = budgetText.getText().toString();
                String duration = durationText.getText().toString();
                String deadline = deadlineText.getText().toString();
                String eligibility = eligibilityText.getText().toString();
                String deliverables = deliverablesText.getText().toString();

                documentReference = firebaseFirestore.collection("FundingAgencyPost").document(psId);

//                fundingAgencyPSPostModel = new FundingAgencyPSPostModel(problemStatement,description,budget,duration,deadline,eligibility,deliverables);
                fundingAgencyPSPostModel = new FundingAgencyPSPostModel();
                fundingAgencyPSPostModel.setNameOfFundingAgency(name);
                fundingAgencyPSPostModel.setProblemStatement(problemStatement);
                fundingAgencyPSPostModel.setDescription(description);
                fundingAgencyPSPostModel.setBudget(budget);
                fundingAgencyPSPostModel.setPsId(psId);
                fundingAgencyPSPostModel.setDuration(duration);
                fundingAgencyPSPostModel.setDeadline(deadline);
                fundingAgencyPSPostModel.setEligibility(eligibility);
                fundingAgencyPSPostModel.setDeliverables(deliverables);
                fundingAgencyPSPostModel.setUid(firebaseUser.getUid());
                fundingAgencyPSPostModel.setStatus("ToBeVerified");
//                    fundingAgencyPSPostModel.setProposalModels(proposals);

                databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
                databaseReference2 = FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency").child(fundingAgencyPostModel.getUid()).child("Posts");

                databaseReference.child(psId).setValue(fundingAgencyPSPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Posted!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(),"unable to post",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                databaseReference2.child(psId).setValue(fundingAgencyPSPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Posted!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(),"unable to post",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                documentReference.set(fundingAgencyPSPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(getContext(), "Post Uploaded!!!", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(), "Failed to upload the post!!!", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

        return view;
    }
}