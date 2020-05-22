package com.example.orderquick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminCustomerAdapter extends RecyclerView.Adapter<AdminCustomerAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> exList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        /*I: we pass the position which item we clicked*/
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        //create attributes to pass
        public ImageView imageView;
        public TextView username;
        public TextView usertelephone;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            /*I: initialize the views here*/
            super(itemView);
            imageView= (ImageView)itemView.findViewById(R.id.customer_image_view_id);
            username = (TextView)itemView.findViewById(R.id.customer_name_text_view_id);
            usertelephone = (TextView)itemView.findViewById(R.id.customer_telephone_text_view_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        /*make sure the position is valid*/
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }

    /*I: Constructor that receives the data we want to pass*/
    public AdminCustomerAdapter(ArrayList<ExampleItem> exampleItems){
        exList = exampleItems;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*I: here we are passing the layout to the adapter*/
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item,parent,false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(v,mListener);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        /*I: here we are passing references to the views created in ExampleViewHolder
        *    We first need information that we are going to assign to these views.
        *    In this case we get that information from Exaple Item (this will change to customer item)*/
        ExampleItem tempItem = exList.get(position);
        holder.imageView.setImageResource(tempItem.getImageSrc());
        holder.username.setText(tempItem.getName());
        holder.usertelephone.setText(tempItem.getTelephone());
    }

    @Override
    public int getItemCount() {
        return exList.size();
    }



}
