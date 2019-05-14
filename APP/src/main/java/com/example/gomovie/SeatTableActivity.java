package com.example.gomovie;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
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


public class SeatTableActivity extends Activity {

	public SeatTable seatTableView;
	private ImageButton backtoinfo;
	private int movieid;
	private int room;
	private static final String TAG = "SeatTableActivity";
	private ArrayList<Seat> arrayList;
	Ticket ticket = new Ticket();
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.SEAT_SUCESS:
				String response = (String) msg.obj;
				try {
					JSONArray jsonArray = new JSONArray(response);
					arrayList = new ArrayList<Seat>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jo = (JSONObject) jsonArray.get(i);
						String address = jo.getString("address");
						int id = jo.getInt("id");
						String isxd = jo.getString("isxd");
						int movieid = jo.getInt("movieid");
						int room = jo.getInt("room");
						int seatx = jo.getInt("seatx");
						int seaty = jo.getInt("seaty");
						int userid = jo.getInt("userid");
						Seat seat = new Seat();
						seat.setAddress(address);
						seat.setId(id);
						seat.setIsxd(isxd);
						seat.setMovieid(movieid);
						seat.setRoom(room);
						seat.setSeatx(seatx);
						seat.setSeaty(seaty);
						seat.setUserid(userid);
						arrayList.add(seat);
						System.out.println(arrayList.toString());

					}
					seatTableView = (SeatTable) findViewById(R.id.seatView);
					seatTableView.setScreenName("荧幕");// 设置屏幕名称
					seatTableView.setMaxSelected(3);// 设置最多选中
					seatTableView.setData(10, 15);
					seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

						public boolean isValidSeat(int row, int column) {
							if (column == 2) {
								return false;
							}
							return true;
						}

						public String[] isSold(int row, int column) {
							String[] strs = new String[arrayList.size()];
							for (int j = 0; j < arrayList.size(); j++) {
								int seatx = arrayList.get(j).getSeatx();
								int seaty = arrayList.get(j).getSeaty();
								strs[j] = seatx + ":" + seaty;

							}
							return strs;
						}

						public void checked(int row, int column) {
							Log.i(TAG, "check row = " + row + " column = "
									+ column);
						}

						public void unCheck(int row, int column) {

						}

						public String[] checkedSeatTxt(int row, int column) {
							return null;
						}

					});
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			case Const.SEAT_FAIL:
				Toast.makeText(getApplicationContext(), "网络连接失败",
						Toast.LENGTH_SHORT).show();
				break;
			case Const.SEATXD:
				ticket.main();
				Toast.makeText(getApplicationContext(), "订票成功",
						Toast.LENGTH_SHORT).show();
				// Intent intent = new Intent(SeatTableActivity.this,.class);
				// startActivity(intent);
				SeatTableActivity.this.finish();
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
		setContentView(R.layout.seat_table);

		Intent intent = getIntent();

		movieid = intent.getIntExtra("movieid", 0);
		room = intent.getIntExtra("room", 0);

		new Thread(new Runnable() {

			public void run() {
				String url = Const.HOSTIPADDRESS + Const.QUERY + Const.MOVIEID
						+ movieid + Const.AND + Const.ROOM + room;
				Log.i(TAG, "url = " + url);
				String response = NetUtil.get(url);
				Log.i(TAG, "response = " + response);
				Message msg = handler.obtainMessage();
				if (!response.equals("")) {
					msg.what = Const.SEAT_SUCESS;
					msg.obj = response;
				} else {
					msg.what = Const.SEAT_FAIL;
				}
				handler.sendMessage(msg);
			}
		}).start();
		backtoinfo = (ImageButton) findViewById(R.id.backtoinfo);
		backtoinfo.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SeatTableActivity.this.finish();
			}
		});
		ensureseat = (Button) findViewById(R.id.ensureseat);
		ensureseat.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ArrayList<String> selectedSeat = seatTableView
						.getSelectedSeat();
				Log.i(TAG, "selectedSeat = " + selectedSeat);
				int size = selectedSeat.size();
				for (int i = 0; i < size; i++) {
					String seat = selectedSeat.get(i);
					Log.i(TAG, "seat = " + seat);
					final String[] splits = seat.split(",");
					// final int index = i;
					new Thread(new Runnable() {
						public void run() {
							Seat seat2 = arrayList.get(0);
							// if (null == seat2) {
							// seat2 = new Seat();
							// seat2.setAddress("");
							// seat2.setId(1);
							// seat2.setIsxd("yes");
							// seat2.setMovieid(1);
							// seat2.setRoom(1);
							// seat2.setSeatx(1);
							// seat2.setSeaty(1);
							// seat2.setUserid(1);
							// }
							Log.i(TAG, "seat2 ============== " + seat2);
							String url = Const.HOSTIPADDRESS + Const.SEATSELECT
									+ Const.SEATX + splits[0] + Const.AND
									+ Const.SEATY + splits[1] + Const.AND
									+ Const.MOVIEID + seat2.getMovieid()
									+ Const.AND + Const.ROOM + seat2.getRoom()
									+ Const.AND + Const.USERID
									+ seat2.getUserid();
							Log.i(TAG, "url = " + url);
							String response = NetUtil.get(url);
							Log.i(TAG, "response = " + response);
							Looper.prepare();
							handler.sendEmptyMessage(Const.SEATXD);

						}
					}).start();
				}
			}
		});

	}
}
