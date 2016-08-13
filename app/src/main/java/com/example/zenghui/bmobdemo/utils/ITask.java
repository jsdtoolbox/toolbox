package com.example.zenghui.bmobdemo.utils;


import com.example.zenghui.bmobdemo.model.LawyerInfo;
import com.example.zenghui.bmobdemo.model.PhoneResponse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by ricky on 15-10-9.
 */
public interface ITask {
    @GET("/mobile/get")
    Call<PhoneResponse> getPhoneAddress(@Query("phone") String phone, @Query("key") String key);

    @GET("/lawyers/city")
    Call<LawyerInfo> getLawyer(@Query("dtype") String dtype, @Query("st") int st, @Query("count") int count, @Query("city") String pro, @Query("key") String key);


}
