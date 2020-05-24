package com.example.orderquick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UniversalCustomerFragmentsAdapter extends RecyclerView.Adapter<UniversalCustomerFragmentsAdapter.MealViewHolder> {
    private ArrayList<MealModel> mealModels;
    public OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemAddOrder(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder{

        public ImageView mealImageView;
        public TextView mealName;
        public TextView mealPrice;
        public TextView mealCategory;
        public ImageView mealAddOrder;


        public MealViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            mealImageView = itemView.findViewById(R.id.customer_meal_image_view_id);
            mealName = itemView.findViewById(R.id.customer_meal_name_text_view_id);
            mealPrice = itemView.findViewById(R.id.customer_meal_telephone_text_view_id);
            mealCategory = itemView.findViewById(R.id.customer_meal_wage_text_view_id);
            mealAddOrder = itemView.findViewById(R.id.customer_meal_delete_image_view_id);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        /*I: make sure position is valid*/
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            /*I: click that allows user to directly add meal to the order list*/
            mealAddOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        /*I: make sure position is valid*/
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemAddOrder(position);
                        }
                    }
                }
            });
        }
    }

    public UniversalCustomerFragmentsAdapter(ArrayList<MealModel> mealmodel){
        mealModels=mealmodel;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_meal_item,parent,false);
        UniversalCustomerFragmentsAdapter.MealViewHolder mealViewHolde = new UniversalCustomerFragmentsAdapter.MealViewHolder(v,mListener);
        return mealViewHolde;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealModel curentMeal = mealModels.get(position);

        holder.mealImageView.setImageBitmap(curentMeal.getMealImg());
        holder.mealName.setText(curentMeal.getMealName());
        holder.mealCategory.setText(curentMeal.getMealCategory());
        holder.mealPrice.setText(curentMeal.getMealPrice());
    }

    @Override
    public int getItemCount() {
        return mealModels.size();
    }


}
