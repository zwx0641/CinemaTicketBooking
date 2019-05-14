package com.example.gomovie;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Message;

public class NetUtil {
	public static String get(String url) {
		HttpURLConnection connection = null;
		Message msg = Message.obtain();
		URL Url;
		try {
			Url = new URL(url);

			connection = (HttpURLConnection) Url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000); // 设置超时时间
			connection.setReadTimeout(5000); // 连上服务器,
			// 但是服务器迟迟不给响应
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				InputStream inputStream = connection.getInputStream();
				String jsonStr = StreamUtil.streamToString(inputStream);
				return jsonStr;

			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
