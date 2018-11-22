package com.example.windows.ijingdong.api;

import com.example.windows.ijingdong.bean.UpdateNickNameBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Windows on 2018/11/20.
 */

public interface IUpdateNickNameAPI {
    @GET("user/updateNickName")
    Observable<UpdateNickNameBean> updateNickName(@Query("uid") int uid,@Query("nickname") String nickname);
}
