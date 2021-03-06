package yonky.yiqi.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/12.
 */

public class GoodBean implements Parcelable{
    /**
     * site_id : 48
     * shop_market_id : 274
     * shop_floor_id : 275
     * shop_id : 39882
     * shop_name : 唯依客网批
     * shop_tb_nick : 唯依客
     * shop_qq : 738138422
     * shop_market : 池尾商圈
     * shop_floor : 1F
     * shop_dangkou : N档
     * shop_services :
     * shop_youhui : null
     * shop_is_flagship : 0
     * goods_id : 106778639
     * gid_old : 0
     * title : 150# (180克双磨牛奶丝)抖音小猪佩奇短袖t恤女【实拍现货】
     * price1 : 29.5
     * price2 : 9.5
     * gno :
     * cate_id : 50000671
     * cate_name : null
     * tb_url : http://item.taobao.com/item.htm?id=569617510344
     * tb_num_iid : 569617510344
     * status : 1
     * tb_img : https://img.alicdn.com/bao/uploaded/i4/2980477580/TB2ybObrntYBeNjy1XdXXXXyVXa_!!2980477580.jpg
     * tb_imgs : null
     * quantity : 0
     * attributes : null
     * sku_attributes : null
     * skus : null
     * spm : C67%2b6Q5%2b6inEQnSwEl%2fV8kmFdMP6NlK%2fKst0z4qes8w%3d
     * wap_url : http://cs.m.17zwd.com/item.htm?gid=106778639
     */

    private String site_id;
    private int shop_market_id;
    private int shop_floor_id;
    private String shop_id;
    private String shop_name;
    private String shop_tb_nick;
    private String shop_qq;
    private String shop_market;
    private String shop_floor;
    private String shop_dangkou;
    private String shop_services;
    private String shop_youhui;
    private int shop_is_flagship;
    private String goods_id;
    private int gid_old;
    private String title;
    private double price1;
    private double price2;
    private String gno;
    private int cate_id;
    private String cate_name;
    private String tb_url;
    private long tb_num_iid;
    private int status;
    private String tb_img;
    private String tb_imgs;
    private int quantity;
    private List<String> attributes;
    private SkuAttributesBean sku_attributes;
    private List<SkusBean> skus;
    private String spm;
    private String wap_url;
//    private static final long serialVersionUID = 1234567891;

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public int getShop_market_id() {
        return shop_market_id;
    }

    public void setShop_market_id(int shop_market_id) {
        this.shop_market_id = shop_market_id;
    }

    public int getShop_floor_id() {
        return shop_floor_id;
    }

    public void setShop_floor_id(int shop_floor_id) {
        this.shop_floor_id = shop_floor_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_tb_nick() {
        return shop_tb_nick;
    }

    public void setShop_tb_nick(String shop_tb_nick) {
        this.shop_tb_nick = shop_tb_nick;
    }

    public String getShop_qq() {
        return shop_qq;
    }

    public void setShop_qq(String shop_qq) {
        this.shop_qq = shop_qq;
    }

    public String getShop_market() {
        return shop_market;
    }

    public void setShop_market(String shop_market) {
        this.shop_market = shop_market;
    }

    public String getShop_floor() {
        return shop_floor;
    }

    public void setShop_floor(String shop_floor) {
        this.shop_floor = shop_floor;
    }

    public String getShop_dangkou() {
        return shop_dangkou;
    }

    public void setShop_dangkou(String shop_dangkou) {
        this.shop_dangkou = shop_dangkou;
    }

    public String getShop_services() {
        return shop_services;
    }

    public void setShop_services(String shop_services) {
        this.shop_services = shop_services;
    }

    public String getShop_youhui() {
        return shop_youhui;
    }

    public void setShop_youhui(String shop_youhui) {
        this.shop_youhui = shop_youhui;
    }

    public int getShop_is_flagship() {
        return shop_is_flagship;
    }

    public void setShop_is_flagship(int shop_is_flagship) {
        this.shop_is_flagship = shop_is_flagship;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public int getGid_old() {
        return gid_old;
    }

    public void setGid_old(int gid_old) {
        this.gid_old = gid_old;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public String getGno() {
        return gno;
    }

    public void setGno(String gno) {
        this.gno = gno;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getTb_url() {
        return tb_url;
    }

    public void setTb_url(String tb_url) {
        this.tb_url = tb_url;
    }

    public long getTb_num_iid() {
        return tb_num_iid;
    }

    public void setTb_num_iid(long tb_num_iid) {
        this.tb_num_iid = tb_num_iid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTb_img() {
        return tb_img;
    }

    public void setTb_img(String tb_img) {
        this.tb_img = tb_img;
    }

    public String getTb_imgs() {
        return tb_imgs;
    }

    public void setTb_imgs(String tb_imgs) {
        this.tb_imgs = tb_imgs;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SkuAttributesBean getSku_attributes() {
        return sku_attributes;
    }

    public void setSku_attributes(SkuAttributesBean sku_attributes) {
        this.sku_attributes = sku_attributes;
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
    }

    public String getWap_url() {
        return wap_url;
    }

    public void setWap_url(String wap_url) {
        this.wap_url = wap_url;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public List<SkusBean> getSkus() {
        return skus;
    }

    public void setSkus(List<SkusBean> skus) {
        this.skus = skus;
    }

    public static class SkuAttributesBean {
        /**
         * colors : 乳白色,白色,灰色,姜黄色,军绿色
         * sizes : S,M,L,XL,2XL
         */

        private String colors;
        private String sizes;

        public String getColors() {
            return colors;
        }

        public void setColors(String colors) {
            this.colors = colors;
        }

        public String getSizes() {
            return sizes;
        }

        public void setSizes(String sizes) {
            this.sizes = sizes;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tb_img);
        dest.writeString(this.site_id);
        dest.writeDouble(this.price1);
        dest.writeDouble(this.price2);
        dest.writeString(this.shop_id);
        dest.writeString(this.shop_tb_nick);
        dest.writeString(this.goods_id);
        dest.writeString(this.title);
        dest.writeString(this.tb_imgs);
        dest.writeString(this.spm);
    }

    public GoodBean() {
    }

    protected GoodBean(Parcel in) {
        this.tb_img = in.readString();
        this.site_id = in.readString();
        this.price1=in.readDouble();
        this.price2=in.readDouble();
        this.shop_id = in.readString();
        this.shop_tb_nick = in.readString();
        this.goods_id = in.readString();
        this.title = in.readString();
        this.tb_imgs = in.readString();
        this.spm = in.readString();
    }

    public static final Creator<GoodBean> CREATOR = new Creator<GoodBean>() {
        @Override
        public GoodBean createFromParcel(Parcel source) {
            return new GoodBean(source);
        }

        @Override
        public GoodBean[] newArray(int size) {
            return new GoodBean[size];
        }
    };
}
