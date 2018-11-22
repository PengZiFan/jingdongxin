package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.TouXiangBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/22.
 */

public interface ITouXiangAPI {

    @Multipart
    @POST("file/upload")
    Observable<TouXiangBean> upLoad(@Query("uid") int uid, @Part MultipartBody.Part part);
}
