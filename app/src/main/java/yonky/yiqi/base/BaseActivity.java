package yonky.yiqi.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yonky.yiqi.R;

public abstract class BaseActivity extends AppCompatActivity  {
    protected Activity mContext;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        设置状态栏透明，并且颜色按toolban的颜色。
//        sdk>19 的toolbar 要设置paddingtop =25dp 的上边距，toolbar内容才不会跟状态栏重叠。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }




        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnbinder.unbind();
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
