package com.example.windows.ijingdong.cart.model;


import com.example.windows.ijingdong.api.ICartAPI;
import com.example.windows.ijingdong.bean.CartBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/14.
 */

public class CartModel {
    public Observable<CartBean> getCart(int uid){
        ICartAPI iCartAPI = RetrofitManager.getInstance().create(ICartAPI.class);
        Observable<CartBean> cartBeanObservable = iCartAPI.getCart(uid);
        return cartBeanObservable;
    }

}
