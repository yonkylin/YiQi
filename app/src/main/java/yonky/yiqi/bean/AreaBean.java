package yonky.yiqi.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AreaBean implements Parcelable{

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

//    private static final long serialVersionUID = 1234567890;

    private String site_id;
    private int ad_id;
    private int page_id;
    private int pos_id;
    private String data_type;
    private String media_type;
    private String target;
    private String goods_id;
    private String shop_id;
    private String title;
    private float price;
    private String url;
    private String img_url;
    private String img_url2;
    private String spm;
    private int seconds;
    private String dtype="sks";

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
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

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.site_id);
        dest.writeString(this.data_type);
        dest.writeString(this.shop_id);
        dest.writeString(this.goods_id);
        dest.writeString(this.title);
        dest.writeFloat(this.price);
        dest.writeString(this.img_url);
        dest.writeString(this.img_url2);
        dest.writeString(this.spm);
        dest.writeString(this.dtype);
    }

    public AreaBean() {
    }

    protected AreaBean(Parcel in) {
        this.site_id = in.readString();
        this.data_type = in.readString();
        this.shop_id = in.readString();
        this.goods_id = in.readString();
        this.title = in.readString();
        this.price = in.readFloat();
        this.img_url = in.readString();
        this.img_url2 = in.readString();
        this.spm = in.readString();
        this.dtype = in.readString();
    }

    public static final Creator<AreaBean> CREATOR = new Creator<AreaBean>() {
        @Override
        public AreaBean createFromParcel(Parcel source) {
            return new AreaBean(source);
        }

        @Override
        public AreaBean[] newArray(int size) {
            return new AreaBean[size];
        }
    };
}
