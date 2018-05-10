package yonky.yiqi.util;

import android.content.Context;

public class MyUtil {
    public static int dp2px(Context context , int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)((dp*scale)+0.5f);
    }
    public static int px2dp(Context context , int px){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)((px/scale)+0.5f);
    }
}
