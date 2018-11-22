package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.BottomDetailsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Windows on 2018/11/8.
 */

public interface IBottomDetailsAPI {
    @GET("home/getHome")
    Observable<BottomDetailsBean> getBottomDetails();
}
