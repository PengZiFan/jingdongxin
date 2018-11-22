package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.AddAddressBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/19.
 */

public interface IAddAddressAPI {
    @GET("user/addAddr")
    Observable<AddAddressBean> addAddress(@Query("uid") int uid,@Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name);
    //?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson
}
