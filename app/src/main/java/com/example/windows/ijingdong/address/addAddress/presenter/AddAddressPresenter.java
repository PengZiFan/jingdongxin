package com.example.windows.ijingdong.address.addAddress.presenter;

import com.example.windows.ijingdong.address.addAddress.model.AddAddressModel;
import com.example.windows.ijingdong.address.addAddress.view.IAddAddressView;
import com.example.windows.ijingdong.api.IAddAddressAPI;
import com.example.windows.ijingdong.bean.AddAddressBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/19.
 */

public class AddAddressPresenter {
    private IAddAddressView iAddAddressView;
    private AddAddressModel addAddressModel;

    public void attach(IAddAddressView iAddAddressView){
        this.iAddAddressView = iAddAddressView;
        addAddressModel = new AddAddressModel();
    }

    public void dettach(){
        if (iAddAddressView != null) {
            iAddAddressView = null;
        }
    }
    public void addAddress(int uid,String addr,String mobile,String name){
        addAddressModel.addAddress(uid,addr,mobile,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddAddressBean>() {
                    @Override
                    public void accept(AddAddressBean addAddressBean) throws Exception {
                        if (addAddressBean != null && "0".equals(addAddressBean.getCode())){
                            if (iAddAddressView != null) {
                                iAddAddressView.success(addAddressBean);
                                return;
                            }
                        }
                        if (iAddAddressView != null) {
                            iAddAddressView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iAddAddressView != null) {
                            iAddAddressView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
