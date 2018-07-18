package com.yandeqing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.yandeqing.collapsed.CollapsedTextView;
import com.yandeqing.stickylayout.NavigatorHelper;
import com.yandeqing.stickylayout.R;
import com.yandeqing.stickylayout.ScrollHelper;
import com.yandeqing.stickylayout.StickyLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StickyLayout mStickyLayout;
    private MagicIndicator magicIndicator;
    private ViewPager mViewPager;
    private CollapsedTextView collapsedTextView;
    SmartRefreshLayout smartRefreshLayout;
    private SparseArray<TabFragment> mFragmentArr = new SparseArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStickyLayout = findViewById(R.id.stickyLayout);
        magicIndicator = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        smartRefreshLayout = findViewById(R.id.smartrefresh_layout);
        collapsedTextView = findViewById(R.id.company_desctv);
        collapsedTextView.setText("我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的我都去外地的 ");

        //设置 Header 为 Material风格
        int color = getResources().getColor(R.color.cardview_dark_background);
        ClassicsHeader classicsHeader = new ClassicsHeader(this);
        smartRefreshLayout.setRefreshHeader(classicsHeader);
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                int size = mFragments.size();
                for (int i = 0; i < size; i++) {
                    TabFragment tabFragment =  mFragments.get(i);
                    tabFragment.refresh();
                }
                smartRefreshLayout.finishRefresh();
            }
        });
        setUpView();
    }

    List<TabFragment> mFragments = new ArrayList<>();

    private void setUpView() {
        TabFragment myCollectionfrag = TabFragment.newInstance();
        mFragments.add(myCollectionfrag);
        TabFragment myCollectionDemandfrag = TabFragment.newInstance();
        mFragments.add(myCollectionDemandfrag);
        TabFragment myFollowFrag = TabFragment.newInstance();
        mFragments.add(myFollowFrag);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("收藏的房源");
        titles.add("收藏的求租");
        titles.add("关注的人");
        NavigatorPagerAdapter mAdapter = new NavigatorPagerAdapter(getSupportFragmentManager(), mFragments, titles);
        NavigatorHelper.addNavigatorContent(mViewPager, magicIndicator, titles, mAdapter);
    }


    public class NavigatorPagerAdapter extends FragmentPagerAdapter {
        List<? extends TabFragment> fragments;
        List<String> titles;

        public NavigatorPagerAdapter(FragmentManager fm, List<? extends TabFragment> fragments, List<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

            ScrollHelper.ScrollableContainer scrollableContainer = null;
            if (position < titles.size()) {
                scrollableContainer = fragments.get(position);
            }
            if (scrollableContainer != null) {
                mStickyLayout.setCurrentScrollableContainer(scrollableContainer);
            }
        }
    }
}
