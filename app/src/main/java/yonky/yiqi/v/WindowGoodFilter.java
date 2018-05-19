package yonky.yiqi.v;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yonky.yiqi.R;
import yonky.yiqi.bean.KVBean;
import yonky.yiqi.listener.MyClickListener;
import yonky.yiqi.listener.MyListener;
import yonky.yiqi.v.adapter.FilterAdapter;

import static yonky.yiqi.v.adapter.FilterAdapter.TYPE_SELECTED_NONE;

/**
 * Created by Administrator on 2018/5/19.
 */

public class WindowGoodFilter {
    MyListener listener;
    private static WindowGoodFilter mWindowGoodFilter;
private List<KVBean> colorList;
private List<KVBean> clothList;
  private   FilterAdapter colorAdapter;
  private   FilterAdapter sizeAdapter;
private int selectColor=TYPE_SELECTED_NONE;
private int selectSize=TYPE_SELECTED_NONE;

    public static WindowGoodFilter getInstance(){
        if(mWindowGoodFilter==null){
            mWindowGoodFilter = new WindowGoodFilter();
        }
        return mWindowGoodFilter;
    }
    public PopupWindow newWindow(Context mContext){
        //        弹出筛选窗口
        View contentView= LayoutInflater.from(mContext).inflate(R.layout.window_filter,null);
        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        mPopupWindow.setContentView(contentView);

//        RecyclerView rvColor;


        RecyclerView rvColor=contentView.findViewById(R.id.rv_color);
        RecyclerView rvSize=contentView.findViewById(R.id.rv_size);
        Button btConcern=contentView.findViewById(R.id.bt_sure);
        Button btReset=contentView.findViewById(R.id.bt_reset);




        colorAdapter= new FilterAdapter(mContext,colorList,5,selectColor);
        sizeAdapter = new FilterAdapter(mContext,clothList,6,selectSize);


        rvColor.setLayoutManager(getLayoutManager());
        rvColor.setAdapter(colorAdapter);

        rvSize.setLayoutManager(getLayoutManager());
        rvSize.setAdapter(sizeAdapter);
        btConcern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColor=colorAdapter.getSelect();
                selectSize=sizeAdapter.getSelect();
                listener.onClick();
                mPopupWindow.dismiss();
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetSelect();

            }
        });



        return mPopupWindow;
    }

    private FlexboxLayoutManager getLayoutManager(){
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        return layoutManager;
    }

    public List<KVBean> getColorList() {
        return colorList;
    }

    public void setColorList(List<KVBean> colorList) {
        this.colorList = colorList;
    }

    public List<KVBean> getClothList() {
        return clothList;
    }

    public void setClothList(List<KVBean> clothList) {
        this.clothList = clothList;
    }

    public int getSelectColor() {
        return selectColor;
    }


    public int getSelectSize() {
        return selectSize;
    }

    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    public String getColorString(){
        return selectColor==TYPE_SELECTED_NONE? "":colorAdapter.getSelectString(getSelectColor());
    }
    public String getSize(){

        return selectSize==TYPE_SELECTED_NONE? "":clothList.get(selectSize).getValue();
    }

    public void resetSelect(){
        selectColor =TYPE_SELECTED_NONE;
        selectSize=TYPE_SELECTED_NONE;
        colorAdapter.resetSelect();
        sizeAdapter.resetSelect();
    }
}
