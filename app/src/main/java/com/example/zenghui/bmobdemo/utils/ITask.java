package com.example.zenghui.bmobdemo.utils;


import com.example.zenghui.bmobdemo.model.CommonListResponse;
import com.example.zenghui.bmobdemo.model.CommonResponse;
import com.example.zenghui.bmobdemo.model.LawyerInfo;
import com.example.zenghui.bmobdemo.model.PhoneResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by ricky on 15-10-9.
 */
public interface ITask {
    @GET("/mobile/get")
    Call<PhoneResponse> getPhoneAddress(@Query("phone") String phone, @Query("key") String key);

    @GET("/lawyers/city")
    Call<LawyerInfo> getLawyer(@Query("dtype") String dtype, @Query("st") int st, @Query("count") int count, @Query("city") String pro, @Query("key") String key);

    @GET("/idcard/index")
    Call<CommonResponse> getIdentity(@Query("key") String key, @Query("cardno") String cardno);

    @GET("/lawyers/pro")
    Call<LawyerInfo> getProLawyer(@Query("dtype") String dtype, @Query("st") int st, @Query("count") int count, @Query("pro") String pro, @Query("key") String key);

    @GET("/ip/ip2addr")
    Call<CommonResponse> getIpAddress(@Query("ip") String ip, @Query("key") String key);

    @GET("/dream/query")
    Call<CommonListResponse> getDreams(@Query("key") String key, @Query("q") String q,@Query("full") int full);


    @GET("/postcode/query")
    Call<CommonResponse> getPostCode(@Query("postcode") String postcode, @Query("key") String key);

    @GET("/joke/content/list.from")
    Call<CommonResponse> getLaugh(@Query("key") String key,@Query("page") int page,@Query("pagesize") int pagesize, @Query("sort") String sort, @Query("time") String time);
}
