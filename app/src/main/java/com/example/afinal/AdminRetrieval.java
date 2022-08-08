package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdminRetrieval extends Fragment {
    private Button heiButton,fundingAgencyButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_retrieval);
        View view = inflater.inflate(R.layout.activity_admin_retrieval,container,false);

        heiButton = view.findViewById(R.id.heiButton);
        fundingAgencyButton = view.findViewById(R.id.fundingAgencyButton);


        heiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new HeiAdminFragment());
            }
        });

        fundingAgencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(AdminRetrieval.this,FundingAgencyRetrieval.class));
                setFragment(new FundingAgencyAdminFragment());
            }
        });

        return view;

    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }
}