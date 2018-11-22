package com.example.windows.ijingdong.address.presenter;

import com.example.windows.ijingdong.address.model.AddressModel;
import com.example.windows.ijingdong.address.view.IAddressView;
import com.example.windows.ijingdong.bean.AddressBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/19.
 */

public class AddressPresenter {
    private IAddressView iAddressView;
    private AddressModel addressModel;

    public void attach(IAddressView iAddressView){
        this.iAddressView = iAddressView;
        addressModel = new AddressModel();
    }
    public void dettach(){
        if (iAddressView != null) {
            iAddressView = null;
        }
    }
    public void getAddress(int uid){
        addressModel.getAddress(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddressBean>() {
                    @Override
                    public void accept(AddressBean addressBean) throws Exception {
                        if (addressBean != null && "0".equals(addressBean.getCode())){
                            if (iAddressView != null) {
                                iAddressView.success(addressBean);
                                return;
                            }
                        }
                        if (iAddressView != null) {
                            iAddressView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iAddressView != null) {
                            iAddressView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
