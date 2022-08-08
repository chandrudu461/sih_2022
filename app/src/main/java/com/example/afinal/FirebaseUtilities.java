package com.example.afinal;

import android.app.NativeActivity;
import android.app.ProgressDialog;
import android.app.backup.FullBackupDataOutput;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseUtilities {
    Context context;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;

    public FirebaseUtilities(Context contex){
        this.context = contex;
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        mAuth = FirebaseAuth.getInstance();
    }

    void uploadImage(Uri uri,AdminPostModel adminPostModel){
        progressDialog.show();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        StorageReference storageReference = firebaseStorage.getReference().child("Images/" + "admin/" + firebaseUser.getUid() + "/" + "AdminProfile.jpg");
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            progressDialog.dismiss();
                            Log.d("storageReference URL", "On Success" + uri.toString());
                            Toast.makeText(context, "Image Uploaded succesfully!!", Toast.LENGTH_SHORT).show();
                            adminPostModel.setImageUrl(uri.toString());
                            post(adminPostModel);
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "Image Upload falied"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void post(AdminPostModel adminPostModel) {
        progressDialog.show();
        DocumentReference documentReference= firebaseFirestore.collection("admin").document(firebaseUser.getUid());
        adminPostModel.setDocId(documentReference.getId());

        documentReference.set(adminPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(context, "Posted Successfully!!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "profile posting failed..!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadPDFFile(Uri uri,AdminPostModel adminPostModel) {
        StorageReference sr = firebaseStorage.getReference().child("PDFs/" + "admin/" + firebaseUser.getUid()+"/" + "docProof.pdf");
        sr.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()) {
                    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            adminPostModel.setPdfDocUri(uri.toString());
                            Toast.makeText(context, "Doc Declaration Proof uploaded successfully!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(context, "Doc declaration proof not submitted!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void uploadPDFDeclarationFile(Uri uri,AdminPostModel adminPostModel) {
        StorageReference sr = firebaseStorage.getReference().child("PDFs/" + "admin/" + firebaseUser.getUid()+"/" + "docDeclarationProof.pdf");
        sr.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            adminPostModel.setPdfDeclarationDocUri(uri.toString());
                            Toast.makeText(context, "Doc Proof uploaded successfully!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(context, "Something went Wrong..!!! Declaration Pdf not uploaded!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void uploadIdCard(Uri uri, AdminPostModel adminPostModel) {
        StorageReference sr = firebaseStorage.getReference().child("PDFs/" + "admin/" + firebaseUser.getUid()+"/" + "docIdCard.pdf");
        sr.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(task.isSuccessful()){
                                adminPostModel.setPdfIdUri(uri.toString());
                                Toast.makeText(context, "Url Uploaded succeesfully!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(context, "Something went wrong ID card not uploaded!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void uploadFundingAgencyImage(Uri uri,FundingAgencyPostModel fundingAgencyPostModel) {
        progressDialog.show();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        StorageReference storageReference = firebaseStorage.getReference().child("Images/" + "Funding Agency/" + firebaseUser.getUid() + "/" + "AdminProfile.jpg");
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            progressDialog.dismiss();
                            Log.d("storageReference URL", "On Success" + uri.toString());
                            Toast.makeText(context, "Image Uploaded succesfully!!", Toast.LENGTH_SHORT).show();
                            fundingAgencyPostModel.setImageUri(uri.toString());
                            postFundingAgencyDetails(fundingAgencyPostModel);
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "Image Upload falied"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadHeiImage(Uri uri, HeiPostModel heiPostModel) {
        progressDialog.show();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        StorageReference storageReference = firebaseStorage.getReference().child("Images/" + "Hei/" + firebaseUser.getUid() + "/" + "HeiProfile.jpg");
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            progressDialog.dismiss();
                            Log.d("storageReference URL", "On Success" + uri);
                            Toast.makeText(context, "Image Uploaded succesfully!! " + uri, Toast.LENGTH_SHORT).show();
                            heiPostModel.setImageUri(uri.toString());
                            postHeiDetails(heiPostModel);
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "Image Upload falied"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postFundingAgencyDetails(FundingAgencyPostModel fundingAgencyPostModel) {
        progressDialog.show();
        DocumentReference documentReference= firebaseFirestore.collection("Funding Agency").document(firebaseUser.getUid());
        fundingAgencyPostModel.setDocumentReference(documentReference.getId());

        documentReference.set(fundingAgencyPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(context, "Posted Successfully!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "profile posting failed..!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postHeiDetails(HeiPostModel heiPostModel) {
        progressDialog.show();

        DocumentReference documentReference= firebaseFirestore.collection("Hei").document(firebaseUser.getUid());
        heiPostModel.setDocumentReference(documentReference.getId());

        documentReference.set(heiPostModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(context, "Posted Successfully!!!", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "profile posting failed..!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void uploadFundingAgencyPdf(Uri uri, FundingAgencyPostModel fundingAgencyPostModel) {
        StorageReference sr = firebaseStorage.getReference().child("PDFs/" + "Funding Agency/" + firebaseUser.getUid()+"/" + "docProof.pdf");
        sr.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            fundingAgencyPostModel.setDeclarationPdfUri(uri.toString());
                            Toast.makeText(context, "Doc Declaration Proof uploaded successfully!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(context, "Something went Wrong..!!! Pdf not uploaded!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void uploadHeiPdf(Uri uri, HeiPostModel heiPostModel) {
        StorageReference sr = firebaseStorage.getReference().child("PDFs/" + "Funding Agency/" + firebaseUser.getUid()+"/" + "docProof.pdf");
        sr.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri1) {
                            heiPostModel.setPdfUri(uri1.toString());
                            Toast.makeText(context, "Hei Proof uploaded successfully!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(context, "Something went Wrong..!!! Pdf not uploaded!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
