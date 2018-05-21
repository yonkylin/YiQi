package yonky.yiqi.v;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.MarketContract;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.bean.ShopFilterBean;
import yonky.yiqi.p.MarketPresenter;
import yonky.yiqi.v.adapter.MarketAdapter;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MarketFragment extends BaseFragment implements MarketContract.View{
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_market)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.button)
    Button btRegion;
    MarketAdapter mMarketAdapter;
    List<ShopBean> shopBean;
    ShopFilterBean shopFilter;

    MarketPresenter mPresenter;
    boolean isLoadingMore;

    SharedPreferences mPreferences;
    String regionSelected;
    String zdid;

        @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initEventAndData() {
            mPreferences= mContext.getSharedPreferences("data",0);

        regionSelected=mPreferences.getString("region","广州");
        zdid =mPreferences.getString("regionId","42");
        btRegion.setText(regionSelected);

            fab.hide();
        mPresenter =new MarketPresenter(mContext);
        mPresenter.attachView(this);

        shopBean = new ArrayList<>();
        shopFilter= new ShopFilterBean();

        mMarketAdapter = new MarketAdapter(mContext, shopBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mMarketAdapter);

        mPresenter.loadData(shopFilter,false);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shopFilter.setPindex("1");
                mPresenter.loadData(shopFilter,false);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount =recyclerView.getLayoutManager().getItemCount();
//               第3个item出现时，显示 回到顶部按钮
                if(firstVisibleItem<=2||dy>0){
                    fab.hide();
                }else{
                    fab.show();
                }

//                最后一个出现，并且向下滑动时，加载更多
                if(lastVisibleItem>=totalItemCount-1 &&dy>0){

                    if(!isLoadingMore){
                        isLoadingMore=true;
                        int page=Integer.valueOf(shopFilter.getPindex())+1;
                        shopFilter.setPindex(String.valueOf(page));
                        mPresenter.loadData(shopFilter,true);
                    }
                }

            }
        }
        );
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.scrollToPosition(0);
                recyclerView.smoothScrollToPosition(0);
//                fab.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void showResult(List<ShopBean> beanList,Boolean loadingMore) {
            mSwipeRefreshLayout.setRefreshing(false);
            if(loadingMore){

                isLoadingMore=false;
                mMarketAdapter.getBeanList().addAll(beanList);
            }else {
                mMarketAdapter.setBeanList(beanList);
            }

        mMarketAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }
}
