package  com.example.afinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.FundingAgency;
import com.example.afinal.FundingAgencyPostModel;
import com.example.afinal.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterFundingAgency extends RecyclerView.Adapter<RecyclerAdapterFundingAgency.ViewHolder>{
    private List<FundingAgencyPostModel> listdata;
    public Context context;
    // RecyclerView recyclerView;
    public RecyclerAdapterFundingAgency(Context context,List<FundingAgencyPostModel> listdata) {
        this.listdata = listdata;
        this.context=context;
    }
    @Override
    public RecyclerAdapterFundingAgency.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.view_holder_funding_agency, parent, false);
        RecyclerAdapterFundingAgency.ViewHolder viewHolder = new RecyclerAdapterFundingAgency.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterFundingAgency.ViewHolder holder, int position) {
        final FundingAgencyPostModel myListData = listdata.get(position);
        String text = myListData.agencyType;
        holder.textview_name.setText(myListData.nameOfFundingAgency);
        holder.textview_founder.setText("Founder -" + myListData.nameOfFunder);
        holder.textview_phnNo.setText( "Phone -" + myListData.phoneNumber);
        holder.textView_State.setText( myListData.state);
        holder.textview_view_type.setText(text);
//        Picasso.with(context).load("https://firebasestorage.googleapis.com/v0/b/final-2923f.appspot.com/o/Images%2FFunding%20Agency%2FYXVyo1qxsVMZPMboNACl6kX98FH3%2FAdminProfile.jpg?alt=media&token=913ac272-4163-4223-8e52-f4901e509d9b")
//                .resize(100,100).centerCrop().into(holder.imageview);

        Picasso.with(context).load(myListData.imageUri).into(holder.imageview);

//        Picasso.with(context.getApplicationContext()).load(myListData.imageUri).networkPolicy(NetworkPolicy.OFFLINE)
//                .placeholder(R.drawable.ic_baseline_picture_as_pdf_24).into(holder.imageview, new Callback() {
//                            @Override
//                            public void onSuccess() {
//
//                            }
//
//                            @Override
//                            public void onError() {
//
//                                Toast.makeText(context, myListData.imageUri, Toast.LENGTH_SHORT).show();
//                                Picasso.with(context).load(myListData.imageUri).placeholder(R.drawable.ic_baseline_picture_as_pdf_24).into(holder.imageview);
//                            }
//                        });
        holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context,AdminYesOrNo.class);
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
