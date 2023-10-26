package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentForm extends AppCompatActivity {

    EditText nameText,emailText,phoneText,amountText,addressText;
    Button proceed;
    DatabaseReference databaseReference;
    MilestonePostModel milestonePostModel;
    TextView ps, milestoneNumber,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_form);

        nameText = findViewById(R.id.pay_name);
        emailText = findViewById(R.id.pay_email);
        phoneText = findViewById(R.id.pay_phone);
        amountText = findViewById(R.id.pay_amount);
        addressText = findViewById(R.id.pay_address);

        ps = findViewById(R.id.payment_ps);
        milestoneNumber = findViewById(R.id.payment_milestone_number);
        amount = findViewById(R.id.payment_amount);
        
        databaseReference = FirebaseDatabase.getInstance().getReference("paymentForms");

        milestonePostModel = (MilestonePostModel) getIntent().getSerializableExtra("paymentForm");

        proceed = findViewById(R.id.proceed);

        ps.setText("Application for the blind");
        milestoneNumber.setText("1");
        amount.setText("10000");
//        milestoneNumber.setText(milestonePostModel.milestoneNumber);
//        amount.setText(milestonePostModel.budget);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameText.getText().toString();
                String email = emailText.getText().toString();
                String phone = phoneText.getText().toString();
                String amount = amountText.getText().toString();
                String address = addressText.getText().toString();

                PaymentFormModel paymentFormModel = new PaymentFormModel();
                paymentFormModel.setName(name);
                paymentFormModel.setEmail(email);
                paymentFormModel.setPhone(phone);
                paymentFormModel.setAmount(amount);
                paymentFormModel.setAddress(address);

                databaseReference.setValue(paymentFormModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PaymentForm.this, "payment form submitted", Toast.LENGTH_SHORT).show();
                            }
                    }
                });

                Intent intent = new Intent(PaymentForm.this,RazorPayPayment.class);
                intent.putExtra("paymentForm",paymentFormModel);
                startActivity(intent);
            }
        });


    }


}