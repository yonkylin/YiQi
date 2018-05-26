package yonky.yiqi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import yonky.yiqi.R;
import yonky.yiqi.util.MyUtil;

/**
 * Created by Administrator on 2018/5/26.
 */

public class TextViewBottomLine extends AppCompatTextView {
    private Context mContext;
    int padding;
    Paint paint;
    boolean isBottomLine;
    public TextViewBottomLine(Context context) {
        super(context);
        init(context);
    }

    public TextViewBottomLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextViewBottomLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        mContext=context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.light_gray));
        padding= MyUtil.dp2px(mContext,10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isBottomLine){
            canvas.drawLine(padding,getHeight(),getWidth()-padding,getHeight(),paint);
        }

    }

    public boolean isBottomLine() {
        return isBottomLine;
    }

    public void setBottomLine(boolean bottomLine) {
        isBottomLine = bottomLine;
    }
}
