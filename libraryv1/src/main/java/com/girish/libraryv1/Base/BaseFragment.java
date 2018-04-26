package com.girish.libraryv1.Base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.girish.libraryv1.Constants.ClsResCheck;
import com.girish.libraryv1.Constants.ClsSharedPreference;
import com.girish.libraryv1.Constants.Constants;
import com.girish.libraryv1.Constants.DialogUtil;
import com.girish.libraryv1.Implements.CallBackServerRes;

import java.util.HashMap;

public class BaseFragment extends Fragment implements CallBackServerRes {


    public DialogUtil dialogUtil;
    protected ClsSharedPreference sharedPreference;
    protected RecyclerView rvMain;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogUtil = new DialogUtil(this);
        sharedPreference = new ClsSharedPreference(this);
    }



    protected void setRVManager(){
        try {
            GridLayoutManager manager = new GridLayoutManager(getActivity(),4);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
                @Override
                public int getSpanSize(int position) {
                    return 4;
                }
            });
            rvMain.setLayoutManager(manager);
        }catch (Exception e){
            e.getLocalizedMessage();
        }

    }
    protected void setRVManager(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        try {
            GridLayoutManager manager = new GridLayoutManager(getActivity(),4);
            manager.setSpanSizeLookup(spanSizeLookup);
            rvMain.setLayoutManager(manager);
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }

    protected void setRVManager(final int num) {
        try {
            GridLayoutManager manager = new GridLayoutManager(getActivity(),4);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return num;
                }
            });
            rvMain.setLayoutManager(manager);
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }

    @Override
    public void CallBackServerRes(String response, int key) {
        if(Constants.IS_PRINT_LOG) Log.d(this.getTag(), response);
        onDirectResponse(response,key);
        try {
            ClsResCheck resCheck = new ClsResCheck(getActivity());
            if (resCheck.checkSuccess(response)) {
                onSuccessServerRes(response, key);
                onSuccessServerRes(response, key,resCheck);
            } else {
                onErrorServerRes(response, key);
                onErrorServerRes(response, key,resCheck);
            }
        }catch (Exception e){
            dialogUtil.dismissProgress();
            e.getLocalizedMessage();
        }
    }

    protected void onErrorServerRes(String response, int key, ClsResCheck resCheck) {
    }

    protected void onSuccessServerRes(String response, int key, ClsResCheck resCheck) {
    }


    @Override
    public void CallBackServerRes(String response, String key) {
        if(Constants.IS_PRINT_LOG) Log.d(this.getTag(), response);
        onDirectResponse(response,key);
        try {
            ClsResCheck resCheck = new ClsResCheck(getActivity());
            if (resCheck.checkSuccess(response)) {
                onSuccessServerRes(response, key);
            } else {
                onErrorServerRes(response, key);
            }
        }catch (Exception e){
            e.getLocalizedMessage();
            dialogUtil.dismissProgress();
            dialogUtil.showErrDialogOk(Constants.CONNECTION_ERROR_MSG);
        }
    }

    @Override
    public void CallBackServerRes(String response, String key, HashMap<String, String> passedParams) {
        if(Constants.IS_PRINT_LOG) Log.d(this.getTag(), response);
        onDirectResponse(response,key,passedParams);
        try {
            ClsResCheck resCheck = new ClsResCheck(getActivity());
            if (resCheck.checkSuccess(response)) {
                onSuccessServerRes(response, key,passedParams);
            } else {
                onErrorServerRes(response, key,passedParams);

            }
        }catch (Exception e){
            e.getLocalizedMessage();
            dialogUtil.dismissProgress();
        }
    }

    protected void onErrorServerRes(String response, int key) {

    }

    protected void onSuccessServerRes(String response, int key) {
    }


    protected void onDirectResponse(String response, int key) {

    }


    protected void onErrorServerRes(String response, String key,HashMap<String,String> passedParams) {

    }

    protected void onSuccessServerRes(String response, String key,HashMap<String,String> passedParams) {

    }
    protected void onDirectResponse(String response, String key,HashMap<String,String> passedParams) {

    }

    protected void onErrorServerRes(String response, String key) {

    }

    protected void onSuccessServerRes(String response, String key) {

    }
    protected void onDirectResponse(String response, String key) {

    }

}

