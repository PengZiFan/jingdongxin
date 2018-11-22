package com.example.windows.ijingdong.login.model;

import com.example.windows.ijingdong.api.ILoginAPI;
import com.example.windows.ijingdong.bean.LoginBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/7.
 */

public class LoginModel {
    public Observable<LoginBean> login(String mobile ,String password){
        ILoginAPI iLoginAPI = RetrofitManager.getInstance().create(ILoginAPI.class);
        Observable<LoginBean> loginBeanObservable = iLoginAPI.login(mobile, password);
        return loginBeanObservable;
    }

}
