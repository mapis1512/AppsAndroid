package com.example.a71.httpapplication.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 71 on 7/15/2017.
 */

public class MyPreferences {

    public final String MY_PREFERENCES="my_pref";
    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPreferences;

    public MyPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUsername(String username){
        editor.putString("username",username);
        editor.apply();
    }
    public String getUsername(){
        return sharedPreferences.getString("username","");
    }
}
