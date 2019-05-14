package com.example.gomovie;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AboutActivity extends Activity {

	private ImageButton backtomy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);

		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				AboutActivity.this.finish();
			}
		});
	}

}
