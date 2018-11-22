package com.example.windows.ijingdong.regist.presenter;

import com.example.windows.ijingdong.bean.RegistBean;
import com.example.windows.ijingdong.regist.model.RegistModel;
import com.example.windows.ijingdong.regist.view.IRegistView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/8.
 */

public class RegistPresenter {
    private IRegistView iRegistView;
    private RegistModel registModel;

    public void attach(IRegistView iRegistView){
        this.iRegistView = iRegistView;
        registModel = new RegistModel();
    }
    public void dettach(){
        if (iRegistView != null){
            iRegistView = null;
        }
    }
    public void regist(String mobile,String password){
        registModel.regist(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegistBean>() {
                    @Override
                    public void accept(RegistBean registBean) throws Exception {
                        if (registBean != null && "1".equals(registBean.getCode())){
                            if (iRegistView != null){
                                iRegistView.success(registBean);
                            }
                            return;
                        }
                        if (iRegistView != null){
                            iRegistView.failed(new Exception("服务器未响应"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iRegistView != null){
                            iRegistView.failed(new Exception("网络请求失败"));
                        }
                    }
                });
    }
}
