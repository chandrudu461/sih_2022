package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProposalValidation extends Fragment {

    RecyclerView recyclerProposalHei;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterProposalHei recyclerAdapter;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    private HeiProposalModel heiProposalModel;
    FundingAgencyPSPostModel fundingAgencyPSPostModel;
    CollectionReference collectionReference;

//    public static ProposalValidation newInstance(FundingAgencyPSPostModel fundingAgencyPSPostModel) {
//        ProposalValidation fragment = new ProposalValidation();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("problemStatement", fundingAgencyPSPostModel);
//        fragment.setArguments(bundle);
//
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_proposal_validation);

        fundingAgencyPSPostModel = new FundingAgencyPSPostModel();

//        Bundle b = getArguments();

//        fundingAgencyPSPostModel = (FundingAgencyPSPostModel)  b.getSerializable("problemStatement");

        View  view = inflater.inflate(R.layout.fragment_proposal_validation, container, false);

        mAuth = FirebaseAuth.getInstance();
        recyclerProposalHei = view.findViewById(R.id.recycler_proposal_validation);
        layoutManager=new LinearLayoutManager(getContext());
//        databaseReference = FirebaseDatabase.getInstance().getReference("Posts").child("FundingAgencyPosts").child(fundingAgencyPSPostModel.id).child("proposals");

        databaseReference = FirebaseDatabase.getInstance().getReference("Proposals");
//                .child(heiProposalModel.psId);

        List<HeiProposalModel> heiProposalList = new ArrayList<HeiProposalModel>();

//        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                for(DocumentChange document : value.getDocumentChanges()){
//                    HeiProposalModel agency = document.getDocument().toObject(HeiProposalModel.class);
//                    heiProposalList.add(agency);
//                }
//
//                recyclerAdapter = new RecyclerAdapterProposalHei(getContext(),heiProposalList);
//                recyclerProposalHei.setAdapter(recyclerAdapter);
//                recyclerProposalHei.setLayoutManager(layoutManager);
//
//            }
//        });

        //working one>>>
        databaseReference.orderByChild("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                heiProposalList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    HeiProposalModel agency = postSnapshot.getValue(HeiProposalModel.class);
//                    if(agency.psId.equals(fundingAgencyPSPostModel.psId)){
//                        heiProposalList.add(agency);
//                    }
                    heiProposalList.add(agency);

                }
                recyclerAdapter = new RecyclerAdapterProposalHei(getContext(),heiProposalList);
                recyclerProposalHei.setAdapter(recyclerAdapter);
                recyclerProposalHei.setLayoutManager(layoutManager);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Query myTopPostsQuery = databaseReference.child("Proposals")
//                .orderByChild("score");
//
//        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot postSnapshot : snapshot.getChildren()){
//                    HeiProposalModel agency = postSnapshot.getValue(HeiProposalModel.class);
//                    heiProposalList.add(agency);
//                }
//                recyclerAdapter = new RecyclerAdapterProposalHei(getContext(),heiProposalList);
//                recyclerProposalHei.setAdapter(recyclerAdapter);
//                recyclerProposalHei.setLayoutManager(layoutManager);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        return view;
    }
}