package yonky.yiqi.v;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.base.contract.MainContract;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.bean.AreaEBean;
import yonky.yiqi.p.MainPresenter;
import yonky.yiqi.v.adapter.MainAdapter;

import static yonky.yiqi.v.adapter.MainAdapter.TYPE_THREE;
import static yonky.yiqi.v.adapter.MainAdapter.TYPE_TWO;

public class MainFragment extends BaseFragment implements MainContract.View{
    private static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.rv_main)
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    List<AreaBean> listItem;

    MainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initEventAndData() {
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
        recyclerView.setLayoutManager(layoutManager );
        recyclerView.setAdapter(mainAdapter);

        mPresenter.loadDatas("A");
        mPresenter.loadDatas("B");
        mPresenter.loadDatas("C");
        mPresenter.loadDatas("D");
        mPresenter.loadDatas("E");

    }

    @Override
    public void showResult(List<AreaBean> areaBeanList, String type) {

        if("A".equals(type)&& mainAdapter.getBannerList()==null){
            mainAdapter.setBannerList(areaBeanList);
            mainAdapter.notifyDataSetChanged();
        }else if("B1".equals(type)&& mainAdapter.getB1List()==null ){
            mainAdapter.setB1List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
        }else if("B2".equals(type)&&mainAdapter.getB2List()==null){
            mainAdapter.setB2List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
            for(int i = 0;i<areaBeanList.size();i++){
                Log.d(TAG,areaBeanList.get(i).getTitle());
            }
        }else if("C1".equals(type)&& mainAdapter.getC1List()==null ){
            mainAdapter.setC1List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
        }else if("C2".equals(type)&&mainAdapter.getC2List()==null){
            mainAdapter.setC2List(areaBeanList);
            mainAdapter.notifyDataSetChanged();
        }else if("D".equals(type)&&mainAdapter.getDList()==null){
            mainAdapter.setDList(areaBeanList);
            mainAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public void showE(List<AreaEBean> listE){
        mainAdapter.seteList(listE);
    }




    @Override
    public void onDestroyView() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }
}
