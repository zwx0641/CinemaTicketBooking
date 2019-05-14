package com.example.gomovie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		initView();

	}

	private void initView() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
					Intent intent = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(intent);
					SplashActivity.this.finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		;

	}
}
