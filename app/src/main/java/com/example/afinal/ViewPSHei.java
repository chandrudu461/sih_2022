package com.example.afinal;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewPSHei extends Fragment {

    RecyclerView recyclerPSHei;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterPSFundingAgency recyclerAdapter;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    DocumentReference documentReference;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String firebaseUid;
    FirebaseAuth mAuth;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_p_s_hei, container, false);


        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        // change the reference to ps posts path
        firebaseFirestore = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Posts").child("FundingAgencyPost");
        collectionReference = firebaseFirestore.collection("FundingAgencyPost");
        mContext = getContext();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseUid = firebaseUser.getUid();

//        documentReference = firebaseFirestore.document("");

        recyclerPSHei = view.findViewById(R.id.recycler_ps_hei);
        layoutManager=new LinearLayoutManager(mContext);


        List<FundingAgencyPSPostModel> fundingAgencyPSList = new ArrayList<FundingAgencyPSPostModel>();
//        documentReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                fundingAgencyPSList.clear();
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    FundingAgencyPSPostModel agency = postSnapshot.getValue(FundingAgencyPSPostModel.class);
//                    fundingAgencyPSList.add(agency);
//
//                    // here you can access to name property like university.name
//
//                }
//                recyclerAdapter = new RecyclerAdapterPSFundingAgency(getContext(),fundingAgencyPSList);
//                recyclerPSHei.setAdapter(recyclerAdapter);
//                recyclerPSHei.setLayoutManager(layoutManager);
//
//            }

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                fundingAgencyPSList.clear();
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    if(postSnapshot.getValue(FundingAgencyPSPostModel.class).problemStatement.equals("demo")){
//                        continue;
//                    }
//                    FundingAgencyPSPostModel agency = postSnapshot.getValue(FundingAgencyPSPostModel.class);
//                    fundingAgencyPSList.add(agency);
//
//                    // here you can access to name property like university.name
//
//                }
//                recyclerAdapter = new RecyclerAdapterPSFundingAgency(getContext(),fundingAgencyPSList);
//                recyclerPSHei.setAdapter(recyclerAdapter);
//                recyclerPSHei.setLayoutManager(layoutManager);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                for(DocumentChange document : value.getDocumentChanges()){
//                    FundingAgencyPSPostModel agency = document.getDocument().toObject(FundingAgencyPSPostModel.class);
//                    fundingAgencyPSList.add(agency);
//                }
//
//                recyclerAdapter = new RecyclerAdapterPSFundingAgency(getContext(),fundingAgencyPSList);
//                recyclerPSHei.setAdapter(recyclerAdapter);
//                recyclerPSHei.setLayoutManager(layoutManager);
//
//                recyclerAdapter.notifyDataSetChanged();
//            }
//        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Posts");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fundingAgencyPSList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    FundingAgencyPSPostModel agency = postSnapshot.getValue(FundingAgencyPSPostModel.class);
                    fundingAgencyPSList.add(agency);
                }
                recyclerAdapter = new RecyclerAdapterPSFundingAgency(getContext(),fundingAgencyPSList);
                recyclerPSHei.setAdapter(recyclerAdapter);
                recyclerPSHei.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}