package com.example.gomovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class RadioActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

	private RadioGroup radioGroup_gender;
	private int showid;
	private int room;
	private ArrayList<Seat> arrayList;
	private ArrayList<String> selectedSeat;
	private ImageButton backtomy;
	private double totalprice;
	private double singleprice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		this.radioGroup_gender = (RadioGroup) this.findViewById(R.id.radioGroup_gender);
		this.radioGroup_gender.setOnCheckedChangeListener(this);
		backtomy = (ImageButton) findViewById(R.id.backtomy);
		backtomy.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				RadioActivity.this.finish();
			}
		});

	}

	/**
	 * 当单选按钮的状态发生变化时自动调用的方法
	 *
	 * @param group     单选按钮所在的按钮组的对象
	 * @param checkedId 用户选中的单选按钮的id值
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		//得到用户选中的 RadioButton 对象
		int requestCode;
		RadioButton radioButton_checked = (RadioButton) group.findViewById(checkedId);
		String gender = radioButton_checked.getText().toString();
		Toast.makeText(this, gender, Toast.LENGTH_LONG).show();



		arrayList = (ArrayList<Seat>)getIntent().getSerializableExtra("arraylist");
		showid = getIntent().getIntExtra("showid",0);
		room = getIntent().getIntExtra("room",0);
		totalprice = getIntent().getDoubleExtra("totalprice",0);
		singleprice = getIntent().getDoubleExtra("singleprice",0);
		selectedSeat = getIntent()
				.getStringArrayListExtra("selectedSeat");

		switch (checkedId) {
			case R.id.radioButton_visa:
				//当用户点击男性按钮时执行的代码
				Intent intent1 = new Intent(this,
						PayActivity.class);
				intent1.putExtra("selectedSeat", selectedSeat);
				intent1.putExtra("arraylist", arrayList);
				intent1.putExtra("showid", showid);
				intent1.putExtra("room", room);
				intent1.putExtra("totalprice", totalprice);
				intent1.putExtra("singleprice",singleprice);

				requestCode = 2;
				startActivityForResult(intent1, requestCode);
				RadioActivity.this.finish();
				break;
			case R.id.radioButton_master:
				Intent intent2 = new Intent(this,
						PayActivity.class);
				intent2.putExtra("selectedSeat", selectedSeat);
				intent2.putExtra("arraylist", arrayList);
				intent2.putExtra("showid", showid);
				intent2.putExtra("room", room);
				intent2.putExtra("totalprice", totalprice);
				intent2.putExtra("singleprice",singleprice);
				requestCode = 2;
				startActivityForResult(intent2, requestCode);
				RadioActivity.this.finish();
				break;
			case R.id.radioButton_union:
				//当用户点击女性按钮时执行的代码
				Intent intent3 = new Intent(this,
						PayActivity.class);
				intent3.putExtra("selectedSeat", selectedSeat);
				intent3.putExtra("arraylist", arrayList);
				intent3.putExtra("showid", showid);
				intent3.putExtra("room", room);
				intent3.putExtra("totalprice", totalprice);
				intent3.putExtra("singleprice",singleprice);
				requestCode = 2;
				startActivityForResult(intent3, requestCode);
				RadioActivity.this.finish();
				break;
		}
		System.out.println("===onCheckedChanged(RadioGroup group=" + group + ", int checkedId=" + checkedId + ")==");
	}
}