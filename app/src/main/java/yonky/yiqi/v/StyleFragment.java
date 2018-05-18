package yonky.yiqi.v;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import yonky.yiqi.p.StylePresenter;
import yonky.yiqi.v.adapter.StyleAdapter;

public class StyleFragment extends BaseFragment implements StyleContract.View {
    private static final String TAG=StyleFragment.class.getSimpleName();
    @BindView(R.id.rv_style)
    RecyclerView recyclerView;
    @BindView(R.id.bt_filter)
    Button btFilter;
     StyleAdapter styleAdapter;
     StylePresenter mPresenter;
    List<GoodBean> goodBeans;

    GoodFilterBean filterBean;


   GoodAttributeBean.GoodsItemGetResponseBean goodAttrs;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_style;
    }

    @Override
    protected void initEventAndData() {
        mPresenter= new StylePresenter(mContext);
        mPresenter.attachView(this);

        goodBeans = new ArrayList<>();
        filterBean = new GoodFilterBean();
        styleAdapter= new StyleAdapter(mContext,goodBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(styleAdapter);

        mPresenter.loadDatas(filterBean);
    }

    @Override
    public void showResult(List<GoodBean> beanList) {
        styleAdapter.setBeanList(beanList);
        styleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showGoodAttr(GoodAttributeBean.GoodsItemGetResponseBean bean) {
        goodAttrs=bean;

    }
    @OnClick(R.id.bt_filter)  void filter(){

        View contentView= LayoutInflater.from(mContext).inflate(R.layout.window_filter,null);
        PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setContentView(contentView);

        mPopupWindow.showAsDropDown(btFilter);
        Log.d(TAG,"filter onClick");
    }
}
