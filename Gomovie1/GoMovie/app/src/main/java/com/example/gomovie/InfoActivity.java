package com.example.gomovie;

import java.lang.reflect.Field;
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


//	private String[] showtimes = new String[] { "10:30", "12:30", "14:30",
//			"16:30" };
	// private String[] showdates = new String[] { "2017-1-1", "2017-2-2",
	// "2017-3-3", "2017-4-4" };
    private String[] showtimes;
	private String[] location;
	// = new String[] { "room 1", "room 2", "room 3", "room 4" };

	private ImageButton infoback;

	private TextView spread;

	private TextView buydate;
	private ImageButton calendar;
	private int price;

	private int intentID;
	private ArrayList<MovieHall> arrayList;
	private String movierank;

    public int getDrawableId(String var) {
        try {
            String variable=var;
            Field field = R.drawable.class.getField(variable);
            int DrawableId = field.getInt(new R.drawable());
            return DrawableId;
        } catch (Exception e) {
            return 0;
        }
    }

	private Handler handler = new Handler() {

		private Map<String, Object> listItem;

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.MOVIE_BY_ID_SHOW_SUCCESS:
				String response = (String) msg.obj;
				try {
					//pass the MovieHall to the infolist : show the information
					JSONArray jsonArray = new JSONArray(response);
					JSONObject jsonObject = (JSONObject) jsonArray.get(0);
					String moviename = jsonObject.getString("moviename");
					String movieicon = jsonObject.getString("movieicon");
					String movietype = jsonObject.getString("type");
					String moviedate = jsonObject.getString("dateStr");
					String moviedirector = jsonObject.getString("director");
					String movieactors = jsonObject.getString("actors");
					String moviedesc = jsonObject.getString("desc");
					String movierank = jsonObject.getString("rank");
					int price = jsonObject.getInt("price");

					showmovieactors.setText(movieactors);
					showmoviedate.setText(moviedate);
					showmoviedesc.setText(moviedesc);
					showmoviedirector.setText(moviedirector);
					showmoviename.setText(moviename);
					showmovietype.setText(movietype);
					showmovierank.setText(movierank);

					Log.i(TAG, "movieicon===================== " + movieicon);
					// //////////////淇敼
                    showmovieicon.setImageResource(getDrawableId(movieicon));
//					if (moviename.equals("速度与激情1")) {
//						showmovieicon.setImageResource(R.drawable.speed1);
//					} else if (moviename.equals("閫熷害涓庢縺鎯�2锛氶椋庡啀璧�")) {
//						showmovieicon.setImageResource(R.drawable.speed2);
//					} else if (moviename.equals("閫熷害涓庢縺鎯�3锛氫笢浜紓绉�")) {
//						showmovieicon.setImageResource(R.drawable.speed3);
//					} else if (moviename.equals("閫熷害涓庢縺鎯�4锛氳禌杞﹂浜� ")) {
//						showmovieicon.setImageResource(R.drawable.speed4);
//					} else if (moviename.equals("閫熷害涓庢縺鎯�5")) {
//						showmovieicon.setImageResource(R.drawable.speed5);
//					} else if (moviename.equals("閫熷害涓庢縺鎯�6")) {
//						showmovieicon.setImageResource(R.drawable.speed6);
//					} else if (moviename.equals("閫熷害涓庢縺鎯�7")) {
//						showmovieicon.setImageResource(R.drawable.speed7);
//					} else if (moviename.equals("閫熷害涓庢縺鎯�8")) {
//						showmovieicon.setImageResource(R.drawable.speed8);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庨瓟娉曠煶")) {
//						showmovieicon.setImageResource(R.drawable.harry1);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庡瘑瀹�")) {
//						showmovieicon.setImageResource(R.drawable.harry2);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庨樋鍏瑰崱鐝殑鍥氬緬")) {
//						showmovieicon.setImageResource(R.drawable.harry3);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庣伀鐒版澂")) {
//						showmovieicon.setImageResource(R.drawable.harry4);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庡嚖鍑扮ぞ")) {
//						showmovieicon.setImageResource(R.drawable.harry5);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庢贩琛�鐜嬪瓙")) {
//						showmovieicon.setImageResource(R.drawable.harry6);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庢浜″湥鍣紙涓婏級")) {
//						showmovieicon.setImageResource(R.drawable.harry7);
//					} else if (moviename.equals("鍝堝埄路娉㈢壒涓庢浜″湥鍣紙涓嬶級")) {
//						showmovieicon.setImageResource(R.drawable.harry8);
//					}

					arrayList = new ArrayList<MovieHall>();
					// location = new String[jsonArray.length()];
					location = new String[response.length()];
					showtimes = new String[response.length()];
					for (int i = 0; i < jsonArray.length(); i++) {
						MovieHall movieHall = new MovieHall();
						JSONObject jo = (JSONObject) jsonArray.get(i);
						movieHall.setId(jo.getInt("id"));
						movieHall.setMovieid(jo.getInt("movieid"));
						movieHall.setRoom(jo.getInt("room"));
						movieHall.setShowaddress(jo.getString("showaddress"));
						movieHall.setShowtime(jo.getString("dateStr"));//
						movieHall.setRank(jo.getString("rank"));
						movieHall.setPrice(jo.getInt("price"));
						arrayList.add(movieHall);
						location[i] = movieHall.getRoom() + "room";
						showtimes[i] = movieHall.getShowtime() ;//
					}
					for (int i = 0; i < jsonArray.length(); i++) {
						listItem = new HashMap<String, Object>();
						listItem.put("showtimes", showtimes[i]);
						// listItem.put("showdates", showdates[i]);
						listItem.put("location", location[i]);
						System.out.println(i);
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
				Toast.makeText(getApplicationContext(), "int fail",
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
	private TextView showmovierank;
	private List<Map<String, Object>> listItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 璁〢ctionBar娑堝け
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.info);
		showmovieicon = (ImageView) findViewById(R.id.movieicon);
		showmovieactors = (TextView) findViewById(R.id.showmovieactors);
		showmoviedate = (TextView) findViewById(R.id.showmoviedate);
		showmoviedesc = (TextView) findViewById(R.id.showmoviedesc);
		showmoviedirector = (TextView) findViewById(R.id.showmoviedirector);
		showmoviename = (TextView) findViewById(R.id.showmoviename);
		showmovietype = (TextView) findViewById(R.id.showmovietype);
		showmovierank = (TextView) findViewById(R.id.showmovierank);

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
				if (spread.getText().equals("View All")) {
					spread.setText("Close up");
					showmoviedesc.setMaxLines(10);

				} else {
					spread.setText("View All");
					showmoviedesc.setMaxLines(2);
				}
			}
		});

		infolistview = (ListView) findViewById(R.id.infolistview);

		listItems = new ArrayList<Map<String, Object>>();

		infolistview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Integer userid = (Integer)MySharedPreference.getId(InfoActivity.this);

				if(userid == 0){
					Toast.makeText(getApplicationContext(), "Please login first！",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(InfoActivity.this,
							LoginActivity.class);
					startActivityForResult(intent, 3);
				}else {
					MovieHall movieHall = arrayList.get(position);
					Intent intent = new Intent(InfoActivity.this,
							SeatTableActivity.class);
					intent.putExtra("showid", movieHall.getId());
					intent.putExtra("room", movieHall.getRoom());
					intent.putExtra("movierank",movieHall.getRank());
					intent.putExtra("price",movieHall.getPrice());
					startActivityForResult(intent, 3);
				}
			}
		});

		buydate = (TextView) findViewById(R.id.buydate);
		calendar = (ImageButton) findViewById(R.id.calendar);

		calendar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 鏃ユ湡閫夋嫨瀵硅瘽妗�
				Calendar c = Calendar.getInstance();
				DatePickerDialog dialog = new DatePickerDialog(
						InfoActivity.this, new OnDateSetListener() {

							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								Toast.makeText(
										InfoActivity.this,
										year + "year" + (monthOfYear + 1) + "month"
												+ dayOfMonth + "day",
										Toast.LENGTH_SHORT).show();
								buydate.setText(year + "year" + (monthOfYear + 1)
										+ "month" + dayOfMonth + "day");
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});

	}

}