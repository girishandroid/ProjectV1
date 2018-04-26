package com.girish.libraryv1.Implements;

import java.util.HashMap;

/**
 * Created by Girish on 23/10/2017.
 */

public interface CallBackServerRes {
    void CallBackServerRes(String response, int key);
    void CallBackServerRes(String response, String key);
    void CallBackServerRes(String response, String key, HashMap<String, String> passedParams);
}
