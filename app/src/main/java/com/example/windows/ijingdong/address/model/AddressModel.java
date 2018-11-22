package com.example.windows.ijingdong.address.model;

import com.example.windows.ijingdong.api.IAddCartAPI;
import com.example.windows.ijingdong.api.IAddressAPI;
import com.example.windows.ijingdong.bean.AddressBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/19.
 */

public class AddressModel {
    public Observable<AddressBean> getAddress(int uid){
        IAddressAPI iAddressAPI = RetrofitManager.getInstance().create(IAddressAPI.class);
        Observable<AddressBean> addressBeanObservable = iAddressAPI.getAddress(uid);
        return addressBeanObservable;
    }
}
