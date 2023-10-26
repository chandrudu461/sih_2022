package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;


import org.json.JSONObject;

import java.io.Serializable;

public class RazorPayPayment extends AppCompatActivity implements PaymentResultListener{

    Button paybtn;
    TextView paytext;
    PaymentFormModel paymentFormModel;
    DocumentReference payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razor_pay_payment);

        paybtn = (Button) findViewById(R.id.paybtn);
        paytext = (TextView) findViewById(R.id.paytext);

        paymentFormModel = new PaymentFormModel();

//        Intent in=getIntent();
//        if(savedInstanceState != null){
//            paymentFormModel = (PaymentFormModel) savedInstanceState.getSerializable("paymentForm");
//        }
//        else{
//            paymentFormModel = (PaymentFormModel) getIntent().getSerializableExtra("paymentForm");
//        }

        paymentFormModel = (PaymentFormModel) getIntent().getSerializableExtra("paymentForm");

        Checkout.preload(getApplicationContext());
//        payment = FirebaseFirestore.getInstance().collection("Payments").document();



        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepayment();
            }
        });



    }

    private void makepayment()
    {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_yMs4HtGCIf5OZl");
        /**
         * Instantiate Checkout
         */


        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.ugc_logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "ProTrack");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //    options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount",paymentFormModel.amount+"00");//pass amount in currency subunits
            options.put("prefill.email", paymentFormModel.email);
            options.put("prefill.contact",paymentFormModel.phone);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        paytext.setText("Successful Traction ID : "+s);
        paymentFormModel.setTransactionId(s);

        payment.set(paymentFormModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RazorPayPayment.this, "payment successful"+paymentFormModel.transactionId, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RazorPayPayment.this, "payment unsuccessful"+e.getMessage().toString() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPaymentError(int i, String s) {
        paytext.setText("Failed Traction and cause is : "+s);
    }
}