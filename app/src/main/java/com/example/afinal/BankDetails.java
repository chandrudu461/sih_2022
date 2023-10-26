package com.example.afinal;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class BankDetails extends Fragment {

    EditText bankNameText, bankAccountNumberText, ifscCodeText, branchText;
    Button submit,addUpi;
    BankModel bankModel;
    RelativeLayout relativeLayout;
    public String upi;
    DatabaseReference databaseReference;
    int counter = 0;
    ImageView imageView;
    List<EditText> allEd;
    ArrayList<String> upiList;
    Spinner spinner;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank_details, container, false);

        bankNameText = view.findViewById(R.id.bank_name);
        bankAccountNumberText = view.findViewById(R.id.back_account_no);
        ifscCodeText = view.findViewById(R.id.ifsc);
        branchText = view.findViewById(R.id.bank_branch);

        submit = view.findViewById(R.id.submit_bank);

        imageView = view.findViewById(R.id.imageView2);

        String url = "https://firebasestorage.googleapis.com/v0/b/extremefinal-444d9.appspot.com/o/Images%2FHei%2FWYxZeKlCuqY2bjc1zQ2UWEUnZf33%2FHeiProfile.jpg?alt=media&token=a82a7ea3-25b8-4d3e-b510-e78e28e74207";

        Uri imageUri = Uri.parse(url);
        Picasso.with(getContext()).load(url).into(imageView);

        addUpi = view.findViewById(R.id.add_upi);

        relativeLayout = view.findViewById(R.id.relative_bank);

        upiList = new ArrayList<String>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        allEd = new ArrayList<>();

        addUpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUpiFunction();
//                upiList.add(upi);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bankName,bankAccountNumber,ifscCode,branchName;
                bankName = bankNameText.getText().toString();
                bankAccountNumber = bankAccountNumberText.getText().toString();
                ifscCode = ifscCodeText.getText().toString();
                branchName = branchText.getText().toString();

                bankModel = new BankModel();
                bankModel.setBankName(bankName);
                bankModel.setBankAccountNo(bankAccountNumber);
                bankModel.setIfscCode(ifscCode);
                bankModel.setBranchName(branchName);

                for(int i=0;i<allEd.size();i++){
                    upiList.add(allEd.get(i).getText().toString().trim());
                }
                bankModel.setUpis(upiList);

                databaseReference = FirebaseDatabase.getInstance().getReference("BankDetails");
//                        .child(firebaseUser.getUid());

                databaseReference.setValue(bankModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "bank details uploaded", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "bank not uploading", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        return view;
    }

    private void addUpiFunction() {

        EditText editText = new EditText(getContext());
        editText.setHint("upi "+counter);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        int idLastChild= relativeLayout.getChildAt(relativeLayout.getChildCount()-1).getId();
        params.addRule(RelativeLayout.BELOW,idLastChild);
//        editText.setHintTextColor(Color.WHITE);
        editText.setId(idLastChild+1);
        // set layout params
        editText.setLayoutParams(params);

        relativeLayout.addView(editText, params);

        counter++;

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                upi = editText.getText().toString().trim();
//                upiList.add(upi);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        allEd.add(editText);
    }
}
