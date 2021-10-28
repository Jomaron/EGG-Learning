package com.headlines.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.westsoft.headlines.LoginActivity;
import com.westsoft.headlines.R;
import com.westsoft.headlines.SettiingActivity;

/**
 * 个人页面
 */
public class Myself_Fragment extends Fragment implements OnClickListener {
	
	private Button btn_login;
	private ImageView iv_phone, img_tv_login;
	private LinearLayout layout_mainPage, layout_my_message, layout_my_store,
			layout_my_wallet, layout_my_stask, layout_my_seeting;
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.myself_fragment, container, false);

		initView();
		initEvent();
		return rootView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initView() {

		btn_login = (Button) rootView.findViewById(R.id.btn_login);
		iv_phone=(ImageView) rootView.findViewById(R.id.img_login);
		img_tv_login=(ImageView) rootView.findViewById(R.id.img_tv_login);
		layout_mainPage=(LinearLayout) rootView.findViewById(R.id.my_mainPage);
		layout_my_message=(LinearLayout) rootView.findViewById(R.id.my_message);
		layout_my_store=(LinearLayout) rootView.findViewById(R.id.my_store);
		layout_my_stask=(LinearLayout) rootView.findViewById(R.id.my_task);
		layout_my_wallet=(LinearLayout) rootView.findViewById(R.id.my_wallet);
		layout_my_seeting=(LinearLayout) rootView.findViewById(R.id.my_setting);
	}

	private void initEvent() {

		btn_login.setOnClickListener(this);
		iv_phone.setOnClickListener(this);
		img_tv_login.setOnClickListener(this);
		layout_mainPage.setOnClickListener(this);
		layout_my_message.setOnClickListener(this);
		layout_my_seeting.setOnClickListener(this);
		layout_my_stask.setOnClickListener(this);
		layout_my_store.setOnClickListener(this);
		layout_my_wallet.setOnClickListener(this);
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_login:
			Intent intent=new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			break;
			
		case R.id.img_login:
//			startActivity(new Intent(getContext(), LoginActivity.class));
			break;
			
		case R.id.img_tv_login:
			startActivity(new Intent(getContext(), LoginActivity.class));
			break;
			
		case R.id.my_mainPage:
//			startActivity(new Intent(getContext(), LoginActivity.class));
			break;
			
		case R.id.my_message:
//			startActivity(new Intent(getContext(), LoginActivity.class));
			break;
			
		case R.id.my_store:
//			startActivity(new Intent(getContext(), LoginActivity.class));
			break;
			
		case R.id.my_task:
//			startActivity(new Intent(getContext(), LoginActivity.class));
			break;
			
		case R.id.my_wallet:
//			startActivity(new Intent(getContext(), LoginActivity.class));
			break;
			
		case R.id.my_setting:
			startActivity(new Intent(getContext(), SettiingActivity.class));
			break;

		default:
			break;
		}
	}
}
