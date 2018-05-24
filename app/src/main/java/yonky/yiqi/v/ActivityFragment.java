package yonky.yiqi.v;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseActivity;

public class ActivityFragment extends BaseActivity {
    @BindView(R.id.frame)
    FrameLayout fragment;
    String dtype;
    @Override
    protected int getLayout() {
        return  R.layout.activity_fragment;
    }

    @Override
    protected void initEventAndData() {
        dtype=getIntent().getStringExtra("dtype");
        StyleFragment fragment = new StyleFragment();
        Bundle bundle =new Bundle();
        bundle.putSerializable("dtype",dtype);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
//        transaction.show(fragment);
        transaction.commit();

    }


}
