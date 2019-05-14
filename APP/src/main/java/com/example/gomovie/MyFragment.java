package com.example.gomovie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyFragment extends Fragment {

	private TextView login;
	private int requestCode;
	private TableRow pagemanager;
	private TableRow pageabout;
	private LinearLayout usedll;
	private Button exit;
	private SharedPreferences sharedPreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		sharedPreferences = getActivity().getSharedPreferences("gomovie",
				Context.MODE_PRIVATE);
		View view = inflater.inflate(R.layout.my, container, false);

		login = (TextView) view.findViewById(R.id.login);
		login.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentLogin = new Intent(getActivity(),
						LoginActivity.class);
				requestCode = 2;
				startActivityForResult(intentLogin, requestCode);

			}
		});
		boolean online = sharedPreferences.getBoolean("online", false);
		if (online) {
			String username = sharedPreferences.getString("username", "登陆/注册");
			login.setText(username);
			if (!username.equals("登陆/注册")) {
				// login.setClickable(false);
				// TODO 退出登陆的操作
			}
		}

		usedll = (LinearLayout) view.findViewById(R.id.usedll);
		usedll.setOnClickListener(new useClickListener());

		pagemanager = (TableRow) view.findViewById(R.id.page_manager);
		pagemanager.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						UpdatepwActivity.class);
				startActivityForResult(intent, 2);
			}
		});

		pageabout = (TableRow) view.findViewById(R.id.page_about);
		pageabout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), AboutActivity.class);
				startActivityForResult(intent, 2);
			}
		});

		exit = (Button) view.findViewById(R.id.exit);
		exit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 简单的文本对话框
				// 第一步：获取Builder对象
				// 可以设置四个区域的内容 可以创建对话框的对象
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				// 1）设置标题
				builder.setTitle("Exit GOMovie");
				// 2)设置图标
				builder.setIcon(R.drawable.exit);
				// 3)设置内容
				builder.setMessage("确定退出应用吗？");
				// 4)设置按钮
				builder.setPositiveButton("是的，我要退出了",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								// 关闭应用
								// 删除数据
								System.exit(0);
							}
						});
				builder.setNegativeButton("不好意思，点错了",
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

			}
		});
		return view;
	}

	class useClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), UsedActivity.class);
			startActivityForResult(intent, 2);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 2 & resultCode == 2) {
			String username = data.getStringExtra("username");
			login.setText(username);

			Editor edit = sharedPreferences.edit();
			edit.putBoolean("online", true);
			edit.putString("username", username);
			edit.commit();
		}
	}

}