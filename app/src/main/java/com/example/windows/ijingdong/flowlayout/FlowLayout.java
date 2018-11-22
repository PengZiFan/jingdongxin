package com.example.windows.ijingdong.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Windows on 2018/11/13.
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        measureChildren(0, 0);
        int totalWidth = 0;
        int totalHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (totalWidth + view.getMeasuredWidth() >= getMeasuredWidth()) {
                totalWidth = 0;
                totalHeight += view.getMeasuredHeight();
            }
            view.layout(
                    totalWidth,
                    totalHeight,
                    totalWidth + view.getMeasuredWidth(),
                    totalHeight + view.getMeasuredHeight());
            totalWidth+=view.getMeasuredWidth();
        }

    }
}
