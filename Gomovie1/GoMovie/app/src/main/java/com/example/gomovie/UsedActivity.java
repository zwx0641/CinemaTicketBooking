package com.example.gomovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class UsedActivity extends Activity implements OnClickListener {

	private static final String TAG = "UsedActivity";
	private String url = "";
	private ListView usedlistview;
	private ImageButton backtomy;

	private String[] movienames;// = new String[] { "movie1", "movie2",
								// "电影3","电影4" };
	private String[] buydates;// = new String[] { "2017-1-1",
								// "2017-2-2","2017-3-3", "2017-4-4" };
	// private String[] showtimes = new String[] { "10:30", "12:30",
	// "14:30","16:30" };
	private String[] location;// = new String[] { "1号厅", "2号厅", "3号厅", "2号厅" };

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.ORDERMOVIE_SUCCESS:
				String responseStr = (String) msg.obj;
				try {
					JSONArray jsonArray = new JSONArray(responseStr);
					location = new String[jsonArray.length()];
					movienames = new String[jsonArray.length()];
					buydates = new String[jsonArray.length()];
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject object = (JSONObject) jsonArray.get(i);
						movienames[i] = object.getString("moviename");
						location[i] ="Room" + object.getInt("room") ;
						int x = object.getInt("seatx") + 1;
						int y = object.getInt("seaty") + 1;
						buydates[i] = x + "Row" + y + "Col";
					}

					List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
					for (int i = 0; i < movienames.length; i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("movienames", movienames[i]);
						listItem.put("buydates", buydates[i]);
						// listItem.put("showtimes", showtimes[i]);
						listItem.put("location", location[i]);
						listItems.add(listItem);
					}

					SimpleAdapter simpleAdapter = new SimpleAdapter(
							UsedActivity.this, listItems, R.layout.useditem,
							new String[] { "movienames", "buydates",
									// "showtimes",
									"location" }, new int[] { R.id.moviename,
									R.id.buydate,
									// R.id.showtimes,
									R.id.location });

					usedlistview.setAdapter(simpleAdapter);
				} catch (Exception e) {
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.used);

		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(this);

		Integer id = (Integer)MySharedPreference.getId(UsedActivity.this);
		if(id == 0){
			Toast.makeText(getApplicationContext(), "Please login first！",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(UsedActivity.this,
					LoginActivity.class);
			startActivityForResult(intent, 3);
			UsedActivity.this.finish();
		}

		usedlistview = (ListView) findViewById(R.id.usedlistview);
		new Thread(new Runnable() {
			public void run() {
				url = Const.HOSTIPADDRESS + Const.ORDERMOVIE;
				String response = NetUtil.get(url);
				Message obtain = Message.obtain();
				obtain.what = Const.ORDERMOVIE_SUCCESS;
				obtain.obj = response;
				handler.sendMessage(obtain);
			}
		}).start();

		System.out.println(movienames);
		System.out.println(location);

		usedlistview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("This item was clicked");
				// Intent intent = new Intent(UsedActivity.this,
				// InfoActivity.class);
				// startActivity(intent);
			}
		});
	}

	public void onClick(View v) {
		UsedActivity.this.finish();
	}

}
