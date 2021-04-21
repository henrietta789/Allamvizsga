package com.example.pixi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context mContext;
    List<Filter> mData;

    public RecyclerViewAdapter(Context context, List<Filter> listFilter) {
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.filter_list_item,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.filtername.setText(mData.get(position).getFilter_name());
        holder.filterimage.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView filtername;
        private ImageView filterimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            filtername = (TextView) itemView.findViewById(R.id.filtername);
            filterimage = (ImageView) itemView.findViewById(R.id.filterimage);
        }
    }

}
