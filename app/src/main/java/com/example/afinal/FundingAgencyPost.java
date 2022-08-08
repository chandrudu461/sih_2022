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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FundingAgencyPost extends Fragment {

    private EditText problemStatementText,descriptionText,budgetText,durationText;
    private EditText deadlineText,eligibilityText,deliverablesText;
    private Button post;

    FirebaseAuth mAuth;
    DocumentReference documentReference;
    FirebaseFirestore firebaseFirestore;

    private FundingAgencyPSPostModel fundingAgencyPSPostModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_funding_agency_post, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();

        documentReference = firebaseFirestore.collection("FundingAgencyPost").document("jsdkjlskjfl");

        problemStatementText = view.findViewById(R.id.problemStatement);
        descriptionText = view.findViewById(R.id.description);
        budgetText = view.findViewById(R.id.budget);
        durationText = view.findViewById(R.id.duration);
        deadlineText = view.findViewById(R.id.deadline);
        eligibilityText = view.findViewById(R.id.eligibility);
        deliverablesText = view.findViewById(R.id.deliverables);

        post = view.findViewById(R.id.post);


        String problemStatement = problemStatementText.getText().toString();
        String description = descriptionText.getText().toString();
        String budget = budgetText.getText().toString();
        String duration = durationText.getText().toString();
        String deadline = deadlineText.getText().toString();
        String eligibility = eligibilityText.getText().toString();
        String deliverables = deliverablesText.getText().toString();




        //fundingAgencyPSPostModel= new FundingAgencyPSPostModel();
        /*
        fundingAgencyPSPostModel.setProblemStatement(problemStatement);
        fundingAgencyPSPostModel.setDescription(description);
        fundingAgencyPSPostModel.setBudget(budget);
        fundingAgencyPSPostModel.setDuration(duration);
        */

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                fundingAgencyPSPostModel = new FundingAgencyPSPostModel(problemStatement,description,budget,duration,deadline,eligibility,deliverables);
                    fundingAgencyPSPostModel = new FundingAgencyPSPostModel();
                    fundingAgencyPSPostModel.setProblemStatement(problemStatement);
                    fundingAgencyPSPostModel.setDescription(description);
                    fundingAgencyPSPostModel.setBudget(budget);
                    fundingAgencyPSPostModel.setDuration(duration);
                    fundingAgencyPSPostModel.setDeadline(deadline);
                    fundingAgencyPSPostModel.setEligibility(eligibility);
                    fundingAgencyPSPostModel.setDeliverables(deliverables);

                Toast.makeText(getContext(), fundingAgencyPSPostModel.problemStatement+"adf"+fundingAgencyPSPostModel.description, Toast.LENGTH_SHORT).show();

                documentReference.set(fundingAgencyPSPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Post Uploaded!!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed to upload the post!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}