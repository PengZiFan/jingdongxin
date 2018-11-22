package com.example.windows.ijingdong.mydingdan.addmydingdan.presenter;

import com.example.windows.ijingdong.bean.AddMyDingDanBean;
import com.example.windows.ijingdong.mydingdan.addmydingdan.model.AddMyDingDanModel;
import com.example.windows.ijingdong.mydingdan.addmydingdan.view.IAddMyDingDanView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/20.
 */

public class AddMyDingDanPresenter {
    private IAddMyDingDanView iAddMyDingDanView;
    private AddMyDingDanModel addMyDingDanModel;

    public void attach(IAddMyDingDanView iAddMyDingDanView){
        this.iAddMyDingDanView = iAddMyDingDanView;
        addMyDingDanModel = new AddMyDingDanModel();
    }
    public void dettach(){
        if (iAddMyDingDanView !=null) {
            iAddMyDingDanView = null;
        }
    }
    public void addMyDingDan(int uid,String price){
        addMyDingDanModel.addMyDingDan(uid,price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddMyDingDanBean>() {
                    @Override
                    public void accept(AddMyDingDanBean addMyDingDanBean) throws Exception {
                        if (addMyDingDanBean != null && "0".equals(addMyDingDanBean.getCode())){
                            if (iAddMyDingDanView != null) {
                                iAddMyDingDanView.success(addMyDingDanBean);
                                return;
                            }
                        }
                        if (iAddMyDingDanView != null){
                            iAddMyDingDanView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iAddMyDingDanView != null){
                            iAddMyDingDanView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
