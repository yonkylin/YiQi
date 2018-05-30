package yonky.yiqi.window;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import yonky.yiqi.R;
import yonky.yiqi.bean.KVBean;
import yonky.yiqi.bean.RegionBean;
import yonky.yiqi.listener.MyListener;
import yonky.yiqi.v.main.adapter.FilterAdapter;

import static yonky.yiqi.v.main.adapter.FilterAdapter.TYPE_ALL;
import static yonky.yiqi.v.main.adapter.FilterAdapter.TYPE_KV;
import static yonky.yiqi.v.main.adapter.FilterAdapter.TYPE_SELECTED_NONE;

/**
 * Created by Administrator on 2018/5/19.
 */

public class WindowGoodFilter {
    MyListener listener;
    private static WindowGoodFilter mWindowGoodFilter;
private List<KVBean> colorList;
private List<KVBean> clothList;
private List<RegionBean.ItemsBean> regionList;
private List<RegionBean.ItemsBean> floorList;
  private   FilterAdapter colorAdapter;
  private   FilterAdapter sizeAdapter;
//  private   FilterAdapter regionAdapter;
//  private   FilterAdapter floorAdapter;
private int selectColor=TYPE_SELECTED_NONE;
private int selectSize=TYPE_SELECTED_NONE;
private int selectRegion=TYPE_SELECTED_NONE;
private int selectFloor=TYPE_SELECTED_NONE;
private int colorExpandSelected,sizeExpandSelected,regionExpandSelected,floorExpandSelected;
private TextView tvPrice1,tvPrice2;
private String price1,price2;

    public static WindowGoodFilter getInstance(){
        if(mWindowGoodFilter==null){
            mWindowGoodFilter = new WindowGoodFilter();
        }
        return mWindowGoodFilter;
    }
    public PopupWindow newWindow(Context mContext){
        colorExpandSelected=0;
        sizeExpandSelected=0;

        //        弹出筛选窗口
        View contentView= LayoutInflater.from(mContext).inflate(R.layout.window_filter,null);
        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        mPopupWindow.setContentView(contentView);

//        RecyclerView rvColor;
        tvPrice1=contentView.findViewById(R.id.price_from);
        tvPrice2=contentView.findViewById(R.id.price_to);

        tvPrice1.setText(price1);
        tvPrice2.setText(price2);


        RecyclerView rvColor=contentView.findViewById(R.id.rv_color);
        RecyclerView rvSize=contentView.findViewById(R.id.rv_size);
//        RecyclerView rvRegion=contentView.findViewById(R.id.rv_region);

        Button btConcern=contentView.findViewById(R.id.bt_sure);
        Button btReset=contentView.findViewById(R.id.bt_reset);

        final ImageView colorExpand=contentView.findViewById(R.id.iv_color);
        ImageView sizeExpand=contentView.findViewById(R.id.iv_size);
//        ImageView regionExpand=contentView.findViewById(R.id.iv_region);


        colorAdapter= new FilterAdapter(mContext,TYPE_KV,5,selectColor);
        sizeAdapter = new FilterAdapter(mContext,TYPE_KV,6,selectSize);
//        regionAdapter=new FilterAdapter(mContext,TYPE_REGION,4,selectRegion);

        colorAdapter.setList(colorList);
        sizeAdapter.setList(clothList);
//        regionAdapter.setRegionList(regionList);

        rvColor.setLayoutManager(getLayoutManager());
        rvColor.setAdapter(colorAdapter);

        rvSize.setLayoutManager(getLayoutManager());
        rvSize.setAdapter(sizeAdapter);

//        rvRegion.setLayoutManager(getLayoutManager());
//        rvRegion.setAdapter(regionAdapter);

        btConcern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColor=colorAdapter.getSelect();
                selectSize=sizeAdapter.getSelect();
                selectRegion=sizeAdapter.getSelect();
                price1=String.valueOf(tvPrice1.getText());
                price2=String.valueOf(tvPrice2.getText());
                mPopupWindow.dismiss();
                listener.onClick();
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetSelect();

            }
        });

        colorExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorExpandSelected++;
                if(colorExpandSelected%2==1){
                    colorAdapter.setCount(TYPE_ALL);
                }else{
                    colorAdapter.setCount(5);
                }
                colorAdapter.notifyDataSetChanged();
            }
        });

        sizeExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeExpandSelected++;
                if(sizeExpandSelected%2==1){
                    sizeAdapter.setCount(TYPE_ALL);
                }else{
                    sizeAdapter.setCount(6);
                }
                sizeAdapter.notifyDataSetChanged();
            }
        });

//        regionExpand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                regionExpandSelected++;
//                if(regionExpandSelected%2==1){
//                    regionAdapter.setCount(TYPE_ALL);
//                }else{
//                    regionAdapter.setCount(4);
//                }
//                Log.e("REGION","REGION COUNT IS"+regionAdapter.getItemCount());
//                regionAdapter.notifyDataSetChanged();
//            }
//        });
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

    public List<RegionBean.ItemsBean> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<RegionBean.ItemsBean> regionList) {
        this.regionList = regionList;
    }

    public List<RegionBean.ItemsBean> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<RegionBean.ItemsBean> floorList) {
        this.floorList = floorList;
    }

    public int getSelectRegion() {
        return selectRegion;
    }

    public int getSelectFloor() {
        return selectFloor;
    }

    public String getColor(){
        return selectColor==TYPE_SELECTED_NONE? "":colorList.get(selectColor).getValue();
    }
    public String getSize(){

        return selectSize==TYPE_SELECTED_NONE? "":clothList.get(selectSize).getValue();
    }
    public String getPrice1(){
        return price1.equals("")? "0.0":tvPrice1.getText()+".0";
    }
    public String getPrice2(){
        return price2.equals("")? "0.0":tvPrice2.getText()+".0";
    }

    public void resetSelect(){
        selectColor =TYPE_SELECTED_NONE;
        selectSize=TYPE_SELECTED_NONE;
        colorAdapter.resetSelect();
        sizeAdapter.resetSelect();
        tvPrice1.setText("");
        tvPrice2.setText("");
    }


}
