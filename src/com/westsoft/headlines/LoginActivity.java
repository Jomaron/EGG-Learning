package com.westsoft.headlines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.db.ConnectionDetector;
import com.db.DBHelper;

public class LoginActivity extends Activity implements OnClickListener {

	private Button btn_findPW, btn_login, btn_return, btn_keyPW, btn_dropDown;
	private ImageButton btn_qqLogin, btn_weichatLogin, btn_weiboLogin;
	private boolean checked = false;
	private EditText et_password, et_user;
	private CheckBox mCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		initView();
		initWidget();
		initEvent();
		initBtnBackground();

	}

	private void initView() {

		btn_findPW = (Button) findViewById(R.id.btnFindPassword);
		btn_login = (Button) findViewById(R.id.btnLogin);
		btn_qqLogin = (ImageButton) findViewById(R.id.btn_qq);
		btn_weichatLogin = (ImageButton) findViewById(R.id.btn_weichat);
		btn_weiboLogin = (ImageButton) findViewById(R.id.btn_weibo);
		btn_return = (Button) findViewById(R.id.btn_return_login);
		btn_keyPW = (Button) findViewById(R.id.btn_password_key_login);
		et_password = (EditText) findViewById(R.id.et_password_login);
		et_user = (EditText) findViewById(R.id.et_user_login);
		btn_dropDown = (Button) findViewById(R.id.btn_dropdown_login);
		mCheckBox = (CheckBox) findViewById(R.id.login_cb_savepwd);
	}

	private void initEvent() {

		btn_findPW.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_qqLogin.setOnClickListener(this);
		btn_weichatLogin.setOnClickListener(this);
		btn_weiboLogin.setOnClickListener(this);
		btn_return.setOnClickListener(this);
		btn_keyPW.setOnClickListener(this);
		btn_dropDown.setOnClickListener(this);
		btn_login.setAlpha(100);

	}

	private void initBtnBackground() {

		// 初始化按钮颜色
		btn_keyPW.setBackgroundResource(R.drawable.wificonnector_verify_password_hide);
	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnFindPassword:
			startActivity(new Intent(LoginActivity.this, FindPWActivity.class));
			break;
		case R.id.btnLogin:

			try {
				if (IsConnectState()) {
					if (et_user.getText().toString().equals("")) {
						Toast toast = Toast.makeText(LoginActivity.this,
								"账号不能为空", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.BOTTOM, 0, 100);
						toast.show();
						et_user.findFocus();
						return;
					} else if (et_password.getText().toString().equals("")) {
						Toast toast = Toast.makeText(LoginActivity.this,
								"密码不能为空", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.BOTTOM, 0, 100);
						toast.show();
						et_password.findFocus();
						return;
					} else {
						GoLogin();
					}

				} else {
					Toast toast = Toast.makeText(LoginActivity.this,
							"您当前网络不可用,请检查网络状态！", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.BOTTOM, 0, 100);
					toast.show();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 记住密码
			String userName = et_user.getText().toString();
			String passWord = et_password.getText().toString();
			if (mCheckBox.isChecked()) {
				dbHelper.insertOrUpdate(userName, passWord, 1);

			} else {
				dbHelper.insertOrUpdate(userName, "", 0);
			}

			Toast.makeText(this, "记录已保存", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_dropdown_login:
			if (popView != null) {
				if (!popView.isShowing()) {
					popView.showAsDropDown(et_user);
				} else {
					popView.dismiss();
				}
			} else {
				// 如果有已经登录过账号
				if (dbHelper.queryAllUserName().length > 0) {
					initPopView(dbHelper.queryAllUserName());
					if (!popView.isShowing()) {
						popView.showAsDropDown(et_user);
					} else {
						popView.dismiss();
					}
				} else {
					Toast.makeText(this, "无记录", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.login_cb_savepwd:

			break;
		case R.id.btn_qq:

			break;
		case R.id.btn_weichat:

			break;
		case R.id.btn_weibo:

			break;
		case R.id.btn_return_login:
			finish();
			break;
		case R.id.btn_password_key_login:
			if (checked) {
				checked = false;
				// 隐藏密码
				et_password.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				btn_keyPW.setBackgroundResource(R.drawable.wificonnector_verify_password_hide);

			} else {
				// 显示密码
				checked = true;
				et_password.setInputType(InputType.TYPE_CLASS_TEXT);
				btn_keyPW.setBackgroundResource(R.drawable.wificonnector_verify_password_show);
			}
			break;

		default:
			break;
		}
	}

	// 记住账号和密码
	private DBHelper dbHelper;
	private PopupWindow popView;
	private MyAdapter adapter;

	private ProgressDialog progressDialog = null;

	private void initWidget() {

		dbHelper = new DBHelper(this);
		initLoginUserName();
	}

	private void initLoginUserName() {

		String[] userName = dbHelper.queryAllUserName();
		if (userName.length > 0) {
			String tempName = userName[userName.length - 1];
			et_user.setText(tempName);
			et_user.setSelection(tempName.length());

			String tempassword = dbHelper.queryPasswordByName(tempName);
			int checkFlag = dbHelper.queryIsSavedByName(tempName);
			if (checkFlag == 0) {
				mCheckBox.setChecked(false);

			} else if (checkFlag == 1) {
				mCheckBox.setChecked(true);
			}
			et_password.setText(tempassword);
		}
		et_user.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				et_password.setText("");
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	/*
	 * 判断网络状态
	 */
	private boolean IsConnectState() throws IOException {
		ConnectionDetector cd = new ConnectionDetector(this);
		return cd.isConnectionToInternet();
	}

	private void GoLogin() {

		progressDialog = new ProgressDialog(LoginActivity.this);
		progressDialog.setTitle("用户验证");
		progressDialog.setMessage("正在登录,请稍候...");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				dialog.dismiss();
			}
		});
		progressDialog.show();

		// 设置登录验证（待）
		new Thread() {
			public void run() {
				if (!et_password.equals("") && !et_user.equals("")) {
					startActivity(new Intent(LoginActivity.this,
							MainActivity.class));
					progressDialog.dismiss();
				}
				// else if (et_password.equals("123")) {
				// Toast.makeText(LoginActivity.this, "请验证",
				// Toast.LENGTH_SHORT).show();
				// }
			};
		}.start();
	}

	private void initPopView(String[] usernames) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < usernames.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", usernames[i]);
			map.put("drawable", R.drawable.xicon);
			list.add(map);
		}
		adapter = new MyAdapter(this, list, R.layout.dropdown_item,
				new String[] { "name", "drawable" }, new int[] { R.id.textView,
						R.id.delete });
		ListView listView = new ListView(this);
		listView.setAdapter(adapter);

		popView = new PopupWindow(listView, et_user.getWidth(),
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popView.setFocusable(true);
		popView.setOutsideTouchable(true);
		popView.setBackgroundDrawable(getResources().getDrawable(R.color.white));
		// popView.showAsDropDown(edtuser);
	}

	class MyAdapter extends SimpleAdapter {

		private List<HashMap<String, Object>> data;

		public MyAdapter(Context context, List<HashMap<String, Object>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			this.data = data;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			System.out.println(position);
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(LoginActivity.this).inflate(
						R.layout.dropdown_item, null);
				holder.btn = (ImageButton) convertView
						.findViewById(R.id.delete);
				holder.tv = (TextView) convertView.findViewById(R.id.textView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(data.get(position).get("name").toString());
			holder.tv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String[] usernames = dbHelper.queryAllUserName();
					et_user.setText(usernames[position]);
					et_password.setText(dbHelper
							.queryPasswordByName(usernames[position]));
					popView.dismiss();
				}
			});
			holder.btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String[] usernames2 = dbHelper.queryAllUserName();
					if (usernames2.length > 0) {
						dbHelper.delete(usernames2[position]);
					}
					String[] newusernames = dbHelper.queryAllUserName();
					if (newusernames.length > 0) {
						initPopView(newusernames);
						popView.showAsDropDown(et_user);
					} else {
						popView.dismiss();
						popView = null;
					}
				}
			});
			return convertView;
		}
	}

	class ViewHolder {
		private TextView tv;
		private ImageButton btn;
	}

	@Override
	protected void onStop() {
		super.onStop();
		dbHelper.cleanup();
	}

}
