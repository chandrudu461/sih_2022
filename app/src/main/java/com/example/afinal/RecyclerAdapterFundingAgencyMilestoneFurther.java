package com.example.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerAdapterFundingAgencyMilestoneFurther extends RecyclerView.Adapter<RecyclerAdapterFundingAgencyMilestoneFurther.ViewHolder> {

    private List<MilestoneFurtherPostModel> listdata;
    public Context context;
    private int amount;
    private String psId;
    DatabaseReference databaseReference;
    String amountRemained;
    SharedPreferences sharedPreferences;

    public RecyclerAdapterFundingAgencyMilestoneFurther(Context context, List<MilestoneFurtherPostModel> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public RecyclerAdapterFundingAgencyMilestoneFurther.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.view_holder_milestone_further, parent, false);
        RecyclerAdapterFundingAgencyMilestoneFurther.ViewHolder viewHolder = new RecyclerAdapterFundingAgencyMilestoneFurther.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MilestoneFurtherPostModel myListData = listdata.get(position);
        //String text = myListData.milestone_name;
        holder.textview_nameOfBudget.setText(myListData.name);
        holder.textview_amountOfBudget.setText(String.valueOf(myListData.value));

//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    amount = Integer.parseInt(holder.inputBudget.getText().toString());
//                    myListData.value -= amount;
//                    holder.textview_amountOfBudget.setText(String.valueOf(myListData.value));
//                    databaseReference = FirebaseDatabase.getInstance().getReference("Proposals")
//                            .child(psId).child("budgetDivision").child(myListData.name);
//                    databaseReference.setValue(myListData.value);
//                }
//            }
//        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Proposals").child(psId).child("budgetDivision").child(myListData.name);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                amountRemained = snapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.textview_amountRemained.setText(amountRemained);

        sharedPreferences = context.getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);
        psId = sharedPreferences.getString("psId", "psId");

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_nameOfBudget;
        TextView textview_amountOfBudget;
        TextView textview_amountRemained;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);

            this.textview_nameOfBudget = view.findViewById(R.id.textView_nameOfbudgetCategory);
            this.textview_amountOfBudget = view.findViewById(R.id.textView_amountOfCategory);
            this.textview_amountRemained = view.findViewById(R.id.amount_entered);
            this.checkBox = view.findViewById(R.id.checkbox_milestone_further);
        }
    }
}