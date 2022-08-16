package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProblemStatementDetails extends AppCompatActivity {

    FundingAgencyPSPostModel presentUser;
    TextView ps,description,name,budget,duration,deadline,eligibility,deliverables;
    Button accpetOffer,declineOffer;
    FundingAgencyPSPostModel fundingAgencyPSPostModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_statement_details);

        presentUser = (FundingAgencyPSPostModel) getIntent().getSerializableExtra("presentUser");

        ps = findViewById(R.id.ps);
        description = findViewById(R.id.ps_description);
        name = findViewById(R.id.funding_agency_ps_name);
        budget = findViewById(R.id.ps_budget);
        duration = findViewById(R.id.ps_duration);
        deadline = findViewById(R.id.ps_deadline);
        eligibility = findViewById(R.id.ps_eligibility);
        deliverables = findViewById(R.id.ps_deliverables);

        accpetOffer = findViewById(R.id.accept_offer);
        declineOffer = findViewById(R.id.decline_offer);

        ps.setText(presentUser.problemStatement);
        description.setText(presentUser.deadline);
        name.setText(presentUser.nameOfFundingAgency);
        budget.setText(presentUser.budget);
        duration.setText(presentUser.duration);
        deadline.setText(presentUser.deadline);
        eligibility.setText(presentUser.eligibility);
        deliverables.setText(presentUser.deliverables);

        accpetOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProblemStatementDetails.this,ViewPSHei.class);
                intent.putExtra("presentUser",presentUser);
                startActivity(intent);
            }
        });

        declineOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}