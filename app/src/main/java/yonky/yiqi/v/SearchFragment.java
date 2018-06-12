package yonky.yiqi.v;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.SearchContract;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.GoodFilterBean;
import yonky.yiqi.p.SearchPresenter;

import static yonky.yiqi.v.SearchAdapter.TYPE_DETAIL;
import static yonky.yiqi.v.SearchAdapter.TYPE_FILTER;
import static yonky.yiqi.v.SearchAdapter.TYPE_NODATA;
import static yonky.yiqi.v.SearchAdapter.TYPE_ORIGINAL;

/**
 * Created by Administrator on 2018/6/1.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
    @BindView(R.id.recyclerview)
            RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    SearchPresenter mPresenter;
    SearchAdapter mAdapter;
    GoodFilterBean goodFilter;
    String searchtype;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initEventAndData() {
        GoodBean goodBean = (GoodBean) getArguments().getParcelable("goodbean");
        searchtype=getArguments().getString("searchtype");

        mPresenter = new SearchPresenter(mContext);
        mPresenter.attachView(this);
        mAdapter = new SearchAdapter(mContext,goodBean);
        GridLayoutManager layoutManager= new GridLayoutManager(mContext,2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
              switch (mAdapter.getItemViewType(position)){
                  case TYPE_ORIGINAL:
                      return 2;
                  case TYPE_FILTER:
                      return 2;
                  case TYPE_DETAIL:
                      return 1;
                  case TYPE_NODATA:
                      return 2;
                 default:
                     return 1;
              }
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if(goodBean!=null){
            goodFilter= new GoodFilterBean();
            goodFilter.setUrl(goodBean.getTb_img());
            goodFilter.setZdid(goodBean.getSite_id());
//            mRefresh.setRefreshing(true);
            loadData();
            mRefresh.setRefreshing(true);
        }

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mAdapter.setEmpty(false);
                mAdapter.notifyDataSetChanged();
                loadData();

            }
        });

    }

    @Override
    public void loadData() {
        if("img".equals(searchtype)){
            mPresenter.imgSearchData(goodFilter);
        }else if("title".equals(searchtype)){
            mPresenter.titleSearchData(goodFilter);
        }

    }

    @Override
    public void showResult(List<GoodBean> listBeans) {
        if(mRefresh!=null){
            mRefresh.setRefreshing(false);
        }

            mAdapter.setGoodBeanList(listBeans);
            mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showNoData() {
        mRefresh.setRefreshing(false);
        mAdapter.setEmpty(true);
        mAdapter.notifyDataSetChanged();
    }
}
