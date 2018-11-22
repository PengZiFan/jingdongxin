package com.example.windows.ijingdong.search.model;

import com.example.windows.ijingdong.api.ISearchAPI;
import com.example.windows.ijingdong.bean.SearchBean;
import com.example.windows.ijingdong.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by Windows on 2018/11/13.
 */

public class SearchModel {
    public Observable<SearchBean> searchProducts(String keywords , int page){
        ISearchAPI iSearchAPI = RetrofitManager.getInstance().create(ISearchAPI.class);
        Observable<SearchBean> searchBeanObservable = iSearchAPI.searchProducts(keywords, page);
        return searchBeanObservable;
    }
}
