package com.example.windows.ijingdong.cart.presenter;

import com.example.windows.ijingdong.bean.CartBean;
import com.example.windows.ijingdong.cart.model.CartModel;
import com.example.windows.ijingdong.cart.view.ICartView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/14.
 */

public class CartPresenter {
    private ICartView iCartView;
    private CartModel cartModel;

    public void attach(ICartView iCartView){
        this.iCartView = iCartView;
        cartModel = new CartModel();
    }
    public void dettach(){
        if (iCartView != null) {
            iCartView = null;
        }
    }
    public void getCart(int uid){
        cartModel.getCart(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CartBean>() {
                    @Override
                    public void accept(CartBean cartBean) throws Exception {
                        if (cartBean != null && "0".equals(cartBean.getCode())){
                            if (iCartView != null){
                                iCartView.success(cartBean);
                                return;
                            }
                        }
                        if (iCartView != null){
                            iCartView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCartView != null){
                            iCartView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
