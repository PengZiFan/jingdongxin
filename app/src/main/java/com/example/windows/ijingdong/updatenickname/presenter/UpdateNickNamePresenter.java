package com.example.windows.ijingdong.updatenickname.presenter;

import android.util.Log;

import com.example.windows.ijingdong.bean.UpdateNickNameBean;
import com.example.windows.ijingdong.updatenickname.model.UpdateNickNameModel;
import com.example.windows.ijingdong.updatenickname.view.IUpdateNickNameView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/20.
 */

public class UpdateNickNamePresenter {
    private IUpdateNickNameView iUpdateNickNameView;
    private UpdateNickNameModel updateNickNameModel;

    public void attach(IUpdateNickNameView iUpdateNickNameView){
        this.iUpdateNickNameView = iUpdateNickNameView;
        updateNickNameModel = new UpdateNickNameModel();
    }
    public void dettach(){
        if (iUpdateNickNameView != null) {
            iUpdateNickNameView = null;
        }
    }
    public void updateNickName(int uid, String nickname){
        updateNickNameModel.updateNickName(uid,nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateNickNameBean>() {
                    @Override
                    public void accept(UpdateNickNameBean updateNickNameBean) throws Exception {
                        if (updateNickNameBean != null && "0".equals(updateNickNameBean.getCode())){
                            if (iUpdateNickNameView != null) {
                                iUpdateNickNameView.success(updateNickNameBean);
                                return;
                            }
                        }
                        if (iUpdateNickNameView != null) {
                            iUpdateNickNameView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iUpdateNickNameView != null) {
                            iUpdateNickNameView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
