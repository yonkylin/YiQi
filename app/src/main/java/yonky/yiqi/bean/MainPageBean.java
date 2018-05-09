package yonky.yiqi.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class MainPageBean {


    /**
     * popularize_items_list_get_response : {"AreaA":[{"site_id":48,"ad_id":11682,"page_id":125,"pos_id":218,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":3158,"title":"爱尚网批","price":0,"url":"http://cs.17zwd.com/shop/3158.htm","img_url":"https://aims.17zwd.com/osys/48/3beada5ca96c3966374f05ecd6d2bc8c.jpg","img_url2":"https://aims.17zwd.com/osys/48/3beada5ca96c3966374f05ecd6d2bc8c.jpg","spm":"c5jEjVMzAhHFy5%2btBPM%2baMQqPYc7jeY%2f6NJ1FG6YAMQ%3d","seconds":5},{"site_id":48,"ad_id":7197,"page_id":125,"pos_id":218,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":23646,"title":"500日韩屋","price":0,"url":"http://cs.17zwd.com/shop/23646.htm","img_url":"https://aims.17zwd.com/osys/48/8dbbf1a7c549f9fc23cfe1519e481d1e.jpg","img_url2":"https://aims.17zwd.com/osys/48/8dbbf1a7c549f9fc23cfe1519e481d1e.jpg","spm":"c5jEjVMzAhF32y8JGAabqC%2b6WPLvtTpBBJD2ohTkPEU%3d","seconds":5},{"site_id":48,"ad_id":7198,"page_id":125,"pos_id":218,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":27006,"title":"奥尼睡衣网批","price":0,"url":"http://cs.17zwd.com/shop/27006.htm","img_url":"https://aims.17zwd.com/osys/48/8bf42e69b018945a5561fc46f8586078.jpg","img_url2":"https://aims.17zwd.com/osys/48/8bf42e69b018945a5561fc46f8586078.jpg","spm":"c5jEjVMzAhF32y8JGAabqJCUh6uu3b1tCvUoiGxvXjY%3d","seconds":5},{"site_id":48,"ad_id":7195,"page_id":125,"pos_id":218,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":11664,"title":"TT网购","price":0,"url":"http://cs.17zwd.com/shop/11664.htm","img_url":"https://aims.17zwd.com/osys/48/030d9c45fd1d902124779eea2557d09b.jpg","img_url2":"https://aims.17zwd.com/osys/48/030d9c45fd1d902124779eea2557d09b.jpg","spm":"c5jEjVMzAhGdG9MIFYUNQh534PM%2bTXI3lm87oftjK3s%3d","seconds":5}]}
     * status_code : 200
     * result : success
     */

    private PopularizeItemsListGetResponseBean popularize_items_list_get_response;
    private int status_code;
    private String result;

    public PopularizeItemsListGetResponseBean getPopularize_items_list_get_response() {
        return popularize_items_list_get_response;
    }

    public void setPopularize_items_list_get_response(PopularizeItemsListGetResponseBean popularize_items_list_get_response) {
        this.popularize_items_list_get_response = popularize_items_list_get_response;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class PopularizeItemsListGetResponseBean {
        private List<AreaABean> AreaA;

        public List<AreaABean> getAreaA() {
            return AreaA;
        }

        public void setAreaA(List<AreaABean> AreaA) {
            this.AreaA = AreaA;
        }

        public static class AreaABean {
            /**
             * site_id : 48
             * ad_id : 11682
             * page_id : 125
             * pos_id : 218
             * data_type : PopularizeModule
             * media_type : Image
             * target : Shop
             * goods_id : 0
             * shop_id : 3158
             * title : 爱尚网批
             * price : 0
             * url : http://cs.17zwd.com/shop/3158.htm
             * img_url : https://aims.17zwd.com/osys/48/3beada5ca96c3966374f05ecd6d2bc8c.jpg
             * img_url2 : https://aims.17zwd.com/osys/48/3beada5ca96c3966374f05ecd6d2bc8c.jpg
             * spm : c5jEjVMzAhHFy5%2btBPM%2baMQqPYc7jeY%2f6NJ1FG6YAMQ%3d
             * seconds : 5
             */

            private int site_id;
            private int ad_id;
            private int page_id;
            private int pos_id;
            private String data_type;
            private String media_type;
            private String target;
            private int goods_id;
            private int shop_id;
            private String title;
            private int price;
            private String url;
            private String img_url;
            private String img_url2;
            private String spm;
            private int seconds;

            public int getSite_id() {
                return site_id;
            }

            public void setSite_id(int site_id) {
                this.site_id = site_id;
            }

            public int getAd_id() {
                return ad_id;
            }

            public void setAd_id(int ad_id) {
                this.ad_id = ad_id;
            }

            public int getPage_id() {
                return page_id;
            }

            public void setPage_id(int page_id) {
                this.page_id = page_id;
            }

            public int getPos_id() {
                return pos_id;
            }

            public void setPos_id(int pos_id) {
                this.pos_id = pos_id;
            }

            public String getData_type() {
                return data_type;
            }

            public void setData_type(String data_type) {
                this.data_type = data_type;
            }

            public String getMedia_type() {
                return media_type;
            }

            public void setMedia_type(String media_type) {
                this.media_type = media_type;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getShop_id() {
                return shop_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getImg_url2() {
                return img_url2;
            }

            public void setImg_url2(String img_url2) {
                this.img_url2 = img_url2;
            }

            public String getSpm() {
                return spm;
            }

            public void setSpm(String spm) {
                this.spm = spm;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }
        }
    }

    @Override
    public String toString() {
        return popularize_items_list_get_response.getAreaA().get(0).getTitle();
    }
}
