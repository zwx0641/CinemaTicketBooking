package com.example.gomovie;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private static final String TAG = "LoginActivity";
	private Button register;
	private ImageButton backtomy;
	private EditText et_username;
	private EditText et_password;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.LOGIN_SUCCESS:
				Log.i(TAG, "login success");
				String username = (String) msg.obj;
				Intent intent = new Intent();
				intent.putExtra("username", username);
				setResult(2, intent);
				finish();
				break;
			case Const.LOGIN_FAIL_USERNAME:
				Toast.makeText(getApplicationContext(), "用户名不存在，登录失败！",
						Toast.LENGTH_SHORT).show();
				break;
			case Const.LOGIN_FAIL_PASSWORD:
				Toast.makeText(getApplicationContext(), "密码错误，登录失败！",
						Toast.LENGTH_SHORT).show();
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(this);
		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(new registerClickListener());
		et_username = (EditText) findViewById(R.id.number);
		et_password = (EditText) findViewById(R.id.password);
		Button loginBtn = (Button) findViewById(R.id.loginbtn);
		loginBtn.setOnClickListener(new OnClickListener() {

			private String inputUsername;
			private String inputPassword;

			public void onClick(View v) {

				inputUsername = et_username.getText().toString().trim();
				inputPassword = et_password.getText().toString().trim();
				new Thread(new Runnable() {

					public void run() {
						try {
							String url = Const.HOSTIPADDRESS + Const.LOGIN
									+ Const.USERNAME + inputUsername
									+ Const.AND + Const.PASSWORD
									+ inputPassword;
							Log.d(TAG, "url = " + url);
							HttpURLConnection connection = null;
							URL Url = new URL(url);
							connection = (HttpURLConnection) Url
									.openConnection();
							connection.setRequestMethod("GET");
							connection.setConnectTimeout(5000); // 设置超时时间
							connection.setReadTimeout(5000); // 连上服务器,
							// 但是服务器迟迟不给响应
							connection.connect();
							int responseCode = connection.getResponseCode();
							if (responseCode == 200) {
								InputStream inputStream = connection
										.getInputStream();
								String jsonStr = StreamUtil
										.streamToString(inputStream);
								try {
									JSONObject jsonObject = new JSONObject(
											jsonStr);
									String string = jsonObject.getString("msg");
									int code = Integer.parseInt(string);
									if (code == 0) {
										handler.sendEmptyMessage(Const.LOGIN_FAIL_USERNAME);// 用户名登录失败
									}
									if (code == 2) {
										handler.sendEmptyMessage(Const.LOGIN_FAIL_PASSWORD);// 密码错误登录失败
									}
									if (code == 1) {
										Message obtain = Message.obtain();
										obtain.what = Const.LOGIN_SUCCESS;
										obtain.obj = inputUsername;
										handler.sendMessage(obtain);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}).start();
				;
			}
		});
	}

	public class registerClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intentRegister = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivityForResult(intentRegister, 4);

		}

	}

	public void onClick(View v) {
		LoginActivity.this.finish();
	}

}
