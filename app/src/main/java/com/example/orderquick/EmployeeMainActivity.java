package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeMainActivity extends BaseAppClass {
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);
        DBHelper dbH = new DBHelper(this);

        if (APPLICATION_CURRENT_USER.getPassword().equals("employee")){
            Intent redirect  = new Intent(this,CustomerProfile.class);
            startActivity(redirect);
        }
        {
            final ArrayList<OrderModel> orm = dbH.GetOrderList();
            recyclerView=findViewById(R.id.orders_recyclerview);
            recyclerView.setHasFixedSize(true);
            manager= new LinearLayoutManager(this);
            adapter= new OrderAdapter(orm);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    OrderModel om= orm.get(position);
                    Intent go = new Intent(EmployeeMainActivity.this,OrderActivity.class);
                    go.putExtra("OrderObj",om);
                    startActivity(go);
                }
            });

        }
    }
}
