package com.example.afinal;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FundingAgencyProfile extends Fragment {

    TextView typeOfAgency,nameOfAgency,year,nameOfFounder,phoneNumber,portFolioLink,socialLink,state,district;
//    FundingAgencyPostModel fundingAgencyPostModel;
ArrayList<MilestoneFurtherPostModel> milestoneFurtherModels;
    FundingAgencyPostModel fundingAgencyPostModel;
    Button showDetails;
    String firebaseUserUid;
    ImageView profilePic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_funding_agency_profile, container, false);

        typeOfAgency = view.findViewById(R.id.fa_profile_agency_type);
        year = view.findViewById(R.id.fa_profile_year);
        nameOfAgency = view.findViewById(R.id.fa_profile_funding_agency);
        nameOfFounder = view.findViewById(R.id.fa_profile_name_of_founder);
        phoneNumber = view.findViewById(R.id.fa_profile_phone_number);
        portFolioLink = view.findViewById(R.id.fa_portfolio_link);
        socialLink = view.findViewById(R.id.fa_profile_social_link);
        state = view.findViewById(R.id.fa_profile_state);
        district = view.findViewById(R.id.fa_profile_district);
        firebaseUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        profilePic = view.findViewById(R.id.image_view_fa_profile);

        showDetails = view.findViewById(R.id.show_details_btn);


        fundingAgencyPostModel = new FundingAgencyPostModel();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Funding Agency").child(firebaseUserUid);
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                fundingAgencyPostModel = snapshot.getValue(FundingAgencyPostModel.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "error"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                fundingAgencyPostModel = (FundingAgencyPostModel) task.getResult().getValue(FundingAgencyPostModel.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "unable to success@@!!!"+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetails();
            }
        });


//        milestoneFurtherModels = new ArrayList<>();
//
//        milestoneFurtherModels.add(new MilestoneFurtherPostModel("name","1000"));
//        milestoneFurtherModels.add(new MilestoneFurtherPostModel("val","2000"));
//        milestoneFurtherModels.add(new MilestoneFurtherPostModel("name3","5000"));
//        milestoneFurtherModels.add(new MilestoneFurtherPostModel("name4","700"));
//        milestoneFurtherModels.add(new MilestoneFurtherPostModel("name5","839"));
//
//        Collections.sort(milestoneFurtherModels, new UserComparator());
//
//        String x = new String();
//        for(int i=0;i<milestoneFurtherModels.size();i++)
//        {
//            x += milestoneFurtherModels.get(i).getName() + "\n";
//        }
//        Toast.makeText(getContext(),x , Toast.LENGTH_SHORT).show();


        return view;
    }

    private void showDetails() {

//        typeOfAgency.setText(fundingAgencyPostModel.agencyType);
//        year.setText(fundingAgencyPostModel.yearOfEstablishment);
//        nameOfAgency.setText(fundingAgencyPostModel.nameOfFundingAgency);
//        typeOfAgency.setText(fundingAgencyPostModel.agencyType);
//        nameOfFounder.setText(fundingAgencyPostModel.nameOfFunder);
//        phoneNumber.setText(fundingAgencyPostModel.phoneNumber);
//        portFolioLink.setText(fundingAgencyPostModel.portFolioLink);
//        socialLink.setText(fundingAgencyPostModel.socialLinks);
//        state.setText(fundingAgencyPostModel.state);
//        district.setText(fundingAgencyPostModel.district);

        Picasso.with(getContext()).load(Uri.parse(fundingAgencyPostModel.imageUri)).into(profilePic);

    }

    public class UserComparator implements Comparator<MilestoneFurtherPostModel> {
        @Override
        public int compare(MilestoneFurtherPostModel u1, MilestoneFurtherPostModel u2) {
            return u1.getValue().compareTo(u2.getValue());
        }
    }
}
