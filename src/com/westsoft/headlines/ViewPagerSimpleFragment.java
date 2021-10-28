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
		// ����ֵ��ʾ��TextView�ϣ���ʾ��Fragment
		TextView tv = new TextView(getActivity());
		tv.setText(mTitles);
		tv.setGravity(Gravity.TOP | Gravity.CENTER);
		return tv;

	}

	// ���徲̬�������������á�����fragment����
	public static ViewPagerSimpleFragment newInstance(String title) {

		// ͨ��Bundle������ֵ����ʾ��Fragment��
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_TITLE, title);
		// ����ֵ���ݸ�Fragment
		ViewPagerSimpleFragment fragment = new ViewPagerSimpleFragment();
		fragment.setArguments(bundle);
		return fragment;

	}

}
