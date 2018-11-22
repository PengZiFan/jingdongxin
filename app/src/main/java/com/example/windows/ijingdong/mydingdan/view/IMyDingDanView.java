package com.example.windows.ijingdong.mydingdan.view;

import com.example.windows.ijingdong.bean.MyDingDanBean;

/**
 * Created by Windows on 2018/11/20.
 */

public interface IMyDingDanView {
    void success(MyDingDanBean myDingDanBean);
    void failed(Exception e);
}
