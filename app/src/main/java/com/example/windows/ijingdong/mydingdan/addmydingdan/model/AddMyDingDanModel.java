package com.example.windows.ijingdong.mydingdan.addmydingdan.model;

import com.example.windows.ijingdong.api.IAddMyDingDanAPI;
import com.example.windows.ijingdong.bean.AddMyDingDanBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/20.
 */

public class AddMyDingDanModel {
    public Observable<AddMyDingDanBean> addMyDingDan(int uid,String price){
        IAddMyDingDanAPI iAddMyDingDanAPI = RetrofitManager.getInstance().create(IAddMyDingDanAPI.class);
        Observable<AddMyDingDanBean> addMyDingDanBeanObservable = iAddMyDingDanAPI.addMyDingDan(uid, price);
        return addMyDingDanBeanObservable;
    }
}
