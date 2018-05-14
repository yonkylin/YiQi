package yonky.yiqi.v;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.util.RippleViewHelper;
import yonky.yiqi.widget.RippleView;

public class FragmentTest extends BaseFragment {
    @BindView(R.id.ripple_view)
    RippleView rippleView;
    RippleViewHelper helper;
    @Override
    protected int getLayoutId() {

        return R.layout.login_info;
    }

    @Override
    protected void initEventAndData() {
        helper = new RippleViewHelper(rippleView);

    }

    @Override
    public void onResume() {
        super.onResume();
        helper.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        helper.cancel();
    }
}
