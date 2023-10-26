package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RecyclerAdapterProposalHei extends RecyclerView.Adapter<RecyclerAdapterProposalHei.ViewHolder> {
    private List<HeiProposalModel> listdata;
    public Context context;
    SharedPreferences sharedPreferences;

    public Context getContext() {
        return context;
    }

    public RecyclerAdapterProposalHei(Context context,List<HeiProposalModel> listdata){
        this.context = context;
        this.listdata = listdata;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.view_holder_proposal_hei,parent, false);
        RecyclerAdapterProposalHei.ViewHolder viewHolder = new RecyclerAdapterProposalHei.ViewHolder(listItem);
        return viewHolder;

//        View views = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.view_holder_proposal_hei,parent,false);
//
//        return new ViewHolder(views);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterProposalHei.ViewHolder holder, int position) {

        final HeiProposalModel myListData = listdata.get(position);
        holder.expectedDuration.setText(myListData.expectedDuration);
        holder.expectedBudget.setText(myListData.expectedBudget);
        holder.nameOfHei.setText(myListData.heiName);
        holder.rating.setText(myListData.getHeiScore());


        if(position == 0){
            Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/extremefinal-444d9.appspot.com/o/Images%2FHei%2FWYxZeKlCuqY2bjc1zQ2UWEUnZf33%2FHeiProfile.jpg?alt=media&token=abc038e4-3e45-4525-9bca-6586a88e1af5").into(holder.image);
        }
        else if(position == 1){
//            Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/extremefinal-444d9.appspot.com/o/Images%2FHei%2FreC01coURdURz7J6Akm940LzPX02%2FHeiProfile.jpg?alt=media&token=5e7e5e37-4ef3-4728-9da9-0175094ac403").into(holder.image);
            Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/extremefinal-444d9.appspot.com/o/Images%2FFunding%20Agency%2FWYxZeKlCuqY2bjc1zQ2UWEUnZf33%2FAdminProfile.jpg?alt=media&token=beb6be4f-e89e-4f6e-90ef-5e7e1d239233").into(holder.image);
        }
        else{
            Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/extremefinal-444d9.appspot.com/o/Images%2FFunding%20Agency%2FreC01coURdURz7J6Akm940LzPX02%2FAdminProfile.jpg?alt=media&token=d4543650-0456-4b23-830f-7484d097d825").into(holder.image);
        }
        //        Picasso.with(getContext()).load(url).into(holder.image);
        holder.userHei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putString("HEI_uid",myListData.getHeiUid()).commit();
                context.startActivity(new Intent(context,HeiProfileActivity.class));

            }
        });

        String user = sharedPreferences.getString("userType","default");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("psId", myListData.psId).commit();

        if(user.equals("HEI"))
        holder.openProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProposalStatus.class);
                listdata.get(position).setHeiScore(listdata.get(position).getHeiScore()+1);
                intent.putExtra("presentProposal", (Serializable)  listdata.get(position));
                context.startActivity(intent);
            }
        });

        if(user.equals("FundingAgency")){
            holder.openProposal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ProposalDetails.class);
                    intent.putExtra("presentProposal", listdata.get(position));
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

        TextView expectedDuration;
        TextView expectedBudget;
        TextView nameOfHei;
        Button userHei;
        Button openProposal;
        TextView rating;
        ImageView image;

        public ViewHolder(@NonNull View view) {
            super(view);

            sharedPreferences = context.getSharedPreferences(String.valueOf((R.string.shared_preferences_user_details)), Context.MODE_PRIVATE);

            this.rating=view.findViewById(R.id.holder_rating);
            this.expectedDuration = view.findViewById(R.id.holder_expected_duration);
            this.expectedBudget = view.findViewById(R.id.holder_expected_budget);
            this.nameOfHei = view.findViewById(R.id.holder_hei_name);
            this.userHei=view.findViewById(R.id.btn_view_HeiProfile);
            this.openProposal = view.findViewById(R.id.btn_open_proposal);
            this.image = view.findViewById(R.id.holder_imgview2);
        }
    }

}
