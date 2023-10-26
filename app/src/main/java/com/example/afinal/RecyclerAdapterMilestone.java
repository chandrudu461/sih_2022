package com.example.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class RecyclerAdapterMilestone extends RecyclerView.Adapter<RecyclerAdapterMilestone.ViewHolder> {

    private static final CharSequence PICK_PDF = "Pdf";
    public List<MilestonePostModel> listdata;
    public Context context,fragmentContext;
    DatabaseReference ref;
    public int counter = 0,resultCodeCounter = 0;
    SharedPreferences sharedPreferences;
    int budgetCounter = 0;
    String key,value;
    String user;
    HashMap<String,String> map;

    public RecyclerAdapterMilestone(Context context,List<MilestonePostModel> listdata){
        this.context = context;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public RecyclerAdapterMilestone.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.view_holder_milestone,parent, false);
        RecyclerAdapterMilestone.ViewHolder viewHolder = new RecyclerAdapterMilestone.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMilestone.ViewHolder holder, int position) {
        MilestonePostModel myListData = listdata.get(position);
        holder.budget.setText(myListData.budget);
        holder.duration.setText(myListData.duration);
        holder.milestone.setText(myListData.milestone);
        holder.deliverables.setText(myListData.deliverables);

        map = new HashMap<>();

//        holder.uploadDeliverables.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadDeliverables();
//            }
//        });

        holder.expandMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,MilestoneFurtherActivity.class);
                intent.putExtra("presentMilestone",listdata.get(position));
                context.startActivity(intent);

//                MilestoneFurther milestoneFurther = new MilestoneFurther();
//                Bundle b = new Bundle();
//                b.putSerializable("presentMilestone",listdata.get(position));
//                milestoneFurther.setArguments(b);
//                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_dashboard,new MilestoneFurther()).commit();
            }
        });

//        holder.pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,PaymentForm.class);
//                intent.putExtra("presentMilestone",listdata.get(position));
//                context.startActivity(intent);
//            }
//        });

//        holder.addBudget.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText editText = new EditText(context);
//                editText.setHint("category"+budgetCounter);
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                        RelativeLayout.LayoutParams.MATCH_PARENT,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT);
//                int idLastChild= holder.milestoneRelativeLayout.getChildAt(holder.milestoneRelativeLayout.getChildCount()-1).getId();
//                params.addRule(RelativeLayout.BELOW,idLastChild);
//                editText.setId(idLastChild+1);
//                // set layout params
//                editText.setLayoutParams(params);
////                holder.milestoneRelativeLayout.addView(editText, params);
//
//                editText.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                        key = editText.getText().toString();
//                    }
//                });
//
////                addText(holder);
//                map.put(key,value);
//                budgetCounter++;
//                counter++;
//            }
//        });

//        while(myListData.status.equals("ToBeCompleted")){
//            holder.status.setBackgroundColor(Integer.parseInt("#FFFFFF"));
//        }
//        if(myListData.status.equals("completed")){
//            holder.status.setBackgroundColor(Integer.parseInt("#808080"));
//            holder.button.setVisibility(View.INVISIBLE);
//        }
//        else{
//            holder.status.setBackgroundColor(Integer.parseInt("#00FF00"));
//            holder.button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(user.equals("HEI")){
//                        holder.uploadDeliverables.setVisibility(View.VISIBLE);
//                    }
//                    else{
//                        holder.downloadDeliverables.setVisibility(View.VISIBLE);
//                    }
//                }
//            });
//        }

//        holder.uploadDeliverables.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadDeliverables();
//            }
//        });
//
//        holder.downloadDeliverables.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        holder.submitMilestone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference databaseReference;
//                databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(myListData.psId).child(String.valueOf(position)).child("budgetMap");
//                databaseReference.setValue(map);
//            }
//        });

    }

//    private void addText(ViewHolder holder) {
//
//        EditText editText = new EditText(context);
//        editText.setHint("Bill amount"+counter);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        int idLastChild= holder.milestoneRelativeLayout.getChildAt(holder.milestoneRelativeLayout.getChildCount()-1).getId();
//        params.addRule(RelativeLayout.BELOW,idLastChild);
//        editText.setId(idLastChild+1);
//        // set layout params
//        editText.setLayoutParams(params);
//        holder.milestoneRelativeLayout.addView(editText, params);
//
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                value = editText.getText().toString();
//            }
//        });
//
//    }


    private void uploadDeliverables() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        ((Activity)context).startActivityForResult(Intent.createChooser(intent,PICK_PDF),resultCodeCounter);
        resultCodeCounter++;
    }

//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//
//        if(requestCode == 13 && resultCode == Activity.RESULT_OK){
//
//        }
//
//    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView duration;
        TextView budget;
        TextView milestone;
        TextView deliverables,downloadDeliverables,uploadDeliverables;
        Button expandMilestone;
        ImageView status;

        public ViewHolder(@NonNull View view) {
            super(view);

            sharedPreferences = context.getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);
            this.duration = view.findViewById(R.id.view_holder_milestone_deadline);
            this.budget = view.findViewById(R.id.view_holder_milestone_estimatedBudget);
            this.milestone = view.findViewById(R.id.view_holder_milestone_name);
            this.deliverables = view.findViewById(R.id.view_holder_milestone_deliverable);
//            this.uploadDeliverables = view.findViewById(R.id.upload_deliverables);
            this.expandMilestone = view.findViewById(R.id.expand_further_milestone);
//            this.addBudget = view.findViewById(R.id.btn_add_milestonebudget);
//            this.milestoneRelativeLayout = view.findViewById(R.id.relativeLayout_Budget);

            this.status = view.findViewById(R.id.status_connected);
//            this.downloadDeliverables = view.findViewById(R.id.download_deliverables);
//            this.submitMilestone = view.findViewById(R.id.deliverables_connected);
//            this.pay = view.findViewById(R.id.btn_pay);

        }
    }

}
