package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RecyclerAdapterHei extends RecyclerView.Adapter<RecyclerAdapterHei.ViewHolder> {
    private List<HeiPostModel> listdata;
    public Context context;

    public RecyclerAdapterHei(Context context,List<HeiPostModel> listdata) {
        this.listdata = listdata;
        this.context=context;
    }
    @Override
    public RecyclerAdapterHei.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.view_holder_hei, parent, false);
        RecyclerAdapterHei.ViewHolder viewHolder = new RecyclerAdapterHei.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterHei.ViewHolder holder, int position) {
        final HeiPostModel myListData = listdata.get(position);
        String text = myListData.nameOfHei;
        holder.textview_name.setText(myListData.nameOfHei);
        holder.textview_founder.setText("YearOfEstablishment -" + myListData.yearOfEstablishment);
        holder.textview_phnNo.setText( "aicteCode -" + myListData.aicteCode);
        holder.textView_State.setText( myListData.selectedstate);
        holder.textview_view_type.setText(text);
        Picasso.with(context).load(myListData.imageUri).into(holder.imageview);

        holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context,AdminYesOrNo.class);
                in.putExtra("userType","Hei");
                in.putExtra("UserToBeVerified", listdata.get(position));
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_view_type;
        TextView textview_name;

        TextView textview_founder;
        TextView textview_phnNo;
        TextView textView_State;
        ImageView imageview ;
        Button registerButton;
        public ViewHolder(View view) {
            super(view);
            this.textview_view_type=view.findViewById(R.id.holder_txtview_item_type);
            this.textview_name=view.findViewById(R.id.holder_item_name);
            this.textview_founder=view.findViewById(R.id.holder_item_founder);
            this.textview_phnNo=view.findViewById(R.id.holder_item_phn_no);
            this.textView_State=view.findViewById(R.id.holder_item_state);
            this.imageview =view.findViewById(R.id.holder_imgview);
            this.registerButton =view.findViewById(R.id.btn_user_verify);

        }
    }

}
