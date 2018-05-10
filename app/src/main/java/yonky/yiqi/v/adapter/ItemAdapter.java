package yonky.yiqi.v.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import yonky.yiqi.R;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.iv.setImageResource(R.drawable.home_mrxk);
                break;
            case 1:
                holder.iv.setImageResource(R.drawable.home_mtsp);
                break;
            case 2:
                holder.iv.setImageResource(R.drawable.home_scbb);
                break;
            case 3:
                holder.iv.setImageResource(R.drawable.home_scdk);
                break;
            case 4:
                holder.iv.setImageResource(R.drawable.home_wtk);
                break;
                default:

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
             ImageView iv ;
            public ItemViewHolder(View view){
                 super(view);
                 iv = view.findViewById(R.id.iv);
             }
        }
}
