package yonky.yiqi.v;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.constraint.ConstraintLayout;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import butterknife.BindView;
import yonky.yiqi.R;
import yonky.yiqi.base.BaseFragment;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.util.RippleViewHelper;
import yonky.yiqi.widget.RippleView;

public class LoginFragment extends BaseFragment {
    @BindView(R.id.ripple_view)
    RippleView rippleView;
    @BindView(R.id.cs_login_info)
    ConstraintLayout csLayout;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    RippleViewHelper helper;

    AnimatorSet animatorSet;
    @Override
    protected int getLayoutId() {

        return R.layout.fragment_login;
    }

    @Override
    protected void initEventAndData() {
        helper = new RippleViewHelper(rippleView);
        ObjectAnimator csAnimation=ObjectAnimator.ofFloat(csLayout,"translationX", MyUtil.getDisplayWidth(mContext),0);
        csAnimation.setInterpolator(new OvershootInterpolator());
        csAnimation.setDuration(1000);
        ObjectAnimator ivAnimation1=ObjectAnimator.ofFloat(ivLogo,"scaleX",0,1);
        ObjectAnimator ivAnimation2=ObjectAnimator.ofFloat(ivLogo,"scaleY",0,1);
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(ivAnimation1,ivAnimation2);
        animatorSet.play(ivAnimation1).after(csAnimation);

    }

    @Override
    public void onResume() {
        super.onResume();
        helper.start();
        animatorSet.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        helper.cancel();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//    }
}
