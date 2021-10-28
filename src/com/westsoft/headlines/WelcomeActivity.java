package com.westsoft.headlines;

import java.util.ArrayList;
import java.util.List;

import adapter.WelcomeViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class WelcomeActivity extends Activity {

	private ViewPager pager;
	private List<ImageView> list;
	private ImageView wImg;
	private ImageButton wImgBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);

		initView();
		initEvent();
	}

	private void initView() {

		pager = (ViewPager) findViewById(R.id.Welcome_viewPager);
		wImgBtn = (ImageButton) findViewById(R.id.welcome_startimgBtn);
	}

	private void initEvent() {

		list = new ArrayList<ImageView>();
		final int imgViews[] = { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3, R.drawable.guide_4 };

		for (int i : imgViews) {
			wImg = new ImageView(this);
			wImg.setImageResource(i);
			wImg.setScaleType(ScaleType.FIT_XY);
			list.add(wImg);
		}

		WelcomeViewAdapter adapter = new WelcomeViewAdapter(list);
		pager.setAdapter(adapter);
		pager.addOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int arg0) {

			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

				if (arg0 == list.size() - 1) {
					
					wImgBtn.setVisibility(View.VISIBLE);
					wImgBtn.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							startActivity(new Intent(WelcomeActivity.this,
									HomeActivity.class));
						}
					});

				}
			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}
}
