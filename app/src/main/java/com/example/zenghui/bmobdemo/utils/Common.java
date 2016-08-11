package com.example.zenghui.bmobdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zenghui on 16/8/11.
 */
public class Common {
    public static final String HTTPREQUEST_COOKIE = "Cookie";
    public static final String PHONE_ADDRESS_KEY = "a9244c333e015e0b46f01086dbeb25cb";
    public static final String LAWYER_KEY = "6f940a4a81649f3b6d30e47cdd37a5ad";
    public static final String WATHER_KEY = "618ae5ff09bcd0ac72c2413563fa11bb";

    public static final String DOMAIN = "http://apis.juhe.cn";
    public static String JSESSIONID = "";
    public static String SERVERID = "";

    public static ITask getTask(final String apiUrl) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        final OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request;

                        if (!TextUtils.isEmpty(JSESSIONID) || !TextUtils.isEmpty(SERVERID) ){
                            StringBuilder value = new StringBuilder();
                            if (!TextUtils.isEmpty(JSESSIONID)) {
                                value.append(JSESSIONID);
                                if (!TextUtils.isEmpty(SERVERID)){
                                    value.append(";"+SERVERID);
                                }
                            } else {
                                value.append(SERVERID);
                                if (!TextUtils.isEmpty(JSESSIONID)){
                                    value.append(";"+JSESSIONID);
                                }
                            }

                            Log.d("","value =====>"+value);
                            request = chain.request()
                                    .newBuilder()
                                    .addHeader(HTTPREQUEST_COOKIE, value.toString())
                                    .build();
                        }else{
                            request = chain.request()
                                    .newBuilder()
                                    .build();
                        }

                        Response originalResponse = chain.proceed(request);
                        return originalResponse;
                    }
                })
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();


        ITask task = retrofit.create(ITask.class);

        return task;
    }
    public static void setCookies(retrofit2.Response response){
        for (String string:response.raw().headers().values("Set-Cookie")){
            Log.d("","headers ===>"+string);
            if (string.contains("JSESSIONID") && !JSESSIONID.equals(string)){
                JSESSIONID = string.split(";")[0];
            }else if (string.contains("SERVERID") && !SERVERID.equals(string)){
                SERVERID = string.split(";")[0];
            }
        }
    }
}
