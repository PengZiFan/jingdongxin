package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.LoginBean;
import com.example.windows.ijingdong.bean.RightBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/9.
 */

public interface IRightAPI {
    @GET("product/getProductCatagory")
    Observable<RightBean> getRight(@Query("cid") String cid);
}
