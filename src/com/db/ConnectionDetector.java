package com.db;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

	private Context mContext;

	public ConnectionDetector(Context context) {
		this.mContext = context;
	}

	/**
	 * ÅÐ¶ÏÍøÂç×´Ì¬
	 * @return
	 */
	public boolean isConnectionToInternet() {

		ConnectivityManager connectivity = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean checkNet(Context context) {

		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {

				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {

					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
