package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.XiangQingBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/13.
 */

public interface IXiangQingAPI {

    @GET("product/getProductDetail")
    Observable<XiangQingBean> getXiangQing(@Query("pid") int pid);
}
