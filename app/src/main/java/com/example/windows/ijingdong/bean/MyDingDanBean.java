package com.example.windows.ijingdong.bean;

import java.util.List;

/**
 * Created by Windows on 2018/11/20.
 */

public class MyDingDanBean {
    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2018-11-20T11:03:50","orderid":13937,"price":5199,"status":0,"title":"订单测试标题21241","uid":21241},{"createtime":"2018-11-20T11:03:55","orderid":13938,"price":2999,"status":0,"title":"订单测试标题21241","uid":21241},{"createtime":"2018-11-20T11:04:01","orderid":13939,"price":234,"status":0,"title":"订单测试标题21241","uid":21241}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2018-11-20T11:03:50
         * orderid : 13937
         * price : 5199
         * status : 0
         * title : 订单测试标题21241
         * uid : 21241
         */

        private String createtime;
        private int orderid;
        private float price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
