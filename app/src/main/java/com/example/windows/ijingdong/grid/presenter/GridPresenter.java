package com.example.windows.ijingdong.grid.presenter;

import com.example.windows.ijingdong.bean.GridBean;
import com.example.windows.ijingdong.grid.model.GridModel;
import com.example.windows.ijingdong.grid.view.IGridView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/8.
 */

public class GridPresenter {
    private IGridView iGridView;
    private GridModel gridModel;

    public void attach(IGridView iGridView){
        this.iGridView = iGridView;
        gridModel = new GridModel();
    }
    public void dettach(){
        if (iGridView != null){
            iGridView = null;
        }
    }
    public void getGrid(){
        gridModel.getGrid()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GridBean>() {
                    @Override
                    public void accept(GridBean gridBean) throws Exception {
                        if (gridBean != null && "0".equals(gridBean.getCode())){
                            iGridView.success(gridBean);
                            return;
                        }
                        if (iGridView != null){
                            iGridView.failed(new Exception("服务器未响应"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (iGridView != null){
                            iGridView.failed(new Exception("服务器未响应"));
                        }

                    }
                });
    }
}
