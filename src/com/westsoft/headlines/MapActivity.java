package com.westsoft.headlines;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.westsoft.headlines.MyOrientationListenner.OnOrientationListener;


public class MapActivity extends Activity  implements OnClickListener{

	private MapView mMapView = null;
	private BaiduMap mBaiduMap;
	private Button btn_nearby, btn_path, btn_myself;
	private Button btn_search, btn_original, btn_weiXing, btn_traffic,
			btn_myPlace;

	// ��λ���
	private LocationClient mLocationClient;
	private MyLocationListenner mLocationListenner;
	private double mLatitude;
	private double mLongtitude;
	private Context context;

	// �Զ��嶨λͼ��
	private BitmapDescriptor miconLocation;
	// ������
	private MyOrientationListenner mMyOrientationListenner;
	private float mCurrentX;

	// ģʽ�л�
	private LocationMode mLocationMode;

	// ���Ա��˵�
	private boolean areButtonsShowing;
	private RelativeLayout composerButtonsWrapper;
	private ImageView composerButtonsShowHideButtonIcon;
	private RelativeLayout composerButtonsShowHideButton;
	private ImageButton imgBtn1, imgBtn2, imgBtn3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ��������
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
		// ע��÷���Ҫ��setContentView����֮ǰʵ��
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_map);

		this.context = this;

		initView();
		initEvent();
		initLocation();
		initMenu();
	}

	private void initView() {

		mMapView = (MapView) findViewById(R.id.id_bmapView);
		mBaiduMap = mMapView.getMap();
		// ������ʾ���������500������
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
		mBaiduMap.setMapStatus(msu);

		// �ײ�������ť
		btn_nearby = (Button) findViewById(R.id.btn_nearby);
		btn_path = (Button) findViewById(R.id.btn_path);
		btn_myself = (Button) findViewById(R.id.btn_myself);

		// ģʽ�л���ť
		btn_search = (Button) findViewById(R.id.btn_search);
		btn_original = (Button) findViewById(R.id.btn_original);
		btn_weiXing = (Button) findViewById(R.id.btn_weiXing);
		btn_traffic = (Button) findViewById(R.id.btn_traffic);
		btn_myPlace = (Button) findViewById(R.id.btn_my_local);

		// �Ա��˵�
		composerButtonsWrapper = (RelativeLayout) findViewById(R.id.composer_buttons_wrapper);
		composerButtonsShowHideButton = (RelativeLayout) findViewById(R.id.composer_buttons_show_hide_button);
		composerButtonsShowHideButtonIcon = (ImageView) findViewById(R.id.composer_buttons_show_hide_button_icon);

		imgBtn1 = (ImageButton) findViewById(R.id.map_mode_common);
		imgBtn2 = (ImageButton) findViewById(R.id.map_mode_following);
		imgBtn3 = (ImageButton) findViewById(R.id.map_mode_compass);
	}

	private void initEvent() {

		btn_nearby.setOnClickListener(this);
		btn_path.setOnClickListener(this);
		btn_myself.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		btn_original.setOnClickListener(this);
		btn_weiXing.setOnClickListener(this);
		btn_traffic.setOnClickListener(this);
		btn_myPlace.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		// ��Activityʱ��������λ
		mBaiduMap.setMyLocationEnabled(true);
		if (!mLocationClient.isStarted()) {
			mLocationClient.start();
		}

		// �������򴫸���
		mMyOrientationListenner.satrt();

	}

	@Override
	protected void onStop() {
		super.onStop();

		// �˳�Anctivityʱ��ֹͣ��λ
		mBaiduMap.setMyLocationEnabled(false);
		mLocationClient.stop();

		// ֹͣ���򴫸���
		mMyOrientationListenner.stop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// ��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onDestroy();
		mLocationClient.stop();
	}

	/*
	 * �ײ���ť��������
	 */
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_nearby:
//			startActivity(new Intent(MapActivity.this, NearbyActivity.class));
			break;
		case R.id.btn_path:
//			startActivity(new Intent(MapActivity.this, PathActivity.class));
			break;
		case R.id.btn_myself:
//			startActivity(new Intent(MapActivity.this, MyselfActivity.class));
			break;
		case R.id.btn_search:
			// startActivity(new Intent(MainActivity.this,
			// MyselfActivity.class));
			break;
		case R.id.btn_original:

			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		case R.id.btn_weiXing:

			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.btn_traffic:

			if (mBaiduMap.isTrafficEnabled()) {
				mBaiduMap.setTrafficEnabled(false);
				btn_traffic.setText("��ͨoff");
			} else {
				mBaiduMap.setTrafficEnabled(true);
				btn_traffic.setText("��ͨon");
			}
			break;
		case R.id.btn_my_local:

			centerToMyLocation();
			break;

		default:
			break;
		}
	}

	// ��λ���ҵ�λ��
	private void centerToMyLocation() {
		LatLng latLng = new LatLng(mLatitude, mLongtitude);
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.animateMapStatus(msu);
	}

	// ��ʼ��λ��
	private void initLocation() {

		mLocationMode = LocationMode.NORMAL;
		mLocationClient = new LocationClient(this);
		mLocationListenner = new MyLocationListenner(mBaiduMap, context,
				mLatitude, mLatitude, miconLocation, mCurrentX, mLocationMode);
		mLocationClient.registerLocationListener(mLocationListenner);

		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll");// ��������
		option.setIsNeedAddress(true);// ����ĳһ��
		option.setOpenGps(true);// ���ص�ǰλ��
		option.setScanSpan(1000);// ÿ��1������һ��
		mLocationClient.setLocOption(option);

		// ��ʼ��ͼ��
		miconLocation = BitmapDescriptorFactory
				.fromResource(R.drawable.navi_map_gps_locked);

		// ������
		mMyOrientationListenner = new MyOrientationListenner(context);

		// �������ص�
		mMyOrientationListenner
				.setmOnOrientationListener(new OnOrientationListener() {

					public void OnOrientationChanged(float x) {

						mCurrentX = x;
					}
				});
	}

	/*
	 * ���Ա��˵�
	 */
	private void initMenu() {

		MyAnimations.initOffset(MapActivity.this);

		composerButtonsShowHideButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!areButtonsShowing) {
					MyAnimations.startAnimationsIn(composerButtonsWrapper, 300);
					composerButtonsShowHideButtonIcon
							.startAnimation(MyAnimations.getRotateAnimation(0,
									-270, 300));
				} else {
					MyAnimations
							.startAnimationsOut(composerButtonsWrapper, 300);
					composerButtonsShowHideButtonIcon
							.startAnimation(MyAnimations.getRotateAnimation(
									-270, 0, 300));
				}
				areButtonsShowing = !areButtonsShowing;
			}
		});

		for (int i = 0; i < composerButtonsWrapper.getChildCount(); i++) {
			composerButtonsWrapper.getChildAt(i).setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							switch (v.getId()) {
							case R.id.map_mode_common:

								mLocationMode = LocationMode.NORMAL;
								break;
							case R.id.map_mode_following:

								mLocationMode = LocationMode.FOLLOWING;
								break;
							case R.id.map_mode_compass:

								mLocationMode = LocationMode.COMPASS;
								Log.e("����ģʽ������", "COMPASS>>>>>>>>");
								break;

							default:
								break;
							}
						}
					});
		}

		composerButtonsShowHideButton.startAnimation(MyAnimations
				.getRotateAnimation(0, 360, 200));

	}
}
