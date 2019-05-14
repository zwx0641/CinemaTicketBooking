package com.example.gomovie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends Activity {

	private ListView infolistview;
	private static final String TAG = "InfoActivity";
	// private Bitmap bitmap;
	// private ImageView movieicon;
	// float scaleWidth;
	// float scaleHeight;
	// int h;
	// boolean num=false;

	private String[] showtimes = new String[] { "10:30", "12:30", "14:30",
			"16:30" };
	// private String[] showdates = new String[] { "2017-1-1", "2017-2-2",
	// "2017-3-3", "2017-4-4" };
	private String[] location;
	// = new String[] { "1号厅", "2号厅", "3号厅", "4号厅" };

	private ImageButton infoback;

	private TextView spread;

	private TextView buydate;
	private ImageButton calendar;

	private int intentID;
	private ArrayList<MovieHall> arrayList;

	private Handler handler = new Handler() {

		private Map<String, Object> listItem;

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.MOVIE_BY_ID_SHOW_SUCCESS:
				String response = (String) msg.obj;
				try {
					JSONArray jsonArray = new JSONArray(response);
					JSONObject jsonObject = (JSONObject) jsonArray.get(0);
					String moviename = jsonObject.getString("moviename");
					String movietype = jsonObject.getString("type");
					String moviedate = jsonObject.getString("dateStr");
					String moviedirector = jsonObject.getString("director");
					String movieactors = jsonObject.getString("actors");
					String moviedesc = jsonObject.getString("desc");
					showmovieactors.setText(movieactors);
					showmoviedate.setText(moviedate);
					showmoviedesc.setText(moviedesc);
					showmoviedirector.setText(moviedirector);
					showmoviename.setText(moviename);
					showmovietype.setText(movietype);
					// //////////////修改
					if (moviename.equals("速度与激情1")) {
						showmovieicon.setImageResource(R.drawable.speed1);
					} else if (moviename.equals("速度与激情2：飙风再起")) {
						showmovieicon.setImageResource(R.drawable.speed2);
					} else if (moviename.equals("速度与激情3：东京漂移")) {
						showmovieicon.setImageResource(R.drawable.speed3);
					} else if (moviename.equals("速度与激情4：赛车风云 ")) {
						showmovieicon.setImageResource(R.drawable.speed4);
					} else if (moviename.equals("速度与激情5")) {
						showmovieicon.setImageResource(R.drawable.speed5);
					} else if (moviename.equals("速度与激情6")) {
						showmovieicon.setImageResource(R.drawable.speed6);
					} else if (moviename.equals("速度与激情7")) {
						showmovieicon.setImageResource(R.drawable.speed7);
					} else if (moviename.equals("速度与激情8")) {
						showmovieicon.setImageResource(R.drawable.speed8);
					} else if (moviename.equals("哈利·波特与魔法石")) {
						showmovieicon.setImageResource(R.drawable.harry1);
					} else if (moviename.equals("哈利·波特与密室")) {
						showmovieicon.setImageResource(R.drawable.harry2);
					} else if (moviename.equals("哈利·波特与阿兹卡班的囚徒")) {
						showmovieicon.setImageResource(R.drawable.harry3);
					} else if (moviename.equals("哈利·波特与火焰杯")) {
						showmovieicon.setImageResource(R.drawable.harry4);
					} else if (moviename.equals("哈利·波特与凤凰社")) {
						showmovieicon.setImageResource(R.drawable.harry5);
					} else if (moviename.equals("哈利·波特与混血王子")) {
						showmovieicon.setImageResource(R.drawable.harry6);
					} else if (moviename.equals("哈利·波特与死亡圣器（上）")) {
						showmovieicon.setImageResource(R.drawable.harry7);
					} else if (moviename.equals("哈利·波特与死亡圣器（下）")) {
						showmovieicon.setImageResource(R.drawable.harry8);
					}

					arrayList = new ArrayList<MovieHall>();
					// location = new String[jsonArray.length()];
					location = new String[response.length()];
					for (int i = 0; i < jsonArray.length(); i++) {
						MovieHall movieHall = new MovieHall();
						JSONObject jo = (JSONObject) jsonArray.get(i);
						movieHall.setId(jo.getInt("id"));
						movieHall.setMovieid(jo.getInt("movieid"));
						movieHall.setRoom(jo.getInt("room"));
						movieHall.setShowaddress(jo.getString("showaddress"));
						arrayList.add(movieHall);
						location[i] = movieHall.getRoom() + "号厅";

					}
					for (int i = 0; i < showtimes.length; i++) {
						listItem = new HashMap<String, Object>();
						listItem.put("showtimes", showtimes[i]);
						// listItem.put("showdates", showdates[i]);
						listItem.put("location", location[i]);
						listItems.add(listItem);
					}

					SimpleAdapter simpleAdapter = new SimpleAdapter(
							InfoActivity.this, listItems, R.layout.infoitem,
							new String[] { "showtimes", "location" },
							new int[] { R.id.showtimes, R.id.location });

					infolistview.setAdapter(simpleAdapter);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			case Const.MOVIE_BY_ID_SHOW_FAIL:
				Toast.makeText(getApplicationContext(), "网络连接有误！",
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		};
	};
	private ImageView showmovieicon;
	private TextView showmovieactors;
	private TextView showmoviedate;
	private TextView showmoviedesc;
	private TextView showmoviedirector;
	private TextView showmoviename;
	private TextView showmovietype;
	private List<Map<String, Object>> listItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.info);
		showmovieicon = (ImageView) findViewById(R.id.movieicon);
		showmovieactors = (TextView) findViewById(R.id.showmovieactors);
		showmoviedate = (TextView) findViewById(R.id.showmoviedate);
		showmoviedesc = (TextView) findViewById(R.id.showmoviedesc);
		showmoviedirector = (TextView) findViewById(R.id.showmoviedirector);
		showmoviename = (TextView) findViewById(R.id.showmoviename);
		showmovietype = (TextView) findViewById(R.id.showmovietype);

		infoback = (ImageButton) findViewById(R.id.infoback);

		infoback.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				InfoActivity.this.finish();
			}
		});

		Intent intent = getIntent();
		intentID = intent.getIntExtra("id", 0);
		new Thread(new Runnable() {

			public void run() {
				String url = Const.HOSTIPADDRESS + Const.MOVIEBYIDSHOW
						+ intentID;
				Log.i(TAG, "url = " + url);
				String response = NetUtil.get(url);
				Log.i(TAG, "response = " + response);
				if (response.equals("")) {
					handler.sendEmptyMessage(Const.MOVIE_BY_ID_SHOW_FAIL);
				}
				Message msg = handler.obtainMessage();
				msg.what = Const.MOVIE_BY_ID_SHOW_SUCCESS;
				msg.obj = response;
				handler.sendMessage(msg);
			}
		}).start();

		spread = (TextView) findViewById(R.id.spread);
		spread.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		spread.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (spread.getText().equals("展开")) {
					spread.setText("收起");
					showmoviedesc.setMaxLines(10);

				} else {
					spread.setText("展开");
					showmoviedesc.setMaxLines(2);
				}
			}
		});

		infolistview = (ListView) findViewById(R.id.infolistview);

		listItems = new ArrayList<Map<String, Object>>();

		infolistview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// MovieHall movieHall = arrayList.get(position);
				MovieHall movieHall = arrayList.get(position);

				Intent intent = new Intent(InfoActivity.this,
						SeatTableActivity.class);
				intent.putExtra("movieid", movieHall.getMovieid());
				intent.putExtra("room", movieHall.getRoom());
				startActivityForResult(intent, 3);
			}
		});

		buydate = (TextView) findViewById(R.id.buydate);
		calendar = (ImageButton) findViewById(R.id.calendar);

		calendar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 日期选择对话框
				Calendar c = Calendar.getInstance();
				DatePickerDialog dialog = new DatePickerDialog(
						InfoActivity.this, new OnDateSetListener() {

							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								Toast.makeText(
										InfoActivity.this,
										year + "年" + (monthOfYear + 1) + "月"
												+ dayOfMonth + "日",
										Toast.LENGTH_SHORT).show();
								buydate.setText(year + "年" + (monthOfYear + 1)
										+ "月" + dayOfMonth + "日");
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH));
				// 显示对话框
				dialog.show();
			}
		});

	}

}