package com.cornflower.kotlin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by xiejingbao on 2019/1/14.
 */
public class BannerImageView extends ImageView {
    private int disWidth;
    public BannerImageView(Context context) {
        this(context,null);
    }

    public BannerImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(getContext());
    }

    private void init(Context context) {
        WindowManager wm1 = ((Activity)context).getWindowManager();
        disWidth = wm1.getDefaultDisplay().getWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
       int width = MeasureSpec.getSize(widthMeasureSpec);
        int height =  MeasureSpec.getSize(heightMeasureSpec);
       if(disWidth>width&&height>0){
           height =  (disWidth/width)*height;
           setMeasuredDimension(disWidth, height);
       }else if(disWidth<width&&height>0){
           height =  (width/disWidth)*height;
           setMeasuredDimension(disWidth, height);
       }
//       LogManager.i("disWidth"+disWidth);
//        LogManager.i("width"+width);
//        LogManager.i("height"+height);
//       height = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
//       int newWidth = MeasureSpec.makeMeasureSpec(disWidth,MeasureSpec.EXACTLY);


    }
}
