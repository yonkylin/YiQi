package yonky.yiqi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;

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

//字符串转16进制
    public static String encode(String str)
    {
          String hexString="0123456789ABCDEF";
//根据默认编码获取字节数组
        byte[] bytes=str.getBytes();
        StringBuilder sb=new StringBuilder(bytes.length*2);
//将字节数组中每个字节拆解成2位16进制整数
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
            sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
        }
        return sb.toString();
    }

    //url编码
public static String getURLEncoderString(String str) {
    String result="";
    if(null==str){
        return "";
    }
    try{
        result=java.net.URLEncoder.encode(str,"UTF-8");
    }catch (UnsupportedEncodingException e){
        e.printStackTrace();
    }
        return result;
}


//判断是否有安装qq
    public static boolean isQQAvailable(Context context) {

        final PackageManager mPackageManager = context.getPackageManager();

        List pinfo = mPackageManager.getInstalledPackages(0);

        if(pinfo !=null) {
            for(int i =0;i < pinfo.size();i++) {
                String pn = ((PackageInfo)pinfo.get(i)).packageName;
                if(pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


    //清除输入法导致的内存泄漏，在使用输入法的activity销毁时调用
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }

        String [] viewArray = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field filed;
        Object filedObject;

        for (String view:viewArray) {
            try{
                filed = inputMethodManager.getClass().getDeclaredField(view);
                if (!filed.isAccessible()) {
                    filed.setAccessible(true);
                }
                filedObject = filed.get(inputMethodManager);
                if (filedObject != null && filedObject instanceof View) {
                    View fileView = (View) filedObject;
                    if (fileView.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        filed.set(inputMethodManager, null); // 置空，破坏掉path to gc节点
                    } else {
                        break;// 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                    }
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }


    public static void toast(Context mContext){
        Toast.makeText(mContext,"该功能还在开发中！",Toast.LENGTH_SHORT).show();
    }
}
