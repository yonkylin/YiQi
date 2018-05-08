package yonky.yiqi.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
    private boolean isCanSwipe=false;

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanSwipe;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanSwipe;
    }

    public boolean isCanSwipe() {
        return isCanSwipe;
    }

    public void setCanSwipe(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }
}
