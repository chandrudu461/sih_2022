package com.example.afinal;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.j2objc.annotations.ObjectiveCName;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MilestoneFurther extends Fragment {

    public TextView nameOfMilestone;
    public TextView deadlineMilestone;
    public String psId,user;
    public StorageReference storageReference;
    public TextView deliverableMilestone;
    public DatabaseReference databaseReference,dataRef;
    public TextView uploadDeliverables;
    public Uri pdfUri,downloadUri;
    public Button downloadButton;
    public MilestonePostModel milestonePostModel;
    public Button submitMilestone,payButton;

    RecyclerView recyclerView;
    RecyclerAdapterMilestoneFurther recyclerAdapterMilestoneFurther;
    RecyclerAdapterFundingAgencyMilestoneFurther recyclerAdapterFundingAgencyMilestoneFurther;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_milestone_further, container, false);

        nameOfMilestone=view.findViewById(R.id.milestone_name);
        deadlineMilestone=view.findViewById(R.id.deadline_mentioned);
        deliverableMilestone=view.findViewById(R.id.deliverables_mentioned);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.milestone_todo_recycler);
        sharedPreferences = getContext().getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);
        psId = sharedPreferences.getString("psId","psId");
        user = sharedPreferences.getString("userType","default");
        uploadDeliverables = view.findViewById(R.id.text_view_uploadDeliverables);
        submitMilestone = view.findViewById(R.id.btn_uploadMilestone);
        milestonePostModel= (MilestonePostModel) getArguments().getSerializable("presentMilestone");
        downloadButton = view.findViewById(R.id.download_button);
        payButton = view.findViewById(R.id.btn_pay);

        uploadDeliverables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadDeliverablesPdf();
            }
        });

        submitMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user.equals("HEI")){
                    uploadPdf(pdfUri);
                }
                if(user.equals("FundingAgency")){
                    uploadDeliverables.setVisibility(View.INVISIBLE);
                    downloadButton.setVisibility(View.VISIBLE);
                }

            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),PaymentForm.class);
                intent.putExtra("presentMilestone",milestonePostModel);
                startActivity(intent);
            }
        });



//        nameOfMilestone.setText(temp.milestone);
//        deadlineMilestone.setText(temp.duration);
//        deliverableMilestone.setText(temp.deliverables);

//        submitMilestone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(milestonePostModel.psId).child("budgetDivision");
        ArrayList<MilestoneFurtherPostModel> milestoneFurtherPostModelArrayList = new ArrayList<MilestoneFurtherPostModel>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                HashMap<String,String>  map = new HashMap<>();
//                map = snapshot.getValue(HashMap.class);
                milestoneFurtherPostModelArrayList.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        MilestoneFurtherPostModel milestoneFurtherPostModel = new MilestoneFurtherPostModel();
                        milestoneFurtherPostModel.name = postSnapshot.getKey();
//                        milestoneFurtherPostModel.value =Long.parseLong(postSnapshot.getValue(String.class));
                        milestoneFurtherPostModel.value = postSnapshot.getValue(String.class);
                        milestoneFurtherPostModelArrayList.add(milestoneFurtherPostModel);
                    }
                    recyclerAdapterMilestoneFurther = new RecyclerAdapterMilestoneFurther(getContext(), milestoneFurtherPostModelArrayList);
                    recyclerView.setAdapter(recyclerAdapterMilestoneFurther);
                    recyclerView.setLayoutManager(layoutManager);

//                if(user.equals("FundingAgency")){
//                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                        MilestoneFurtherPostModel milestoneFurtherPostModel = new MilestoneFurtherPostModel();
//                        milestoneFurtherPostModel.name = postSnapshot.getKey();
//                        milestoneFurtherPostModel.value = postSnapshot.getValue(String.class);
//                        milestoneFurtherPostModelArrayList.add(milestoneFurtherPostModel);
//                    }
//                    recyclerAdapterFundingAgencyMilestoneFurther = new RecyclerAdapterFundingAgencyMilestoneFurther(getContext(), milestoneFurtherPostModelArrayList);
//                    recyclerView.setAdapter(recyclerAdapterFundingAgencyMilestoneFurther);
//                    recyclerView.setLayoutManager(layoutManager);
//                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }

//    private void downloadPdf() {
//
//
//
//
//    }

    private void uploadPdf(Uri uri) {
        StorageReference sr = FirebaseStorage.getInstance().getReference().child("PDFs/"  + FirebaseAuth.getInstance().getCurrentUser().getUid()+"/" + "docProof.pdf");
        sr.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadUri = uri;

                            dataRef = FirebaseDatabase.getInstance().getReference("Proposals").child(psId).child("pdfUri");
                            dataRef.setValue(downloadUri.toString());

                            Toast.makeText(getContext(), "Hei Proof uploaded successfully!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "Something went Wrong..!!! Pdf not uploaded!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadDeliverablesPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,13);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 13 && resultCode == RESULT_OK && data != null && data.getData() != null){
            pdfUri = data.getData();
        }
    }
}