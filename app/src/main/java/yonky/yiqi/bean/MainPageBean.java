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
        private List<AreaBean> AreaA;
        private List<AreaBean> AreaB1;
        private List<AreaBean> AreaB2;
        private List<AreaBean> AreaC1;
        private List<AreaBean> AreaC2;
        private List<AreaBean> AreaD;

        private String type;

        public List<AreaBean> getAreaA() {
            return AreaA;
        }

        public void setAreaA(List<AreaBean> AreaA) {
            this.AreaA = AreaA;
        }

        public List<AreaBean> getAreaB1() {
            return AreaB1;
        }

        public void setAreaB1(List<AreaBean> areaB1) {
            AreaB1 = areaB1;
        }

        public List<AreaBean> getAreaB2() {
            return AreaB2;
        }

        public void setAreaB2(List<AreaBean> areaB2) {
            AreaB2 = areaB2;
        }

        public List<AreaBean> getAreaC1() {
            return AreaC1;
        }

        public void setAreaC1(List<AreaBean> areaC1) {
            AreaC1 = areaC1;
        }

        public List<AreaBean> getAreaC2() {
            return AreaC2;
        }

        public void setAreaC2(List<AreaBean> areaC2) {
            AreaC2 = areaC2;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<AreaBean> getAreaD() {
            return AreaD;
        }

        public void setAreaD(List<AreaBean> areaD) {
            AreaD = areaD;
        }
    }

}
