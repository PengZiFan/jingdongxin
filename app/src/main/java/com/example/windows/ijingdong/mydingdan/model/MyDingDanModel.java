package com.example.windows.ijingdong.mydingdan.model;

import com.example.windows.ijingdong.api.IMyDingDanAPI;
import com.example.windows.ijingdong.bean.MyDingDanBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/20.
 */

public class MyDingDanModel {
    public Observable<MyDingDanBean> getMyDingDan(int uid){
        IMyDingDanAPI iMyDingDanAPI = RetrofitManager.getInstance().create(IMyDingDanAPI.class);
        Observable<MyDingDanBean> myDingDanBeanObservable = iMyDingDanAPI.getMyDingDan(uid);
        return myDingDanBeanObservable;
    }
}
