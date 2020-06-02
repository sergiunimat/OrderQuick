package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BaseAppClass  extends AppCompatActivity {

    /*I: This object holds the logged in user throughout the entire session*/
    public static CustomerModel APPLICATION_CURRENT_USER = null;
    /*I: This var represents the number of elements within the ORDER_LIST*/
    public static int TROLLEY_NOTIFICATION = 0;
    /*I: This array list contains the meals added to to the order.*/
    public static ArrayList<MealModel> ORDER_LIST=new ArrayList<>();

    public static int ORDER_TOTAL=0;


    public static String MealIdsToString(ArrayList<MealModel> array) {
        String result = "";
        if (array.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (MealModel s : array){

                    sb.append(s.getMealId()).append(",");
            }
                result = sb.deleteCharAt(sb.length() - 1).toString();
            }
        return result;
    }



}
