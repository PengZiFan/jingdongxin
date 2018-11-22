package com.example.windows.ijingdong.mydingdan.addmydingdan.view;

import com.example.windows.ijingdong.bean.AddMyDingDanBean;

/**
 * Created by Windows on 2018/11/20.
 */

public interface IAddMyDingDanView {
    void success(AddMyDingDanBean addMyDingDanBean);
    void failed(Exception e);
}
