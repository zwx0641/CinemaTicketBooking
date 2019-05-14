package com.example.gomovie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SeatTempleActivity extends Activity {

	public SeatTable seatTableView;
	private ImageButton backtoinfo;
	private int movieid;
	private int room;
	private static final String TAG = "SeatTempleActivity";
	private ArrayList<Seat> arrayList;

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.SEATXD:
				Toast.makeText(getApplicationContext(), "Success!",
						Toast.LENGTH_SHORT).show();
				// Intent intent = new Intent(SeatTableActivity.this,.class);
				// startActivity(intent);
				SeatTempleActivity.this.finish();
				break;

			default:
				break;
			}
		};
	};
	private Button ensureseat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.temp);
		backtoinfo = (ImageButton) findViewById(R.id.backtoinfo);
		backtoinfo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SeatTempleActivity.this.finish();

			}
		});
		arrayList = (ArrayList<Seat>)getIntent().getSerializableExtra("arraylist");
		movieid = getIntent().getIntExtra("movieid",0);
		room = getIntent().getIntExtra("room",0);

		ensureseat = (Button) findViewById(R.id.button);
		ensureseat.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ArrayList<String> selectedSeat = getIntent()
						.getStringArrayListExtra("selectedSeat");
				Log.i(TAG, "selectedSeat = " + selectedSeat);
				int size = selectedSeat.size();
				for (int i = 0; i < size; i++) {
					String seat = selectedSeat.get(i);
					Log.i(TAG, "seat = " + seat);
					final String[] splits = seat.split(",");
					// final int index = i;
					new Thread(new Runnable() {
						public void run() {
//						if (arrayList.size() != 0){
//							Seat seat2 = arrayList.get(0);
//							Log.i(TAG, "seat2 ============== " + seat2);
//							String url = Const.HOSTIPADDRESS + Const.SEATSELECT
//									+ Const.SEATX + splits[0] + Const.AND
//									+ Const.SEATY + splits[1] + Const.AND
//									+ Const.MOVIEID + seat2.getMovieid()
//									+ Const.AND + Const.ROOM + seat2.getRoom()
//									+ Const.AND + Const.USERID
//									+ seat2.getUserid();
//							Log.i(TAG, "url = " + url);
//							String response = NetUtil.get(url);
//							Log.i(TAG, "response = " + response);
//							Looper.prepare();
//							handler.sendEmptyMessage(Const.SEATXD);
//						}
//						else{
							String url = Const.HOSTIPADDRESS + Const.SEATSELECT
									+ Const.SEATX + splits[0] + Const.AND
									+ Const.SEATY + splits[1] + Const.AND
									+ Const.MOVIEID + movieid
									+ Const.AND + Const.ROOM + room
									+ Const.AND + Const.USERID
									+ 1;
							Log.i(TAG, "url = " + url);
							String response = NetUtil.get(url);
							Log.i(TAG, "response = " + response);
							Looper.prepare();
							handler.sendEmptyMessage(Const.SEATXD);
						}
//						}
					}).start();
				}
			}
		});
	}
}
