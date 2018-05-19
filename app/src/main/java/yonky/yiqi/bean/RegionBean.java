package yonky.yiqi.bean;

import java.util.List;

public class RegionBean {

    /**
     * region_items_list_get_response : {"items":[{"site_id":52,"reg_id":859,"title":"牛仔城"},{"site_id":52,"reg_id":964,"title":"四季网批"},{"site_id":52,"reg_id":899,"title":"牛仔谷"},{"site_id":52,"reg_id":904,"title":"更多"}]}
     * status_code : 200
     * result : success
     */

    private RegionItemsListGetResponseBean region_items_list_get_response;
    private int status_code;
    private String result;

    public RegionItemsListGetResponseBean getRegion_items_list_get_response() {
        return region_items_list_get_response;
    }

    public void setRegion_items_list_get_response(RegionItemsListGetResponseBean region_items_list_get_response) {
        this.region_items_list_get_response = region_items_list_get_response;
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

    public static class RegionItemsListGetResponseBean {
        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }


    }
    public static class ItemsBean {
        /**
         * site_id : 52
         * reg_id : 859
         * title : 牛仔城
         */

        private String site_id;
        private String reg_id;
        private String title;

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getReg_id() {
            return reg_id;
        }

        public void setReg_id(String reg_id) {
            this.reg_id = reg_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
