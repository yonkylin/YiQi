package yonky.yiqi.v;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.bt_login)
    Button btLogin;
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
   @OnClick(R.id.bt_login)
   void setLogin(){
       Toast.makeText(mContext,"该功能还在开发中！",Toast.LENGTH_SHORT).show();
   }

    @Override
    public void onResume() {
        Log.d("LoginFragment ","on Resume");
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
