package yonky.yiqi.window;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;

import yonky.yiqi.R;
import yonky.yiqi.bean.ShopBean;
import yonky.yiqi.util.MyUtil;
import yonky.yiqi.widget.TextViewBottomLine;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Administrator on 2018/5/26.
 */

public class ConnectWindow {
    Context mContext;
    ShopBean shopBean;
    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    ClipboardManager clipboardManager;
    public ConnectWindow(Context context,ShopBean shopBean) {
        mContext = context;
        this.shopBean=shopBean;
        clipboardManager =(ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public PopupWindow newWindow(int height){

            View contentView= LayoutInflater.from(mContext).inflate(R.layout.window_connect,null);
          final  PopupWindow window = new PopupWindow(contentView, MATCH_PARENT,height,true);
         linearLayout = contentView.findViewById(R.id.ll);
         relativeLayout=contentView.findViewById(R.id.rl);

        if(shopBean.getQq()!=null){
            TextViewBottomLine tvQ=newText("联系QQ",true);
            tvQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(MyUtil.isQQAvailable(mContext)){
                        String url="mqqwpa://im/chat?chat_type=wpa&uin="+shopBean.getQq();
                        Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                        mContext.startActivity(intent);
                    }else {
                        Toast.makeText(mContext,"本机没有安装qq软件",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
        if(shopBean.getTb_nick()!=null){
            TextViewBottomLine tvW=newText("联系旺旺",true);
            tvW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipData clipData = ClipData.newPlainText("wangwang",shopBean.getTb_nick());
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(mContext,"旺旺昵称已经复制到粘贴板中，请到旺信客户端搜索使用",Toast.LENGTH_SHORT).show();
                }
            });

        }

        if(shopBean.getPhone()!=null){
            final String[] phones=shopBean.getPhone().split("/");
            for(int i=0;i<phones.length;i++){
                final String phone = phones[i];
                TextViewBottomLine tvP=newText(phones[i],true);
                tvP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+phone));
                        mContext.startActivity(intent);
                    }
                });
            }


        }
            TextViewBottomLine tvCancel=newText("取消",false);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                window.dismiss();
//            }
//        });




        return window;
    }
    private TextViewBottomLine newText(String text,boolean isDrawBottomLine){

        TextViewBottomLine tv=new TextViewBottomLine(mContext);
        tv.setText(text);
        tv.setBottomLine(isDrawBottomLine);
        tv.setTextColor(mContext.getResources().getColor(R.color.gray));
        tv.setGravity(Gravity.CENTER);
        linearLayout.addView(tv,LinearLayout.LayoutParams.MATCH_PARENT, MyUtil.dp2px(mContext,40));
        return tv;
    }






}
