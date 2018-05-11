package yonky.yiqi.bean;

import com.google.gson.annotations.SerializedName;

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
    /**
     * popularize_items_list_get_response : {"AreaE":[{"m_Item1":[{"site_id":48,"ad_id":7203,"page_id":125,"pos_id":219,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":8258,"title":"朴清衣舍","price":0,"url":"http://cs.17zwd.com/shop/8258.htm","img_url":"https://aims.17zwd.com/osys/48/0e6463eb1eb84d9aebc946ce35efc265.jpg","img_url2":"https://aims.17zwd.com/osys/48/0e6463eb1eb84d9aebc946ce35efc265.jpg","spm":"c5jEjVMzAhHHfPtgy4D0bysYXrzV7VX7185Oekm3Rmg%3d","seconds":5}],"m_Item2":[{"site_id":48,"ad_id":11831,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1074606,"shop_id":3136,"title":"2446#纯棉95棉2017新款韩版潮短袖t恤女","price":20,"url":"http://cs.17zwd.com/item.htm?gid=1074606","img_url":"https://aims.17zwd.com/osys/48/a7c8ed3a807cf9f79a351e9f054be326.jpg","img_url2":"https://aims.17zwd.com/osys/48/a7c8ed3a807cf9f79a351e9f054be326.jpg","spm":"c5jEjVMzAhHEdo1ROdmViVoPG2Mz9cRKL0CC6NKRQNuJiukivdkvKg%3d%3d","seconds":5},{"site_id":48,"ad_id":7182,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":807302,"shop_id":18701,"title":"9036#2016新款韩版加绒套头连帽外套卫衣中长款宽松秋季","price":26,"url":"http://cs.17zwd.com/item.htm?gid=807302","img_url":"https://aims.17zwd.com/osys/48/be34a1da83f32c896bcec16c65c54179.jpg","img_url2":"https://aims.17zwd.com/osys/48/be34a1da83f32c896bcec16c65c54179.jpg","spm":"c5jEjVMzAhGnP84vGFB9fAAZ3kcT8dVwtpteCGq2zftsaj%2batGx%2bIw%3d%3d","seconds":5},{"site_id":48,"ad_id":7184,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":822107,"shop_id":24788,"title":"3314(实拍)高质量6535中长款卫衣女加厚宽松套头长袖印花卫衣","price":33,"url":"http://cs.17zwd.com/item.htm?gid=822107","img_url":"https://aims.17zwd.com/osys/48/9b082704ec7cb0037cdcd70453847b99.jpg","img_url2":"https://aims.17zwd.com/osys/48/9b082704ec7cb0037cdcd70453847b99.jpg","spm":"c5jEjVMzAhHWCByGDdoXs%2bih0%2fsVx715DIty%2bezl%2f0HKlsEx3mpqYA%3d%3d","seconds":5},{"site_id":48,"ad_id":10073,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1185709,"shop_id":27881,"title":"1067#(6535)新版破洞女装印花 实拍 已出货","price":18,"url":"http://cs.17zwd.com/item.htm?gid=1185709","img_url":"https://aims.17zwd.com/osys/48/e5e6e23ff83ed02131732cb79bac3cea.jpg","img_url2":"https://aims.17zwd.com/osys/48/e5e6e23ff83ed02131732cb79bac3cea.jpg","spm":"c5jEjVMzAhHWCByGDdoXs%2b9tg%2bZ9%2bYH5urd0feKo81SObpcyHbAZ5w%3d%3d","seconds":5}]},{"m_Item1":[{"site_id":48,"ad_id":11758,"page_id":125,"pos_id":219,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":15340,"title":"富迪网购","price":0,"url":"http://cs.17zwd.com/shop/15340.htm","img_url":"https://aims.17zwd.com/osys/48/d6ed98eaa40955f4fe472007980d2b1f.jpg","img_url2":"https://aims.17zwd.com/osys/48/d6ed98eaa40955f4fe472007980d2b1f.jpg","spm":"c5jEjVMzAhGyPf3ACQcaZfIeNP%2fVvD5m%2bpW1svz5ciU%3d","seconds":5}],"m_Item2":[{"site_id":48,"ad_id":11810,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1239522,"shop_id":27006,"title":"春夏秋冬T恤女装孕妇睡衣纯棉质月子服产后哺乳衣套装家居服","price":25,"url":"http://cs.17zwd.com/item.htm?gid=1239522","img_url":"https://aims.17zwd.com/osys/48/034a5ccdeee9c37c8ccbd6af630cf9c6.jpg","img_url2":"https://aims.17zwd.com/osys/48/034a5ccdeee9c37c8ccbd6af630cf9c6.jpg","spm":"c5jEjVMzAhHWCByGDdoXs7gRPl6WfMz37Fjn1DtGyNzTNBkhtErL4g%3d%3d","seconds":5},{"site_id":48,"ad_id":10069,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":900992,"shop_id":30311,"title":"201#字母款厚款18.薄16小脚裤女长裤打底裤哈伦裤运动裤原宿风三","price":18,"url":"http://cs.17zwd.com/item.htm?gid=900992","img_url":"https://aims.17zwd.com/osys/48/f590a3b3de6cae52ff148ff7f420db86.jpg","img_url2":"https://aims.17zwd.com/osys/48/f590a3b3de6cae52ff148ff7f420db86.jpg","spm":"c5jEjVMzAhHEdo1ROdmVic26%2b0HlwFvMDjV0VC2j5PJJh%2fdwWjSxYQ%3d%3d","seconds":5},{"site_id":48,"ad_id":7191,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1226317,"shop_id":16306,"title":"秋新款女装织带连帽飘带长宽松卫衣学生外女潮 1113# (实拍)","price":19,"url":"http://cs.17zwd.com/item.htm?gid=1226317","img_url":"https://aims.17zwd.com/osys/48/7ed060f4d1d424e37033489f230a6d0a.jpg","img_url2":"https://aims.17zwd.com/osys/48/7ed060f4d1d424e37033489f230a6d0a.jpg","spm":"c5jEjVMzAhGnP84vGFB9fOGrPSjtQSVQEExtFfqNFDmxnDnD29PzSQ%3d%3d","seconds":5},{"site_id":48,"ad_id":10083,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1240938,"shop_id":7213,"title":"夏秋季睡衣女长袖睡袍日式和服开衫系绳宽松大码菠萝卡通睡裙居家","price":23,"url":"http://cs.17zwd.com/item.htm?gid=1240938","img_url":"https://aims.17zwd.com/osys/48/04ed7c0495800eece0e2fdb8571032bc.jpg","img_url2":"https://aims.17zwd.com/osys/48/04ed7c0495800eece0e2fdb8571032bc.jpg","spm":"c5jEjVMzAhGq817KgS7t54vh%2bh1k57MIomlBjVDAvmEG3RWlPsTfKQ%3d%3d","seconds":5}]},{"m_Item1":[{"site_id":48,"ad_id":10071,"page_id":125,"pos_id":219,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":24253,"title":"依雅网批","price":0,"url":"http://cs.17zwd.com/shop/24253.htm","img_url":"https://aims.17zwd.com/osys/48/6f1867042378fda58339269341792615.jpg","img_url2":"https://aims.17zwd.com/osys/48/6f1867042378fda58339269341792615.jpg","spm":"c5jEjVMzAhEqMknXPYkPU1jKMaKsFX%2fn26zrBo1VGCg%3d","seconds":5}],"m_Item2":[{"site_id":48,"ad_id":7187,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":805314,"shop_id":9088,"title":"(实拍)904#2016新款套装秋季休闲运动套装女款春秋套装运动服","price":35,"url":"http://cs.17zwd.com/item.htm?gid=805314","img_url":"https://aims.17zwd.com/osys/48/d7af3ba05792f804517b90f8d176360d.jpg","img_url2":"https://aims.17zwd.com/osys/48/d7af3ba05792f804517b90f8d176360d.jpg","spm":"c5jEjVMzAhH3vrUL5OKlzgOHE3TQUW3e8nkA5xDoOWQ%3d","seconds":5},{"site_id":48,"ad_id":7183,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":823795,"shop_id":26344,"title":"316#实拍运动装女春秋两件套韩版卫衣套装女长袖大码休闲棒球服潮","price":40,"url":"http://cs.17zwd.com/item.htm?gid=823795","img_url":"https://aims.17zwd.com/osys/48/78ebc40ac77b0829d7b3d94b32e3df6d.jpg","img_url2":"https://aims.17zwd.com/osys/48/78ebc40ac77b0829d7b3d94b32e3df6d.jpg","spm":"c5jEjVMzAhHWCByGDdoXswhH%2fCnX85kmcaJwsDLS6AqtUFBD9mm4Gg%3d%3d","seconds":5},{"site_id":48,"ad_id":7190,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1239925,"shop_id":26971,"title":"9008#2017春秋季韩版宽松运动裤女小脚长裤子显瘦卫裤休闲哈伦裤","price":19,"url":"http://cs.17zwd.com/item.htm?gid=1239925","img_url":"https://aims.17zwd.com/osys/48/35d668cec87033c440cc19c8287cadaf.jpg","img_url2":"https://aims.17zwd.com/osys/48/35d668cec87033c440cc19c8287cadaf.jpg","spm":"c5jEjVMzAhHWCByGDdoXswN3wDD1TQBtpz7DJBAsREJO9w11K%2bZQSw%3d%3d","seconds":5},{"site_id":48,"ad_id":7192,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1240833,"shop_id":11676,"title":"(现货)997【模特实拍】秋冬款韩版竖条纹套头女毛衣长袖 针织衫","price":46,"url":"http://cs.17zwd.com/item.htm?gid=1240833","img_url":"https://aims.17zwd.com/osys/48/845dfd91052ae649d04d08fd28d0ed5e.jpg","img_url2":"https://aims.17zwd.com/osys/48/845dfd91052ae649d04d08fd28d0ed5e.jpg","spm":"c5jEjVMzAhGnP84vGFB9fGHVAM2s4SNEcbCp0ncGZJ8L0ufIJ0eb%2fw%3d%3d","seconds":5}]},{"m_Item1":[{"site_id":48,"ad_id":11798,"page_id":125,"pos_id":219,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":28528,"title":"香艾吧","price":0,"url":"http://cs.17zwd.com/shop/28528.htm","img_url":"https://aims.17zwd.com/osys/48/21cf0a0b513c636f6419f01541656681.jpg","img_url2":"https://aims.17zwd.com/osys/48/21cf0a0b513c636f6419f01541656681.jpg","spm":"c5jEjVMzAhEqMknXPYkPU8bHQbdS35Np4moDiSeVOvc%3d","seconds":5}],"m_Item2":[{"site_id":48,"ad_id":7185,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1229377,"shop_id":23646,"title":"6807#官图长袖卫衣女宽松加大码薄款学生蕾丝t恤显瘦上衣","price":20,"url":"http://cs.17zwd.com/item.htm?gid=1229377","img_url":"https://aims.17zwd.com/osys/48/f78b5f549148202389ead99beccf9ed1.jpg","img_url2":"https://aims.17zwd.com/osys/48/f78b5f549148202389ead99beccf9ed1.jpg","spm":"c5jEjVMzAhHWCByGDdoXs2t3UnEETEPBTiQE%2bB7AHqfJdXeHnbECMA%3d%3d","seconds":5},{"site_id":48,"ad_id":10080,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1238151,"shop_id":16306,"title":"原宿风宽松bf织带破洞薄款套头长袖卫衣女学生百搭 1126#(实拍)","price":18,"url":"http://cs.17zwd.com/item.htm?gid=1238151","img_url":"https://aims.17zwd.com/osys/48/48556e90c2ec80df055d952b6bb8fdf0.jpg","img_url2":"https://aims.17zwd.com/osys/48/48556e90c2ec80df055d952b6bb8fdf0.jpg","spm":"c5jEjVMzAhGnP84vGFB9fFB5%2btx0WIVy%2f9xqV%2byP1zCEAXmWBl35aA%3d%3d","seconds":5},{"site_id":48,"ad_id":11778,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1039255,"shop_id":32732,"title":"实拍图158#        牛仔裙夏季女2017新款韩版高腰短裙百搭半身裙","price":20,"url":"http://cs.17zwd.com/item.htm?gid=1039255","img_url":"https://aims.17zwd.com/osys/48/9cc39691593a229d6f5554424fae6962.jpg","img_url2":"https://aims.17zwd.com/osys/48/9cc39691593a229d6f5554424fae6962.jpg","spm":"c5jEjVMzAhHEdo1ROdmViY5ssMlwYv653s%2bjtg%2bs8mO3XQrcOz5Z8A%3d%3d","seconds":5},{"site_id":48,"ad_id":10074,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":925176,"shop_id":18701,"title":"9086#官方图(好质量大卫衣250克)","price":16,"url":"http://cs.17zwd.com/item.htm?gid=925176","img_url":"https://aims.17zwd.com/osys/48/7d8fc2671d34dbaf79f7e9f07a9997c8.jpg","img_url2":"https://aims.17zwd.com/osys/48/7d8fc2671d34dbaf79f7e9f07a9997c8.jpg","spm":"c5jEjVMzAhGnP84vGFB9fJ%2f1bsxkGDVFzV3GLhBXFyVdmUTwcs3mlA%3d%3d","seconds":5}]},{"m_Item1":[{"site_id":48,"ad_id":10070,"page_id":125,"pos_id":219,"data_type":"PopularizeModule","media_type":"Image","target":"Shop","goods_id":0,"shop_id":26974,"title":"知名度","price":0,"url":"http://cs.17zwd.com/shop/26974.htm","img_url":"https://aims.17zwd.com/osys/48/d5070b93592c599f0a1fbc330567710a.jpg","img_url2":"https://aims.17zwd.com/osys/48/d5070b93592c599f0a1fbc330567710a.jpg","spm":"c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2bVE%3d","seconds":5}],"m_Item2":[{"site_id":48,"ad_id":10078,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1239826,"shop_id":27430,"title":"D2#实拍长袖卫衣女韩版潮学生宽松bf ulzzang休闲百搭印花笑脸薄","price":868,"url":"http://cs.17zwd.com/item.htm?gid=1239826","img_url":"https://aims.17zwd.com/osys/48/27a338509c9e75cd1691835f38a1ce62.jpg","img_url2":"https://aims.17zwd.com/osys/48/27a338509c9e75cd1691835f38a1ce62.jpg","spm":"c5jEjVMzAhHWCByGDdoXs77vA%2f3StXjJRwkJTvewanycDjwTOm2YmA%3d%3d","seconds":5},{"site_id":48,"ad_id":7188,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":823745,"shop_id":27620,"title":"811#实拍 夏装韩版学生长袖女韩国原宿风短袖bf班服","price":13,"url":"http://cs.17zwd.com/item.htm?gid=823745","img_url":"https://aims.17zwd.com/osys/48/04949348fea405e5d12a4f44eaf2c4ea.jpg","img_url2":"https://aims.17zwd.com/osys/48/04949348fea405e5d12a4f44eaf2c4ea.jpg","spm":"c5jEjVMzAhHWCByGDdoXs7Owq51SvmnJTatfZHURv8XaF9yJhNemRA%3d%3d","seconds":5},{"site_id":48,"ad_id":11777,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1241224,"shop_id":32732,"title":"2288#实拍图    韩版破洞牛仔裤女9分高腰宽松显瘦九分裤哈伦裤子","price":30,"url":"http://cs.17zwd.com/item.htm?gid=1241224","img_url":"https://aims.17zwd.com/osys/48/d2f203900de9e7c5a211f50db667f2b1.jpg","img_url2":"https://aims.17zwd.com/osys/48/d2f203900de9e7c5a211f50db667f2b1.jpg","spm":"c5jEjVMzAhHEdo1ROdmVidJAtAKO0lE9372W86XsTtEEBJqBEZUqPw%3d%3d","seconds":5},{"site_id":48,"ad_id":7181,"page_id":125,"pos_id":223,"data_type":"PopularizeModule","media_type":"Image","target":"Goods","goods_id":1235414,"shop_id":26974,"title":"793#实拍2017秋季新款简约长袖印花圆领动物卡通图案长袖T恤女","price":22,"url":"http://cs.17zwd.com/item.htm?gid=1235414","img_url":"https://aims.17zwd.com/osys/48/275bda4e40a04427741d347e89841b94.jpg","img_url2":"https://aims.17zwd.com/osys/48/275bda4e40a04427741d347e89841b94.jpg","spm":"c5jEjVMzAhHWCByGDdoXs4GDaW4Jff7%2fyd6jXB5jPlLCuUPQIqg2Kg%3d%3d","seconds":5}]}]}
     */




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
        private List<AreaEBean> AreaE;

//        private String type;

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

//        public String getType() {
//            return type;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }

        public List<AreaBean> getAreaD() {
            return AreaD;
        }

        public void setAreaD(List<AreaBean> areaD) {
            AreaD = areaD;
        }

        public List<AreaEBean> getAreaE() {
            return AreaE;
        }

        public void setAreaE(List<AreaEBean> AreaE) {
            this.AreaE = AreaE;
        }

    }

}
