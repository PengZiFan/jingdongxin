package com.example.windows.ijingdong.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Windows on 2018/11/7.
 */

public class RetrofitManager {
    private static final String URL = "http://www.zhaoapi.cn/";
    private final Retrofit mRetrofit;

    private static final class Instance{
        private static final RetrofitManager instance = new RetrofitManager();
    }
    public static RetrofitManager getInstance(){
        return Instance.instance;
    }
    private RetrofitManager(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client())
                .build();
    }
    private OkHttpClient client(){
        return new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
    }
    public Retrofit getmRetrofit(){
        return mRetrofit;
    }
    public <T> T create(Class<T> clazz){
        return mRetrofit.create(clazz);
    }
}
