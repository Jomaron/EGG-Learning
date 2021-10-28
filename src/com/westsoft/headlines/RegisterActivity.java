package com.westsoft.headlines;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends Activity implements OnClickListener {

	private Button btn_getYZM, btnLogin, btn_return, btnAgreement, btn_keyPW;
	private ImageButton btn_qqLogin, btn_weichatLogin, btn_weiboLogin;
	private boolean checked = true;
	private EditText et_user, et_password, et_yzm;
	private TimeCount time;

	// 短信验证密匙
	private static final String APPKEY = "1406ea2c27912";
	private static final String APPSECRET = "cf4e9ec2f024fd43a6d373f9d1ccc114";

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {// 操作之前的事件回调
				if (msg.arg1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码
					Log.e("===", "==beforeEvent=" + msg.obj);
				} else if (msg.arg1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// 获取验证码
					Log.e("===", "==beforeEvent=" + msg.obj);
				}
			}
			if (msg.what == 2) {// 操作之后的事件回调
				if (msg.arg2 == SMSSDK.RESULT_COMPLETE) {// 操作成功
					Log.e("===", "==操作成功=");
					if (msg.arg1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码
						Log.e("===", "==afterEvent=" + msg.obj);
						Toast.makeText(RegisterActivity.this,
								"提交验证码验证成功，请登录！", Toast.LENGTH_LONG)
								.show();

					} else if (msg.arg1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// 获取验证码
						Log.e("===", "==afterEvent=" + msg.obj);
						Toast.makeText(RegisterActivity.this, "获取验证码成功！", 0)
								.show();
					}
				} else if (msg.arg2 == SMSSDK.RESULT_ERROR) {// 操作失败
					Log.e("===", "==操作失败=afterEvent=" + msg.obj);
					try {
						Throwable throwable = (Throwable) msg.obj;
						throwable.printStackTrace();
						JSONObject object = new JSONObject(
								throwable.getMessage());
						String des = object.optString("detail");// 错误描述
						int status = object.optInt("status");// 错误代码
						if (status > 0 && !TextUtils.isEmpty(des)) {
							Toast.makeText(RegisterActivity.this, des,
									Toast.LENGTH_SHORT).show();
							return;
						}
					} catch (Exception e) {
						// do something
					}

				}
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);

		// 初始换短信验证
		SMSSDK.initSDK(RegisterActivity.this, APPKEY, APPSECRET);

		// 构造CountDownTimer对象
		time = new TimeCount(60000, 1000);

		initView();
		initEvent();
		initBtnBackground();

	}

	private void initView() {

		btn_getYZM = (Button) findViewById(R.id.btn_getYZM);
		btnLogin = (Button) findViewById(R.id.btnLogin_regist);
		btn_qqLogin = (ImageButton) findViewById(R.id.btn_qq_regist);
		btn_weichatLogin = (ImageButton) findViewById(R.id.btn_weichat_regist);
		btn_weiboLogin = (ImageButton) findViewById(R.id.btn_weibo_regist);
		btn_return = (Button) findViewById(R.id.btn_return_regist);
		btnAgreement = (Button) findViewById(R.id.btnAgreement);
		btn_keyPW = (Button) findViewById(R.id.btn_password_key_regist);
		et_password = (EditText) findViewById(R.id.et_password_regist);
		et_user = (EditText) findViewById(R.id.et_user);
		et_yzm = (EditText) findViewById(R.id.et_yzm);
	}

	private void initEvent() {

		btn_getYZM.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btn_qqLogin.setOnClickListener(this);
		btn_weichatLogin.setOnClickListener(this);
		btn_weiboLogin.setOnClickListener(this);
		btn_return.setOnClickListener(this);
		btnAgreement.setOnClickListener(this);
		btn_keyPW.setOnClickListener(this);
		btn_getYZM.setAlpha(100);
		btnLogin.setAlpha(100);

		EventHandler eh = new EventHandler() {
			@Override
			public void onRegister() {
				super.onRegister();
				Log.e("===", "==回调对象注册的时候被触发=");
			}

			// 参数：event表示操作的类型，data是从外部传入的数据
			@Override
			public void beforeEvent(int event, Object data) {
				super.beforeEvent(event, data);
				Log.e("===", "==操作执行前被触发=");
				Message msg = Message.obtain();
				msg.what = 1;
				msg.arg1 = event;
				msg.obj = data;
				handler.sendMessage(msg);
			}

			// 参数event表示操作的类型，data是从外部传入的数据,但是data是事件操作结果，其具体取值根据参数result而定。
			// result是操作结果，为SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.RESULT_ERROR表示操作失败。
			@Override
			public void afterEvent(int event, int result, Object data) {
				super.afterEvent(event, result, data);
				Log.e("===", "==操作结束时被触发=");
				Message msg = Message.obtain();
				msg.what = 2;
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}

			@Override
			public void onUnregister() {
				super.onUnregister();
				Log.e("===", "==回调注册取消=");
			}
		};
		// 注册短信回调
		SMSSDK.registerEventHandler(eh);

	}

	private void initBtnBackground() {

		// 初始化按钮颜色
		btn_keyPW.setBackgroundResource(R.drawable.status_accountkey);
	}

	public void onClick(View v) {

		String phone = et_user.getText().toString().trim();
		String yzm = et_yzm.getText().toString().trim();
		String password = et_password.getText().toString().trim();

		switch (v.getId()) {
		case R.id.btn_return_regist:
			finish();
			break;
		case R.id.btn_getYZM:// 获取验证码
			if (TextUtils.isEmpty(phone) || phone.length() != 11) {
				Toast.makeText(RegisterActivity.this, "请输入正确手机号码！", 0).show();
			} else {
				getYzm(phone);
				Toast.makeText(RegisterActivity.this, "获取验证码成功！", 0).show();
				time.start();
			}

			break;
		case R.id.btnAgreement:

			break;
		case R.id.btnLogin_regist:
			// 提交验证码验证注册
			if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(yzm)
					|| TextUtils.isEmpty(password) || password.length() < 6) {
				Toast.makeText(RegisterActivity.this, "验证码或密码错误！", 0).show();
			} else {
				commitYzm(phone, yzm);
				Toast.makeText(RegisterActivity.this, "注册成功！", 0).show();
				startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
			}
			
			break;
		case R.id.btn_qq_regist:

			break;
		case R.id.btn_weichat_regist:

			break;
		case R.id.btn_weibo_regist:

			break;
		case R.id.btn_password_key_regist:
			if (checked) {
				checked = false;
				// 隐藏密码
				et_password.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				btn_keyPW.setBackgroundResource(R.drawable.status_accountunkey);

			} else {
				// 显示密码
				checked = true;
				et_password.setInputType(InputType.TYPE_CLASS_TEXT);
				btn_keyPW.setBackgroundResource(R.drawable.status_accountkey);
			}
			break;

		default:
			break;
		}
	}

	// 按钮倒计时
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {// 计时完毕
			btn_getYZM.setText("重新验证");
			btn_getYZM.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程
			btn_getYZM.setClickable(false);// 防止重复点击
			btn_getYZM.setText(millisUntilFinished / 1000 + "秒");
		}
	}

	// 获取验证码
	private void getYzm(final String phone) {

		// 请求获取短信验证码，在监听中返回
		SMSSDK.getVerificationCode("86", phone);
	}

	// 提交验证码
	private void commitYzm(String phone, String yzm) {

		// 提交短信验证码，在监听中返回
		SMSSDK.submitVerificationCode("86", phone, yzm);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 取消指定事件的注册短信回调
		// SMSSDK.unregisterEventHandler(eh);
		// 取消所有注册短信回调
		SMSSDK.unregisterAllEventHandler();
	}
}
