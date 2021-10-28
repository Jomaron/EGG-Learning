package com.headlines.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.westsoft.headlines.ChatTagActivity;
import com.westsoft.headlines.FriendListActivity;
import com.westsoft.headlines.R;

/**
 * 好友
 */
public class Ranking_Fragment extends Fragment implements OnClickListener {

	private Button btnUser, btnContent;
	private View rootView;
	private FragmentManager manager;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		rootView = inflater
				.inflate(R.layout.ranking_fragment, container, false);
		
		initView();
		initEvent();
//		defaultFragment();
		
		return rootView;
	}

	private void initView() {

		btnUser = (Button) rootView.findViewById(R.id.ranking_user_btn);
		btnContent = (Button) rootView.findViewById(R.id.ranking_content_btn);
	}

	private void initEvent() {

		btnUser.setOnClickListener(this);
		btnContent.setOnClickListener(this);
	}
	
	/*
	 * 初始化默认首页Fragment
	 */
//	private void defaultFragment() {
//		manager.beginTransaction().add(R.id.ranking_fragment, new FriendListFragment())
//				.commit();
//	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ranking_user_btn:
//			btnUser.setBackgroundResource(R.drawable.user_select);
//			btnContent.setBackgroundResource(R.drawable.content_unselect);
//			FriendListFragment newFragment=new FriendListFragment();
//			manager.beginTransaction().replace(R.id.ranking_fragment, newFragment)
//			.commit();
			startActivity(new Intent(getContext(), FriendListActivity.class));

			break;
		case R.id.ranking_content_btn:
//			btnUser.setBackgroundResource(R.drawable.user_unselect);
//			btnContent.setBackgroundResource(R.drawable.content_select);

			startActivity(new Intent(getContext(), ChatTagActivity.class));
			break;

		default:
			break;
		}
	}
}
