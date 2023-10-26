package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ProposalValidationActivity extends AppCompatActivity {

    FundingAgencyPSPostModel fundingAgencyPSPostModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_validation);

//        fundingAgencyPSPostModel = (FundingAgencyPSPostModel) getIntent().getSerializableExtra("problemStatement");
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Fragment fragment = ProposalValidation.newInstance(fundingAgencyPSPostModel);
//        ft.replace(R.id.frag, fragment);
//        ft.commit();


        fundingAgencyPSPostModel = new FundingAgencyPSPostModel();
        fundingAgencyPSPostModel = (FundingAgencyPSPostModel) getIntent().getSerializableExtra("problemStatement") ;


        setFragment(new ProposalValidation());

    }
//
    private void setFragment(Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("problemStatement", fundingAgencyPSPostModel);
// set Fragmentclass Arguments
        Fragment fragobj = new Fragment();
        fragobj.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
        fragmentTransaction.commit();
    }
}