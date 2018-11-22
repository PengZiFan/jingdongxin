package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.AddCart;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/15.
 */

public interface IAddCartAPI {
    @GET("product/addCart")
    Observable<AddCart> addCart(@Query("uid") int uid ,@Query("pid") int pid);
}
