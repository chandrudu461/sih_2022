package com.example.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HeiProposal extends AppCompatActivity {

    private static final CharSequence PICK_PDF = "pdf";
    TextView problemStatementText,descriptionText,proposal;
    EditText expectedDurationText,expectedBudgetText;
    Button addMilestone,submit,addBill;
    String budget;
    private int counter=1;
    DatabaseReference dataRef;
    ArrayList<MilestonePostModel> listdata;
    ArrayList<MilestoneFurtherPostModel> furtherList;
    ArrayList<BillModel> bills;
    BillModel billModel;
    RelativeLayout r,r2;
    CollectionReference collectionReference;
    HeiPostModel heiPostModel;
    private MilestonePostModel milestonePostModel;
    private Uri proposalUri;
    private FirebaseStorage firebaseStorage;
    MilestoneFurtherPostModel milestoneFurtherPostModel;
    private DocumentReference documentReference;
    private int counter1 = 1;
    DatabaseReference databaseReference,nameReference,databaseReference2;
    FundingAgencyPSPostModel fundingAgencyPSPostModel;
    FirebaseUser firebaseUser;

    EditText proposalId;
    ArrayList<MilestoneFurtherPostModel> budgetDivision;
    private String name;
    private String value;
    final int min = 0;
    final int max = 20;
    final int random = new Random().nextInt((max - min) + 1) + min;
//    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hei_proposal);

        problemStatementText = findViewById(R.id.problem_statement3);
        descriptionText = findViewById(R.id.description3);
        proposal = findViewById(R.id.proposal);
        milestonePostModel = new MilestonePostModel();
        billModel = new BillModel();
        fundingAgencyPSPostModel = new FundingAgencyPSPostModel();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        listdata = new ArrayList<MilestonePostModel>();
        bills = new ArrayList<BillModel>();
        furtherList = new ArrayList<MilestoneFurtherPostModel>();

        expectedBudgetText = findViewById(R.id.expected_budget);
        expectedDurationText = findViewById(R.id.expected_duration);

        addBill = findViewById(R.id.bill_button);
        addMilestone = findViewById(R.id.milestone_button);
        submit = findViewById(R.id.submit3);
        r = findViewById(R.id.relativeLayout2);
        r2 = findViewById(R.id.relativeLayout);
        proposalId = findViewById(R.id.proposalID);

        fundingAgencyPSPostModel = (FundingAgencyPSPostModel) getIntent().getSerializableExtra("problemStatement");

//        problemStatementText.setText(fundingAgencyPSPostModel.getProblemStatement());
//
         nameReference = FirebaseDatabase.getInstance().getReference("Users").child("Hei").child(firebaseUser.getUid());
//        databaseReference2=FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency").child(fundingAgencyPSPostModel.getUid()).child("Posts").child(fundingAgencyPSPostModel.psId).child("Proposals");

        milestonePostModel = new MilestonePostModel();

        firebaseStorage = FirebaseStorage.getInstance();
//        documentReference = FirebaseFirestore.getInstance().collection("Hei Proposals").document(fundingAgencyPSPostModel.getId());

      problemStatementText.setText(fundingAgencyPSPostModel.problemStatement);
      descriptionText.setText(fundingAgencyPSPostModel.description);
        addBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMilestoneEditText();
                addDurationEditText();
                addBudgetEditText();
                addDeliverableEditText();
            }
        });

        addMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBillEditText();
                addBillAmountEditText();
            }
        });

        proposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                nameReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        HeiPostModel heiPostModel = (HeiPostModel) snapshot.getValue(HeiPostModel.class);
//                        name = heiPostModel.nameOfHei;
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

//                nameReference.child("nameOfHei").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if(task.isSuccessful()){
//                            name = String.valueOf(task.getResult().getValue());
//                        }
//                        else{
//                            Toast.makeText(HeiProposal.this, "unable to fetch name!!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

                String problemStatement = fundingAgencyPSPostModel.problemStatement;
                String description = fundingAgencyPSPostModel.description;

                String expectedDuration = expectedDurationText.getText().toString();
                String expectedBudget = expectedBudgetText.getText().toString();


                HeiProposalModel heiProposalModel = new HeiProposalModel();
                heiProposalModel.setProblemStatement(problemStatement);
                heiProposalModel.setDescription(description);
                heiProposalModel.setExpectedDuration(expectedDuration);
                heiProposalModel.setExpectedBudget(expectedBudget);
                heiProposalModel.setMilestones(listdata);
                heiProposalModel.setBills(bills);
                heiProposalModel.setHeiScore(String.valueOf(random));
                heiProposalModel.setFaUid(fundingAgencyPSPostModel.uid);
                heiProposalModel.setPsId(fundingAgencyPSPostModel.psId);
                heiProposalModel.setHeiName("UGC Agency");
//                heiProposalModel.setBudgetMap(map);
                heiProposalModel.setStatus("ToBeVerified");
                heiProposalModel.setProposalId(proposalId.getText().toString());
//                heiProposalModel.setHeiName(name);



                uploadProposalPdf(proposalUri, heiProposalModel);




