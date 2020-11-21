package com.ahba1.geographylearningapp.tools;

import android.content.Context;

public class SizeUtil {
    public static int dp2px(float dpValue, Context context){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue,Context context){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
