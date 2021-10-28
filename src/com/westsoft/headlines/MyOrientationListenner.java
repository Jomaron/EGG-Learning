package com.westsoft.headlines;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyOrientationListenner implements SensorEventListener {

	private SensorManager mSensorManager;
	private Context mContext;
	private Sensor mSensor;

	private float lastX;

	public MyOrientationListenner(Context mContext) {

		this.mContext = mContext;
	}

	@SuppressWarnings("deprecation")
	public void satrt() {

		mSensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager != null) {

			// 获得方向传感器
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		}
		if (mSensor != null) {
			mSensorManager.registerListener(this, mSensor,
					SensorManager.SENSOR_DELAY_UI);
		}
	}

	public void stop() {

		// 停止定位
		mSensorManager.unregisterListener(this);
	}

	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {

			float x = event.values[SensorManager.DATA_X];

			if (Math.abs(x - lastX) > 1.0) {

				if (mOnOrientationListener != null) {
					mOnOrientationListener.OnOrientationChanged(x);
				}
			}
		}
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	private OnOrientationListener mOnOrientationListener;

	// 定义接口
	public interface OnOrientationListener {

		void OnOrientationChanged(float x);
	}

	public void setmOnOrientationListener(
			OnOrientationListener mOnOrientationListener) {
		this.mOnOrientationListener = mOnOrientationListener;
	}

}
