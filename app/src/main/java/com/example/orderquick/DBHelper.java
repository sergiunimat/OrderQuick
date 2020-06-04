package com.example.orderquick;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER = "Customer";
    public static final String CUSTOMER_NAME = "CustomerName";
    public static final String CUSTOMER_PASSWORD = "CustomerPassword";
    public static final String CUSTOMER_TELEPHONE_NUMBER = "CustomerTelephoneNumber";
    public static final String CUSTOMER_ROLE = "CustomerRole";
    public static final String CUSTOMER_ID = "CustomerId";
    public static final String CUSTOMER_WAGE = "CustomerWage";

    public static final String MEAL = "Meal";
    public static final String MEAL_NAME = "MealName";
    public static final String MEAL_PRICE = "MealPrice";
    public static final String MEAL_DESCRIPTION = "MealDescription";
    public static final String MEAL_CATEGORY= "MealCategory";
    public static final String MEAL_ID = "MealId";
    public static final String MEAL_IMG = "MealImg";

    public static final String ORDERS = "Orders";
    public static final String ORDER_ID = "OrderId";
    public static final String ORDER_CUSTOMER_ID = "OrderCustomerId";
    public static final String ORDER_LIST = "OrderList";
    public static final String PENDING = "Pending";
    public static final String SEEN = "Seen";


    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] mealImgInByte;

    public DBHelper(@Nullable Context context) {
        super(context, "db.TestDbbBBB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*I: create customer table*/
        String customerTable = "CREATE TABLE " + CUSTOMER + "" +
                "(" + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CUSTOMER_NAME + " TEXT," +
                    CUSTOMER_PASSWORD + " TEXT," +
                    CUSTOMER_TELEPHONE_NUMBER + " TEXT," +
                    CUSTOMER_ROLE + " INT,"+
                CUSTOMER_WAGE +" TEXT)";
        db.execSQL(customerTable);

        /*I: create power-user admin*/
        String powerUser = "INSERT INTO "+CUSTOMER+" ("+
                CUSTOMER_NAME+", "+
                CUSTOMER_PASSWORD+", "+
                CUSTOMER_TELEPHONE_NUMBER+", "+
                CUSTOMER_ROLE+") VALUES ('admin','admin','1234567', 0)";
        db.execSQL(powerUser);

        /*I: create meal table*/
        String mealTable = "CREATE TABLE " + MEAL + "" +
                "(" + MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MEAL_NAME + " TEXT," +
                    MEAL_PRICE + " TEXT," +
                    MEAL_DESCRIPTION + " TEXT," +
                    MEAL_CATEGORY + " TEXT,"+
                    MEAL_IMG +" BLOB)";
        db.execSQL(mealTable);

        /*I: create order table*/
        String orderTable = "CREATE TABLE  " + ORDERS + " " +
                "(" + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ORDER_CUSTOMER_ID + " INTEGER," +
                ORDER_LIST + " TEXT," +
                PENDING + " INTEGER," +
                SEEN + " INTEGER)";
        db.execSQL(orderTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public CustomerModel GetCustomerById(int customerId){
        SQLiteDatabase db = this.getReadableDatabase();
        CustomerModel retCust = new CustomerModel();
        String query = "SELECT * FROM "+CUSTOMER+" WHERE "+CUSTOMER_ID+" = "+customerId;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                int custId= cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerPassword = cursor.getString(2);
                String customerTelephone = cursor.getString(3);
                int customerRoleId= cursor.getInt(4);
                String customerWage = cursor.getString(5);
                /*I: create a customer object*/
                CustomerModel customerModel = new CustomerModel(
                        custId,
                        customerName,
                        customerPassword,
                        customerTelephone,
                        customerRoleId,
                        customerWage);
                retCust=customerModel;
            }while (cursor.moveToNext());
        }else {/*I: code if there isnt any result*/}
        cursor.close();
        db.close();
        return retCust;
    }

    //add customer to database when register (default role.3)
    public boolean AddCustomerToDb(CustomerModel customerModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_NAME,customerModel.getCustomerName());
        contentValues.put(CUSTOMER_PASSWORD,customerModel.getPassword());
        contentValues.put(CUSTOMER_TELEPHONE_NUMBER,customerModel.getTelephoneNumber());
        contentValues.put(CUSTOMER_ROLE,customerModel.getRole());
        contentValues.put(CUSTOMER_WAGE,customerModel.getWage());
        long insert = db.insert(CUSTOMER, null, contentValues);
        db.close();
        if (insert>=1){ return true; }
        else { return false;}
    }

    //check if tel nr is unique
    public boolean UniqueUserTel(String telephoneNumer){
        SQLiteDatabase db = this.getReadableDatabase();
        String query =  "SELECT * FROM " + CUSTOMER +
                        " WHERE " + CUSTOMER_TELEPHONE_NUMBER +
                        " = " + telephoneNumer;
        Cursor cursor = db.rawQuery(query,null);
//        cursor.close();
//        db.close();
        if(cursor.moveToFirst()){return true;}
        else {return false;}
    }

    public CustomerModel  Login(String username,String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+CUSTOMER+
                " WHERE " +CUSTOMER_NAME+" = '" +username+
                "' AND "+CUSTOMER_PASSWORD+" = '"+password+"'";
        Cursor cursor = db.rawQuery(query,null);
        CustomerModel dbuser = new CustomerModel();
        if (cursor.moveToFirst()){
            dbuser.setCustomerId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CUSTOMER_ID))));
            dbuser.setCustomerName(cursor.getString(cursor.getColumnIndex(CUSTOMER_NAME)));
            dbuser.setPassword(cursor.getString(cursor.getColumnIndex(CUSTOMER_PASSWORD)));
            dbuser.setTelephoneNumber(cursor.getString(cursor.getColumnIndex(CUSTOMER_TELEPHONE_NUMBER)));
            dbuser.setRole(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CUSTOMER_ROLE))));
            dbuser.setWage(cursor.getString(cursor.getColumnIndex(CUSTOMER_WAGE)));

        }
        cursor.close();
        db.close();
        return dbuser;
    }

    /*I: function that enables the user to modify its data.*/
    public boolean EditCustomer(CustomerModel customerModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_NAME,customerModel.getCustomerName());
        contentValues.put(CUSTOMER_PASSWORD,customerModel.getPassword());
        contentValues.put(CUSTOMER_TELEPHONE_NUMBER,customerModel.getTelephoneNumber());
        contentValues.put(CUSTOMER_ROLE,customerModel.getRole());
        contentValues.put(CUSTOMER_WAGE,customerModel.getWage());
        int i =  db.update(CUSTOMER, contentValues, CUSTOMER_ID + "=" + customerModel.getCustomerId(), null);
        db.close();
        if (i>=1){ return true; }
        else { return false;}
    }

    /*I: get all customers from the database*/
    public ArrayList<CustomerModel> GetAllCustomers(){
        ArrayList<CustomerModel>customerModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+CUSTOMER+
                        " Where "+CUSTOMER_ROLE + " = 3";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int customerId= cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerPassword = cursor.getString(2);
                String customerTelephone = cursor.getString(3);
                int customerRoleId= cursor.getInt(4);
                String customerWage = cursor.getString(5);
                /*I: create a customer object*/
                CustomerModel customerModel = new CustomerModel(
                        customerId,
                        customerName,
                        customerPassword,
                        customerTelephone,
                        customerRoleId,
                        customerWage);
                /*I: add customer to the list*/
                customerModels.add(customerModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return customerModels;
    }

    /*I: delete user by unique telephone number*/
    public boolean DeleteCustomerByTelephoneNumber(String telephoneNumber){
        SQLiteDatabase db =this.getWritableDatabase();
        int result = db.delete(CUSTOMER, CUSTOMER_TELEPHONE_NUMBER + " = " + telephoneNumber, null);
        db.close();
        if (result>=1){return true;}
        else {return false;}
    }

    public boolean DeleteCustomerById(int custId){
        SQLiteDatabase db =this.getWritableDatabase();
        int result = db.delete(CUSTOMER, CUSTOMER_ID + " = " + custId, null);
        db.close();
        if (result>=1){return true;}
        else {return false;}
    }

    /*I: get all employees from the database*/
    public ArrayList<CustomerModel> GetAllEmployees(){
        ArrayList<CustomerModel>customerModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+CUSTOMER+
                " Where "+CUSTOMER_ROLE + " = 2";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int customerId= cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerPassword = cursor.getString(2);
                String customerTelephone = cursor.getString(3);
                int customerRoleId= cursor.getInt(4);
                String customerWage = cursor.getString(5);
                /*I: create a customer object*/
                CustomerModel customerModel = new CustomerModel(
                        customerId,
                        customerName,
                        customerPassword,
                        customerTelephone,
                        customerRoleId,
                        customerWage);
                /*I: add customer to the list*/
                customerModels.add(customerModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return customerModels;
    }

    public boolean AddMealToDb(MealModel meal){
        SQLiteDatabase db =this.getWritableDatabase();
        Bitmap tempImg = meal.getMealImg();
        byteArrayOutputStream=new ByteArrayOutputStream();

        /*convert image to byte array*/
        tempImg.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        mealImgInByte=byteArrayOutputStream.toByteArray();

        /*I: add in to content values*/
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEAL_NAME,meal.getMealName());
        contentValues.put(MEAL_PRICE,meal.getMealPrice());
        contentValues.put(MEAL_DESCRIPTION,meal.getMealDescription());
        contentValues.put(MEAL_CATEGORY,meal.getMealCategory());
        /*I: here we add the picture*/
        contentValues.put(MEAL_IMG,mealImgInByte);
        long insert = db.insert(MEAL, null, contentValues);
        db.close();
        if (insert>=1){ return true; }
        else { return false;}
    }

    public ArrayList<MealModel> GetAllMeals(){
        ArrayList<MealModel>mealModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MEAL ;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {

                /*I: get the raw data from database*/
                int mealId= cursor.getInt(0);
                String mealName = cursor.getString(1);
                String mealPrice = cursor.getString(2);
                String mealDescription = cursor.getString(3);
                String mealCategory = cursor.getString(4);
                byte[] mealImg = cursor.getBlob(5);

                /*I: Convert the img to Bitmap*/
                Bitmap mealPic = BitmapFactory.decodeByteArray(mealImg,0,mealImg.length);

                /*I: create a customer object*/
                MealModel mealModel = new MealModel(
                        mealId,
                        mealName,
                        mealPrice,
                        mealDescription,
                        mealCategory,
                        mealPic);
                /*I: add customer to the list*/
                mealModels.add(mealModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return mealModels;
    }

    public ArrayList<MealModel> GetAllPastaMeals(){
        ArrayList<MealModel>mealModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MEAL +" WHERE "+MEAL_CATEGORY+" = 'Pasta'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {

                /*I: get the raw data from database*/
                int mealId= cursor.getInt(0);
                String mealName = cursor.getString(1);
                String mealPrice = cursor.getString(2);
                String mealDescription = cursor.getString(3);
                String mealCategory = cursor.getString(4);
                byte[] mealImg = cursor.getBlob(5);

                /*I: Convert the img to Bitmap*/
                Bitmap mealPic = BitmapFactory.decodeByteArray(mealImg,0,mealImg.length);

                /*I: create a customer object*/
                MealModel mealModel = new MealModel(
                        mealId,
                        mealName,
                        mealPrice,
                        mealDescription,
                        mealCategory,
                        mealPic);
                /*I: add customer to the list*/
                mealModels.add(mealModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return mealModels;
    }

    public ArrayList<MealModel> GetAllPizzaMeals(){
        ArrayList<MealModel>mealModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MEAL +" WHERE "+MEAL_CATEGORY+" = 'Pizza'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {

                /*I: get the raw data from database*/
                int mealId= cursor.getInt(0);
                String mealName = cursor.getString(1);
                String mealPrice = cursor.getString(2);
                String mealDescription = cursor.getString(3);
                String mealCategory = cursor.getString(4);
                byte[] mealImg = cursor.getBlob(5);

                /*I: Convert the img to Bitmap*/
                Bitmap mealPic = BitmapFactory.decodeByteArray(mealImg,0,mealImg.length);

                /*I: create a customer object*/
                MealModel mealModel = new MealModel(
                        mealId,
                        mealName,
                        mealPrice,
                        mealDescription,
                        mealCategory,
                        mealPic);
                /*I: add customer to the list*/
                mealModels.add(mealModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return mealModels;
    }

    public ArrayList<MealModel> GetAllVegetarianMeals(){
        ArrayList<MealModel>mealModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MEAL +" WHERE "+MEAL_CATEGORY+" = 'Vegetarian'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {

                /*I: get the raw data from database*/
                int mealId= cursor.getInt(0);
                String mealName = cursor.getString(1);
                String mealPrice = cursor.getString(2);
                String mealDescription = cursor.getString(3);
                String mealCategory = cursor.getString(4);
                byte[] mealImg = cursor.getBlob(5);

                /*I: Convert the img to Bitmap*/
                Bitmap mealPic = BitmapFactory.decodeByteArray(mealImg,0,mealImg.length);

                /*I: create a customer object*/
                MealModel mealModel = new MealModel(
                        mealId,
                        mealName,
                        mealPrice,
                        mealDescription,
                        mealCategory,
                        mealPic);
                /*I: add customer to the list*/
                mealModels.add(mealModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return mealModels;
    }

    public ArrayList<MealModel> GetAllDesertMeals(){
        ArrayList<MealModel>mealModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MEAL +" WHERE "+MEAL_CATEGORY+" = 'Dessert'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {

                /*I: get the raw data from database*/
                int mealId= cursor.getInt(0);
                String mealName = cursor.getString(1);
                String mealPrice = cursor.getString(2);
                String mealDescription = cursor.getString(3);
                String mealCategory = cursor.getString(4);
                byte[] mealImg = cursor.getBlob(5);

                /*I: Convert the img to Bitmap*/
                Bitmap mealPic = BitmapFactory.decodeByteArray(mealImg,0,mealImg.length);

                /*I: create a customer object*/
                MealModel mealModel = new MealModel(
                        mealId,
                        mealName,
                        mealPrice,
                        mealDescription,
                        mealCategory,
                        mealPic);
                /*I: add customer to the list*/
                mealModels.add(mealModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return mealModels;
    }

    public boolean DeleteMealById(int mealId){
        SQLiteDatabase db =this.getWritableDatabase();
        int result = db.delete(MEAL, MEAL_ID + " = " + mealId, null);
        db.close();
        if (result>=1){return true;}
        else {return false;}
    }

    public MealModel GetMealById(int mealID){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MEAL + " WHERE " + MEAL_ID + " = " + mealID;
        Cursor cursor = db.rawQuery(query,null);
        MealModel mealModel = new MealModel();
        if (cursor.moveToFirst()){
            do {
                /*I: get the raw data from database*/
                int mealId= cursor.getInt(0);
                String mealName = cursor.getString(1);
                String mealPrice = cursor.getString(2);
                String mealDescription = cursor.getString(3);
                String mealCategory = cursor.getString(4);
                byte[] mealImg = cursor.getBlob(5);

                /*I: Convert the img to Bitmap*/
                Bitmap mealPic = BitmapFactory.decodeByteArray(mealImg,0,mealImg.length);

                /*I: create a customer object*/
                mealModel = new MealModel(
                        mealId,
                        mealName,
                        mealPrice,
                        mealDescription,
                        mealCategory,
                        mealPic);
            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return mealModel;
    }


    public boolean AddOrderToDb(OrderModel orderModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_CUSTOMER_ID,orderModel.getCustomerId());
        contentValues.put(ORDER_LIST,orderModel.getOrderList());
        contentValues.put(PENDING,orderModel.getPending());
        contentValues.put(SEEN,orderModel.getSeen());
        long insert = db.insert(ORDERS, null, contentValues);
        db.close();
        if (insert>=1){ return true; }
        else { return false;}
    }


    public ArrayList<OrderModel> GetOrderList(){
        ArrayList<OrderModel>orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ORDERS;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int orderId= cursor.getInt(0);
                int customerId = cursor.getInt(1);
                String ordersList = cursor.getString(2);
                int pending = cursor.getInt(3);
                int seen= cursor.getInt(4);
                /*I: create a Order object*/
                OrderModel orderModel = new OrderModel(orderId,customerId,ordersList,pending,seen);
                /*I: add order to the list*/
                orderList.add(orderModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return orderList;

    }

    public ArrayList<OrderModel> GetPendingOrderList(){
        ArrayList<OrderModel>orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ORDERS+" WHERE "+PENDING +" = "+ 1;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int orderId= cursor.getInt(0);
                int customerId = cursor.getInt(1);
                String ordersList = cursor.getString(2);
                int pending = cursor.getInt(3);
                int seen= cursor.getInt(4);
                /*I: create a Order object*/
                OrderModel orderModel = new OrderModel(orderId,customerId,ordersList,pending,seen);
                /*I: add order to the list*/
                orderList.add(orderModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return orderList;
    }


    public ArrayList<OrderModel> GetOrderListByCustomerId(int cId){
        ArrayList<OrderModel>orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ORDERS+" WHERE "+ORDER_CUSTOMER_ID+" = "+cId;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int orderId= cursor.getInt(0);
                int customerId = cursor.getInt(1);
                String ordersList = cursor.getString(2);
                int pending = cursor.getInt(3);
                int seen= cursor.getInt(4);
                /*I: create a Order object*/
                OrderModel orderModel = new OrderModel(orderId,customerId,ordersList,pending,seen);
                /*I: add order to the list*/
                orderList.add(orderModel);


            }while (cursor.moveToNext());
        }
        else {
            /*I: in case the array is empty, here logic can be inserted if needed.*/
        }
        cursor.close();
        db.close();
        return orderList;

    }

    public boolean UpdateOrder(OrderModel orderModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_CUSTOMER_ID,orderModel.getCustomerId());
        contentValues.put(ORDER_LIST,orderModel.getOrderList());
        contentValues.put(PENDING,0);
        contentValues.put(SEEN,orderModel.getSeen());
        int i =  db.update(ORDERS, contentValues, ORDER_ID + "=" + orderModel.getOrderId(), null);
        db.close();
        if (i>=1){ return true; }
        else { return false;}
    }

}
