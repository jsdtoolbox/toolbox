package com.example.zenghui.bmobdemo.model;

import com.example.zenghui.bmobdemo.utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zenghui on 2016/7/17.
 */
public class CookieCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Common.setCookies(response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
