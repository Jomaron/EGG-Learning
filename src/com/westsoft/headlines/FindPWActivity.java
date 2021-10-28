package com.westsoft.headlines;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class FindPWActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_pw);
		
		// 启用向上返回按钮
//		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
