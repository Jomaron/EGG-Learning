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
	// 自定义定位图标
	private BitmapDescriptor miconLocation;

	// 方向传感器
	private float mCurrentX;
	// 模式切换
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
//			Log.d("adafds", "定位失败");
//			Log.d("adafds", "---"+location.getLocType());
//			Log.d("adafds", "---"+location.getLocTypeDescription());
//		}

		MyLocationData data = new MyLocationData.Builder()//
				.direction(mCurrentX)// 方向传感器
				.accuracy(location.getRadius())// 精度
				.latitude(location.getLatitude())// 经度
				.longitude(location.getLongitude())// 纬度
				.build();

		mBaiduMap.setMyLocationData(data);

		// 设置自定义图标
		MyLocationConfiguration configuration = new MyLocationConfiguration(
				mLocationMode, true, miconLocation);
		mBaiduMap.setMyLocationConfigeration(configuration);

		// 更新经纬度
		mLatitude = location.getLatitude();
		mLongtitude = location.getLongitude();

		if (isFirstIn) {
			// 设置经纬度
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
