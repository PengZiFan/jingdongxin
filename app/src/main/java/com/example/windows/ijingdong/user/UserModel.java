package com.example.windows.ijingdong.user;

import com.example.windows.ijingdong.api.IUserAPI;
import com.example.windows.ijingdong.bean.UserBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/22.
 */

public class UserModel {
    public Observable<UserBean> getUser(int uid){
        IUserAPI iUserAPI = RetrofitManager.getInstance().create(IUserAPI.class);
        Observable<UserBean> user = iUserAPI.getUser(uid);
        return user;
    }
}
