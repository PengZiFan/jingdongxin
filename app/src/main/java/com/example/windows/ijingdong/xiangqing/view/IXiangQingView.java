package com.example.windows.ijingdong.xiangqing.view;

import com.example.windows.ijingdong.bean.XiangQingBean;

/**
 * Created by Windows on 2018/11/13.
 */

public interface IXiangQingView {
    void success(XiangQingBean xiangQingBean);
    void failed(Exception e);
}
