package com.example.windows.ijingdong.login.presenter;

import com.example.windows.ijingdong.bean.LoginBean;
import com.example.windows.ijingdong.login.model.LoginModel;
import com.example.windows.ijingdong.login.view.ILoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/7.
 */

public class LoginPresenter {

    private ILoginView iLoginView;
    private LoginModel loginModel;

    public void attach(ILoginView iLoginView){
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();

    }
    public void dettach(){
        if (iLoginView != null){
            iLoginView = null;
        }
    }

    public void login(String mobile,String password){
        loginModel.login(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        if (loginBean != null &&"0".equals(loginBean.getCode())){
                            if (iLoginView != null){
                                iLoginView.success(loginBean);
                                return;
                            }
                        }
                        if (iLoginView != null){
                            iLoginView.failed(new Exception("服务器未响应"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iLoginView != null){
                            iLoginView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
