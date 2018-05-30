package yonky.yiqi.v;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.v.main.StyleFragment;

public class ActivityFragment extends BaseActivity {
//    @BindView(R.id.frame)
//    FrameLayout fragment;
    String dtype;
    BaseFragment fragment;
    @Override
    protected int getLayout() {
        return  R.layout.activity_fragment;
    }

    @Override
    protected void initEventAndData() {
        dtype=getIntent().getStringExtra("dtype");
        if(dtype!=null){
            fragment = new StyleFragment();
            Bundle bundle =new Bundle();
            bundle.putSerializable("dtype",dtype);
            fragment.setArguments(bundle);

        }else{
            fragment=new ShopFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }


}
