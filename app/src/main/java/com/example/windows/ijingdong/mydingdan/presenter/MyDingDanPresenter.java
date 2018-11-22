package com.example.windows.ijingdong.mydingdan.presenter;

import com.example.windows.ijingdong.bean.MyDingDanBean;
import com.example.windows.ijingdong.mydingdan.model.MyDingDanModel;
import com.example.windows.ijingdong.mydingdan.view.IMyDingDanView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/20.
 */

public class MyDingDanPresenter {
    private IMyDingDanView iMyDingDanView;
    private MyDingDanModel myDingDanModel;

    public void attach(IMyDingDanView iMyDingDanView){
        this.iMyDingDanView = iMyDingDanView;
        myDingDanModel = new MyDingDanModel();
    }
    public void dettach(){
        if (iMyDingDanView != null) {
            iMyDingDanView = null;
        }
    }
    public void getMyDingDan(int uid){
        myDingDanModel.getMyDingDan(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyDingDanBean>() {
                    @Override
                    public void accept(MyDingDanBean myDingDanBean) throws Exception {
                        if (myDingDanBean != null && "0".equals(myDingDanBean.getCode())){
                            if (iMyDingDanView !=null) {
                                iMyDingDanView.success(myDingDanBean);
                                return;
                            }
                        }
                        if (iMyDingDanView != null) {
                            iMyDingDanView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iMyDingDanView != null) {
                            iMyDingDanView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
