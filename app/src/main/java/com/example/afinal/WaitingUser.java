
package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaitingUser extends AppCompatActivity {

    String reason;
    TextView waitingMeassage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_user);

        waitingMeassage = findViewById(R.id.waitingMessage);


        waitingMeassage.setText("Your Profile is under evaluation!!!");

//
//        final boolean[] flag = {true};
//        while(flag[0])
//        {
//            DatabaseReference r = FirebaseDatabase.getInstance().getReference().child("Users").child("Hei").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("declineReason");
//            r.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    reason = (String) snapshot.getValue();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//            DatabaseReference dr = FirebaseDatabase.getInstance().getReference().child("Users").child("Hei").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("verify");
//            dr.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                HeiPostModel h = (HeiPostModel) snapshot.getValue();
//                    String h = (String) snapshot.getValue();
//                    if (h.equals("pending")) {
//                        waitingMeassage.setText("Your Profile is under evaluation!!!");
//
//                        // Dispaly lotttie file
//                    }
//
//                    if (h.equals("accept")) {
//                        flag[0] = false;
//                    } else if (h.equals("decline")) {
//                        waitingMeassage.setText(reason);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//            if(flag[0] == false){
//                break;
//            }
//        }
//        if (flag[0] == false) {
//
//            startActivity(new Intent(WaitingUser.this, DashBoardActivity.class));
//        }
    }
    @Override
    public void onResume() {
        super.onResume();


        waitingMeassage = findViewById(R.id.waitingMessage);


            DatabaseReference r = FirebaseDatabase.getInstance().getReference().child("Users").child("Hei").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("declineReason");
            r.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    reason = (String) snapshot.getValue();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            DatabaseReference dr = FirebaseDatabase.getInstance().getReference().child("Users").child("Hei").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("verify");
            dr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                HeiPostModel h = (HeiPostModel) snapshot.getValue();
                    String h = (String) snapshot.getValue();
                    if (h.equals("pending")) {
                        waitingMeassage.setText("Your Profile is under evaluation!!!");

                        // Dispaly lotttie file
                    }

                    if (h.equals("accepted")) {
                        startActivity(new Intent(WaitingUser.this, DashBoardActivity.class));
                    } else if (h.equals("declined")) {
                        waitingMeassage.setText(reason);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }
}