package yonky.yiqi.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.v.GoodDetailActivity;
import yonky.yiqi.v.GoodsActivity;

import static yonky.yiqi.v.adapter.MainAdapter.TYPE_GOODS;
import static yonky.yiqi.v.adapter.MainAdapter.TYPE_GOOD_DETAIL;

/**
 * Created by Administrator on 2018/5/15.
 */

public class MyClickListener implements View.OnClickListener {

        AreaBean bean;
        int type;
        Context mContext;

        public MyClickListener(Context mContext, AreaBean bean, int type) {
            this.bean = bean;
            this.type = type;
            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            if(type==TYPE_GOODS){
                Intent intent = new Intent(mContext, GoodsActivity.class);
                intent.putExtra("areabean",bean);
                mContext.startActivity(intent);
            }else if(type==TYPE_GOOD_DETAIL){
                Intent intent= new Intent(mContext, GoodDetailActivity.class);
                intent.putExtra("areabean",bean);
                mContext.startActivity(intent);
            }

        }


}
