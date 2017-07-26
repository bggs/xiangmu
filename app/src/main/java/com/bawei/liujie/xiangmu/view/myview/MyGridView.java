package com.bawei.liujie.xiangmu.view.myview;

import android.widget.GridView;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/20
 */

public class MyGridView extends GridView {
//    public MyGridView(Context context) {
//        super(context);
//    }
//
//    public MyGridView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }
    public MyGridView(android.content.Context context,
                      android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);


    }


}
