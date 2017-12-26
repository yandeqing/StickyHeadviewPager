package com.feeling;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.feeling.stickylayout.ScrollHelper;
import com.feeling.stickylayout.R;

/**
 * Created by Andy on 2017/12/26.
 */

public class TabFragment extends Fragment implements ScrollHelper.ScrollableContainer {

    private ScrollView mScrollView;

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
        mScrollView = view.findViewById(R.id.scrollView);
    }

    @Override
    public View getScrollableView() {
        return mScrollView;
    }
}
