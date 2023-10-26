package com.example.afinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class FundingAgencyUploadedPS extends Fragment {


    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    RecyclerView recyclerPSHei;
    RecyclerView.LayoutManager layoutManager;
    CollectionReference collectionReference;
    RecyclerAdapterPSFundingAgency recyclerAdapter;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_funding_agency_uploaded_p_s, container, false);
        //        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String firebaseUid = mAuth.getUid();
        collectionReference = FirebaseFirestore.getInstance().collection("FundingAgencyPost");
        // change the reference to ps posts path
//        firebaseFirestore = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
//        documentReference = firebaseFirestore.collection("FundingAgencyPost").document();
        recyclerPSHei = view.findViewById(R.id.recycler_uploaded_ps);

        layoutManager=new LinearLayoutManager(getContext());

        List<FundingAgencyPSPostModel> fundingAgencyPSList = new ArrayList<FundingAgencyPSPostModel>();



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fundingAgencyPSList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    FundingAgencyPSPostModel agency = postSnapshot.getValue(FundingAgencyPSPostModel.class);
                    if(agency.getUid().equals(firebaseUid)){
                        fundingAgencyPSList.add(agency);
                    }
                }
                recyclerAdapter = new RecyclerAdapterPSFundingAgency(getContext(),fundingAgencyPSList);
                recyclerPSHei.setAdapter(recyclerAdapter);
                recyclerPSHei.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                for(DocumentChange document : value.getDocumentChanges()){
//                    FundingAgencyPSPostModel agency = document.getDocument().toObject(FundingAgencyPSPostModel.class);
//                    fundingAgencyPSList.add(agency);
//                }
//
//                recyclerAdapter = new RecyclerAdapterPSFundingAgency(getContext(),fundingAgencyPSList);
//                recyclerPSHei.setAdapter(recyclerAdapter);
//                recyclerPSHei.setLayoutManager(layoutManager);
//
//            }
//        });


        return view;
    }
}