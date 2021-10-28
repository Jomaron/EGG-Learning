package com.westsoft.headlines;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MyLocationListenner implements BDLocationListener {

	private boolean isFirstIn = true;
	private BaiduMap mBaiduMap;
	private Context context;
	private double mLatitude;
	private double mLongtitude;
	// �Զ��嶨λͼ��
	private BitmapDescriptor miconLocation;

	// ���򴫸���
	private float mCurrentX;
	// ģʽ�л�
	private LocationMode mLocationMode;

	public MyLocationListenner(BaiduMap mBaiduMap, Context context,
			double mLatitude, double mLongtitude,
			BitmapDescriptor miconLocation, float mCurrentX,
			LocationMode mLocationMode) {

		this.mBaiduMap = mBaiduMap;
		this.context = context;
		this.mLatitude = mLatitude;
		this.mLongtitude = mLongtitude;
		this.miconLocation = miconLocation;
		this.mCurrentX = mCurrentX;
		this.mLocationMode = mLocationMode;
	}

	public void onReceiveLocation(BDLocation location) {

//		if (Math.abs(location.getLatitude() - 0.0) < 0.01
//				&& Math.abs(location.getLongitude() - 0.0) < 0.01) {
//			Log.d("adafds", "��λʧ��");
//			Log.d("adafds", "---"+location.getLocType());
//			Log.d("adafds", "---"+location.getLocTypeDescription());
//		}

		MyLocationData data = new MyLocationData.Builder()//
				.direction(mCurrentX)// ���򴫸���
				.accuracy(location.getRadius())// ����
				.latitude(location.getLatitude())// ����
				.longitude(location.getLongitude())// γ��
				.build();

		mBaiduMap.setMyLocationData(data);

		// �����Զ���ͼ��
		MyLocationConfiguration configuration = new MyLocationConfiguration(
				mLocationMode, true, miconLocation);
		mBaiduMap.setMyLocationConfigeration(configuration);

		// ���¾�γ��
		mLatitude = location.getLatitude();
		mLongtitude = location.getLongitude();

		if (isFirstIn) {
			// ���þ�γ��
			LatLng latLng = new LatLng(location.getLatitude(),
					location.getLongitude());
			MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
			mBaiduMap.animateMapStatus(msu);
			isFirstIn = false;

			Toast.makeText(context, location.getAddrStr(), Toast.LENGTH_SHORT)
					.show();

		}

	}
}
