package com.example.afinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminHeiRelated extends AppCompatActivity {
    RecyclerView recyclerAdmin;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterHei recyclerAdapter;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hei_related);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child("Hei");

        recyclerAdmin = findViewById(R.id.recyclerViewHei_Allevents);
        layoutManager = new LinearLayoutManager(this);

        List<HeiPostModel> heiList = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange( DataSnapshot snapshot) {
                heiList.clear();

                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    HeiPostModel agency = postSnapshot.getValue(HeiPostModel.class);
                    heiList.add(agency);
                }

                recyclerAdapter = new RecyclerAdapterHei(getApplicationContext(),heiList);
                recyclerAdmin.setAdapter(recyclerAdapter);
                recyclerAdmin.setLayoutManager(layoutManager);

            }



            public void onCancelled( DatabaseError error) {
                System.out.println("The thread Failed: " + error.getMessage());
            }
        });

    }
}
