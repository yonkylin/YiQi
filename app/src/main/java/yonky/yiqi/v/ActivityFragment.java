package yonky.yiqi.v;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.v.main.StyleFragment;

public class ActivityFragment extends BaseActivity {
//    @BindView(R.id.frame)
//    FrameLayout fragment;
    String dtype;
    BaseFragment fragment;
    ShopBean mShopBean;
    @BindView(R.id.tv_title)
    TextView title;
    @Override
    protected int getLayout() {
        return  R.layout.activity_fragment;
    }

    @Override
    protected void initEventAndData() {
        dtype=getIntent().getStringExtra("dtype");
        if(dtype!=null){
            if("mrxk".equals(dtype)){
                title.setText("每日新款");
            }else if("mtsp".equals(dtype)){
                title.setText("模特实拍");
            }
            fragment = new StyleFragment();
            Bundle bundle =new Bundle();
            bundle.putSerializable("dtype",dtype);
            fragment.setArguments(bundle);

        }else{
            fragment=new ShopFragment();
            title.setText("店铺简介");
         mShopBean=(ShopBean)getIntent().getSerializableExtra("shopbean");
         Bundle bundle = new Bundle();
         bundle.putSerializable("shopbean",mShopBean);
         fragment.setArguments(bundle);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }


}
