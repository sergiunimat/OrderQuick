package com.example.orderquick;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER = "Customer";
    public static final String CUSTOMER_NAME = "CustomerName";
    public static final String CUSTOMER_PASSWORD = "CustomerPassword";
    public static final String CUSTOMER_TELEPHONE_NUMBER = "CustomerTelephoneNumber";
    public static final String CUSTOMER_ROLE = "CustomerRole";
    public static final String CUSTOMER_ID = "CustomerId";

    public DBHelper(@Nullable Context context) {
        super(context, "db.OrderQuick", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String customerTable = "CREATE TABLE " + CUSTOMER + "" +
                "(" + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CUSTOMER_NAME + " TEXT," +
                    CUSTOMER_PASSWORD + " TEXT," +
                    CUSTOMER_TELEPHONE_NUMBER + " TEXT," +
                    CUSTOMER_ROLE + " INT)";

        db.execSQL(customerTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean AddCustomerToDb(CustomerModel customerModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_NAME,customerModel.getCustomerName());
        contentValues.put(CUSTOMER_PASSWORD,customerModel.getPassword());
        contentValues.put(CUSTOMER_TELEPHONE_NUMBER,customerModel.getTelephoneNumber());
        contentValues.put(CUSTOMER_ROLE,customerModel.getRole());
        long insert = db.insert(CUSTOMER, null, contentValues);
        if (insert==1){ return true; }
        else { return false;}
    }

    public boolean UniqueUserTel(String telephoneNumer){
        SQLiteDatabase db = this.getReadableDatabase();
        String query =  "SELECT * FROM " + CUSTOMER +
                        " WHERE " + CUSTOMER_TELEPHONE_NUMBER +
                        " = " + telephoneNumer;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){return true;}
        else {return false;}
    }
}
