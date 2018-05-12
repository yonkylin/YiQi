package yonky.yiqi.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

import yonky.yiqi.R;
import yonky.yiqi.base.App;

/**
 * Created by Administrator on 2018/5/10.
 */

public class GlideUtil extends ImageLoader {
    private static RequestOptions options = new RequestOptions().placeholder(R.drawable.default_icon);
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imageView);
    }

    public static void loadImage(Object path,ImageView imageView){

        Glide.with(App.getInstance())
                .load(path)
                .apply(options)
                .into(imageView);
    }


}
