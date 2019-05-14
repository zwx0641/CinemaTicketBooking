package com.example.gomovie;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ViewAllActivity extends FragmentActivity {

	private ImageButton backtomy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.viewall);
		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.content,new SoonFragment())
				.commit();

		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				ViewAllActivity.this.finish();
			}
		});
	}

}
