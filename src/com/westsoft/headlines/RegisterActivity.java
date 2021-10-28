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

	// ������֤�ܳ�
	private static final String APPKEY = "1406ea2c27912";
	private static final String APPSECRET = "cf4e9ec2f024fd43a6d373f9d1ccc114";

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {// ����֮ǰ���¼��ص�
				if (msg.arg1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// �ύ��֤��
					Log.e("===", "==beforeEvent=" + msg.obj);
				} else if (msg.arg1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// ��ȡ��֤��
					Log.e("===", "==beforeEvent=" + msg.obj);
				}
			}
			if (msg.what == 2) {// ����֮����¼��ص�
				if (msg.arg2 == SMSSDK.RESULT_COMPLETE) {// �����ɹ�
					Log.e("===", "==�����ɹ�=");
					if (msg.arg1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// �ύ��֤��
						Log.e("===", "==afterEvent=" + msg.obj);
						Toast.makeText(RegisterActivity.this,
								"�ύ��֤����֤�ɹ������¼��", Toast.LENGTH_LONG)
								.show();

					} else if (msg.arg1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// ��ȡ��֤��
						Log.e("===", "==afterEvent=" + msg.obj);
						Toast.makeText(RegisterActivity.this, "��ȡ��֤��ɹ���", 0)
								.show();
					}
				} else if (msg.arg2 == SMSSDK.RESULT_ERROR) {// ����ʧ��
					Log.e("===", "==����ʧ��=afterEvent=" + msg.obj);
					try {
						Throwable throwable = (Throwable) msg.obj;
						throwable.printStackTrace();
						JSONObject object = new JSONObject(
								throwable.getMessage());
						String des = object.optString("detail");// ��������
						int status = object.optInt("status");// �������
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

		// ��ʼ��������֤
		SMSSDK.initSDK(RegisterActivity.this, APPKEY, APPSECRET);

		// ����CountDownTimer����
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
				Log.e("===", "==�ص�����ע���ʱ�򱻴���=");
			}

			// ������event��ʾ���������ͣ�data�Ǵ��ⲿ���������
			@Override
			public void beforeEvent(int event, Object data) {
				super.beforeEvent(event, data);
				Log.e("===", "==����ִ��ǰ������=");
				Message msg = Message.obtain();
				msg.what = 1;
				msg.arg1 = event;
				msg.obj = data;
				handler.sendMessage(msg);
			}

			// ����event��ʾ���������ͣ�data�Ǵ��ⲿ���������,����data���¼���������������ȡֵ���ݲ���result������
			// result�ǲ��������ΪSMSSDK.RESULT_COMPLETE��ʾ�����ɹ���ΪSMSSDK.RESULT_ERROR��ʾ����ʧ�ܡ�
			@Override
			public void afterEvent(int event, int result, Object data) {
				super.afterEvent(event, result, data);
				Log.e("===", "==��������ʱ������=");
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
				Log.e("===", "==�ص�ע��ȡ��=");
			}
		};
		// ע����Żص�
		SMSSDK.registerEventHandler(eh);

	}

	private void initBtnBackground() {

		// ��ʼ����ť��ɫ
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
		case R.id.btn_getYZM:// ��ȡ��֤��
			if (TextUtils.isEmpty(phone) || phone.length() != 11) {
				Toast.makeText(RegisterActivity.this, "��������ȷ�ֻ����룡", 0).show();
			} else {
				getYzm(phone);
				Toast.makeText(RegisterActivity.this, "��ȡ��֤��ɹ���", 0).show();
				time.start();
			}

			break;
		case R.id.btnAgreement:

			break;
		case R.id.btnLogin_regist:
			// �ύ��֤����֤ע��
			if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(yzm)
					|| TextUtils.isEmpty(password) || password.length() < 6) {
				Toast.makeText(RegisterActivity.this, "��֤����������", 0).show();
			} else {
				commitYzm(phone, yzm);
				Toast.makeText(RegisterActivity.this, "ע��ɹ���", 0).show();
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
				// ��������
				et_password.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				btn_keyPW.setBackgroundResource(R.drawable.status_accountunkey);

			} else {
				// ��ʾ����
				checked = true;
				et_password.setInputType(InputType.TYPE_CLASS_TEXT);
				btn_keyPW.setBackgroundResource(R.drawable.status_accountkey);
			}
			break;

		default:
			break;
		}
	}

	// ��ť����ʱ
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {// ��ʱ���
			btn_getYZM.setText("������֤");
			btn_getYZM.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {// ��ʱ����
			btn_getYZM.setClickable(false);// ��ֹ�ظ����
			btn_getYZM.setText(millisUntilFinished / 1000 + "��");
		}
	}

	// ��ȡ��֤��
	private void getYzm(final String phone) {

		// �����ȡ������֤�룬�ڼ����з���
		SMSSDK.getVerificationCode("86", phone);
	}

	// �ύ��֤��
	private void commitYzm(String phone, String yzm) {

		// �ύ������֤�룬�ڼ����з���
		SMSSDK.submitVerificationCode("86", phone, yzm);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// ȡ��ָ���¼���ע����Żص�
		// SMSSDK.unregisterEventHandler(eh);
		// ȡ������ע����Żص�
		SMSSDK.unregisterAllEventHandler();
	}
}
