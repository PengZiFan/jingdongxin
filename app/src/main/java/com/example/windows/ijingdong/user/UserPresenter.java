package com.example.windows.ijingdong.user;

import com.example.windows.ijingdong.api.IUserAPI;
import com.example.windows.ijingdong.bean.UserBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/22.
 */

public class UserPresenter {
    private IUserView iUserView;
    private UserModel userModel;

    public void attach(IUserView iUserView){
        this.iUserView = iUserView;
        userModel = new UserModel();
    }

    public void dettach(){
        if (iUserView != null)
        {
            iUserView = null;
        }
    }

    public void getUser(final int uid){
        userModel.getUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        if (userBean != null){
                            if (iUserView != null) {
                                iUserView.success(userBean);
                                return;
                            }
                        }
                        if (iUserView != null){
                            iUserView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iUserView != null){
                            iUserView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
