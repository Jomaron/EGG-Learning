package com.westsoft.headlines;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener{

	private Button btnRegist,btnLogin,btnVisitor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		
		initView();
		initEvent();
	}
	
	private void initView() {

		btnRegist=(Button) findViewById(R.id.btnRegist_main);
		btnLogin=(Button) findViewById(R.id.btnLogin_main);
		btnVisitor=(Button) findViewById(R.id.btnVisitor);
	}
	
	private void initEvent() {

		btnRegist.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnVisitor.setOnClickListener(this);
		//…Ë÷√∞¥≈•∞ÎÕ∏√˜
		btnRegist.setAlpha(100);
		btnLogin.setAlpha(100);

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnRegist_main:
			startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
			break;
		case R.id.btnLogin_main:
			startActivity(new Intent(HomeActivity.this, LoginActivity.class));
			break;
		case R.id.btnVisitor:
			startActivity(new Intent(HomeActivity.this, VisitorActivity.class));
			break;

		default:
			finish();
			break;
		}
	}
	
}
