package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RecyclerAdapterPSFundingAgency extends RecyclerView.Adapter<RecyclerAdapterPSFundingAgency.ViewHolder> {

    private List<FundingAgencyPSPostModel> listdata;
    public Context context;
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;

    public Context getContext() {
        return context;
    }

    public RecyclerAdapterPSFundingAgency(Context context,List<FundingAgencyPSPostModel> listData){
        this.listdata = listData;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.view_holder_funding_agency_ps, parent, false);
        RecyclerAdapterPSFundingAgency.ViewHolder viewHolder = new RecyclerAdapterPSFundingAgency.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FundingAgencyPSPostModel myListData = listdata.get(position);
        holder.name.setText(myListData.nameOfFundingAgency);
        holder.ps.setText(myListData.problemStatement);
        holder.deadline.setText(myListData.deadline);
        holder.budget.setText(myListData.budget);
        String user = sharedPreferences.getString("userType","default");
        // load image to image view

        if(position == 1){
            Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/extremefinal-444d9.appspot.com/o/Images%2FHei%2FWYxZeKlCuqY2bjc1zQ2UWEUnZf33%2FHeiProfile.jpg?alt=media&token=abc038e4-3e45-4525-9bca-6586a88e1af5").into(holder.img);
        }
        else{
            Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/extremefinal-444d9.appspot.com/o/Images%2FFunding%20Agency%2FWYxZeKlCuqY2bjc1zQ2UWEUnZf33%2FAdminProfile.jpg?alt=media&token=beb6be4f-e89e-4f6e-90ef-5e7e1d239233").into(holder.img);
        }
        if(user.equals("HEI")){
            holder.openPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ProblemStatementDetails.class);
                    intent.putExtra("problemStatement",listdata.get(position));
                    context.startActivity(intent);
                }
            });
        }
        if(user.equals("FundingAgency")){
            holder.openPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ProposalValidationActivity.class);
                    intent.putExtra("problemStatement",listdata.get(position));
                    context.startActivity(intent);
                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView ps;
        TextView deadline;
        TextView budget;
        TextView typeOfAgency;
        ImageView img;
        Button openPS;

        public ViewHolder(@NonNull View view) {
            super(view);

            sharedPreferences = context.getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);

            this.name = view.findViewById(R.id.holder_item_name);
            this.ps = view.findViewById(R.id.holder_item_ps);
            this.deadline = view.findViewById(R.id.holder_item_deadline);
            this.budget = view.findViewById(R.id.holder_item_budget);
            this.typeOfAgency=view.findViewById(R.id.holder_txtview_item_type);
            this.openPS = view.findViewById(R.id.btn_open_ps);
            this.img = view.findViewById(R.id.holder_imgview);
        }
    }
}
