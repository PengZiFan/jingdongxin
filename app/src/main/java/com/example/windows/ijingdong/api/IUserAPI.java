package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/22.
 */

public interface IUserAPI {
    @GET("user/getUserInfo")
    Observable<UserBean> getUser(@Query("uid") int uid);
}
