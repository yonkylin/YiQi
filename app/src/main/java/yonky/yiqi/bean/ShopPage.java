package yonky.yiqi.bean;

/**
 * Created by Administrator on 2018/5/15.
 */

public class ShopPage {

    /**
     * shop_item_get_response : {"item":{"site_id":48,"site_tag":"cs","shop_id":26974,"shop_name":"知名度","is_reg":1,"vip":2,"tb_nick":"淘亿林","tb_url":"shop137276001.taobao.com","tb_url_2":"http://shop137276001.taobao.com","qq":"2646142581","phone":"13144082450","wechat":"","major":"女装","address":"广东省普宁市池尾大圆往揭阳方向300米左右，池尾大道路旁","market_id":274,"market":"池尾商圈","floor_id":275,"floor":"1F","dangkou":"N档","s_service":"一件代发","seller_head":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg_50x50.jpg","serller_head_original":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg","discount":"减20","spm":"5cKZa.48.66938.26974.0.10070","wap_url":"http://cs.m.17zwd.com/shop/26974.htm"},"is_favor_shop":0}
     * status_code : 200
     * result : success
     */

    private ShopItemGetResponseBean shop_item_get_response;
    private int status_code;
    private String result;

    public ShopItemGetResponseBean getShop_item_get_response() {
        return shop_item_get_response;
    }

    public void setShop_item_get_response(ShopItemGetResponseBean shop_item_get_response) {
        this.shop_item_get_response = shop_item_get_response;
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

    public static class ShopItemGetResponseBean {
        /**
         * item : {"site_id":48,"site_tag":"cs","shop_id":26974,"shop_name":"知名度","is_reg":1,"vip":2,"tb_nick":"淘亿林","tb_url":"shop137276001.taobao.com","tb_url_2":"http://shop137276001.taobao.com","qq":"2646142581","phone":"13144082450","wechat":"","major":"女装","address":"广东省普宁市池尾大圆往揭阳方向300米左右，池尾大道路旁","market_id":274,"market":"池尾商圈","floor_id":275,"floor":"1F","dangkou":"N档","s_service":"一件代发","seller_head":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg_50x50.jpg","serller_head_original":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg","discount":"减20","spm":"5cKZa.48.66938.26974.0.10070","wap_url":"http://cs.m.17zwd.com/shop/26974.htm"}
         * is_favor_shop : 0
         */

        private ShopBean item;
        private int is_favor_shop;

        public ShopBean getItem() {
            return item;
        }

        public void setItem(ShopBean item) {
            this.item = item;
        }

        public int getIs_favor_shop() {
            return is_favor_shop;
        }

        public void setIs_favor_shop(int is_favor_shop) {
            this.is_favor_shop = is_favor_shop;
        }


    }
}
