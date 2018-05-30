package yonky.yiqi.v.main;

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
    boolean isResume;
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
       MyUtil.toast(mContext);
   }

//    @Override
//    public void onResume() {
//        Log.d("LoginFragment ","on Resume");
//        super.onResume();
//        isResume=true;
//
//    }
    //    viewPager加载的机制是缓存左右连个page的fragment.这样导致此fragment左右两边的page切换到此fragment
    //    时，此fragment的onResume()方法不会被调用。（已经被缓存加载好了）
    //     但，setUserVisibleHint()会被多次调用。在切换到此page时，也会被调用。所以动画放在此方法才能正常执行
    //    关于viewpager中fragment的生命周期请参考 https://blog.csdn.net/jemenchen/article/details/52645380


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
       Log.d("LoginFragment","setUserVisibleHint  "+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){

            helper.start();
            animatorSet.start();
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        helper.cancel();
//        isResume=false;
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//    }
}
