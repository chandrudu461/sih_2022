package com.example.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HeiProposal extends Fragment {

    private static final CharSequence PICK_PDF = "pdf";
    TextView problemStatement,description,proposal;
    EditText expectedDurationText,expectedBudgetText;
    Button addMilestone,submit,addBill;
    private int counter=1;
    ArrayList<MilestonePostModel> listdata;
    ArrayList<BillModel> bills;
    BillModel billModel;
    RelativeLayout r,r2;
    private MilestonePostModel milestonePostModel;
    private Uri proposalUri;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference databaseReference;
    private int counter1 = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hei_proposal, container, false);

        problemStatement = view.findViewById(R.id.problemStatement);
        description = view.findViewById(R.id.description3);
        proposal = view.findViewById(R.id.proposal);
        milestonePostModel = new MilestonePostModel();
        billModel = new BillModel();

        expectedBudgetText = view.findViewById(R.id.expected_budget);
        expectedDurationText = view.findViewById(R.id.expected_duration);

        addBill = view.findViewById(R.id.bill_button);
        addMilestone = view.findViewById(R.id.milestone_button);
        submit = view.findViewById(R.id.submit3);
        r = view.findViewById(R.id.relativeLayout);
        r2 = view.findViewById(R.id.relativeLayout2);

        firebaseStorage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Hei Proposals").child("uid");

//      problemStatement.setText();
//      description.setText();
        addMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMilestoneEditText();
                addDurationEditText();
                addDeliverableEditText();
            }
        });

        addBill.setOnClickListener(new View.OnClickListener() {
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

                String problemStatement  = "";
                String description = "";

                String expectedDuration = expectedDurationText.getText().toString();
                String expectedBudget = expectedBudgetText.getText().toString();

                ArrayList milestones = listdata;


                HeiProposalModel heiProposalModel = new HeiProposalModel();
                heiProposalModel.setProblemStatement(problemStatement);
                heiProposalModel.setDescription(description);
                heiProposalModel.setExpectedDuration(expectedDuration);
                heiProposalModel.setExpectedBudget(expectedBudget);
                heiProposalModel.setMilestones(listdata);

                uploadProposalPdf(proposalUri,heiProposalModel);

                databaseReference.setValue(heiProposalModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "proposal data sent to database!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(),"Failed to send proposal data to database!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // change the intent
            }
        });

        return view;
    }

    private void addBillAmountEditText() {

        EditText editText = new EditText(getContext());
        editText.setHint("Bill amount"+counter1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r.getChildAt(r2.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
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
                String billAmount;
                billAmount = editText.getText().toString();
                billModel.setBillAmount(billAmount);
                bills.add(billModel);
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
                            Toast.makeText(getContext(), "Proposal Pdf uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getContext(), "Failed to upload proposal!!!", Toast.LENGTH_SHORT).show();
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

        EditText editText = new EditText(getContext());
        editText.setHint("name of party/person"+counter1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= r.getChildAt(r2.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
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
                String name;
                name = editText.getText().toString();
                billModel.setNameOfPerson(name);

            }
        });

    }

    private void addDurationEditText() {


        EditText editText = new EditText(getContext());
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
            }
        });

    }

    private void addMilestoneEditText() {


        EditText editText = new EditText(getContext());
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


        EditText editText = new EditText(getContext());
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
                listdata.add(milestonePostModel);
            }
        });
        counter++;
    }

}