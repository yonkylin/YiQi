package yonky.yiqi.bean;

/**
 * Created by Administrator on 2018/6/1.
 */

public class SkusBean {
    /**
     * price : 888
     * price2 : 868
     * properties : 1627207:15409374;20518:28314
     * properties_name : 1627207:15409374:姜黄色;20518:28314:S
     * num : 5000
     * sku_id : 3783241614758
     * img_url : https://img.alicdn.com/bao/uploaded/i1/2489702925/TB2c65mlv5TBuNjSspcXXbnGFXa_!!2489702925.jpg
     */

    private int price;
    private int price2;
    private String properties;
    private String properties_name;
    private int num;
    private long sku_id;
    private String img_url;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getProperties_name() {
        return properties_name;
    }

    public void setProperties_name(String properties_name) {
        this.properties_name = properties_name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getSku_id() {
        return sku_id;
    }

    public void setSku_id(long sku_id) {
        this.sku_id = sku_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}
