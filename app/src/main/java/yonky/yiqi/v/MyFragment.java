package yonky.yiqi.v;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.util.MyUtil;

public class MyFragment extends BaseFragment {
    @BindView(R.id.cl)
    ConstraintLayout mConstraintLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initEventAndData() {
    }
    @OnClick({R.id.iv_avatar,R.id.iv_gzdk,R.id.iv_gzbb,R.id.iv_wsp,R.id.iv_wtk,R.id.iv_cgd,R.id.iv_kdcx,R.id.iv_dkcz,
    R.id.iv_dftd,R.id.iv_qmsy,R.id.iv_lxkf})
    void ivToast(){
        MyUtil.toast(mContext);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("myFragment ","on Resume");
    }
}
