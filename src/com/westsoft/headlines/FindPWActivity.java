package com.westsoft.headlines;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class FindPWActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); // �����ޱ���
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_pw);
		
		// �������Ϸ��ذ�ť
//		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
