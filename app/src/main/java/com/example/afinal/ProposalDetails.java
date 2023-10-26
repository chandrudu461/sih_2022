package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ProposalDetails extends AppCompatActivity {

    TextView problemStatementText,Hei_name,stmt_description,expected_duration,expected_budget,milestones;
    Button acceptProposal,declineProposal,submitReason;
    EditText declineReason;
    HeiProposalModel heiProposalModel;
    DatabaseReference databaseReference,ref,budgetref;
    TextView amtdisp1, amtdisp2, amtdisp3, amtdisp4;
    TextView textView1, textView2, textView3, textView4;
    TextView txtDisplay1, txtDisplay2, txtDisplay3, txtDisplay4;
    ArrayList<MilestoneFurtherPostModel> listBudget=new ArrayList<>();
    PieChart pieChart;
    MilestoneFurtherPostModel temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_details);
        stmt_description=findViewById(R.id.descriptiondisp);
        expected_duration=findViewById(R.id.expectedDuration);
        expected_budget=findViewById(R.id.expectedbudgetdisp);
        acceptProposal = findViewById(R.id.accept_proposal);
        declineProposal = findViewById(R.id.decline_proposal);
        declineReason = findViewById(R.id.decline_reason_proposal);
        problemStatementText = findViewById(R.id.textProblemstmt);
        submitReason = findViewById(R.id.submit_reason);
        Hei_name=findViewById(R.id.heiNamedisp);
        milestones=findViewById(R.id.milestonesdisp);

        heiProposalModel = (HeiProposalModel) getIntent().getSerializableExtra("presentProposal");




        textView1 = findViewById(R.id.txtview1);
        textView2 = findViewById(R.id.txtview2);
        textView3 = findViewById(R.id.txtview3);
        textView4 = findViewById(R.id.txtview4);

        txtDisplay1 = findViewById(R.id.txtDisplay1);
        txtDisplay2 = findViewById(R.id.txtDisplay2);
        txtDisplay3 = findViewById(R.id.txtDisplay3);
        txtDisplay4 = findViewById(R.id.txtDisplay4);

        amtdisp1 = findViewById(R.id.amountdisp1);
        amtdisp2 = findViewById(R.id.amountdisp2);
        amtdisp3 = findViewById(R.id.amountdisp3);
        amtdisp4 = findViewById(R.id.amountdisp4);
        pieChart = findViewById(R.id.piechart);


//        TextView problemStatementText,Hei_name,stmt_description,expected_duration,expected_budget,milestones;

        problemStatementText.setText(heiProposalModel.problemStatement);
        expected_budget.setText(heiProposalModel.expectedBudget);
        stmt_description.setText(heiProposalModel.getDescription());
        expected_duration.setText(heiProposalModel.expectedDuration);
//        Hei_name.setText(heiProposalModel.heiName);


        budgetref=FirebaseDatabase.getInstance().getReference("Proposals").child(heiProposalModel.psId).child("budgetDivision");
        budgetref.addValueEventListener(new ValueEventListener() {
            @Override

                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot postSnapshot : snapshot.getChildren()){
//                        MilestonePostModel agency = (MilestonePostModel) postSnapshot.getValue(MilestonePostModel.class);
                        temp=snapshot.getValue(MilestoneFurtherPostModel.class);
                        listBudget.add(temp);
                    }

                }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setData();

        databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(heiProposalModel.psId);
        ref = FirebaseDatabase.getInstance().getReference("posts").child(heiProposalModel.psId);
        acceptProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("status").setValue("accepted");
                ref.child("status").setValue("accepted");
                Intent intent = new Intent(ProposalDetails.this,FundingAgencyMilestonePage.class);
                intent.putExtra("presentProposal",heiProposalModel);
                startActivity(intent);
            }
        });

        declineProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("status").setValue("declined");
                declineReason.setVisibility(View.VISIBLE);
                submitReason.setVisibility(View.VISIBLE);
            }
        });

        submitReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reason = declineReason.getText().toString();
                String decline = declineProposal.getText().toString();
                databaseReference.child("declinedReason").setValue(decline);
            }
        });


    }
    private void setData() {

        textView1.setText("Personnel");
        textView2.setText("Technical papers");
        textView3.setText("Expendables");
        textView4.setText("Travel");

        txtDisplay1.setText("Personnel");
        txtDisplay2.setText("Technical papers");
        txtDisplay3.setText("Expendables");
        txtDisplay4.setText("Travel");
        for(int i=0;i<listBudget.size();i++){
           switch(listBudget.get(i).getName()){
               case "Personnel":
                   amtdisp1.setText(listBudget.get(i).getValue());
                   break;
               case "Technical papers":
                   amtdisp2.setText(listBudget.get(i).getValue());
                   break;
               case "Expendables":
                   amtdisp3.setText(listBudget.get(i).getValue());
                   break;
               case "Travel":
                   amtdisp4.setText(listBudget.get(i).getValue());
                   break;

           }
        }
        // Set the percentage of language used

        amtdisp2.setText(Integer.toString(30));
        amtdisp3.setText(Integer.toString(5));
        amtdisp4.setText(Integer.toString(25));



        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        txtDisplay1.getText().toString(),
                        Integer.parseInt(amtdisp1.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        txtDisplay2.getText().toString(),
                        Integer.parseInt(amtdisp2.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        txtDisplay3.getText().toString(),
                        Integer.parseInt(amtdisp3.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        txtDisplay4.getText().toString(),
                        Integer.parseInt(amtdisp4.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}