package com.example.afinal;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
//import com.squareup.picasso.Picasso;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerAdapterMilestoneFurther extends RecyclerView.Adapter<RecyclerAdapterMilestoneFurther.ViewHolder>{
    private List<MilestoneFurtherPostModel> listdata;
    public Context context;
    private int amount;
    private String psId;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;

    public RecyclerAdapterMilestoneFurther(Context context, List<MilestoneFurtherPostModel> listdata) {
        this.listdata = listdata;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.view_holder_milestone_further, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MilestoneFurtherPostModel myListData = listdata.get(position);
        //String text = myListData.milestone_name;
        holder.textview_nameOfBudget.setText(myListData.name);
        holder.textview_amountOfBudget.setText(String.valueOf(myListData.value));
        holder.CurrentProgress+=(position*15);
        holder.progressBar.setProgress(holder.CurrentProgress);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    amount = Integer.parseInt(holder.inputBudget.getText().toString());
                    int val = Integer.parseInt(myListData.value);
                    holder.CurrentProgress+=15;
                    holder.progressBar.setProgress(holder.CurrentProgress);
                    val -= (amount);
                    myListData.value = String.valueOf(val);

                    holder.textview_amountOfBudget.setText(String.valueOf(myListData.value));

                    databaseReference = FirebaseDatabase.getInstance().getReference("Proposals")
                            .child(psId).child("budgetDivision").child(myListData.name);
                    databaseReference.setValue(myListData.value);
                }
            }
        });

        sharedPreferences = context.getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);
        psId = sharedPreferences.getString("psId","psId");
        String user = sharedPreferences.getString("userType","default");


        if(user.equals("FundingAgency")){
            holder.inputBudget.setVisibility(View.INVISIBLE);
            holder.checkBox.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_nameOfBudget;
        TextView textview_amountOfBudget;
        EditText inputBudget;
        CheckBox checkBox;
        int CurrentProgress;
        ProgressBar progressBar;


        public ViewHolder(View view) {
            super(view);
            this.progressBar=view.findViewById(R.id.progress_bar);
            this.CurrentProgress=0;
            this.textview_nameOfBudget = view.findViewById(R.id.textView_nameOfbudgetCategory);
            this.textview_amountOfBudget = view.findViewById(R.id.textView_amountOfCategory);
            this.inputBudget = view.findViewById(R.id.amount_entered);
            this.checkBox = view.findViewById(R.id.checkbox_milestone_further);
        }
    }
}
