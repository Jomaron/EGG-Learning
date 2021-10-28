package com.headlines.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.westsoft.headlines.FoundSearchActivity;
import com.westsoft.headlines.MapActivity;
import com.westsoft.headlines.NoteBookActivity;
import com.westsoft.headlines.R;

import contents.FoundContentFragment;
import contents.FoundContentFragment2;

/**
 * 发现
 */
public class Found_Fragment extends Fragment implements OnClickListener,
		OnPageChangeListener, OnItemClickListener {

	private ImageView iv_found;
	private View rootView;
	private Button btn1, btn2;
	private FragmentManager fm;
	private FragmentTransaction traction;
	private GridView gridViewImage;


	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.found_fragment, container, false);

		initView();
		initEvent();
		defaultFragment();
		setGridViewData();

		return rootView;
	}

	private void initView() {

		btn1 = (Button) rootView.findViewById(R.id.found_points_mall_btn);
		btn2 = (Button) rootView.findViewById(R.id.found_special_events_btn);
		iv_found = (ImageView) rootView.findViewById(R.id.found_search_img);
		gridViewImage = (GridView) rootView
				.findViewById(R.id.prize_exchange_gv);
	}

	private void initEvent() {

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		iv_found.setOnClickListener(this);
		gridViewImage.setOnItemClickListener(this);
	}

	// 默认加载的fragment
	private void defaultFragment() {
		fm = getFragmentManager();
		traction = fm.beginTransaction();
		traction.replace(R.id.fragment_replase, new FoundContentFragment(),
				"fragment1");
		traction.commit();
	}

	private void setGridViewData() {
		gridViewImage.setAdapter(new adapter.GridViewAdapter(getContext()));
//		gridViewImage.setOnItemClickListener(new adapter.GridViewAdapter(
//				getContext()));

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.found_points_mall_btn:

			btn1.setBackgroundResource(R.drawable.common_version_bg);
			btn2.setBackgroundResource(R.drawable.common_shape_black);
			fm = getFragmentManager();
			traction = fm.beginTransaction();
			traction.replace(R.id.fragment_replase, new FoundContentFragment(),
					"fragment1");
			traction.commit();

			break;

		case R.id.found_special_events_btn:

			btn2.setBackgroundResource(R.drawable.common_version_bg);
			btn1.setBackgroundResource(R.drawable.common_shape_black);
			fm = getFragmentManager();
			traction = fm.beginTransaction();
			traction.replace(R.id.fragment_replase,
					new FoundContentFragment2(), "fragment2");
			traction.commit();
			break;
		case R.id.found_search_img:
			startActivity(new Intent(getContext(), FoundSearchActivity.class));

			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {

		// for (int i = 0; i < mFTvTitles.length; i++) {
		// //
		// mFTvTitles[i].setEnabled(true);
		// }
		// // 设置ViewPager选中页面的标题控件失去事件焦点
		// mFTvTitles[arg0].setEnabled(false);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		GridViewData data=(GridViewData) getItem(position);
		Integer images[] = new Integer[] { R.drawable.myimage_01,
				R.drawable.myimage_10, R.drawable.myimage_11,
				R.drawable.myimage_05, R.drawable.myimage_06,
				R.drawable.myimage_07,R.drawable.myimage_05,
				R.drawable.myimage_06};

		switch (images[position]) {
		case R.drawable.myimage_01:

			startActivity(new Intent(getContext(), NoteBookActivity.class));
			onStop();
			break;
		case R.drawable.myimage_10:

			startActivity(new Intent(getContext(), MapActivity.class));
			onStop();
			break;

		default:
			break;
		}
	}


}
