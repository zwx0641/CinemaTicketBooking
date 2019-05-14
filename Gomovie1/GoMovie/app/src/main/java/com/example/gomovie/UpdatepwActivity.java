package com.example.gomovie;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.example.gomovie.RegisterActivity.backtologinClickListener;

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

public class UpdatepwActivity extends Activity {

	private static final String TAG = "UpdatepwActivity";
	private ImageButton backtomy;
	private Button updatepw;
	private EditText username;
	private EditText oldpassword;//
	private EditText newpassword;
	private EditText repeatnewpassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.updatepw);

		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(new backtomyClickListener());

		updatepw = (Button) findViewById(R.id.updatepassword_btn);
		updatepw.setOnClickListener(new updatepwClickListener());

		username = (EditText) findViewById(R.id.username);
		oldpassword = (EditText) findViewById(R.id.oldpassword);
		newpassword = (EditText) findViewById(R.id.newpassword);
		repeatnewpassword = (EditText) findViewById(R.id.repeatnewpassword);

	}

	// registerBtn.setOnClickListener(new backtologinClickListener());
	// backToLogin.setOnClickListener(new backtologinClickListener());
	// }
	//

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.UPDATEPASSWORD_SUCCESS:
				Toast.makeText(getApplicationContext(), "修改成功",
						Toast.LENGTH_SHORT).show();
				UpdatepwActivity.this.finish();
				break;
			case Const.UPDATEPUSERNAME_FAIL:
				Toast.makeText(getApplicationContext(), "用户不存在，修改失败",
						Toast.LENGTH_SHORT).show();
				break;
			case Const.UPDATEPASSWORD_FAIL:
				Toast.makeText(getApplicationContext(), "密码错误，修改失败",
						Toast.LENGTH_SHORT).show();
				break;
			case Const.UPDATEPASSWORDTWO_FAIL:
				Toast.makeText(getApplicationContext(), "新密码两次不一致，修改失败",
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		};
	};

	class updatepwClickListener implements OnClickListener {

		private String url;
		int msg;

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.updatepassword_btn:

				String username1 = username.getText().toString().trim();
				String oldpassword1 = oldpassword.getText().toString().trim();
				String newpassword1 = newpassword.getText().toString().trim();
				String repeatnewpassword1 = repeatnewpassword.getText()
						.toString().trim();

				if (oldpassword1.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入用户名",
							Toast.LENGTH_SHORT).show();
				}
				if (newpassword1.equals("") | newpassword1.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入密码",
							Toast.LENGTH_SHORT).show();
				}
				// if (!newpassword1.equals(repeatnewpassword1)) {
				// Toast.makeText(getApplicationContext(), "两次密码不一致",
				// Toast.LENGTH_SHORT).show();
				// }
				// url =
				// http://192.168.4.194:8089/gomovie1/update?username=123&oldpassword=1&newpassword=2&repeatnewpassword=1
				url = Const.HOSTIPADDRESS + Const.UPDATEPASSWORD
						+ Const.USERNAME + username1 + Const.AND
						+ Const.OLDPASSWROD + oldpassword1 + Const.AND
						+ Const.NEWPASSWORD + newpassword1 + Const.AND
						+ Const.REPEATNEWPASSWORD + repeatnewpassword1;
				Log.i(TAG, "url = " + url);

				new Thread(new Runnable() {
					public void run() {
						String response = NetUtil.get(url);
						Log.i(TAG, "response = " + response);

						if (null == response | response.equals("")) {
							handler.sendEmptyMessage(Const.UPDATEPASSWORD_FAIL);
						}

						try {
							JSONObject jsonObject = new JSONObject(response);
							String string = jsonObject.getString("msg");
							msg = Integer.parseInt(string);
							if (msg == 0) {
								handler.sendEmptyMessage(Const.UPDATEPUSERNAME_FAIL);
							}
							if (msg == 10) {
								handler.sendEmptyMessage(Const.UPDATEPASSWORD_FAIL);
							}
							if (msg == 110) {
								handler.sendEmptyMessage(Const.UPDATEPASSWORDTWO_FAIL);
							}
							if (msg == 111) {
								handler.sendEmptyMessage(Const.UPDATEPASSWORD_SUCCESS);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				).start();
			}
			if (msg == 111) {
				UpdatepwActivity.this.finish();
			}
		}
	}

	class backtomyClickListener implements OnClickListener {
		public void onClick(View v) {
			UpdatepwActivity.this.finish();
		}
	}

}
