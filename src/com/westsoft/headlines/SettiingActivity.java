package com.westsoft.headlines;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SettiingActivity extends Activity implements OnClickListener {

	private Button btnT, btnReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settiing);
		// 启用向上返回按钮
		// getActionBar().setDisplayHomeAsUpEnabled(true);

		btnT = (Button) findViewById(R.id.buttonT);
		btnReturn = (Button) findViewById(R.id.btn_return_settings);

		btnT.setOnClickListener(this);
		btnReturn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.buttonT:

			Intent i = new Intent(this, HomeActivity.class);
			startActivity(i);
			break;
		case R.id.btn_return_settings:

			finish();
			break;

		default:
			break;
		}

		
	}
}
