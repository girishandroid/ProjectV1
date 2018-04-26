package com.girish.libraryv1.Constants;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.girish.libraryv1.R;

import org.json.JSONException;
import org.json.JSONObject;


public class DialogUtil {

    private Context context;
    private AlertDialog.Builder dialog;
    private ProgressDialog progressDialog;
    private EditText editText;

    /**
     * Constructor for dialog util
     * @param context
     */
    public DialogUtil(Context context) {
        this.context = context;
    }

    public DialogUtil(Fragment context) {
        this.context = context.getActivity();
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * To get recent edit text (for dialog with edittext only)
     * @return
     */
    public EditText getEditText() {
        return editText;
    }

    /**
     * Show loading dialog with custom message
     * @param msg
     */
    public void showProgressDialog(String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void showToastCenter(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Dismiss current shows dialog
     */
    public void dismissProgress(){
        try {
            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }

    /**
     * Dialog to show message with ok and custom button listener
     * @param msg
     * @param okListener
     */
    public void showDialogOk(String msg, DialogInterface.OnClickListener okListener) {
        dialog = new AlertDialog.Builder(context);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.setPositiveButton(android.R.string.ok, okListener);
        dialog.show();
    }

    /**
     * Dialog to show message with ok and custom button listener
     * @param msg
     * @param okListener
     */
    public void showDialogOk(String title, String msg, DialogInterface.OnClickListener okListener) {
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.setPositiveButton(android.R.string.ok, okListener);
        dialog.setNegativeButton(context.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * Dialog to show ok button with custom message
     * @param msg
     */
    public void showDialogOk(String msg) {
        try {
            if(context == null)
                return;

            dialog = new AlertDialog.Builder(context);
            dialog.setMessage(msg);
            dialog.setCancelable(false);
            dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialog.show();
        }catch (Exception e){
            e.getLocalizedMessage();
        }

    }

    /**
     * Dialog to show ok button with custom message
     * @param msg
     */
    public void showErrDialogOk(String msg) {
        if(context == null)
            return;

        dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Error!");
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * Dialog to show yes no button with custom message with yes callback
     * @param msg
     * @param yesListener
     */
    public void showDialogYesNo(String msg, DialogInterface.OnClickListener yesListener) {
        dialog = new AlertDialog.Builder(context);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.setPositiveButton(android.R.string.yes, yesListener);
        dialog.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * Dialog with custom message and options with its callback
     * @param msg
     * @param option
     * @param optionListener
     */
    public void showDialogOption(String msg, CharSequence[] option,
                                 DialogInterface.OnClickListener optionListener){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(msg);
        dialog.setItems(option, optionListener);
        dialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * Dialog with edittext and custom message with customisation and callback
     * @param context
     * @param title
     * @param editTextHint
     * @param value
     * @param allCaps
     * @param yesButton
     * @param yesClickListener
     * @param noButton
     * @param noClickListener
     */
    public void showDialogWithEditText(Context context, String title, String editTextHint,
                                       String value, boolean allCaps, String yesButton,
                                       DialogInterface.OnClickListener yesClickListener,
                                       String noButton,
                                       @Nullable DialogInterface.OnClickListener noClickListener){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        editText = new EditText(context);

        if(allCaps){
            editText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        }

        if(editTextHint != null){
            editText.setHint(editTextHint);
        }
        if(value != null){
            editText.setText(value);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(lp);
        dialog.setView(editText);
        dialog.setPositiveButton(yesButton, yesClickListener);
        if(noClickListener == null){
            dialog.setNegativeButton(noButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }else {
            dialog.setNegativeButton(noButton, noClickListener);
        }
        dialog.show();
    }


    /**
     * Dialog with edittext and custom message with customisation and callback
     * @param context
     * @param title
     * @param editTextHint
     * @param value
     * @param allCaps
     * @param yesButton
     * @param yesClickListener
     * @param noButton
     * @param noClickListener
     */
    public void showDialogWithEditText(Context context, String title, String editTextHint,
                                       String value, boolean allCaps, String yesButton,
                                       DialogInterface.OnClickListener yesClickListener,
                                       String noButton,
                                       @Nullable DialogInterface.OnClickListener noClickListener,int inputType){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        editText = new EditText(context);

        if(allCaps){
            editText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        }

        if(editTextHint != null){
            editText.setHint(editTextHint);
        }
        if(value != null){
            editText.setText(value);
        }

        editText.setInputType(inputType);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(10,0,10,0);
        editText.setLayoutParams(lp);

        TextInputLayout inputLayout = new TextInputLayout(context);
        inputLayout.setLayoutParams(lp);
        inputLayout.addView(editText);
        dialog.setView(inputLayout);
        dialog.setPositiveButton(yesButton, yesClickListener);
        if(noClickListener == null){
            dialog.setNegativeButton(noButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }else {
            dialog.setNegativeButton(noButton, noClickListener);
        }
        dialog.show();
    }

    public void showDialogWithEditText(Context context, String title, String editTextHint,
                                       String value, boolean allCaps, String yesButton,
                                       DialogInterface.OnClickListener yesClickListener,
                                       String noButton,
                                       @Nullable DialogInterface.OnClickListener noClickListener,int inputType,boolean withDialog){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        editText = new EditText(context);

        if(allCaps){
            editText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        }

        if(editTextHint != null){
            editText.setHint(editTextHint);
        }
        if(value != null){
            editText.setText(value);
        }

        editText.setInputType(inputType);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(10,0,10,0);
        editText.setLayoutParams(lp);

        TextInputLayout inputLayout = new TextInputLayout(context);
        inputLayout.setLayoutParams(lp);
        inputLayout.addView(editText);
        dialog.setView(inputLayout);
        dialog.setPositiveButton(yesButton, yesClickListener);
        if(noClickListener == null){
            dialog.setNegativeButton(noButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    d.dismiss();
                }
            });
        }else {
            dialog.setNegativeButton(noButton, noClickListener);
        }
        d = dialog.create();
        d.show();
    }


    public Dialog getD() {
        return d;
    }

    Dialog d;
    public void setView(Activity a, String title, View v){

        /*android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(a);
        alertDialog.setView(v);
        alertDialog.setNegativeButton(noButton,noClickListener);
        alertDialog.setPositiveButton(yesButton,yesClickListener);

        d = alertDialog.create();
        d.setCancelable(false);
        d.show();*/
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setView(v);
        dialog.setCancelable(false);
        d = dialog.create();
        d.show();
    }
    public JSONObject getObj(JSONObject object,String key){
        String info = null;
        try {
            info = object.getString(key);
            JSONObject object1 = new JSONObject(info);
            return object1;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    public boolean checkStringEmpty(String str){
        return TextUtils.isEmpty(str) || str.equals("") || str.equals("null");
    }


    public boolean checkStringEmpty(int id) {
        try {
            String.valueOf(id);
            return false;
        }catch (Exception e){
            return true;
        }

    }
}
