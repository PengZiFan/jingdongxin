package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.GridBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Windows on 2018/11/8.
 */

public interface IGridAPI {
    @GET("product/getCatagory")
    Observable<GridBean> getGrid();
}
