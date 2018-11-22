package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.CartBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/14.
 */

public interface ICartAPI {
    @GET("product/getCarts")
    Observable<CartBean> getCart(@Query("uid") int uid);
}
