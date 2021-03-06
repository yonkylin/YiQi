package yonky.yiqi.v.main.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import yonky.yiqi.R;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.listener.MyClickListener;
import yonky.yiqi.util.GlideUtil;
import yonky.yiqi.util.MyUtil;

import static yonky.yiqi.v.main.adapter.MainAdapter.TYPE_GOODS;
import static yonky.yiqi.v.main.adapter.MainAdapter.TYPE_ITEM_MRXK;
import static yonky.yiqi.v.main.adapter.MainAdapter.TYPE_ITEM_MY;
import static yonky.yiqi.v.main.adapter.MainAdapter.TYPE_ITEM_TJBB;
import static yonky.yiqi.v.main.adapter.MainAdapter.TYPE_SERVICE;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final String TAG=ItemAdapter.class.getSimpleName();

    private List<AreaBean> b2List;
    private List<AreaBean> dList;
    private Context mContext;
    private int type;
    private int[] drawables;
    private SharedPreferences preferences;

    public ItemAdapter(Context context) {
        mContext = context;
        drawables = new int[]{R.drawable.home_mrxk,R.drawable.home_mtsp,R.drawable.home_scbb,R.drawable.home_scdk,R.drawable.home_wtk};
        preferences= context.getSharedPreferences("data",0);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
        switch (type){
            case TYPE_ITEM_MY :
                layoutParams.width=MyUtil.dp2px(mContext,100);
                layoutParams.height= MyUtil.dp2px(mContext,100);
                holder.iv.setLayoutParams(layoutParams);
                holder.iv.setImageResource(drawables[position]);
                if(position==0){
                    holder.itemView.setOnClickListener(new MyClickListener(mContext,"mrxk",TYPE_SERVICE));
                }else if(position==1){
                    holder.itemView.setOnClickListener(new MyClickListener(mContext,"mtsp",TYPE_SERVICE));
                }

                break;
            case TYPE_ITEM_TJBB:
                layoutParams.width=MyUtil.dp2px(mContext,130);
                layoutParams.height= MyUtil.dp2px(mContext,220);
                holder.iv.setLayoutParams(layoutParams);

                GlideUtil.loadImage(b2List.get(position).getImg_url(),holder.iv);
                holder.itemView.setOnClickListener(new MyClickListener(mContext,b2List.get(position),TYPE_GOODS));
                break;
            case TYPE_ITEM_MRXK:
                layoutParams.width=MyUtil.dp2px(mContext,200);
                layoutParams.height= MyUtil.dp2px(mContext,250);
                holder.iv.setLayoutParams(layoutParams);

                GlideUtil.loadImage(dList.get(position).getImg_url(),holder.iv);
                holder.itemView.setOnClickListener(new MyClickListener(mContext,dList.get(position),TYPE_GOODS));
        }



    }

    @Override
    public int getItemCount() {
        switch (type){
            case TYPE_ITEM_MY:
                return drawables.length;
            case TYPE_ITEM_TJBB:
                return b2List.size();
            case TYPE_ITEM_MRXK:
                return dList.size();
                default:
                    return 0;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
             ImageView iv ;
            public ItemViewHolder(View view){
                 super(view);
                 iv = view.findViewById(R.id.iv);
                 iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
             }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<AreaBean> getB2List() {
        return b2List;
    }

    public void setB2List(List<AreaBean> b2List) {
        this.b2List = b2List;
    }

    public List<AreaBean> getDList() {
        return dList;
    }

    public void setDList(List<AreaBean> dList) {
        this.dList = dList;
    }
}