//                documentReference = FirebaseFirestore.getInstance().collection("FundingAgencyPost").document(fundingAgencyPSPostModel.psId).collection("proposals").document(heiProposalModel.psId);

//                fundingAgencyPSPostModel.proposalModels.add(heiProposalModel);

//                FirebaseDatabase.getInstance().getReference("Posts").child("FundingAgencyPost").child(fundingAgencyPSPostModel.id).setValue(fundingAgencyPSPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(HeiProposal.this, "fundingagencyps updated..", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(HeiProposal.this, "unable to update funding agency ps", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(heiProposalModel.proposalId);

                databaseReference.setValue(heiProposalModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(HeiProposal.this, "UPLOADED..!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(HeiProposal.this,"Failed to upload",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                databaseReference2.setValue(heiProposalModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(HeiProposal.this, "UPLOADED..!!!", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(HeiProposal.this,"Failed to upload",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                databaseReference2=FirebaseDatabase.getInstance().getReference("Users").child("Hei").child(heiPostModel.getUid()).child("Proposals");
//                databaseReference2.setValue(heiProposalModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(HeiProposal.this, "UPLOADED..!!!", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(HeiProposal.this,"Failed to upload",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                for(int i=0;i<furtherList.size();i++){
                    dataRef = FirebaseDatabase.getInstance().getReference("Proposals").child(fundingAgencyPSPostModel.psId).child("budgetDivision").child(furtherList.get(i).name);
                    dataRef.setValue(furtherList.get(i).value);
                }
            }
        });

    }

    private void addBudgetEditText() {

        EditText editText = new EditText(getApplicationContext());
        editText.setHint("Budget amount"+counter1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r.getChildAt(r.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
        editText.setId(idLastChild+1);
        // set layout params
        editText.setLayoutParams(params);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER );
        r.addView(editText, params);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                budget =editText.getText().toString();
                milestonePostModel.setBudget(budget);
            }
        });

    }

    private void addBillAmountEditText() {

        EditText editText = new EditText(getApplicationContext());
        editText.setHint("Bill amount"+counter1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r2.getChildAt(r2.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
        editText.setId(idLastChild+1);
        // set layout params
        editText.setLayoutParams(params);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER );
        r2.addView(editText, params);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                value =editText.getText().toString();
                furtherList.add(new MilestoneFurtherPostModel(name,value));

            }
        });

        counter1++;

    }


    private void uploadProposalPdf(Uri proposalUri, HeiProposalModel heiProposalModel) {

        StorageReference sr = firebaseStorage.getReference().child("HeiProposal" + "uid" + "proposaldoc.pdf");
        sr.putFile(proposalUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            heiProposalModel.setProposalDocumentUri(uri.toString());
                            Toast.makeText(getApplicationContext(), "Proposal Pdf uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed to upload proposal!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,PICK_PDF),13);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        proposalUri = data.getData();
        proposal.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));
    }

    private void addBillEditText() {

        EditText editText = new EditText(getApplicationContext());
        editText.setHint("name of party/person"+counter1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r2.getChildAt(r2.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
        params.addRule(RelativeLayout.BELOW,idLastChild);
        editText.setId(idLastChild+1);
        // set layout params
        editText.setLayoutParams(params);
        r2.addView(editText, params);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = editText.getText().toString();
//                billModel.setNameOfPerson(name);
            }
        });

    }

    private void addDurationEditText() {


        EditText editText = new EditText(getApplicationContext());
        editText.setHint("Duration"+counter);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r.getChildAt(r.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
//        editText.setHintTextColor(Color.WHITE);
        params.addRule(RelativeLayout.BELOW,idLastChild);
        editText.setId(idLastChild+1);
        // set layout params
        editText.setLayoutParams(params);
        r.addView(editText, params);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String duration;
                duration = editText.getText().toString();
                milestonePostModel.setDuration(duration);
                milestonePostModel.setPsId(fundingAgencyPSPostModel.psId);
            }
        });

    }

    private void addMilestoneEditText() {


        EditText editText = new EditText(getApplicationContext());
        editText.setHint("Milestone"+counter);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r.getChildAt(r.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
//        editText.setHintTextColor(Color.WHITE);
        params.addRule(RelativeLayout.BELOW,idLastChild);
        editText.setId(idLastChild+1);
        // set layout params
        editText.setLayoutParams(params);
        r.addView(editText, params);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String milestone;
                milestone = editText.getText().toString();
                milestonePostModel.setMilestone(milestone);
            }
        });



    }

    private void addDeliverableEditText(){


        EditText editText = new EditText(getApplicationContext());
        editText.setHint("Deliverables"+counter);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r.getChildAt(r.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
        params.addRule(RelativeLayout.BELOW,idLastChild);
        editText.setId(idLastChild+1);
        editText.setLayoutParams(params);
        r.addView(editText, params);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String deliverables;
                deliverables = editText.getText().toString();
                milestonePostModel.setDeliverables(deliverables);
                milestonePostModel.setStatus("ToBeVerified");
                milestonePostModel.setPs(fundingAgencyPSPostModel.problemStatement);
                milestonePostModel.setMilestoneNumber(String.valueOf(listdata.size()+1));
                listdata.add(milestonePostModel);
            }
        });
        counter++;
    }

}