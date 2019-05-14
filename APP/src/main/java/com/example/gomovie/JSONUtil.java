package com.example.gomovie;

import com.google.gson.Gson;

public class JSONUtil {
	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}
}
