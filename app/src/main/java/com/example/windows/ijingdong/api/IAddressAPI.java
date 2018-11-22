package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.AddressBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/19.
 */

public interface IAddressAPI {
    @GET("user/getAddrs")
    Observable<AddressBean> getAddress(@Query("uid") int uid);
}
