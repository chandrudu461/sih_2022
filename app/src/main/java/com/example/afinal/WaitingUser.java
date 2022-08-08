
package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaitingUser extends AppCompatActivity {

    TextView waitingMeassage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_user);

        waitingMeassage = findViewById(R.id.waitingMessage);

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference().child("Users").child("Hei").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("isVerified");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                HeiPostModel h = (HeiPostModel) snapshot.getValue();
                Boolean h = (Boolean) snapshot.getValue();
                if (h == false){
                    waitingMeassage.setText("Your Profile is under evaluation!!!");

                    // Dispaly lotttie file
                }

                if(h == true){
                    startActivity(new Intent(WaitingUser.this, DashBoardActivity.class));
                }
//                else if(h == true) {
//                    waitingMeassage.setText(h.declinedReason);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}