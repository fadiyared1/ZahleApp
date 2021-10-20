package com.example.menu.ui.Hotels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.R;

public class MyRestaurantAdapter extends RecyclerView.Adapter<MyRestaurantAdapter.ViewHolder> {

    com.example.menu.ui.Hotels.MyRestaurantData[] myRestaurantData;
    Context context;
    Intent myIntent;
    public MyRestaurantAdapter(com.example.menu.ui.Hotels.MyRestaurantData[] myRestaurantData , FragmentActivity activity) {

        this.myRestaurantData = myRestaurantData;
        this.context = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restau_item_list_restaurant, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyRestaurantData myRestaurantDataList = myRestaurantData[position];
        holder.textViewName.setText(myRestaurantDataList.getRestname());
        holder.textViewStyle.setText(myRestaurantDataList.getReststyle());
        holder.textViewDesc.setText(myRestaurantDataList.getRestdesc());
        holder.restauImg.setImageResource(myRestaurantDataList.getRestimg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getAdapterPosition();
               // Intent intt = new Intent(this , Info.class);
                //startActivity(intt);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myRestaurantData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView restauImg;
        TextView textViewName;
        TextView textViewStyle;
        TextView textViewDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restauImg = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewStyle = itemView.findViewById(R.id.textStyle);
            textViewDesc = itemView.findViewById(R.id.textDesc);

        }
    }
}
