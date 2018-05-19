package yonky.yiqi.util;

import android.content.Context;
import android.support.constraint.ConstraintLayout;

public class MyUtil {

    public static int dp2px(Context context , int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)((dp*scale)+0.5f);
    }
    public static int px2dp(Context context , int px){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)((px/scale)+0.5f);
    }

//    获取屏幕宽高
    public static int getDisplayWidth(Context context){
      return   context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getDisplayHeight(Context context){
        return   context.getResources().getDisplayMetrics().heightPixels;
    }

//字符串转16进制  每个字节前面加% 以混淆
    public static String encode(String str)
    {
          String hexString="0123456789ABCDEF";
//根据默认编码获取字节数组
        byte[] bytes=str.getBytes();
        StringBuilder sb=new StringBuilder(bytes.length*2);
//将字节数组中每个字节拆解成2位16进制整数
        for(int i=0;i<bytes.length;i++)
        {   sb.append("%");
            sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
            sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
        }
        return sb.toString();
    }
}
