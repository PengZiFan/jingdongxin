package com.example.windows.ijingdong.regist.model;

import com.example.windows.ijingdong.api.IRegistAPI;
import com.example.windows.ijingdong.bean.RegistBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/8.
 */

public class RegistModel {
    public Observable<RegistBean> regist(String mobile ,String password){
        IRegistAPI iRegistAPI = RetrofitManager.getInstance().create(IRegistAPI.class);
        Observable<RegistBean> registBeanObservable = iRegistAPI.regist(mobile, password);
        return registBeanObservable;
    }
}
