package contents;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.westsoft.headlines.R;

public class FoundRedActivity extends Activity {

	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_found_red);

		initView();
		initEvent();
	}

	private void initView() {
		mWebView = (WebView) findViewById(R.id.found_red_webview);

	}

	private void initEvent() {
		// "http://app.lerays.com/activity/integrate/rule"
		Bundle bundle = getIntent().getBundleExtra("bundelValue");
		String value = bundle.getString("value");
		//
		WebSettings webSettings = mWebView.getSettings();
		// 自动加载图片
		webSettings.setLoadsImagesAutomatically(true);
		// 缩放至屏幕的大小
		webSettings.setLoadWithOverviewMode(true);
		// 支持js
		webSettings.setJavaScriptEnabled(true);
		mWebView.loadUrl(value);
	}
}
