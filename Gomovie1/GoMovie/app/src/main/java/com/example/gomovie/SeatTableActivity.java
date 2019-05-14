package com.example.gomovie;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SeatTableActivity extends FragmentActivity {

	public SeatTable seatTableView;
	private ImageButton backtoinfo;
	private ArrayList<String> selectedSeat;
	private int showid;
	private int requestCode;
	private double discount;
	private int room;
	private int price;
	private String email;
	private Boolean canbuy;
	private String movierank;
	private int age;
	private static final String TAG = "SeatTableActivity";
	private ArrayList<Seat> arrayList;
	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			//display selected seat
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
						int showid = jo.getInt("showid");
						int room = jo.getInt("room");
						int seatx = jo.getInt("seatx");
						int seaty = jo.getInt("seaty");
						int userid = jo.getInt("userid");
						int price = jo.getInt("price");

						Seat seat = new Seat();
						seat.setAddress(address);
						seat.setId(id);
						seat.setIsxd(isxd);
						seat.setShowid(showid);
						seat.setRoom(room);
						seat.setSeatx(seatx);
						seat.setSeaty(seaty);
						seat.setUserid(userid);
						seat.setPrice(price);
						arrayList.add(seat);
						Log.i(TAG, "!!!arraylist ============== " + arrayList);
					}
					seatTableView = (SeatTable) findViewById(R.id.seatView);
					seatTableView.setScreenName("SCREEN");// 设置屏幕名称
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
				Toast.makeText(getApplicationContext(), "Network Connection Failure",
						Toast.LENGTH_SHORT).show();
				break;
			case Const.SEATXD:
				Toast.makeText(getApplicationContext(), "Booking success",
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
		Log.i(TAG, "url = EMAIL"+email);
		email = (String)MySharedPreference.getEmail(SeatTableActivity.this);
		age = (Integer) MySharedPreference.getAge(SeatTableActivity.this);
		Log.i(TAG, "url = "+age);

		showid = intent.getIntExtra("showid", 0);

		room = intent.getIntExtra("room", 0);
		movierank = intent.getStringExtra("movierank");
		price = intent.getIntExtra("price",0);

		discount = 1;
		if (age>=65){
			discount = 0.5;
		}
		if (movierank != "PG" && age<18){
			canbuy = false;
		}else{
			canbuy = true;
		}

		new Thread(new Runnable() {

			public void run() {
				String url = Const.HOSTIPADDRESS + Const.QUERY + Const.SHOWID
						+ showid + Const.AND + Const.ROOM + room;
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
            	selectedSeat = seatTableView
						.getSelectedSeat();
            	final double singleprice = price*discount;
				final double totalprice =selectedSeat.size()*price*discount;
				if (selectedSeat.size() != 0 && canbuy) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							SeatTableActivity.this);
					// 1）设置标题
					builder.setTitle("How to pay?");
					// 2)设置图标
					builder.setIcon(R.drawable.exit);
					// 3)设置内容
					builder.setMessage("$"+totalprice+"intotal.\nwhich way would you like to pay for your ticket?");
					// 4)设置按钮
					builder.setPositiveButton("By credit card",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int which) {
									// TODO Auto-generated method stub
									// 关闭应用
									// 删除数据
//									Intent intentLogin1 = new Intent(SeatTableActivity.this,
////											RadioActivity.class);
////									requestCode = 2;
////									startActivityForResult(intentLogin1, requestCode);
									Intent intent2 = new Intent(SeatTableActivity.this, RadioActivity.class);
									intent2.putExtra("selectedSeat", selectedSeat);
									intent2.putExtra("arraylist", arrayList);
									intent2.putExtra("showid", showid);
									intent2.putExtra("room", room);
									intent2.putExtra("totalprice",totalprice);
									intent2.putExtra("singleprice",singleprice);

									startActivityForResult(intent2, 2);
									SeatTableActivity.this.finish();
								}
							});
					builder.setNeutralButton("by cash",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int which) {
									int size = selectedSeat.size();
									for (int i = 0; i < size; i++) {
										String seat = selectedSeat.get(i);
										Log.i(TAG, "seat = " + seat);
										final String[] splits = seat.split(",");
										Log.i(TAG, "!!!splits " + splits);
										// final int index = i;
										new Thread(new Runnable() {
											public void run() {
                                                Integer id = (Integer)MySharedPreference.getId(SeatTableActivity.this);
                                                String url = Const.HOSTIPADDRESS + Const.SEATSELECT
														+ Const.SEATX + splits[0] + Const.AND
														+ Const.SEATY + splits[1] + Const.AND
														+ Const.SHOWID + showid
														+ Const.AND + Const.ROOM + room
														+ Const.AND + Const.USERID
														+ id+Const.AND+Const.PRICEBYSEAT+singleprice;
												Log.i(TAG, "url = " + url);
												String response = NetUtil.get(url);
												Log.i(TAG, "response = " + response);
												Looper.prepare();
												handler.sendEmptyMessage(Const.SEATXD);
											}
//						}
										}).start();
									}
									EmailActivity hh = new EmailActivity();
									hh.sendEmailMessagesWithFiles("598323830@qq.com",
											"fhfowlfbbudgbcdi", email,
											"unpayed_ticket.pdf", "unpayed.pdf");
								}
							});
					builder.setNegativeButton("cancel",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
													int which) {
									// TODO Auto-generated method stub
									// 对话框消失
									dialog.dismiss();
								}
							});
					// 5)创建AlertDialog对象
					builder.setCancelable(true);
					AlertDialog dialog = builder.create();
					// 6)显示对话框
					dialog.show();
				} else if (selectedSeat.size() == 0){
					Toast.makeText(getApplicationContext(), "Please select seat",
							Toast.LENGTH_SHORT).show();
				} else if (!canbuy){
					Toast.makeText(getApplicationContext(), "You cannot buy this movie",
							Toast.LENGTH_SHORT).show();
				}
            }
		});

	}
}
