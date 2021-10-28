package com.westsoft.headlines;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.headlines.view.SwipeBackFrame;


/**
 * 实现向右滑动删除Activity效果继承SwipeBackActivity
 * 
 */
public class SwipeBackActivity extends Activity {
	protected SwipeBackFrame mFrame;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFrame = (SwipeBackFrame) LayoutInflater.from(this).inflate(
				R.layout.swipe_back_custom_frame, null);
		mFrame.attachToActivity(this);
	}

	@Override 
	public void startActivity(Intent intent) { 
		super.startActivity(intent);
		overridePendingTransition(R.anim.swipe_back_slide_right_in,
				R.anim.swipe_back_slide_remain);
	}


}
