package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/13.
 */

public interface ISearchAPI {
    @GET("product/searchProducts")
    Observable<SearchBean> searchProducts(@Query("keywords") String keywords ,@Query("page") int page);
}
