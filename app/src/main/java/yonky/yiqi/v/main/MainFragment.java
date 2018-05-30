package yonky.yiqi.v.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;


import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.MainContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.AreaEBean;
import yonky.yiqi.listener.MyListener;
import yonky.yiqi.p.MainPresenter;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.v.main.adapter.MainAdapter;
import yonky.yiqi.v.main.adapter.RegionAdapter;

import static yonky.yiqi.v.main.adapter.MainAdapter.TYPE_THREE;
import static yonky.yiqi.v.main.adapter.MainAdapter.TYPE_TWO;

public class MainFragment extends BaseFragment implements MainContract.View,MyListener {
    private static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_main)
    RecyclerView recyclerViewMain;
    MainAdapter mainAdapter;
    @BindView(R.id.button)
    Button btRegion;
    String regionSelected;
    String zdid;
    SharedPreferences preferences;
//    @BindView(R.id.fl_region)
//    FrameLayout mFrameLayout;

    PopupWindow mPopupWindow;

//    @BindView(R.id.rv_region)
//            RecyclerView rvRegion;
    RegionAdapter mRegionAdapter;


    List<AreaBean> listItem;

    MainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void initEventAndData() {
         preferences= mContext.getSharedPreferences("data",0);
        regionSelected=preferences.getString("region","广州");
        zdid =preferences.getString("regionId","42");
       btRegion.setText(regionSelected);

        mainAdapter = new MainAdapter(mContext);

        mPresenter =new MainPresenter(mContext);
        mPresenter.attachView(this);

        listItem = new ArrayList<>();
//        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);
           GridLayoutManager layoutManager = new GridLayoutManager(mContext,6, LinearLayoutManager.VERTICAL,false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {


                switch(mainAdapter.getItemViewType(position)){
                    case TYPE_TWO:
                        return 3;
                    case TYPE_THREE:
                        return 2;


                        default:
                            if(position>15 &&position!=20 &&position!=25 &&position!=30 &&position!=35){
                                return 3;
                            }
                            return 6;
                }

            }
        });
        recyclerViewMain.setLayoutManager(layoutManager );
        recyclerViewMain.setAdapter(mainAdapter);
        loadData();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

    }
    private void loadData(){
        mPresenter.loadDatas("A",zdid);
        mPresenter.loadDatas("B",zdid);
        mPresenter.loadDatas("C",zdid);
        mPresenter.loadDatas("D",zdid);
        mPresenter.loadDatas("E",zdid);
    }


    @Override
    public void showResult(List<AreaBean> areaBeanList, String type) {

//        if("A".equals(type)&& mainAdapter.getBannerList()==null){
        if("A".equals(type)){
            mainAdapter.setBannerList(areaBeanList);
            mainAdapter.notifyDataSetChanged();
//        }else if("B1".equals(type)&& mainAdapter.getB1List()==null ){
        }else if("B1".equals(type) ){
            mainAdapter.setB1List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
//        }else if("B2".equals(type)&&mainAdapter.getB2List()==null){
        }else if("B2".equals(type)){
            mainAdapter.setB2List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
            for(int i = 0;i<areaBeanList.size();i++){
                Log.d(TAG,areaBeanList.get(i).getTitle());
            }
//        }else if("C1".equals(type)&& mainAdapter.getC1List()==null ){
        }else if("C1".equals(type)){
            mainAdapter.setC1List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
//        }else if("C2".equals(type)&&mainAdapter.getC2List()==null){
        }else if("C2".equals(type)){
            mainAdapter.setC2List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
//        }else if("D".equals(type)&&mainAdapter.getDList()==null){
        }else if("D".equals(type)){
            mainAdapter.setDList(areaBeanList);
            mainAdapter.notifyDataSetChanged();
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void showE(List<AreaEBean> listE){
        mainAdapter.seteList(listE);
    }


    @OnClick(R.id.button)void regionButton(){
        showRegion();
    }
    private void showRegion(){
        View contentView= LayoutInflater.from(mContext).inflate(R.layout.window_region,null);
        mPopupWindow = new PopupWindow(contentView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT,true){
            @Override
            public void dismiss() {
                super.dismiss();
                updateButton();
            }
        };
        mPopupWindow.setContentView(contentView);

        RecyclerView rvRegion=contentView.findViewById(R.id.rv_region1);

        mRegionAdapter=new RegionAdapter(mContext,regionSelected);
        mRegionAdapter.setListener(this);
        FlexboxLayoutManager regionManager= new FlexboxLayoutManager();
        regionManager.setFlexDirection(FlexDirection.ROW);
        regionManager.setFlexWrap(FlexWrap.WRAP);

        rvRegion.setLayoutManager(regionManager);
        rvRegion.setAdapter(mRegionAdapter);
        mPopupWindow.showAsDropDown(btRegion,0, -MyUtil.dp2px(mContext,5));
    }
    private void updateButton(){
        String s=preferences.getString("region","广州");
        if(!s.equals(regionSelected)){
            regionSelected=s;
            btRegion.setText(s);
            zdid=preferences.getString("regionId","42");
            loadData();
        }

    }

    @OnClick(R.id.imageView)
    void alarm(){
        MyUtil.toast(mContext);
    }

    @Override
    public void onDestroyView() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void onClick() {
        mPopupWindow.dismiss();
        updateButton();
    }

}
