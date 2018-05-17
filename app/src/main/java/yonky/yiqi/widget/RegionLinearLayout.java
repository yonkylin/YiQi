package yonky.yiqi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import yonky.yiqi.R;
import yonky.yiqi.util.MyUtil;

/**
 * Created by Administrator on 2018/5/17.
 */

public class RegionLinearLayout extends LinearLayout {
    private Paint mPaint;
    private Path path;
    Context mContext;
    int x0,x1,x2,x3,h;
    public RegionLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public RegionLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RegionLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

   private void init(Context mContext){
       setWillNotDraw(false);

        this.mContext = mContext;
       mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       mPaint.setColor(getResources().getColor(R.color.light_background));
       path = new Path();
       x0=0;
       h=MyUtil.dp2px(mContext,5);
       x1=MyUtil.dp2px(mContext,40);
       x2=MyUtil.dp2px(mContext,45);
       x3=MyUtil.dp2px(mContext,50);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(x0,h);
        path.lineTo(x1,h);
        path.lineTo(x2,0);
        path.lineTo(x3,h);
        path.lineTo(getWidth(),h);
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());



        path.close();
        canvas.drawPath(path,mPaint);
    }
}
