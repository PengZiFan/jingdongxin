package com.example.windows.ijingdong.xiangqing.presenter;

import com.example.windows.ijingdong.bean.XiangQingBean;
import com.example.windows.ijingdong.xiangqing.model.XiangQingModel;
import com.example.windows.ijingdong.xiangqing.view.IXiangQingView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/13.
 */

public class XiangQingPresenter {
    private IXiangQingView iXiangQingView;
    private XiangQingModel xiangQingModel;

    public void attach(IXiangQingView iXiangQingView){
        this.iXiangQingView = iXiangQingView;
        xiangQingModel = new XiangQingModel();
    }
    public void dettach(){
        if (iXiangQingView != null){
            iXiangQingView = null;
        }
    }
    public void getXiangQing(int pid){
        xiangQingModel.getXiangQing(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XiangQingBean>() {
                    @Override
                    public void accept(XiangQingBean xiangQingBean) throws Exception {
                        if (xiangQingBean != null &&"0".equals(xiangQingBean.getCode())){
                            if (iXiangQingView != null){
                                iXiangQingView.success(xiangQingBean);
                                return;
                            }
                        }
                        if (iXiangQingView != null){
                            iXiangQingView.failed(new Exception("服务请求异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iXiangQingView != null){
                            iXiangQingView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
