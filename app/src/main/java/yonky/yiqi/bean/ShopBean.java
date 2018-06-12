package yonky.yiqi.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/12.
 */

public class ShopBean implements Parcelable{

        /**
         * site_id : 48
         * site_tag : cs
         * shop_id : 505447
         * shop_name : 机器服饰
         * is_reg : 1
         * vip : 1
         * tb_nick : 机器服饰
         * tb_url : shop137831045.taobao.com
         * tb_url_2 : null
         * qq : 429225408
         * phone : 15819559299
         * wechat : null
         * major : 女装,男装
         * address : 军埔电子商务产业园
         * market_id : 243
         * market : 更多
         * floor_id : 702
         * floor : 军埔商圈
         * dangkou : N档
         * s_service : 一件代发,退现金,包换款
         * seller_head : http://logo.taobao.com/shop-logo/db/db/TB1GeSsis2vU1JjSZFwSut2cpXa.jpg_50x50.jpg
         * serller_head_original : http://logo.taobao.com/shop-logo/db/db/TB1GeSsis2vU1JjSZFwSut2cpXa.jpg
         * discount : 实价
         * spm : C67%2b6Q5%2b6im0MUn7W8yuK7N4%2b56FHZmG
         * wap_url : http://cs.m.17zwd.com/shop/505447.htm
         */
        private static final long serialVersionUID = 1234567892;
        private String site_id;
        private String site_tag;
        private String shop_id;
        private String shop_name;
        private int is_reg;
        private int vip;
        private String tb_nick;
        private String tb_url;
        private String tb_url_2;
        private String qq;
        private String phone;
        private String wechat;
        private String major;
        private String address;
        private int market_id;
        private String market;
        private int floor_id;
        private String floor;
        private String dangkou;
        private String s_service;
        private String seller_head;
        private String serller_head_original;
        private String discount;
        private String spm;
        private String wap_url;


        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getSite_tag() {
            return site_tag;
        }

        public void setSite_tag(String site_tag) {
            this.site_tag = site_tag;
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

        public int getIs_reg() {
            return is_reg;
        }

        public void setIs_reg(int is_reg) {
            this.is_reg = is_reg;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getTb_nick() {
            return tb_nick;
        }

        public void setTb_nick(String tb_nick) {
            this.tb_nick = tb_nick;
        }

        public String getTb_url() {
            return tb_url;
        }

        public void setTb_url(String tb_url) {
            this.tb_url = tb_url;
        }

        public String getTb_url_2() {
            return tb_url_2;
        }

        public void setTb_url_2(String tb_url_2) {
            this.tb_url_2 = tb_url_2;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getMarket_id() {
            return market_id;
        }

        public void setMarket_id(int market_id) {
            this.market_id = market_id;
        }

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public int getFloor_id() {
            return floor_id;
        }

        public void setFloor_id(int floor_id) {
            this.floor_id = floor_id;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getDangkou() {
            return dangkou;
        }

        public void setDangkou(String dangkou) {
            this.dangkou = dangkou;
        }

        public String getS_service() {
            return s_service;
        }

        public void setS_service(String s_service) {
            this.s_service = s_service;
        }

        public String getSeller_head() {
            return seller_head;
        }

        public void setSeller_head(String seller_head) {
            this.seller_head = seller_head;
        }

        public String getSerller_head_original() {
            return serller_head_original;
        }

        public void setSerller_head_original(String serller_head_original) {
            this.serller_head_original = serller_head_original;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.site_id);
        dest.writeString(this.site_tag);
        dest.writeString(this.shop_id);
        dest.writeString(this.shop_name);
        dest.writeInt(this.is_reg);
        dest.writeInt(this.vip);
        dest.writeString(this.tb_nick);
        dest.writeString(this.tb_url);
        dest.writeString(this.tb_url_2);
        dest.writeString(this.qq);
        dest.writeString(this.phone);
        dest.writeString(this.wechat);
        dest.writeString(this.major);
        dest.writeString(this.address);
        dest.writeInt(this.market_id);
        dest.writeString(this.market);
        dest.writeInt(this.floor_id);
        dest.writeString(this.floor);
        dest.writeString(this.dangkou);
        dest.writeString(this.s_service);
        dest.writeString(this.seller_head);
        dest.writeString(this.serller_head_original);
        dest.writeString(this.discount);
        dest.writeString(this.spm);
        dest.writeString(this.wap_url);
    }

    public ShopBean() {
    }

    protected ShopBean(Parcel in) {
        this.site_id = in.readString();
        this.site_tag = in.readString();
        this.shop_id = in.readString();
        this.shop_name = in.readString();
        this.is_reg = in.readInt();
        this.vip = in.readInt();
        this.tb_nick = in.readString();
        this.tb_url = in.readString();
        this.tb_url_2 = in.readString();
        this.qq = in.readString();
        this.phone = in.readString();
        this.wechat = in.readString();
        this.major = in.readString();
        this.address = in.readString();
        this.market_id = in.readInt();
        this.market = in.readString();
        this.floor_id = in.readInt();
        this.floor = in.readString();
        this.dangkou = in.readString();
        this.s_service = in.readString();
        this.seller_head = in.readString();
        this.serller_head_original = in.readString();
        this.discount = in.readString();
        this.spm = in.readString();
        this.wap_url = in.readString();
    }

    public static final Creator<ShopBean> CREATOR = new Creator<ShopBean>() {
        @Override
        public ShopBean createFromParcel(Parcel source) {
            return new ShopBean(source);
        }

        @Override
        public ShopBean[] newArray(int size) {
            return new ShopBean[size];
        }
    };
}
