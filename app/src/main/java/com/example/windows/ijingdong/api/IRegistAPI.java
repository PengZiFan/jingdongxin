package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.RegistBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/8.
 */

public interface IRegistAPI {
    @GET("user/reg")
    Observable<RegistBean> regist(@Query("mobile") String mobile , @Query("password") String password);
}
