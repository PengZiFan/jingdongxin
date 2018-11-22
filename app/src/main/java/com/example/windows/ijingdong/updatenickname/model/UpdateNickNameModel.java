package com.example.windows.ijingdong.updatenickname.model;

import com.example.windows.ijingdong.api.IUpdateNickNameAPI;
import com.example.windows.ijingdong.bean.UpdateNickNameBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/20.
 */

public class UpdateNickNameModel {
    public Observable<UpdateNickNameBean> updateNickName(int uid,String nickname){
        IUpdateNickNameAPI iUpdateNickNameAPI = RetrofitManager.getInstance().create(IUpdateNickNameAPI.class);
        Observable<UpdateNickNameBean> updateNickNameBeanObservable = iUpdateNickNameAPI.updateNickName(uid, nickname);
        return updateNickNameBeanObservable;
    }
}
