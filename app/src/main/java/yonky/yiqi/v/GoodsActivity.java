package yonky.yiqi.v;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.contract.GoodContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.bean.ShopFilterBean;
import yonky.yiqi.p.GoodPresenter;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.v.adapter.StyleAdapter;

import static yonky.yiqi.v.adapter.StyleAdapter.TYPE_NODATA;

/**
 * Created by Administrator on 2018/5/14.
 */

public class GoodsActivity extends BaseActivity implements GoodContract.View{
    public static final String TAG=GoodsActivity.class.getSimpleName();
    @BindView(R.id.rv_goods)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_shop)
    ImageView mShopBackground;
    @BindView(R.id.iv_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_shop_name)
    TextView mShopName;
    @BindView(R.id.tv_position)
    TextView mPosition;
    @BindView(R.id.fab)
        FloatingActionButton fab;

    boolean isLoadingMore;
    StyleAdapter mAdapter;
    List<GoodBean> mGoodList;
    GoodFilterBean goodFilter;
    ShopFilterBean shopFilter;
    GoodPresenter mPresenter;
    @Override
    protected int getLayout() {
        return R.layout.activity_goods;
    }



    @Override
    protected void initEventAndData() {
        fab.hide();
        mPresenter = new GoodPresenter(mContext);
        mPresenter.attachView(this);



        AreaBean areaBean =(AreaBean) getIntent().getSerializableExtra("areabean");
        ShopBean shopBean=(ShopBean) getIntent().getSerializableExtra("shopbean");

        mGoodList = new ArrayList<>();
        goodFilter = new GoodFilterBean();
        shopFilter= new ShopFilterBean();

        if(areaBean!=null){
//            设置过滤条件
            goodFilter.setShop_id(areaBean.getShop_id());
            goodFilter.setSpm(areaBean.getSpm());
            goodFilter.setZdid(areaBean.getSite_id());
            shopFilter.setShop_id(areaBean.getShop_id());
            shopFilter.setSpm(areaBean.getSpm());
            shopFilter.setZdid(areaBean.getSite_id());
        }else if(shopBean!=null){
            goodFilter.setShop_id(shopBean.getShop_id());
            goodFilter.setSpm(shopBean.getSpm());
            goodFilter.setZdid(shopBean.getSite_id());
            shopFilter.setShop_id(shopBean.getShop_id());
            shopFilter.setSpm(shopBean.getSpm());
            shopFilter.setZdid(shopBean.getSite_id());
        }



        mAdapter = new StyleAdapter(mContext,mGoodList);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if( mAdapter.getItemViewType(position)==TYPE_NODATA){
                    return 2;
                }else{
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mPresenter.loadGoods(goodFilter,false);
        mPresenter.loadShop(shopFilter);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        int page=Integer.valueOf(goodFilter.getPindex())+1;
                        goodFilter.setPindex(String.valueOf(page));
                        mPresenter.loadGoods(goodFilter,true);
                    }
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public void showShop(ShopBean shopBean) {
        GlideUtil.loadRoundImage(shopBean.getSerller_head_original(),mAvatar);
        mShopName.setText(shopBean.getShop_name());
        mPosition.setText(shopBean.getMarket()+"-"+shopBean.getFloor()+"-"+shopBean.getDangkou());
        MultiTransformation multi= new MultiTransformation(new BlurTransformation(25,2),new ColorFilterTransformation(R.color.gray));
        Glide.with(this).load(shopBean.getSerller_head_original())
                .apply(RequestOptions.bitmapTransform(multi))
                .into(mShopBackground);
    }

    @Override
    public void showResult(List<GoodBean> list,boolean loadingMore) {
        if(loadingMore){
            isLoadingMore=false;
            mAdapter.getBeanList().addAll(list);
        }else{
            mAdapter.setBeanList(list);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        mAdapter.setEmptyCount(1);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
