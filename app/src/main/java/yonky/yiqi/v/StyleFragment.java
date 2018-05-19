package yonky.yiqi.v;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

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
import yonky.yiqi.listener.MyClickListener;
import yonky.yiqi.listener.MyListener;
import yonky.yiqi.p.StylePresenter;
import yonky.yiqi.v.adapter.FilterAdapter;
import yonky.yiqi.v.adapter.StyleAdapter;

import static yonky.yiqi.base.Constants.FILTER_CLOTHES;
import static yonky.yiqi.base.Constants.FILTER_COLOR;

public class StyleFragment extends BaseFragment implements StyleContract.View ,MyListener {
    private static final String TAG=StyleFragment.class.getSimpleName();
    @BindView(R.id.rv_style)
    RecyclerView recyclerView;
    @BindView(R.id.bt_filter)
    Button btFilter;
     StyleAdapter styleAdapter;
     StylePresenter mPresenter;
    List<GoodBean> goodBeans;

    GoodFilterBean filterBean;


//   GoodAttributeBean.GoodsItemGetResponseBean goodAttrs;
   List<KVBean> colorList;
   List<KVBean> clothList;
   WindowGoodFilter mWindowGoodFilter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_style;
    }

    @Override
    protected void initEventAndData() {
        mWindowGoodFilter = WindowGoodFilter.getInstance();
        mWindowGoodFilter.setListener(this);
        colorList= new ArrayList<>();
        clothList= new ArrayList<>();
//        goodAttrs=new GoodAttributeBean.GoodsItemGetResponseBean();
        mPresenter= new StylePresenter(mContext);
        mPresenter.attachView(this);

        goodBeans = new ArrayList<>();
        filterBean = new GoodFilterBean();
        styleAdapter= new StyleAdapter(mContext,goodBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(styleAdapter);

        mPresenter.loadDatas(filterBean);
        mPresenter.getGoodColor("get_colors");
        mPresenter.getGoodColor("get_sizes");
    }



    @Override
    public void showResult(List<GoodBean> beanList) {
        styleAdapter.setBeanList(beanList);
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
//    逛商场 筛选按钮
    @OnClick(R.id.bt_filter)  void filter(){

        PopupWindow mPopupWindow=mWindowGoodFilter.newWindow(mContext);

        mPopupWindow.showAsDropDown(btFilter);



    }

    @Override
    public void onClick() {
        filterBean.setColor(mWindowGoodFilter.getColorString());
        filterBean.setSize(mWindowGoodFilter.getSize());
        mPresenter.loadDatas(filterBean);
    }
}
