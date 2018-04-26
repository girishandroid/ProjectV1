package com.girish.libraryv1.Constants;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Girish on 26/10/2017.
 */

public class ClsSharedPreference
{
    private SharedPreferences sp;

    public ClsSharedPreference(Activity activity)
    {
        sp = activity.getSharedPreferences(Constants.PREF_NAME_1,MODE_PRIVATE);
    }

    public ClsSharedPreference(Fragment fragment)
    {
        sp = fragment.getActivity().getSharedPreferences(Constants.PREF_NAME_1,MODE_PRIVATE);
    }

    public ClsSharedPreference(android.support.v4.app.Fragment fragment)
    {
        sp = fragment.getActivity().getSharedPreferences(Constants.PREF_NAME_1,MODE_PRIVATE);
    }

    public void setSharedValue(String key, boolean value)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void setSharedValue(String key, String value)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setSharedValue(String key, int value)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String str)
    {
        return sp.getBoolean(str,false);
    }

    public int getIntValue(String str)
    {
        return sp.getInt(str,0);
    }

    public String getStringValue(String str)
    {
        return sp.getString(str,"");
    }

    public void clearAllSharedData()
    {
        sp.edit().clear().apply();
    }
}
