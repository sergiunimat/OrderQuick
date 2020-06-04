package com.example.orderquick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeMealAdapter extends RecyclerView.Adapter<EmployeeMealAdapter.MealViewHolde> {
    private ArrayList<MealModel> mealModels;
    private EmployeeMealAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(EmployeeMealAdapter.OnItemClickListener listener){
        mListener=listener;
    }

    public static class MealViewHolde extends RecyclerView.ViewHolder{
        public ImageView mealImageView;
        public TextView mealName;
        public TextView mealPrice;
        public TextView mealCategory;
        public ImageView mealDelete;

        public MealViewHolde(@NonNull View itemView, final EmployeeMealAdapter.OnItemClickListener listener) {
            super(itemView);

            mealImageView = itemView.findViewById(R.id.meal_image_view_id);
            mealName = itemView.findViewById(R.id.meal_name_text_view_id);
            mealPrice = itemView.findViewById(R.id.meal_telephone_text_view_id);
            mealCategory = itemView.findViewById(R.id.meal_wage_text_view_id);
            mealDelete = itemView.findViewById(R.id.meal_delete_image_view_id);

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

            /*I: create a way to allow the user to click on the image delete from where he/she can delete directyl*/
            mealDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        /*I: make sure position is valid*/
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }

    public EmployeeMealAdapter(ArrayList<MealModel> mm){
        mealModels=mm;
    }

    @NonNull
    @Override
    public EmployeeMealAdapter.MealViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.universal_meal_item,parent,false);
        EmployeeMealAdapter.MealViewHolde mealViewHolde = new EmployeeMealAdapter.MealViewHolde(v,mListener);
        return mealViewHolde;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeMealAdapter.MealViewHolde holder, int position) {
        MealModel currentMeal = mealModels.get(position);

        holder.mealImageView.setImageBitmap(currentMeal.getMealImg());
        holder.mealName.setText(currentMeal.getMealName());
        holder.mealCategory.setText(currentMeal.getMealCategory());
        holder.mealPrice.setText(currentMeal.getMealPrice());
    }

    @Override
    public int getItemCount() {
        return mealModels.size();
    }




}
