package utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


	/**
	 * Http 请求的工具类
	 * 
	 */
	public class HttpUtils2 {

		// 网络判断
		public static boolean isNetworkConn(Context context) {
			// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager == null) {
				return false;
			} else {
				// 获取NetworkInfo对象
				NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

				if (networkInfo != null && networkInfo.length > 0) {
					for (int i = 0; i < networkInfo.length; i++) {
						// 判断当前网络状态是否为连接状态
						if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
			return false;

		}

		// 网络请求
		/**
		 * 
		 * @param path
		 *            :网络请求的路径 $
		 */
		public static byte[] getData(String path) {
			try {
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.connect();
				if (connection.getResponseCode() == 200) {
					InputStream is = connection.getInputStream();
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int len = 0;
					while ((len = is.read(buf)) != -1) {
						bos.write(buf, 0, len);
						bos.flush();
					}
					byte[] byteArray = bos.toByteArray();
					// Log.d("admin", Arrays.toString(byteArray));
					// Toast.makeText(context, "123", 0).show();
					return byteArray;
				} else {
					throw new RuntimeException("请求错误");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}

	}
