package  com.example.afinal;

import android.annotation.SuppressLint;
import android.content.Context;
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.view_holder_funding_agency, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FundingAgencyPostModel myListData = listdata.get(position);
        String text = myListData.agencyType;
        holder.textview_name.setText(myListData.nameOfFundingAgency);
        holder.textview_founder.setText("Founder -" + myListData.nameOfFunder);
        holder.textview_phnNo.setText( "Phone -" + myListData.phoneNumber);
        holder.textView_State.setText( myListData.state);
        holder.textview_view_type.setText(text);
        Picasso.with(context).load(myListData.imageUri).into(holder.imageview);

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
