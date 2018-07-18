package com.yandeqing.stickylayout;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

/**
 * 求租出租适配器
 */
public abstract class BaseNavigatorAdapter extends CommonNavigatorAdapter {

    private static float sDensity;
    private List<String> titles;

    public BaseNavigatorAdapter(List<String> titles) {
        this.titles = titles;
    }



    @Override
    public int getCount() {
        return titles.size();
    }

    public abstract void onClickItem(int position);

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        simplePagerTitleView.setText(titles.get(index));
        int color = context.getResources().getColor(R.color.colorPrimary);
        int colorprimary = context.getResources().getColor(R.color.colorPrimaryDark);
        simplePagerTitleView.setNormalColor(color);
        simplePagerTitleView.setSelectedColor(colorprimary);
        simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem(index);
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        int colorprimary = context.getResources().getColor(R.color.colorPrimaryDark);
        indicator.setColors(colorprimary);
        indicator.setLineWidth(dp2px(context, 17));
        indicator.setRoundRadius(dp2px(context, 6));
        return indicator;
    }

    /**
     * 单位转换: dp -> px
     */
    public static int dp2px(Context context, int dp) {
        return (int) (getDensity(context) * dp + 0.5);
    }
    public static float getDensity(Context context) {
        if (sDensity == 0f) {
            sDensity = getDisplayMetrics(context).density;
        }
        return sDensity;
    }
    /**
     * 获取 DisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

}