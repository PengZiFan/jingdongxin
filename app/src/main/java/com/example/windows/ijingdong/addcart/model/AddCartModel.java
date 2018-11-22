package com.example.windows.ijingdong.addcart.model;

import com.example.windows.ijingdong.api.IAddCartAPI;
import com.example.windows.ijingdong.bean.AddCart;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/15.
 */

public class AddCartModel {
    public Observable<AddCart> addCart(int uid,int pid){
        IAddCartAPI iAddCartAPI = RetrofitManager.getInstance().create(IAddCartAPI.class);
        Observable<AddCart> addCartObservable = iAddCartAPI.addCart(uid, pid);
        return addCartObservable;
    }
}
