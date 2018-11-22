package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.MyDingDanBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/20.
 */

public interface IMyDingDanAPI {
    @GET("product/getOrders")
    Observable<MyDingDanBean> getMyDingDan(@Query("uid") int uid);
}
