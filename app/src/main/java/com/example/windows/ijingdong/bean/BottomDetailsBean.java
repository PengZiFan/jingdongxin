package com.example.windows.ijingdong.bean;

import java.util.List;

/**
 * Created by Windows on 2018/11/8.
 */

public class BottomDetailsBean {
    /**

     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**

         */

        private MiaoshaBean miaosha;
        private TuijianBean tuijian;
        private List<BannerBean> banner;
        private List<FenleiBean> fenlei;

        public MiaoshaBean getMiaosha() {
            return miaosha;
        }

        public void setMiaosha(MiaoshaBean miaosha) {
            this.miaosha = miaosha;
        }

        public TuijianBean getTuijian() {
            return tuijian;
        }

        public void setTuijian(TuijianBean tuijian) {
            this.tuijian = tuijian;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<FenleiBean> getFenlei() {
            return fenlei;
        }

        public void setFenlei(List<FenleiBean> fenlei) {
            this.fenlei = fenlei;
        }

        public static class MiaoshaBean {
            /**

             */

            private String name;
            private int time;
            private List<ListBean> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * bargainPrice : 99
                 * createtime : 2017-10-14T21:38:26
                 * detailUrl : https://item.m.jd.com/product/4345173.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends
                 * images : https://m.360buyimg.com/n0/jfs/t6037/35/2944615848/95178/6cd6cff0/594a3a10Na4ec7f39.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t6607/258/1025744923/75738/da120a2d/594a3a12Ne3e6bc56.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t6370/292/1057025420/64655/f87644e3/594a3a12N5b900606.jpg!q70.jpg
                 * itemtype : 1
                 * pid : 45
                 * price : 2999
                 * pscid : 39
                 * salenum : 4666
                 * sellerid : 1
                 * subhead : 高清双摄，就是清晰！2000+1600万高清摄像头，6GB大内存+高通骁龙835处理器，性能怪兽！
                 * title : 一加手机5 (A5000) 6GB+64GB 月岩灰 全网通 双卡双待 移动联通电信4G手机
                 */

                private int bargainPrice;
                private String createtime;
                private String detailUrl;
                private String images;
                private int itemtype;
                private int pid;
                private int price;
                private int pscid;
                private int salenum;
                private int sellerid;
                private String subhead;
                private String title;

                public int getBargainPrice() {
                    return bargainPrice;
                }

                public void setBargainPrice(int bargainPrice) {
                    this.bargainPrice = bargainPrice;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public int getItemtype() {
                    return itemtype;
                }

                public void setItemtype(int itemtype) {
                    this.itemtype = itemtype;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getPscid() {
                    return pscid;
                }

                public void setPscid(int pscid) {
                    this.pscid = pscid;
                }

                public int getSalenum() {
                    return salenum;
                }

                public void setSalenum(int salenum) {
                    this.salenum = salenum;
                }

                public int getSellerid() {
                    return sellerid;
                }

                public void setSellerid(int sellerid) {
                    this.sellerid = sellerid;
                }

                public String getSubhead() {
                    return subhead;
                }

                public void setSubhead(String subhead) {
                    this.subhead = subhead;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class TuijianBean {
            /**

             */

            private String name;
            private List<ListBeanX> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class ListBeanX {
                /**
                 * bargainPrice : 11800
                 * createtime : 2017-10-10T17:33:37
                 * detailUrl : https://item.m.jd.com/product/4338107.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends
                 * images : https://m.360buyimg.com/n0/jfs/t6700/155/2098998076/156185/6cf95035/595dd5a5Nc3a7dab5.jpg!q70.jpg
                 * itemtype : 0
                 * pid : 57
                 * price : 5199
                 * pscid : 40
                 * salenum : 4343
                 * sellerid : 1
                 * subhead : 【i5 MX150 2G显存】全高清窄边框 8G内存 256固态硬盘 支持指纹识别 预装WIN10系统
                 * title : 小米(MI)Air 13.3英寸全金属轻薄笔记本(i5-7200U 8G 256G PCle SSD MX150 2G独显 FHD 指纹识别 Win10）银

                 */

                private float bargainPrice;
                private String createtime;
                private String detailUrl;
                private String images;
                private int itemtype;
                private int pid;
                private float price;
                private int pscid;
                private int salenum;
                private int sellerid;
                private String subhead;
                private String title;

                public float getBargainPrice() {
                    return bargainPrice;
                }

                public void setBargainPrice(int bargainPrice) {
                    this.bargainPrice = bargainPrice;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public int getItemtype() {
                    return itemtype;
                }

                public void setItemtype(int itemtype) {
                    this.itemtype = itemtype;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public float getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getPscid() {
                    return pscid;
                }

                public void setPscid(int pscid) {
                    this.pscid = pscid;
                }

                public int getSalenum() {
                    return salenum;
                }

                public void setSalenum(int salenum) {
                    this.salenum = salenum;
                }

                public int getSellerid() {
                    return sellerid;
                }

                public void setSellerid(int sellerid) {
                    this.sellerid = sellerid;
                }

                public String getSubhead() {
                    return subhead;
                }

                public void setSubhead(String subhead) {
                    this.subhead = subhead;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class BannerBean {
            /**
             * aid : 1
             * createtime : 2017-12-26T21:49:44
             * icon : https://www.zhaoapi.cn/images/quarter/ad1.png
             * productId : null
             * title : 第十三界瑞丽模特大赛
             * type : 0
             * url : http://m.mv14449315.icoc.bz/index.jsp
             */

            private int aid;
            private String createtime;
            private String icon;
            private Object productId;
            private String title;
            private int type;
            private String url;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getProductId() {
                return productId;
            }

            public void setProductId(Object productId) {
                this.productId = productId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class FenleiBean {
            /**
             * cid : 1
             * createtime : 2017-10-10T19:41:39
             * icon : http://120.27.23.105/images/category/shop.png
             * ishome : 1
             * name : 京东超市
             */

            private int cid;
            private String createtime;
            private String icon;
            private int ishome;
            private String name;

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getIshome() {
                return ishome;
            }

            public void setIshome(int ishome) {
                this.ishome = ishome;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
