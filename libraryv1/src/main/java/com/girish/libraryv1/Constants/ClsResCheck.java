package com.girish.libraryv1.Constants;

import android.app.Activity;
import android.text.TextUtils;

import com.girish.libraryv1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Girish on 23/10/2017.
 */

public class ClsResCheck {
    Activity activity;
    public ClsResCheck(Activity activity){
        this.activity = activity;
        if(!(activity==null))
          responseMsg=activity.getString(R.string.CONNECTION_ERROR_MSG);
    }
    public String getResponseMsg() {
        return responseMsg;
    }

    public JSONObject getJo() {
        return jo;
    }

    private JSONObject jo;

    public boolean isExpire() {
        return isExpire;
    }

    public void setExpire(boolean expire) {
        isExpire = expire;
    }

    private boolean isExpire;

    public String responseMsg;

    public String getParams() {
        return params;
    }


    public String params = "";
    public boolean checkSuccess(String response){
        boolean success = true;
        try {
            JSONObject jsonResponse = new JSONObject(response);
            responseMsg = jsonResponse.getString("msg");
        }catch (Exception e){
            e.getLocalizedMessage();
        }
            if (TextUtils.isEmpty(response)|| Objects.equals(response, "")){
                success = false;
            }else {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                if(jsonResponse.getBoolean("Success")) {
                    success = true;
                }else {
                    success =false;
                }

                } catch (JSONException e) {
                    success=false;
                    e.printStackTrace();
                }
            }
        return success;
    }

}
