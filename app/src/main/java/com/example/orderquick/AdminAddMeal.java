package com.example.orderquick;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AdminAddMeal extends BaseAppClass {

    private EditText mealName;
    private EditText mealPrice;
    private EditText mealDescription;
    private RadioGroup mealRadioGroup;
    private String mealCategory;
    private ImageButton mealImg;
    private Button mealAddMeal;
    private static final int PICK_IMG_REQ = 1 ;
    private Uri mealImageUri;
    private Bitmap mealUriBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_meal);
        /*I: db instance*/
        DBHelper dbH = new DBHelper(this);

        /*I: init vars*/
        mealRadioGroup = (RadioGroup)findViewById(R.id.radbtngroup);
        mealName = (EditText)findViewById(R.id.add_meal_name_id);
        mealPrice = (EditText)findViewById(R.id.add_meal_price_id);
        mealDescription = (EditText)findViewById(R.id.add_meal_description_id);

        mealImg = (ImageButton)findViewById(R.id.add_meal_picture_id);
        mealAddMeal = (Button)findViewById(R.id.add_meal_button_id);


        mealAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId =mealRadioGroup.getCheckedRadioButtonId();
                findRadioButton(radioId);

            }
        });
    }

    /*I: this method based on what radiobutton is chosen will generate the meal category*/
    private void findRadioButton(int radioId) {
        switch (radioId){
            case R.id.category_pizza_id:
                mealCategory = "Pizza";
                break;
            case R.id.category_pasta_id:
                mealCategory = "Pasta";
                break;
            case R.id.category_dessert_id:
                mealCategory = "Dessert";
                break;
            case R.id.category_vegetarian_id:
                mealCategory = "Vegetarian";
                break;

        }
    }

    /*I: fn to select picture from device*/
    public void selectImage(View v){
        try {
            Intent imgIntent = new Intent();
            /*I: allow user to select all type of images*/
            imgIntent.setType("image/*");
            imgIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(imgIntent,PICK_IMG_REQ);
        }catch (Exception e){
            Log.i("",e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==PICK_IMG_REQ && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                mealImageUri=data.getData();
                /*I: next we store the img in Bitmap*/
                mealUriBitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),mealImageUri);
                mealImg.setImageBitmap(mealUriBitmap);
            }
        }catch (Exception e){
        Log.i("",e.toString());
    }

    }
}
