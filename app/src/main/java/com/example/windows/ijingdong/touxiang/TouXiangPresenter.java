package com.example.windows.ijingdong.touxiang;

import com.example.windows.ijingdong.bean.TouXiangBean;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/22.
 */

public class TouXiangPresenter {
    private ITouXiangView iTouXiangView;
    private TouXiangModel touXiangModel;

    public void attach(ITouXiangView iTouXiangView){
        this.iTouXiangView = iTouXiangView;
        touXiangModel = new TouXiangModel();
    }
    public void dettach(){
        if (iTouXiangView != null) {
            iTouXiangView = null;
        }
    }

    public void upLoad(int uid, File file){
        touXiangModel.upLoad(uid,file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TouXiangBean>() {
                    @Override
                    public void accept(TouXiangBean touXiangBean) throws Exception {

                        if (touXiangBean != null){
                            if (iTouXiangView != null){
                                iTouXiangView.success(touXiangBean);
                                return;
                            }
                        }
                        if (iTouXiangView != null){
                            iTouXiangView.failed(new Exception("服务器异常"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iTouXiangView != null){
                            iTouXiangView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
