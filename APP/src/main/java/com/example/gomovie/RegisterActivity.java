package com.example.gomovie;

import org.json.JSONObject;

import android.app.Activity;
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

public class RegisterActivity extends Activity {
	private static final String TAG = "RegisterActivity";
	private Button registerBtn;
	private ImageButton backToLogin;
	private EditText number;
	private EditText password;
	private EditText repeatpassword;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.REGIST_SUCCESS:
				Toast.makeText(getApplicationContext(), "注册成功",
						Toast.LENGTH_SHORT).show();
				RegisterActivity.this.finish();
				break;
			case Const.REGIST_FAIL:
				Toast.makeText(getApplicationContext(), "注册失败",
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);

		registerBtn = (Button) findViewById(R.id.register_btn);
		backToLogin = (ImageButton) findViewById(R.id.backtologin);
		number = (EditText) findViewById(R.id.number);
		password = (EditText) findViewById(R.id.password);
		repeatpassword = (EditText) findViewById(R.id.repeatpassword);
		registerBtn.setOnClickListener(new backtologinClickListener());
		backToLogin.setOnClickListener(new backtologinClickListener());
	}

	class backtologinClickListener implements OnClickListener {

		private String url;

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.register_btn:
				String numberText = number.getText().toString().trim();
				String passwordText = password.getText().toString().trim();
				String repeatpasswordText = repeatpassword.getText().toString()
						.trim();
				if (numberText.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入用户名",
							Toast.LENGTH_SHORT).show();
				}
				if (passwordText.equals("") | repeatpasswordText.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入密码",
							Toast.LENGTH_SHORT).show();
				}
				if (!passwordText.equals(repeatpasswordText)) {
					Toast.makeText(getApplicationContext(), "两次密码不一致",
							Toast.LENGTH_SHORT).show();
				}
				url = Const.HOSTIPADDRESS + Const.REGIST + Const.USERNAME
						+ numberText + Const.AND + Const.PASSWORD
						+ passwordText + Const.AND + Const.PASSWORDTWO
						+ repeatpasswordText;
				Log.i(TAG, "url = " + url);
				new Thread(new Runnable() {
					public void run() {
						String response = NetUtil.get(url);
						Log.i(TAG, "response = " + response);
						if (null == response | response.equals("")) {
							handler.sendEmptyMessage(Const.REGIST_FAIL);
						}

						try {
							JSONObject jsonObject = new JSONObject(response);
							String string = jsonObject.getString("msg");
							int msg = Integer.parseInt(string);
							if (msg == 0) {
								handler.sendEmptyMessage(Const.REGIST_FAIL);
							}
							if (msg == 1) {
								handler.sendEmptyMessage(Const.REGIST_SUCCESS);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
	}
}
