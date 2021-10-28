package com.westsoft.headlines;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.headlines.view.KeywordsFlowFrame;
import com.headlines.view.SearchEditText;

public class FoundSearchActivity extends SwipeBackActivity implements
		OnClickListener {

	private SearchEditText mSearchEditText;

	private ImageView mImgSearchReturn;

	// 动画数据
	public static final String[] keywords = { "热门推荐", "美食",
			"生活与创意", "经文", "诗歌", "书籍", "故事",
			"见证", "互动", "运动", "生活" };

	private KeywordsFlowFrame keywordsFlowFrame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_found_search);
		// 隐藏ActionBar
		// getActionBar().hide();
		// 设置全屏
		// requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
//		 getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT,
//		 WindowManager.LayoutParams.FILL_PARENT); // 设置全屏

		initView();
		initEvent();
	}

	private void initView() {
		mSearchEditText = (SearchEditText) findViewById(R.id.search_edit);
		mImgSearchReturn = (ImageView) findViewById(R.id.search_return_img);

	}

	private void initEvent() {
		mImgSearchReturn.setOnClickListener(this);
		//
		keywordsFlowFrame = (KeywordsFlowFrame) findViewById(R.id.frame);
		keywordsFlowFrame.setDuration(800l);
		keywordsFlowFrame.setOnItemClickListener(this);
		// 添加
		feedKeywordsFlow(keywordsFlowFrame, keywords);
		keywordsFlowFrame.go2Show(KeywordsFlowFrame.ANIMATION_IN);
	}

	public void onClick(View v) {

		if (v == mImgSearchReturn) {
			finish();
		}
		// else if (v instanceof TextView) {
		// String keyword = ((TextView) v).getText().toString();
		// Intent intent = new Intent();
		// intent.setAction(Intent.ACTION_VIEW);
		// intent.addCategory(Intent.CATEGORY_DEFAULT);
		// intent.setData(Uri.parse("http://www.google.com.hk/#q=" + keyword));
		// startActivity(intent);
		// Log.e("Search", keyword);
		//
		// }

	}

	private static void feedKeywordsFlow(KeywordsFlowFrame keywordsFlowFrame,
			String[] arr) {
		Random random = new Random();
		for (int i = 0; i < KeywordsFlowFrame.MAX; i++) {
			int ran = random.nextInt(arr.length);
			String tmp = arr[ran];
			keywordsFlowFrame.feedKeyword(tmp);
		}
	}
}
