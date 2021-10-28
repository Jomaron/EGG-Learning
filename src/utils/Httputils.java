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
			// �õ�url����
			URL url = new URL(path);
			// ͨ��url�õ�HttpsURLConnection����
			conn = (HttpURLConnection) url.openConnection();
			// ��������ʽ
			conn.setRequestMethod(method);
			conn.connect();
			if (conn.getResponseCode() == 200) {// ����ɹ�
				is = conn.getInputStream();
				// ��������װ���ֽ�����
				bos = new ByteArrayOutputStream();
				// ����һ����׼������
				byte[] buf = new byte[1024];
				// ��¼����Ĵ�С
				int len = 0;
				// ����������
				while ((len = is.read(buf)) != -1) {
					// д��bos
					bos.write(buf, 0, len);
					// ˢ��
					bos.flush();
				}
				return bos.toByteArray();
			} else {
				Log.e("==", "��������ʧ��");
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
