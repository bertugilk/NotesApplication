package com.example.notesapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared_Preferences {
    static final String PREF_NAME="Login";

    public void saveText(Context context, String key, String value){
        SharedPreferences settings=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public void saveBoolean(Context context, String key,Boolean value){
        SharedPreferences settings=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public String getUserName(Context context,String key){
        SharedPreferences settings=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        String text=settings.getString(key,null);
        return text;
    }
    public boolean getBooleanValue(Context context,String key){
        SharedPreferences settings=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        Boolean text=settings.getBoolean(key,false);
        return text;
    }
}
