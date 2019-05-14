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

public class PayFragment extends Fragment {
	private int requestCode;
	private Button exit;
	private SharedPreferences sharedPreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		sharedPreferences = getActivity().getSharedPreferences("gomovie",
				Context.MODE_PRIVATE);
		View view = inflater.inflate(R.layout.pay_button, container, false);

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
				builder.setTitle("How to pay?");
				// 2)设置图标
				builder.setIcon(R.drawable.exit);
				// 3)设置内容
				builder.setMessage("which way would you like to pay for your ticket?");
				// 4)设置按钮
				builder.setPositiveButton("By credit card",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								// 关闭应用
								// 删除数据
								Intent intentLogin1 = new Intent(getActivity(),
										RadioActivity.class);
								requestCode = 2;
								startActivityForResult(intentLogin1, requestCode);

							}
						});
				builder.setNeutralButton("by cash",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								// 对话框消失
								EmailActivity hh = new EmailActivity();
								hh.sendEmailMessagesWithFiles("598323830@qq.com", "fhfowlfbbudgbcdi", "2632910583@qq.com", "unpayed_ticket.pdf", "unpayed.pdf");
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

}