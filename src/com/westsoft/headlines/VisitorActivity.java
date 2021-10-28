package com.westsoft.headlines;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class VisitorActivity extends Activity implements OnClickListener {

	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_visitor);
		
		
		btnBack=(Button) findViewById(R.id.btn_visior_back);
		btnBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_visior_back:
			finish();
			break;

		default:
			break;
		}
	}
}
