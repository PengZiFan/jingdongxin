package com.example.windows.ijingdong.touxiang;

import com.example.windows.ijingdong.api.ITouXiangAPI;
import com.example.windows.ijingdong.bean.TouXiangBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Windows on 2018/11/22.
 */

public class TouXiangModel {
    public Observable<TouXiangBean> upLoad(int uid , File file){
        ITouXiangAPI iTouXiangAPI = RetrofitManager.getInstance().create(ITouXiangAPI.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Observable<TouXiangBean> touXiangBeanObservable = iTouXiangAPI.upLoad(uid, part);
        return touXiangBeanObservable;
    }
}
