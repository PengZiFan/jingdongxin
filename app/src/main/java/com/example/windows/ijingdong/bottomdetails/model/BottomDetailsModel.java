package com.example.windows.ijingdong.bottomdetails.model;

import com.example.windows.ijingdong.api.IBottomDetailsAPI;
import com.example.windows.ijingdong.bean.BottomDetailsBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/8.
 */

public class BottomDetailsModel {
    public Observable<BottomDetailsBean> getBottomDetails(){
        IBottomDetailsAPI iBottomDetailsAPI = RetrofitManager.getInstance().create(IBottomDetailsAPI.class);
        Observable<BottomDetailsBean> bottomDetailsBeanObservable = iBottomDetailsAPI.getBottomDetails();
        return bottomDetailsBeanObservable;
    }
}
