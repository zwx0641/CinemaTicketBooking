package com.example.gomovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bumptech.glide.Glide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IntroductionActivity extends Activity {
	private static final String TAG = "IntroductionActivity";
	private ImageView movieimage;
	private TextView moviename;
	private TextView movietype;
	private TextView moviedatestr;
	private TextView moviedirector;
	private TextView movieactor;
	private TextView moviedesc;
	private ImageButton introback;
	private int id;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.MOVIE_BY_ID_SUCCESS:
				String response = (String) msg.obj;
				try {
					JSONArray jsonArray = new JSONArray(response);
					JSONObject jsonObject = (JSONObject) jsonArray.get(0);
					// JSONObject jsonObject = new JSONObject(response);
					Movie movie = new Movie();
					movie.setActors(jsonObject.getString("actors"));
					movie.setDatestr(jsonObject.getString("datestr"));
					movie.setDesc(jsonObject.getString("desc"));
					movie.setDirector(jsonObject.getString("director"));
					movie.setMoviename(jsonObject.getString("moviename"));
					movie.setMovieicon(jsonObject.getString("movieicon"));
					movie.setType(jsonObject.getString("type"));
					// //////////修改
					if ((movie.getMoviename()).equals("Journey to the West")) {
						movieimage.setImageResource(R.drawable.xiyouji);
					} else if ((movie.getMoviename()).equals("Where are the Magic Animals 2")) {
						movieimage.setImageResource(R.drawable.shenqidongwu);
					} else if ((movie.getMoviename()).equals("Catch the devil 3")) {
						movieimage.setImageResource(R.drawable.zhuoyaoji);
					} else if ((movie.getMoviename()).equals("Four Heavenly Kings of Di Renjie")) {
						movieimage.setImageResource(R.drawable.direnjie);
					} else if ((movie.getMoviename()).equals("Mobile Maze 3: The Antidote to Death")) {
						movieimage.setImageResource(R.drawable.yidongmigong);
					} else if ((movie.getMoviename()).equals("Flash man")) {
						movieimage.setImageResource(R.drawable.shandianxia);
					}

					movieactor.setText(movie.getActors());
					moviedatestr.setText(movie.getDatestr());
					moviedesc.setText(movie.getDesc());
					moviedirector.setText(movie.getDirector());
					moviename.setText(movie.getMoviename());
					movietype.setText(movie.getType());

				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			case Const.MOVIE_BY_ID_FAIL:
				Toast.makeText(getApplicationContext(), "Network connection exception！",
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
		setContentView(R.layout.introduction);

		movieimage = (ImageView) findViewById(R.id.noshowmovieimage);
		moviename = (TextView) findViewById(R.id.noshowmoviename);
		movietype = (TextView) findViewById(R.id.noshowmovietype);
		moviedatestr = (TextView) findViewById(R.id.noshowdatestr);
		moviedirector = (TextView) findViewById(R.id.noshowmoviedirector);
		movieactor = (TextView) findViewById(R.id.noshowmovieactor);
		moviedesc = (TextView) findViewById(R.id.noshowmoviedesc);

		Intent intent = getIntent();
		id = intent.getIntExtra("id", 0);
		new Thread(new Runnable() {

			public void run() {
				String url = Const.HOSTIPADDRESS + Const.MOVIEBYIDNOSHOW + id;
				Log.i(TAG, "url = " + url);
				String response = NetUtil.get(url);
				Log.i(TAG, "response = " + response);
				Message msg = handler.obtainMessage();
				if (response != null) {
					msg.what = Const.MOVIE_BY_ID_SUCCESS;
					msg.obj = response;
				} else {
					msg.what = Const.MOVIE_BY_ID_FAIL;
				}
				handler.sendMessage(msg);
			}
		}).start();

		introback = (ImageButton) findViewById(R.id.introback);
		introback.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				IntroductionActivity.this.finish();
			}
		});
	}

}
