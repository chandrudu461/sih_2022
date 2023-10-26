package com.example.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HeiProfile extends Fragment {
    DatabaseReference databaseReference;
    TextView nameText,yearText,aicteCodeText,stateText,DistrictText,naacText,nbaText,nirfText,websiteText;
    Button viewDetails;
    ImageView image;
    HeiPostModel heiPostModel=new HeiPostModel();
  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hei_profile, container, false);

//        heiPostModel  = (HeiPostModel) getIntent().getSerializableExtra("presentHeiModel");

        nameText = view.findViewById(R.id.profile_hei_name);
        yearText = view.findViewById(R.id.hei_profile_year);
        aicteCodeText = view.findViewById(R.id.hei_profile_aicte_code);
        stateText = view.findViewById(R.id.hei_profile_state);
        DistrictText = view.findViewById(R.id.hei_profile_district);
        naacText = view.findViewById(R.id.hei_profile_nba);
        nbaText = view.findViewById(R.id.hei_profile_naac);
        nirfText = view.findViewById(R.id.hei_profile_nirf);
        websiteText = view.findViewById(R.id.hei_profile_website_link);

        image = view.findViewById(R.id.image_view_hei_profile);

        viewDetails = view.findViewById(R.id.view_details);

      SharedPreferences sharedPreferences= getContext().getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);

      viewDetails.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              viewDetailsFunction();
          }
      });

      String user_UID=sharedPreferences.getString("HEI_uid","User_Hei_Empty");

      if(user_UID.equals("User_Hei_Empty")){
          databaseReference=  FirebaseDatabase.getInstance().getReference("Users").child("Hei").child("WYxZeKlCuqY2bjc1zQ2UWEUnZf33");
      }
      else{
        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child("Hei").child(user_UID);
      }
      //      Toast.makeText(view.getContext(), "user"+user_UID, Toast.LENGTH_SHORT).show();

      databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                heiPostModel = task.getResult().getValue(HeiPostModel.class);
            }
        });
//        databaseReference.get().addOnCompleteListener(task -> heiPostModel=task.getResult().getValue(HeiPostModel.class));
//      databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//          @Override
//          public void onDataChange(DataSnapshot dataSnapshot) {
//              Log.d("LOGGING", "Entered");
//              heiPostModel=dataSnapshot.getValue(HeiPostModel.class);
//
//          }
//          @Override
//          public void onCancelled(DatabaseError e) {
//              Log.d("Error", e.getMessage());
//          }
//      });
        return view;
    }

    private void viewDetailsFunction() {

//        nameText.setText(heiPostModel.getNameOfHei());
//        yearText.setText(heiPostModel.getYearOfEstablishment());
//        aicteCodeText.setText(heiPostModel.getAicteCode());
//        stateText.setText(heiPostModel.getSelectedstate());
//        DistrictText.setText(heiPostModel.getSelectedDistrict());
//        naacText.setText(heiPostModel.getNaac());
//        nbaText.setText(heiPostModel.getNba());
//        nirfText.setText(heiPostModel.getNirf());
//        websiteText.setText(heiPostModel.getLink());

        Picasso.with(getContext()).load(Uri.parse(heiPostModel.imageUri)).into(image);

    }
}

