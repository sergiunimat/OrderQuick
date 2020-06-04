package com.example.orderquick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private ArrayList<OrderModel> orderModelsList;
    private OnItemClickListener mListener;


    /*I: create interface*/
    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;

    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public OrderViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView=itemView.findViewById(R.id.ordertextid);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        /*I: making sure that the position is valid*/
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public OrderAdapter(ArrayList<OrderModel> om){
        orderModelsList=om;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        OrderViewHolder ovh = new OrderViewHolder(v,mListener);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderModel orM = orderModelsList.get(position);

        holder.textView.setText("NEW ORDER");
//        holder.textView.setText(orM.OrderId);

    }

    @Override
    public int getItemCount() {
        return orderModelsList.size();
    }
}
