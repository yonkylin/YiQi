package yonky.yiqi.v;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.bean.GoodBean;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.v.main.StyleFragment;

public class ActivityFragment extends BaseActivity {
//    @BindView(R.id.frame)
//    FrameLayout fragment;
BaseFragment fragment;

    String dtype;
    ShopBean mShopBean;
//    GoodBean mGoodBean;
    String url;
    String zdid;
    @BindView(R.id.tv_title)
    TextView title;
    @Override
    protected int getLayout() {
        return  R.layout.activity_fragment;
    }

    @Override
    protected void initEventAndData() {
        Bundle bundle =new Bundle();
        dtype=getIntent().getStringExtra("dtype");
        mShopBean=(ShopBean)getIntent().getSerializableExtra("shopbean");
//        mGoodBean=(GoodBean)getIntent().getSerializableExtra("goodbean");
        url=getIntent().getStringExtra("url");
        zdid=getIntent().getStringExtra("zdid");
        if(dtype!=null){
            if("mrxk".equals(dtype)){
                title.setText("每日新款");
            }else if("mtsp".equals(dtype)){
                title.setText("模特实拍");
            }
            fragment = new StyleFragment();

            bundle.putSerializable("dtype",dtype);
            fragment.setArguments(bundle);

        }else if(mShopBean!=null){
            fragment=new ShopFragment();
            title.setText("店铺简介");

             bundle.putSerializable("shopbean",mShopBean);
             fragment.setArguments(bundle);
        }else {
            fragment =new SearchFragment();
            title.setText("同款宝贝");
//            bundle.putSerializable("goodbean",mGoodBean);
            fragment.setArguments(bundle);
        }
        Log.e("ActivityFragment","do");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }


}
