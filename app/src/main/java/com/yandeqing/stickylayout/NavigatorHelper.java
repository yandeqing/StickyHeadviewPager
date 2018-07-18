//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yandeqing.stickylayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.List;

public class NavigatorHelper {
    public NavigatorHelper() {
    }

    public static void addNavigatorContent(final ViewPager fragmentViewpager, MagicIndicator tabLayout, final List<String> titles, PagerAdapter mAdapter) {
        fragmentViewpager.setAdapter(mAdapter);
        fragmentViewpager.setOffscreenPageLimit(titles.size());
        CommonNavigator commonNavigator = new CommonNavigator(tabLayout.getContext());
        commonNavigator.setAdjustMode(true); //true 即标题平分屏幕宽度的模式
        BaseNavigatorAdapter commonNavigatorAdapter = new BaseNavigatorAdapter(titles) {
            @Override
            public void onClickItem(int position) {
                fragmentViewpager.setCurrentItem(position);
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, fragmentViewpager);
        fragmentViewpager.setCurrentItem(0);
    }




}
