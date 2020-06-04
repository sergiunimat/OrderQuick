package com.example.orderquick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
            final ArrayList<OrderModel> orm = dbH.GetPendingOrderList();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.employee_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.employee_logout_id:
                Intent login = new Intent(this,LogIn.class);
                startActivity(login);
                finish();
                return true;
            default:return false;
        }

    }
}
