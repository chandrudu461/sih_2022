package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminYesOrNo extends AppCompatActivity {

    FundingAgencyPostModel presentUser;
    Button accept,decline;
    TextView t1,t2,t3,t4;
    DatabaseReference databaseReference;
    Button submit;
    EditText textBox;
    ImageView i;
    String user = (String) getIntent().getSerializableExtra("UserType");
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_yes_or_no);

        if(user == "FundingAgency"){
            presentUser = (FundingAgencyPostModel)getIntent().getSerializableExtra("UserToBeVerified");
        } else if (user == "Hei") {
            presentUser = (FundingAgencyPostModel) getIntent().getSerializableExtra("UserToBeVerified");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency").child(presentUser.getUid());
//                .child(presentUser.getUid());

        i = findViewById(R.id.view_profilepic);
        t1 = findViewById(R.id.txtView_typeOfUser);
        t2 = findViewById(R.id.user_name);
        t3 = findViewById(R.id.founder_name);
        t4 = findViewById(R.id.txtView_yearofEst);

        submit = findViewById(R.id.adminSubmit);
        textBox= findViewById(R.id.textBox);


        t1.setText(presentUser.agencyType);
        t2.setText(presentUser.nameOfFundingAgency);
        t3.setText(presentUser.nameOfFunder);
        t4.setText(presentUser.yearOfEstablishment);

        accept = findViewById(R.id.accept);
        decline = findViewById(R.id.decline);

        Picasso.with(this).load(presentUser.imageUri).resize(100,100).centerCrop().into(i);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentUser.setVerified(true);
                textBox.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                textBox.setHint("Enter the razor pay id for the user");

//                finish();
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textBox.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                presentUser.setVerified(false);
                presentUser.setIsDeclined(true);
                textBox.setHint("Enter the reason to decline the request!!!");

//                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textBox.getHint() == "Enter the razor pay id for the user") {
                    String textBoxValue = textBox.getText().toString().trim();
                    presentUser.setRazorPayId(textBoxValue);
                    databaseReference.setValue(presentUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AdminYesOrNo.this, "accept  Uploaded successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminYesOrNo.this, "failed to upload@!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if(textBox.getHint() == "Enter the reason to decline the request!!!"){
                    String textBoxValue = textBox.getText().toString().trim();
                    presentUser.setDeclineReason(textBoxValue);
                    databaseReference.setValue(presentUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AdminYesOrNo.this, "decline  Uploaded successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminYesOrNo.this, "failed to upload@!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}