package com.example.windows.ijingdong.addcart.presenter;

import com.example.windows.ijingdong.addcart.model.AddCartModel;
import com.example.windows.ijingdong.addcart.view.IAddCartView;
import com.example.windows.ijingdong.bean.AddCart;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/15.
 */

public class AddCartPresenter {
    private IAddCartView iAddCartView;
    private AddCartModel addCartModel;

    public void attach(IAddCartView iAddCartView){
        this.iAddCartView = iAddCartView;
        addCartModel = new AddCartModel();
    }
    public void dettach(){
        if (iAddCartView != null){
            iAddCartView = null;
        }
    }
    public void addCart(int uid ,int pid){
        addCartModel.addCart(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCart>() {
                    @Override
                    public void accept(AddCart addCart) throws Exception {
                        if (addCart != null && "0".equals(addCart.getCode())){
                            if (iAddCartView != null){
                                iAddCartView.success(addCart);
                                return;
                            }
                        }
                        if (iAddCartView != null){
                            iAddCartView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iAddCartView != null){
                            iAddCartView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
