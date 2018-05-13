package yonky.yiqi.bean;

public class GoodFilterBean {

    //    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48
//搜款式
  private  String price1="0.0";
    private  String psize="10";
    private   String size="";
    private   String seller_cid="";
    private   String orderby="mr";
    private   String color="" ;
    private  String keyword="";
    private  String pindex="1";
    private String from="android";
    private  String price2="9999.0";
    private  String dtype="sks";
    private   String zdid="48";



    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getPsize() {
        return psize;
    }

    public void setPsize(String psize) {
        this.psize = psize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSeller_cid() {
        return seller_cid;
    }

    public void setSeller_cid(String seller_cid) {
        this.seller_cid = seller_cid;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getZdid() {
        return zdid;
    }

    public void setZdid(String zdid) {
        this.zdid = zdid;
    }
}
