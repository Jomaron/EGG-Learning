package utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class Httputils {

	private static HttpURLConnection conn;
	private static InputStream is;
	private static ByteArrayOutputStream bos;

	public static byte[] LoadData(String path, String method) {

		try {
			// 得到url对象
			URL url = new URL(path);
			// 通过url得到HttpsURLConnection对象
			conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			conn.setRequestMethod(method);
			conn.connect();
			if (conn.getResponseCode() == 200) {// 请求成功
				is = conn.getInputStream();
				// 将输入流装入字节数组
				bos = new ByteArrayOutputStream();
				// 定义一个标准的数组
				byte[] buf = new byte[1024];
				// 记录输入的大小
				int len = 0;
				// 遍历输入流
				while ((len = is.read(buf)) != -1) {
					// 写入bos
					bos.write(buf, 0, len);
					// 刷新
					bos.flush();
				}
				return bos.toByteArray();
			} else {
				Log.e("==", "网络请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
				is.close();
				conn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
