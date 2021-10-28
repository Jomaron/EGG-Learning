package com.headlines.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import asynctask.LoadDataAsynctask;

import com.westsoft.headlines.R;

public class ReMenFragment extends Fragment {

	private static final String PATH = "http://api.kuaikanmanhua.com/v1/topic_lists/mixed";
	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.remenlistview, container,
				false);
		listView = (ListView) rootView.findViewById(R.id.list_AsyncTack);
		LoadDataAsynctask loadDataAsynctask = new LoadDataAsynctask(
				getContext(), listView);
		loadDataAsynctask.execute(PATH);
		return rootView;
	}

}
