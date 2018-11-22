package com.example.windows.ijingdong.bottomdetails.presenter;

import com.example.windows.ijingdong.bean.BottomDetailsBean;
import com.example.windows.ijingdong.bottomdetails.model.BottomDetailsModel;
import com.example.windows.ijingdong.bottomdetails.view.IBottomDetailsView;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/8.
 */

public class BottomDetailsPresenter {
    private IBottomDetailsView iBottomDetailsView;
    private BottomDetailsModel bottomDetailsModel;

    public void attach(IBottomDetailsView iBottomDetailsView){
        this.iBottomDetailsView = iBottomDetailsView;
        bottomDetailsModel = new BottomDetailsModel();
    }
    public void dettach(){
        if (iBottomDetailsView != null){
            iBottomDetailsView = null;
        }
    }
    public void getBottomDetails(){
        bottomDetailsModel.getBottomDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BottomDetailsBean>() {
                    @Override
                    public void accept(BottomDetailsBean bottomDetailsBean) throws Exception {
                        if (bottomDetailsBean != null){
                            if (iBottomDetailsView != null){
                                iBottomDetailsView.success(bottomDetailsBean);
                                return;
                            }
                        }
                        if (iBottomDetailsView != null){
                            iBottomDetailsView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iBottomDetailsView != null){
                            iBottomDetailsView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
