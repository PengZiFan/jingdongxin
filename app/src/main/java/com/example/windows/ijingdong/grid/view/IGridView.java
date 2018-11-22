package com.example.windows.ijingdong.grid.view;

import com.example.windows.ijingdong.bean.GridBean;

/**
 * Created by Windows on 2018/11/8.
 */

public interface IGridView {
    void success(GridBean gridBean);
    void failed(Exception e);
}
