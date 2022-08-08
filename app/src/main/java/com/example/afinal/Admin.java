package com.example.afinal;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Admin extends Fragment {

    private static final int PERMISSION_IMAGE = 23;
    private static final CharSequence PICK_PDF = "pdf";
    private static final CharSequence PICK_PDF1 = "pdf1";
    private static final CharSequence PICK_PDF2 = "pdf2";
    private ImageView imageView;
    private EditText nameText,empIdText,phoneNumberText,departmentText,genderText;
    private TextView UploadIdCard,uploadDocProof,uploadDocdeclaration,t4,t5,t6;
    private Button submit;
    public Uri selectedImageUri,selectedPDFUri,selectedPDFDeclarationUri,selectedPDFIdUri;
    private FirebaseUtilities firebaseUtilities;
    private FirebaseStorage firebaseStorage;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private int PICK_IMAGE = 12;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseUtilities = new FirebaseUtilities(this.getContext());
        nameText = view.findViewById(R.id.name);
        empIdText = view.findViewById(R.id.empID);
        phoneNumberText = view.findViewById(R.id.phoneNumber);
        departmentText = view.findViewById(R.id.department);
        genderText = view.findViewById(R.id.gender);
        t4 = view.findViewById(R.id.textView4);
        t5 = view.findViewById(R.id.textView5);
        t6 = view.findViewById(R.id.textView6);


        UploadIdCard = view.findViewById(R.id.uploadIDCard);
        uploadDocProof = view.findViewById(R.id.uploadDocProof);
        uploadDocdeclaration = view.findViewById(R.id.uploadDocDeclaration);

        imageView = view.findViewById(R.id.imageView);
        submit = view.findViewById(R.id.submit);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Admin").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRunTimePermissions();
            }
        });

        uploadDocProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();
            }
        });

        UploadIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDFIdDeclaration();
            }
        });

        uploadDocdeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDFDeclaration();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImageUri == null) {
                    Toast.makeText(getContext(), "Please Select Image!!!", Toast.LENGTH_SHORT).show();
                }

                String name = nameText.getText().toString().trim();
                String empId = empIdText.getText().toString().trim();
                String phoneNumber = phoneNumberText.getText().toString().trim();
                String department = departmentText.getText().toString().trim();
                String gender = genderText.getText().toString().trim();

                AdminPostModel adminPostModel = new AdminPostModel();
                adminPostModel.setName(name);
                adminPostModel.setEmpID(empId);
                adminPostModel.setPhoneNumber(phoneNumber);
                adminPostModel.setDepartment(department);
                adminPostModel.setGender(gender);

                firebaseUtilities.uploadIdCard(selectedPDFIdUri, adminPostModel);
                firebaseUtilities.uploadPDFFile(selectedPDFUri, adminPostModel);
                firebaseUtilities.uploadPDFDeclarationFile(selectedPDFDeclarationUri, adminPostModel);
                firebaseUtilities.uploadImage(selectedImageUri, adminPostModel);
                databaseReference.setValue(adminPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Data is sent to realtime database!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed to send the data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                startActivity(new Intent(getContext(),DashBoardActivity.class));

            }

        });


        return view;
    }

    void checkRunTimePermissions() { // This function is to check runtime permissions, to access images.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_IMAGE);
            } else {
                pickImageFromGallery(); // If permission is given, we'll call the pickImageFromGallery function
            }
        }
        else {
            pickImageFromGallery();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_IMAGE && grantResults.length > 0) {
            pickImageFromGallery();  // If permission is given, we'll pick image from gallery
        }
    }
    private void pickImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }
    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,PICK_PDF),12);
    }
    private void selectPDFDeclaration() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,PICK_PDF1),13);
    }
    private void selectPDFIdDeclaration() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,PICK_PDF2),14);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {  // Checks if the image is selected or not.
            selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
        }
        if(requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null){
            t5.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));
            selectedPDFUri = data.getData();
        }
        if(requestCode == 13 && resultCode == RESULT_OK && data != null && data.getData() != null){
            t6.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));
            selectedPDFDeclarationUri = data.getData();
        }
        if(requestCode == 14 && resultCode == RESULT_OK && data != null && data.getData() != null){
            t4.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));
            selectedPDFIdUri = data.getData();
        }
    }

}