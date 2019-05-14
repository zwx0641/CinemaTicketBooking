package com.example.gomovie;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class PayActivity extends Activity implements OnClickListener {

	private static final String TAG = "PayActivity";
	private int showid;
	private int room;
	private ArrayList<Seat> arrayList;
	private ArrayList<String> selectedSeat;
	private Button register;
	private ImageButton backtomy;
	private String email;
	private double totalprice;
	private double singleprice;
	private EditText et_cardnumber;
	private EditText et_password;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case Const.SEATXD:
					EmailActivity hh = new EmailActivity();
					hh.sendEmailMessagesWithFiles("598323830@qq.com",
							"fhfowlfbbudgbcdi", email,
							"payed_ticket.pdf", "payed.pdf");
					Toast.makeText(getApplicationContext(), "Congratulations!Successful payment!",
							Toast.LENGTH_SHORT).show();
					PayActivity.this.finish();
					break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让ActionBar消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pay);
		Log.i(TAG, "seat = " + "test1");
		email = (String)MySharedPreference.getEmail(PayActivity.this);
		Log.d(TAG, "seat = " + "test1");
		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(this);
		et_cardnumber = (EditText) findViewById(R.id.number);
		et_password = (EditText) findViewById(R.id.password);
		arrayList = (ArrayList<Seat>)getIntent().getSerializableExtra("arraylist");
		showid = getIntent().getIntExtra("showid",0);
		room = getIntent().getIntExtra("room",0);
		totalprice = getIntent().getDoubleExtra("totalprice",0);
		singleprice = getIntent().getDoubleExtra("singleprice",0);
		selectedSeat = getIntent()
				.getStringArrayListExtra("selectedSeat");

		Button loginBtn = (Button) findViewById(R.id.loginbtn);
		loginBtn.setOnClickListener(new OnClickListener() {

			private String inputCardnumber;
			private String inputPassword;

			public void onClick(View v) {


				int size = selectedSeat.size();
				for (int i = 0; i < size; i++) {
					String seat = selectedSeat.get(i);
					Log.i(TAG, "seat = " + seat);
					final String[] splits = seat.split(",");
					// final int index = i;
					new Thread(new Runnable() {
							public void run() {
								Log.i(TAG, "url = " + "test");

								Integer id = (Integer)MySharedPreference.getId(PayActivity.this);
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
						}).start();
				   }
			}
		});

	}
	public void onClick(View v) {
		PayActivity.this.finish();
	}

}
