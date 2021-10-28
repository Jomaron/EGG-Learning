package com.westsoft.headlines;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerSimpleFragment extends Fragment {

	private String mTitles;
	public static final String BUNDLE_TITLE = "title";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		Bundle bundle = getArguments();
		if (bundle != null) {
			mTitles = bundle.getString(BUNDLE_TITLE);
		}
		// 将数值显示在TextView上，显示在Fragment
		TextView tv = new TextView(getActivity());
		tv.setText(mTitles);
		tv.setGravity(Gravity.TOP | Gravity.CENTER);
		return tv;

	}

	// 定义静态方法，类名调用。返回fragment对象。
	public static ViewPagerSimpleFragment newInstance(String title) {

		// 通过Bundle传递数值，显示在Fragment上
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_TITLE, title);
		// 将数值传递给Fragment
		ViewPagerSimpleFragment fragment = new ViewPagerSimpleFragment();
		fragment.setArguments(bundle);
		return fragment;

	}

}
