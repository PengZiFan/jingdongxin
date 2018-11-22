package com.example.windows.ijingdong.xiangqing.model;

import com.example.windows.ijingdong.api.IXiangQingAPI;
import com.example.windows.ijingdong.bean.XiangQingBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/13.
 */

public class XiangQingModel {
    public Observable<XiangQingBean> getXiangQing(int pid){
        IXiangQingAPI iXiangQingAPI = RetrofitManager.getInstance().create(IXiangQingAPI.class);
        Observable<XiangQingBean> xiangQingBeanObservable = iXiangQingAPI.getXiangQing(pid);
        return xiangQingBeanObservable;
    }
}
