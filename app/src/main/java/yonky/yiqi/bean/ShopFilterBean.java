package yonky.yiqi.bean;

public class ShopFilterBean {
    // 逛市场
    //    http://api2.17zwd.com/rest/shop/search_shops/?psize=10&orderby=mr&keyword=&bq=&service=&pindex=1&from=android&zdid=48
    //获取店铺详情
//    http://api2.17zwd.com/rest/shop/get_shop?shop_id=26974&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D

    private  String psize="10";
    private  String orderby="mr";
    private  String keyword="";
    private   String bq="";
    private  String service="";
    private   String pindex="1";
    private   String from="android";
    private   String zdid="48";
    private String shop_id="";
    private String spm="";
    private String user_id="-1";

    public String getPsize() {
        return psize;
    }

    public void setPsize(String psize) {
        this.psize = psize;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPindex() {
        return pindex;
    }

    public void setPindex(String pindex) {
        this.pindex = pindex;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getZdid() {
        return zdid;
    }

    public void setZdid(String zdid) {
        this.zdid = zdid;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getSpm() {
        return spm;
    }

    public void setSpm(String spm) {
        this.spm = spm;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
