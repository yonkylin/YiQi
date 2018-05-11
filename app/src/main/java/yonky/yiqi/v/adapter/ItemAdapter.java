package yonky.yiqi.v.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import yonky.yiqi.R;
import yonky.yiqi.bean.AreaBean;
import yonky.yiqi.util.MyUtil;

import static yonky.yiqi.v.adapter.MainAdapter.TYPE_ITEM_MY;
import static yonky.yiqi.v.adapter.MainAdapter.TYPE_ITEM_TJBB;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final String TAG=ItemAdapter.class.getSimpleName();

    private List<AreaBean> b2List;
    private Context mContext;
    private int type;
    private int[] drawables;

    public ItemAdapter(Context context) {
        mContext = context;
        drawables = new int[]{R.drawable.home_mrxk,R.drawable.home_mtsp,R.drawable.home_scbb,R.drawable.home_scdk,R.drawable.home_wtk};
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
                break;
            case TYPE_ITEM_TJBB:
                layoutParams.width=MyUtil.dp2px(mContext,130);
                layoutParams.height= MyUtil.dp2px(mContext,220);
                holder.iv.setLayoutParams(layoutParams);

                Glide.with(mContext).load(b2List.get(position).getImg_url()).into(holder.iv);
        }



    }

    @Override
    public int getItemCount() {
        switch (type){
            case TYPE_ITEM_MY:
                return drawables.length;
            case TYPE_ITEM_TJBB:
                return b2List.size();
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
}
