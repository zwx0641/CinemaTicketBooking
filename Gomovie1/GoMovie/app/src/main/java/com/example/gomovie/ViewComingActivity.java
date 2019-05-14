package com.example.gomovie;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class ViewComingActivity extends FragmentActivity {

	private ImageButton backtomy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.viewall);
		getSupportFragmentManager()    //
				.beginTransaction()
				.add(R.id.content,new ComingFragment())
				.commit();

		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				ViewComingActivity.this.finish();
			}
		});
	}

}
