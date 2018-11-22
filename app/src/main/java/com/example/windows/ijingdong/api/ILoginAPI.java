package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/7.
 */

public interface ILoginAPI {

    //http://www.zhaoapi.cn/user/login
    @GET("user/login")
    Observable<LoginBean> login(@Query("mobile") String mobile , @Query("password") String password);

}
