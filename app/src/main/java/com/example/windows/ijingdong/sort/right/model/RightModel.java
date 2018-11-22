package com.example.windows.ijingdong.sort.right.model;

import com.example.windows.ijingdong.api.IRightAPI;
import com.example.windows.ijingdong.bean.RightBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/9.
 */

public class RightModel {
    public Observable<RightBean> getRight(String cid){
        IRightAPI iRightAPI = RetrofitManager.getInstance().create(IRightAPI.class);
        Observable<RightBean> rightBeanObservable = iRightAPI.getRight(cid);
        return rightBeanObservable;
    }
}
