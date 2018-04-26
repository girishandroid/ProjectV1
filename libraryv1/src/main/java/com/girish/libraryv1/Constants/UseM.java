package com.girish.libraryv1.Constants;

import com.google.gson.Gson;

public class UseM {
    public static <S> S useGson(String response,Class<S> tClass){
        Gson gson = new Gson();
        return gson.fromJson(response,tClass);
    }


}
