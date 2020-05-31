package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AdminMealActivity extends BaseAppClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_meal);
        /*I: database helper instance*/
        final DBHelper dbH = new DBHelper(this);
        /*I: getting the meal id (of type int) from where its been sent (i.e. customer fragments)*/
        Intent mIntent = getIntent();
        final int mId = mIntent.getIntExtra("MEAL_ID", 0);
        /*I: init the meal model based on the passed it.*/
        MealModel mealModel = dbH.GetMealById(mId);

//        TextView test = (TextView)findViewById(R.id.test);
//        test.setText(mealModel.getMealName());
        final EditText mName = (EditText)findViewById(R.id.add_meal_name_id);
        EditText mPrice = (EditText)findViewById(R.id.add_meal_price_id);
        EditText mDescription = (EditText)findViewById(R.id.add_meal_description_id);
        ImageButton mImg = (ImageButton)findViewById(R.id.add_meal_picture_id);
        Button mEdit = (Button)findViewById(R.id.editMeal); 
        Button mDelete = (Button)findViewById(R.id.deleteMeal); 
        
        /*I default setting */
        RadioButton mCategory;
        switch (mealModel.getMealCategory()){
            case "Pizza":
                 mCategory = (RadioButton)findViewById(R.id.category_pizza_id);
                 mCategory.setChecked(true);
                 break;
            case "Pasta":
                mCategory = (RadioButton)findViewById(R.id.category_pasta_id);
                mCategory.setChecked(true);
                break;
            case "Dessert":
                mCategory = (RadioButton)findViewById(R.id.category_dessert_id);
                mCategory.setChecked(true);
                break;
            case "Vegetarian":
                mCategory = (RadioButton)findViewById(R.id.category_vegetarian_id);
                mCategory.setChecked(true);
                break;
        }
        mName.setText(mealModel.getMealName());
        mPrice.setText(mealModel.getMealPrice());
        mDescription.setText(mealModel.getMealDescription());
        mImg.setImageBitmap(mealModel.getMealImg());
        
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*I: call the db helper fn to edit the meal*/
                Toast.makeText(AdminMealActivity.this, "Edit meal", Toast.LENGTH_SHORT).show();
            }
        });
        
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbH.DeleteMealById(mId);
                Intent back = new Intent(AdminMealActivity.this,MainAdminActivity.class);
                startActivity(back);
                finish();
                if (result){
                    Toast.makeText(AdminMealActivity.this, "("+mName.getText()+") deleted from the database", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AdminMealActivity.this, "("+mName.getText()+") was NOT deleted from the database", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
