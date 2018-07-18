package com.yandeqing;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yandeqing.stickylayout.adapter.CommonAdapter;
import com.yandeqing.stickylayout.adapter.CommonViewHolder;
import com.yandeqing.stickylayout.R;
import com.yandeqing.stickylayout.ScrollHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/12/26.
 */

public class TabFragment extends Fragment implements ScrollHelper.ScrollableContainer {

    private ListView listView;
    CommonAdapter baseAdapter;
    List<String> datas = new ArrayList();
    private SmartRefreshLayout smartRefreshLayout;

    public static TabFragment newInstance() {
        return new TabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_1, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.scrollView);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.smartrefresh_layout);
        //设置 Header 为 Material风格
        Context context = getContext();
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(context).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale));
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableAutoLoadmore(true);
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                addDatas();
                smartRefreshLayout.finishLoadmore();
            }

        });


        baseAdapter = new CommonAdapter<String>(getContext(), datas, R.layout.item) {
            @Override
            protected void convertView(View item, String s, int position) {
                TextView textView = CommonViewHolder.get(item, R.id.textview);
                textView.setText(s+(position+1));
            }
        };
        listView.setAdapter(baseAdapter);
        addDatas();
    }

    private void addDatas() {
        for (int i = 0; i < 10; i++) {
            datas.add("testview");
        }
        baseAdapter.notifyDataSetChanged();
    }

    public  void refresh() {
        if (baseAdapter != null) {
            datas.clear();
            for (int i = 0; i < 10; i++) {
                datas.add("testview");
            }
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View getScrollableView() {
        return listView;
    }
}
