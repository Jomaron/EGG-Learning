package com.westsoft.headlines;

import home.FriendListFragment;
import home.HomeFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.headlines.fragment.Found_Fragment;
import com.headlines.fragment.Myself_Fragment;
import com.headlines.fragment.Ranking_Fragment;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private Button homebtn;
	private Button foundebtn;
	private Button rackingbtn;
	private Button myselfbtn;
	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_layout);

		initViews();
		initEvent();
		defaultFragment();

	}

	/*
	 * 初始化控件
	 */
	private void initViews() {

		homebtn = (Button) findViewById(R.id.home);
		foundebtn = (Button) findViewById(R.id.found);
		rackingbtn = (Button) findViewById(R.id.racking);
		myselfbtn = (Button) findViewById(R.id.myself);
		homebtn.setSelected(true);
		foundebtn.setSelected(false);
		rackingbtn.setSelected(false);
		myselfbtn.setSelected(false);
		manager = getSupportFragmentManager();

	}

	/*
	 * 初始化事件
	 */
	private void initEvent() {

		homebtn.setOnClickListener(this);
		foundebtn.setOnClickListener(this);
		rackingbtn.setOnClickListener(this);
		myselfbtn.setOnClickListener(this);
	}

	/*
	 * 初始化默认首页Fragment
	 */
	private void defaultFragment() {
		manager.beginTransaction().add(R.id.fragment, new HomeFragment())
				.commit();
	}

	/*
	 * 底部按键监听
	 */
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.home:
			homebtn.setSelected(true);
			foundebtn.setSelected(false);
			rackingbtn.setSelected(false);
			myselfbtn.setSelected(false);
			HomeFragment homeFragment = new HomeFragment();
			manager.beginTransaction().replace(R.id.fragment, homeFragment)
					.commit();

			break;
		case R.id.found://好友
			homebtn.setSelected(false); 
			foundebtn.setSelected(true);
			rackingbtn.setSelected(false);
			myselfbtn.setSelected(false);
			Ranking_Fragment ranking_Fragment = new Ranking_Fragment();
			manager.beginTransaction().replace(R.id.fragment, ranking_Fragment)
					.commit();
			
//			startActivity(new Intent(MainActivity.this, FriendListActivity.class));
			
			break;
		case R.id.racking://发现
			homebtn.setSelected(false);
			foundebtn.setSelected(false);  
			rackingbtn.setSelected(true);
			myselfbtn.setSelected(false); 
			Found_Fragment found_Fragment = new Found_Fragment();
			manager.beginTransaction().replace(R.id.fragment, found_Fragment)
					.commit();
			 
			found_Fragment.getActivity();//待定 
//			replaceFragment(new Found_Fragment());
			
			break;
		case R.id.myself:
			homebtn.setSelected(false);
			foundebtn.setSelected(false);
			rackingbtn.setSelected(false);
			myselfbtn.setSelected(true);
			Myself_Fragment myself_Fragment = new Myself_Fragment();
			manager.beginTransaction().replace(R.id.fragment, myself_Fragment)
					.commit();
			break;

		default:
			break;
		}
	}
	
//	private void replaceFragment(Fragment newFragment) {
//
//		FragmentTransaction trasection = getSupportFragmentManager()
//				.beginTransaction();
//		if (!newFragment.isAdded()) {
//			try {
//				getFragmentManager().beginTransaction();
//				trasection.replace(R.id.fragment, newFragment);
//				trasection.addToBackStack(null);
//				trasection.commit();
//			} catch (Exception e) {
//			}
//		} else {
//			trasection.show(newFragment);
//		}
//	}
	
	// 重写Activity方法，双击退出程序
	private long lastClickTime = 0;
	@Override
	public void onBackPressed() {
		if (lastClickTime <= 0) {
			// 自定义Toast显示信息
			Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT)
					.show();

			lastClickTime = System.currentTimeMillis();
		} else {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastClickTime < 1000) {
				finish();
			} else {
				// 自定义Toast显示信息
				Toast.makeText(MainActivity.this, "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				lastClickTime = currentTime;
			}
		}
	}
}
