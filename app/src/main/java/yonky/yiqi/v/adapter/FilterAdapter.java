package yonky.yiqi.v.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.bean.KVBean;
import yonky.yiqi.util.MyUtil;

/**
 * Created by Administrator on 2018/5/19.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {
    public static final String TAG=FilterAdapter.class.getSimpleName();
    public static final int TYPE_ALL=0X1000;
    public static final int TYPE_SELECTED_NONE=0X100;


    private Context mContext;
    private List<KVBean> list;
    private  int count;
    private int select=TYPE_SELECTED_NONE;

    public FilterAdapter(Context context, List<KVBean> list,int count,int select) {
        this.select = select;
        mContext = context;
        this.list = list;
        this.count = count;
    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }else{
            return count==TYPE_ALL? list.size():count;
        }

    }

    @NonNull
    @Override
    public FilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilterHolder(LayoutInflater.from(mContext).inflate(R.layout.filter_text,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterHolder holder, final int position) {
        if(position!=select){
            holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.tv.setTextColor(mContext.getResources().getColor(R.color.gray));
        }else{
            holder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
            holder.tv.setTextColor(mContext.getResources().getColor(R.color.white));
        }

        holder.tv.setText(list.get(position).getText());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setSelect(position);
                notifyDataSetChanged();
            }
        });

    }

    class FilterHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_filter)
        TextView tv;
        public FilterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public void resetSelect(){
        select=TYPE_SELECTED_NONE;
        notifyDataSetChanged();
    }
    public String getSelectString(int position){
        return MyUtil.encode(list.get(position).getValue());
    }
}
