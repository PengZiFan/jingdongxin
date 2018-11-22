package com.example.windows.ijingdong.search.view;

import com.example.windows.ijingdong.bean.SearchBean;

/**
 * Created by Windows on 2018/11/13.
 */

public interface ISearchView {
    void success(SearchBean searchBean);
    void failed(Exception e);
}
