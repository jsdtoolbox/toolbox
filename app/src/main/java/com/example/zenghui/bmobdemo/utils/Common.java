package com.example.zenghui.bmobdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static int screem_width = 0,screem_height = 0;

    public static final String HTTPREQUEST_COOKIE = "Cookie";
    public static final String PHONE_ADDRESS_KEY = "a9244c333e015e0b46f01086dbeb25cb";
    public static final String LAWYER_KEY = "6f940a4a81649f3b6d30e47cdd37a5ad";
    public static final String WATHER_KEY = "618ae5ff09bcd0ac72c2413563fa11bb";
    public static final String IDENTITY_KEY = "307b157238089b3aecb9f731aba56b4e";
    public static final String IP_KEY = "fdf9c4bdedd30bc5b4cfe37130977da3";
    public static final String POSTCODE_KEY = "6cd9c292a69c5760d09cc416edbf1d51";
    public static final String DREAM_KEY = "0529623216b0d61f681a25aae89613e0";
    public static final String LAUGH_KEY = "8f55880c06c4d735ef889140877e6433";

    public static final String DOMAIN = "http://apis.juhe.cn";
    public static String JSESSIONID = "";
    public static String SERVERID = "";
    public static String PHONE_PATTERN ="^[1][3,4,7,5,8][0-9]{9}$";
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

    /**
     * 校验电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneValid(String phone) {
        if (TextUtils.isEmpty(phone)){
            return false;
        }
        Pattern pattern = Pattern.compile(Common.PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /*Java 验证Ip是否合法*/
    public static boolean isIPAddress(String ipaddr) {
        if (TextUtils.isEmpty(ipaddr)){
            return false;
        }
        boolean flag = false;
        Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        Matcher m = pattern.matcher(ipaddr);
        flag = m.matches();
        return flag;
    }

    String str = "^[1-9][0-9]{5}$";
    /**
     * 判断邮编
     * @param zipString
     * @return
     */
    public static boolean isZipNO(String zipString){
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }

    public static void showSoftIput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftIput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

}
