package com.example.windows.ijingdong.grid.model;

import com.example.windows.ijingdong.api.IGridAPI;
import com.example.windows.ijingdong.bean.GridBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/8.
 */

public class GridModel {
    public Observable<GridBean> getGrid(){
        IGridAPI iGridAPI = RetrofitManager.getInstance().create(IGridAPI.class);
        Observable<GridBean> gridBeanObservable = iGridAPI.getGrid();
        return gridBeanObservable;
    }
}
