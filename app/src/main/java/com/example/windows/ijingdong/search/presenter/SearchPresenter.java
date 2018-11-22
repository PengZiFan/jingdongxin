package com.example.windows.ijingdong.search.presenter;

import com.example.windows.ijingdong.bean.SearchBean;
import com.example.windows.ijingdong.search.model.SearchModel;
import com.example.windows.ijingdong.search.view.ISearchView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Windows on 2018/11/13.
 */

public class SearchPresenter {
    private ISearchView iSearchView;
    private SearchModel searchModel;

    public void attach(ISearchView iSearchView){
        this.iSearchView = iSearchView;
        searchModel = new SearchModel();
    }
    public void dettach(){
        if (iSearchView != null){
            iSearchView = null;
        }
    }
    public void searchProducts(String keywords , int page){
        searchModel.searchProducts(keywords,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(SearchBean searchBean) throws Exception {
                        if (searchBean != null && "0".equals(searchBean.getCode())){
                            if (iSearchView != null){
                                iSearchView.success(searchBean);
                                return;
                            }
                        }
                        if (iSearchView != null){
                            iSearchView.failed(new Exception("请输入要搜索的宝贝"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iSearchView != null){
                            iSearchView.failed(new Exception("网络异常"));
                        }
                    }
                });
    }
}
