package yonky.yiqi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import yonky.yiqi.R;
import yonky.yiqi.util.MyUtil;

/**
 * Created by Administrator on 2018/5/17.
 */

public class RegionLinearLayout extends LinearLayout {
    private Paint mPaint1;
    private Paint mPaint2;
    private Path path1;
    private Path path2;
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
       mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
       mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
       mPaint2.setColor(getResources().getColor(R.color.light_background));

       mPaint1.setColor(getResources().getColor(R.color.light));
       path1 = new Path();
       path2 = new Path();
       x0=0;
       h=MyUtil.dp2px(mContext,5);
       x1=MyUtil.dp2px(mContext,40);
       x2=MyUtil.dp2px(mContext,45);
       x3=MyUtil.dp2px(mContext,50);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path1.moveTo(x0,h);
        path1.lineTo(x1,h);
        path1.lineTo(x2,0);
        path1.lineTo(x3,h);
        path1.lineTo(getWidth(),h);
        path1.lineTo(getWidth(),getHeight());
        path1.lineTo(0,getHeight());
        path1.close();
        canvas.drawPath(path1, mPaint1);


        path2.lineTo(x0,h);
        path2.lineTo(x1,h);
        path2.lineTo(x2,0);
        path2.lineTo(x3,h);
        path2.lineTo(getWidth(),h);
        path2.lineTo(getWidth(),0);
        path2.close();
        canvas.drawPath(path2,mPaint2);
    }
}
