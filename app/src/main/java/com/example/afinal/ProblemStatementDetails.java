package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProblemStatementDetails extends AppCompatActivity {

    TextView ps,description,name,budget,duration,deadline,eligibility,deliverables;
    Button accpetOffer;
    FundingAgencyPSPostModel fundingAgencyPSPostModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_statement_details);
//
//        fundingAgencyPSPostModel = (FundingAgencyPSPostModel) getIntent().getSerializableExtra("presentUser");

        if (getIntent() == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                fundingAgencyPSPostModel = null;
            } else {
                fundingAgencyPSPostModel = (FundingAgencyPSPostModel) getIntent().getSerializableExtra("problemStatement");
            }
        } else {
            fundingAgencyPSPostModel = (FundingAgencyPSPostModel) getIntent().getSerializableExtra("problemStatement");
        }

        ps = findViewById(R.id.ps_details);
        description = findViewById(R.id.description_details);
        name = findViewById(R.id.funding_agency_ps_name);
        budget = findViewById(R.id.ps_budget);
        duration = findViewById(R.id.ps_duration);
        deadline = findViewById(R.id.ps_deadline);
        eligibility = findViewById(R.id.ps_eligibility);
        deliverables = findViewById(R.id.ps_deliverables);

        accpetOffer = findViewById(R.id.accept_offer);

        ps.setText(fundingAgencyPSPostModel.problemStatement);
        description.setText(fundingAgencyPSPostModel.description);
        name.setText(fundingAgencyPSPostModel.nameOfFundingAgency);
        budget.setText(fundingAgencyPSPostModel.budget);
        duration.setText(fundingAgencyPSPostModel.duration);
        deadline.setText(fundingAgencyPSPostModel.deadline);
        eligibility.setText(fundingAgencyPSPostModel.eligibility);
        deliverables.setText(fundingAgencyPSPostModel.deliverables);

        accpetOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProblemStatementDetails.this,HeiProposal.class);
                intent.putExtra("problemStatement",fundingAgencyPSPostModel);
                startActivity(intent);
            }
        });

    }
}