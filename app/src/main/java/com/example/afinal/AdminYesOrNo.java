package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

import java.io.Serializable;

public class AdminYesOrNo extends AppCompatActivity {

    Button accept,decline;
    TextView t1,t2,t3,t4;
    DatabaseReference databaseReference;
    Button submit;
    EditText textBox;
    ImageView i;
    String user;
    FundingAgencyPostModel presentUser;
//    String user = (String) getIntent().getStringExtra("userType");
    SharedPreferences sharedPreferences;
    private HeiPostModel HeiPresentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.sharedPreferences = getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_yes_or_no);

        if (getIntent() == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getString("userType");
                if(user.equals("FundingAgency")) {
                    presentUser = (FundingAgencyPostModel) getIntent().getSerializableExtra("UserToBeVerified");
                }
                else if(user.equals("Hei")){
                    HeiPresentUser = (HeiPostModel)getIntent().getSerializableExtra("Hei");
                }
            }
        } else {
            user = (String) getIntent().getStringExtra("userType");
            if(user.equals("FundingAgency")) {
                presentUser = (FundingAgencyPostModel) getIntent().getSerializableExtra("UserToBeVerified");
            }
            else if(user.equals("Hei")){
                HeiPresentUser = (HeiPostModel)getIntent().getSerializableExtra("UserToBeVerified");
            }
        }
        if (user.equals("FundingAgency") ) {
//            presentUser = (FundingAgencyPostModel) getIntent().getSerializableExtra("UserToBeVerified");
//        }
//        else if (user == "Hei") {
//            presentUser = (HeiPostModel) getIntent().getSerializableExtra("UserToBeVerified");
//        }

            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency").child(presentUser.getUid());
//                .child(presentUser.getUid());

            i = findViewById(R.id.view_profilepic);
            t1 = findViewById(R.id.txtView_ps);
            t2 = findViewById(R.id.ps_description);
            t3 = findViewById(R.id.funding_agency_name);
            t4 = findViewById(R.id.admin_budget);

            submit = findViewById(R.id.ps);
            textBox = findViewById(R.id.textBox);


            t1.setText(presentUser.agencyType);
            t2.setText(presentUser.nameOfFundingAgency);
            t3.setText(presentUser.nameOfFunder);
            t4.setText(presentUser.yearOfEstablishment);

            accept = findViewById(R.id.accept_offer);
            decline = findViewById(R.id.decline_offer);

            Picasso.with(this).load(presentUser.imageUri).resize(100, 100).centerCrop().into(i);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    presentUser.setVerify("verified");
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
//                    presentUser.setVerify("decline");
                    textBox.setHint("Enter the reason to decline the request!!!");

//                finish();
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textBox.getHint() == "Enter the razor pay id for the user") {
                        String textBoxValue = textBox.getText().toString().trim();
                        presentUser.setRazorPayId(textBoxValue);
                        presentUser.setVerify("accepted");
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
                    if (textBox.getHint() == "Enter the reason to decline the request!!!") {
                        String textBoxValue = textBox.getText().toString().trim();
                        presentUser.setDeclineReason(textBoxValue);
                        presentUser.setVerify("declined");
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
        else{

//            HeiPresentUser = (HeiPostModel) getIntent().getSerializableExtra("userToBeVerified");

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Hei").child(HeiPresentUser.getUid());

            i = findViewById(R.id.view_profilepic);
            t1 = findViewById(R.id.txtView_ps);
            t2 = findViewById(R.id.ps_description);
            t3 = findViewById(R.id.funding_agency_name);
            t4 = findViewById(R.id.admin_budget);

            submit = findViewById(R.id.ps);
            textBox = findViewById(R.id.textBox);

            t1.setText(HeiPresentUser.nameOfHei);
            t2.setText(HeiPresentUser.yearOfEstablishment);
            t3.setText(HeiPresentUser.aicteCode);
            t4.setText(HeiPresentUser.link);

            accept = findViewById(R.id.accept_offer);
            decline = findViewById(R.id.decline_offer);


            Picasso.with(this).load(HeiPresentUser.imageUri).resize(100, 100).centerCrop().into(i);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                    textBox.setHint("Enter the reason to decline the request!!!");

//                finish();
                }
            });


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textBox.getHint() == "Enter the razor pay id for the user") {
                        String textBoxValue = textBox.getText().toString().trim();
                        HeiPresentUser.setVerify("accepted");
                        HeiPresentUser.setRazorPayId(textBoxValue);
                        databaseReference.setValue(HeiPresentUser).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                    if (textBox.getHint() == "Enter the reason to decline the request!!!") {
                        String textBoxValue = textBox.getText().toString().trim();
                        HeiPresentUser.setVerify("declined");
                        HeiPresentUser.setDeclineReason(textBoxValue);
                        databaseReference.setValue(HeiPresentUser).addOnSuccessListener(new OnSuccessListener<Void>() {
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
}
