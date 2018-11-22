package com.example.windows.ijingdong.address.addAddress.model;

import com.example.windows.ijingdong.api.IAddAddressAPI;
import com.example.windows.ijingdong.bean.AddAddressBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/19.
 */

public class AddAddressModel {
    public Observable<AddAddressBean> addAddress(int uid,String addr,String mobile,String name){
        IAddAddressAPI iAddAddressAPI = RetrofitManager.getInstance().create(IAddAddressAPI.class);
        Observable<AddAddressBean> addAddressBeanObservable = iAddAddressAPI.addAddress(uid, addr, mobile, name);
        return addAddressBeanObservable;
    }
}
