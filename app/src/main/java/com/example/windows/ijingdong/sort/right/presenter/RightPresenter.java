package com.example.windows.ijingdong.sort.right.presenter;

import com.example.windows.ijingdong.bean.RightBean;
import com.example.windows.ijingdong.sort.right.model.RightModel;
import com.example.windows.ijingdong.sort.right.view.IRightView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/9.
 */

public class RightPresenter {
    private IRightView iRightView;
    private RightModel rightModel;

    public void attah(IRightView iRightView){
        this.iRightView = iRightView;
        rightModel = new RightModel();
    }
    public void getRight(String cid){
        rightModel.getRight(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RightBean>() {
                    @Override
                    public void accept(RightBean rightBean) throws Exception {
                        if (rightBean != null && "0".equals(rightBean.getCode())){
                            if (iRightView != null) {
                                iRightView.success(rightBean);
                            }
                        }
                        if (iRightView != null){
                            iRightView.failed(new Exception("服务未响应"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iRightView != null){
                            iRightView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
