package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.AddMyDingDanBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/20.
 */

public interface IAddMyDingDanAPI {
    @GET("product/createOrder")
    Observable<AddMyDingDanBean> addMyDingDan(@Query("uid") int uid,@Query("price") String price);
}
