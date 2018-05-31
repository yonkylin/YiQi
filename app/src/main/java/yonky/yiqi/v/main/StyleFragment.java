package yonky.yiqi.v.main;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.StyleContract;
import yonky.yiqi.bean.GoodAttributeBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.KVBean;
import yonky.yiqi.bean.RegionBean;
import yonky.yiqi.listener.MyListener;
import yonky.yiqi.p.StylePresenter;
import yonky.yiqi.v.main.adapter.StyleAdapter;
import yonky.yiqi.window.WindowGoodFilter;

import static yonky.yiqi.base.Constants.FILTER_REGION;

public class StyleFragment extends BaseFragment implements StyleContract.View ,MyListener {
    private static final String TAG=StyleFragment.class.getSimpleName();
    SharedPreferences preferences;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_style)
    RecyclerView recyclerView;
    @BindView((R.id.bt_default))
    Button btDefault;
    @BindView(R.id.bt_new)
    Button btNew;
    @BindView(R.id.bt_price)
    Button btPrice;
    @BindView(R.id.bt_filter)
    Button btFilter;
    @BindView(R.id.fab)
    FloatingActionButton fab;
//    @BindView(R.id.button)
//            Button btRegion;

    boolean isLoadingMore;
    String sort;
    String dtype;
    boolean isResume;


     StyleAdapter styleAdapter;
     StylePresenter mPresenter;
    List<GoodBean> goodBeans;

    GoodFilterBean filterBean;


//   GoodAttributeBean.GoodsItemGetResponseBean goodAttrs;
   List<KVBean> colorList;
   List<KVBean> clothList;
   List<RegionBean.ItemsBean> regionList;
   List<RegionBean.ItemsBean> floorList;
   WindowGoodFilter mWindowGoodFilter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_style;
    }

    @Override
    protected void initEventAndData() {
        filterBean = new GoodFilterBean();
        if(getArguments()!=null){
            dtype= getArguments().getString("dtype");
            filterBean.setDtype(dtype);
        }

        fab.hide();
        preferences = mContext.getSharedPreferences("data",0);
        mWindowGoodFilter = WindowGoodFilter.getInstance();
        mWindowGoodFilter.setListener(this);
        colorList= new ArrayList<>();
        clothList= new ArrayList<>();
        floorList = new ArrayList<>();
        regionList=new ArrayList<>();
//        goodAttrs=new GoodAttributeBean.GoodsItemGetResponseBean();
        mPresenter= new StylePresenter(mContext);
        mPresenter.attachView(this);

        goodBeans = new ArrayList<>();

        styleAdapter= new StyleAdapter(mContext,goodBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(styleAdapter);

        filterBean.setZdid(preferences.getString("regionId","42"));



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                filterBean.setPindex("1");
                mPresenter.loadDatas(filterBean,false);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemPosition =((GridLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int lastItemPosition =((GridLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalCount =recyclerView.getLayoutManager().getItemCount();
                if(firstItemPosition<2||dy>0 ){
                    fab.hide();
                }else{
                    fab.show();
                }

//                最后一个显示时，加载更多
                if(lastItemPosition>=totalCount-1&&dy>0){
                    if(!isLoadingMore){
                        isLoadingMore=true;
                        int page=Integer.valueOf(filterBean.getPindex())+1;
                        filterBean.setPindex(String.valueOf(page));
                        mPresenter.loadDatas(filterBean,true);
                    }
                }
            }
        });
//        btRegion.setText(preferences.getString("region","广州"));

        mPresenter.loadDatas(filterBean,false);
        mPresenter.getGoodColor("get_colors");
        mPresenter.getGoodColor("get_sizes");

    }
//这个fragment不只在viewpager中使用，所以不使用懒加载
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            mPresenter.loadDatas(filterBean,false);
//            mPresenter.getGoodColor("get_colors");
//            mPresenter.getGoodColor("get_sizes");
//        }
//    }



    //    逛商场 筛选按钮
    @OnClick(R.id.bt_filter)  void setBtFilter(){
        PopupWindow mPopupWindow=mWindowGoodFilter.newWindow(mContext);
        mPopupWindow.showAsDropDown(btFilter);

    }
    @OnClick(R.id.bt_default) void setBtDefault(){
        setColor(btDefault,btNew,btPrice);
        sort="mr";
        filterBean.setOrderby(sort);
        mPresenter.loadDatas(filterBean,false);
    }

    private int i;
    @OnClick(R.id.bt_price) void setBtPrice(){
        setColor(btPrice,btNew,btDefault);
        if(i%2==0){
            btPrice.setBackgroundResource(R.drawable.background_sort_up);
            sort="priOn_asc";
        }else{
            btPrice.setBackgroundResource(R.drawable.background_sort_down);
            sort="priOn_desc";
        }
        filterBean.setOrderby(sort);
        mPresenter.loadDatas(filterBean,false);
        i++;
    }
    @OnClick(R.id.bt_new)void setBtNew(){
        setColor(btNew,btDefault,btPrice);
        sort="newOn_asc";
        filterBean.setOrderby(sort);
        mPresenter.loadDatas(filterBean,false);
    }
    @OnClick(R.id.fab)void setFab(){
        recyclerView.smoothScrollToPosition(0);
    }



    private void setColor(Button bt, Button bt1, Button bt2){

        bt.setTextColor(getResources().getColor(R.color.colorPrimary));
        bt1.setTextColor(getResources().getColor(R.color.gray));
        bt2.setTextColor(getResources().getColor(R.color.gray));
        bt1.setBackgroundResource(R.color.light_background);
        bt2.setBackgroundResource(R.color.light_background);

    }

    @Override
    public void showResult(List<GoodBean> beanList,boolean loadingMore) {
        mSwipeRefreshLayout.setRefreshing(false);
        if(loadingMore){
            isLoadingMore=false;
            styleAdapter.getBeanList().addAll(beanList);
        }else{
            styleAdapter.setBeanList(beanList);
        }

        styleAdapter.notifyDataSetChanged();

    }

    @Override
    public void showGoodAttr(GoodAttributeBean.GoodsItemGetResponseBean bean) {
       if(bean.getItems()!=null){
           mWindowGoodFilter.setColorList(bean.getItems());
           colorList=bean.getItems();
       }else{
           mWindowGoodFilter.setClothList(bean.getClothes());
           clothList=bean.getClothes();
       }

    }

    @Override
    public void showRegion(List<RegionBean.ItemsBean> regionList,int type) {
        if(type==FILTER_REGION){
//            mWindowGoodFilter.setRegionList(regionList);
            Log.d(TAG,"SHOW Region data "+regionList.size());

        }
    }




    @Override
    public void onClick() {
        Log.d(TAG,"ON CLICK GET ENCODE"+mWindowGoodFilter.getColor());
//        filterBean.setColor(mWindowGoodFilter.getColorString()mWindowGoodFilter.getColorString());
        filterBean.setColor(mWindowGoodFilter.getColor());
        filterBean.setSize(mWindowGoodFilter.getSize());
        filterBean.setPindex("1");
        filterBean.setPrice1(mWindowGoodFilter.getPrice1());
        filterBean.setPrice2(mWindowGoodFilter.getPrice2());
        mPresenter.loadDatas(filterBean,false);
    }
}
