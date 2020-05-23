package com.example.orderquick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminEmployeAdapter extends RecyclerView.Adapter<AdminEmployeAdapter.EmployeeViewHolder> {

    private ArrayList<EmployeeViewModel> employeeViewHolders;
    private OnItemClickListener empListener;


    /*I: create interface*/
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        /*I: this method is called into the employee fragment together with a listener*/
        empListener=listener;
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder{
        public ImageView empImgView;
        public TextView empName;
        public TextView empTel;
        public TextView empWage;
        public ImageView empImgViewDelete;

        public EmployeeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            empImgView = (ImageView)itemView.findViewById(R.id.employee_image_view_id);
            empName = (TextView)itemView.findViewById(R.id.employee_name_text_view_id);
            empTel = (TextView)itemView.findViewById(R.id.employee_telephone_text_view_id);
            empWage = (TextView)itemView.findViewById(R.id.employee_wage_text_view_id);
            empImgViewDelete = (ImageView)itemView.findViewById(R.id.employee_delete_image_view_id);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*I: here we are passing the click to the fragment*/
                    if (listener!=null){
                        int position = getAdapterPosition();
                        /*I: making sure that the position is valid*/
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            /*I: the button that allows the user to delete directly*/
            empImgViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    /*I: adapter constructor*/
    public AdminEmployeAdapter(ArrayList<EmployeeViewModel> employeeViewModels){
        employeeViewHolders=employeeViewModels;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*I: passing the layout*/
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item,parent,false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(v,empListener);
        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        EmployeeViewModel currentEmp = employeeViewHolders.get(position);
        holder.empImgView.setImageResource(currentEmp.getImgSrc());
        holder.empName.setText(currentEmp.getEmpName());
        holder.empTel.setText(currentEmp.getEmpTel());
        holder.empWage.setText(currentEmp.getEmWage());
        holder.empImgViewDelete.setImageResource(currentEmp.getDeleteImgSrc());

    }

    @Override
    public int getItemCount() {
        return employeeViewHolders.size();
    }


}
