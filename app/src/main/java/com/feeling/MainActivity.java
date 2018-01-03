package com.feeling;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.feeling.stickylayout.ScrollHelper;
import com.feeling.stickylayout.StickyLayout;
import com.feeling.stickylayout.R;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.container.RotationHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SpringView mSpringView;
    private StickyLayout mStickyLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PageAdapter mPageAdapter;

    private SparseArray<TabFragment> mFragmentArr = new SparseArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStickyLayout = findViewById(R.id.stickyLayout);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(10);
        mPageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mSpringView = findViewById(R.id.springView);
        mSpringView.setHeader(new AliHeader(this));
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mSpringView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 1500);
            }

            @Override
            public void onLoadmore() {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> titles = new ArrayList<>();
                titles.add("商品");
                titles.add("详情");
                titles.add("评价");
                mPageAdapter.setData(titles);
                mPageAdapter.notifyDataSetChanged();
                mTabLayout.setupWithViewPager(mViewPager);
                mViewPager.setCurrentItem(0);
            }
        }, 1000);
    }

    class PageAdapter extends FragmentPagerAdapter {

        final List<String> mTitles = new ArrayList<>();

        public void setData(List<String> titles) {
            mTitles.clear();
            if (titles != null) {
                mTitles.addAll(titles);
            }
        }

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            TabFragment fragment = mFragmentArr.get(position);
            if (fragment == null) {
                fragment = TabFragment.newInstance();
                mFragmentArr.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

            ScrollHelper.ScrollableContainer scrollableContainer = null;
            if (position < mTitles.size()) {
                scrollableContainer = mFragmentArr.get(position);
            }
            if (scrollableContainer != null) {
                mStickyLayout.setCurrentScrollableContainer(scrollableContainer);
            }
        }
    }
}
