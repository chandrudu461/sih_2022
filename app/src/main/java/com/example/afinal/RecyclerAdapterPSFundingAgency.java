package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterPSFundingAgency extends RecyclerView.Adapter<RecyclerAdapterPSFundingAgency.ViewHolder> {


    private List<FundingAgencyPSPostModel> listdata;
    public Context context;

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

        // load image to image view

        holder.openPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(context,ProblemStatementDetails.class);
            intent.putExtra("preserntUser",listdata.get(position));
            context.startActivity(intent);
            }
        });

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

        Button openPS;

        public ViewHolder(@NonNull View view) {
            super(view);

            this.name = view.findViewById(R.id.holder_item_name);
            this.ps = view.findViewById(R.id.holder_item_ps);
            this.deadline = view.findViewById(R.id.holder_item_deadline);
            this.budget = view.findViewById(R.id.holder_item_budget);

            this.openPS = view.findViewById(R.id.btn_open_ps);
        }
    }
}
