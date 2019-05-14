package com.example.gomovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SoonFragment extends Fragment {
	private static final String TAG = "SoonFragment";
	private ImageButton sousuo;
	private ImageView movieicon;
	private List<Movie> arrayList;
	private AutoCompleteTextView auto;
	private GridView gridView;
	private int id;
	private String url;
	private String url1;
	private String response;
	private String[] hotmoviesname;

	// 设置水平滚动的图片
	private LinearLayout linear;

	// 导航界面 添加图片 //////
	private int[] hotmoviesicon = { R.drawable.speed1, R.drawable.speed2,
			R.drawable.speed3, R.drawable.speed4, R.drawable.speed5,
			R.drawable.speed6, R.drawable.speed7, R.drawable.speed8,
			R.drawable.harry1, R.drawable.harry2, R.drawable.harry3,
			R.drawable.harry4, R.drawable.harry5, R.drawable.harry6,
			R.drawable.harry7, R.drawable.harry8 };
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Const.MOVIE_SUCCESS:
				String responseStr = (String) msg.obj;
				try {
					JSONArray jsonArray = new JSONArray(responseStr);
					int length = jsonArray.length();
					Log.i(TAG, "length = " + length);
					arrayList = new ArrayList<Movie>();
					for (int i = 0; i < length; i++) {
						Movie movie = new Movie();
						JSONObject object = (JSONObject) jsonArray.get(i);
						movie.setMoviename(object.getString("moviename"));
						movie.setIsshow(object.getString("isshow"));
						movie.setId(object.getInt("id"));
						if (movie.getIsshow().equals("yes")) {
							arrayList.add(movie);
						}
					}
					hotmoviesname = new String[arrayList.size()];
					for (int i = 0; i < arrayList.size(); i++) {
						hotmoviesname[i] = arrayList.get(i).getMoviename();
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getActivity(), android.R.layout.simple_list_item_1,
							hotmoviesname);
					auto.setAdapter(adapter);

					// 网格布局的填充
					ArrayList<HashMap<String, Object>> movielist = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; i < hotmoviesname.length; i++) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("hotmoviesicon", hotmoviesicon[i]);
						map.put("moviesname", hotmoviesname[i]);
						movielist.add(map);
					}
					// SimpleAdapter simpleAdapter = new
					// SimpleAdapter(getActivity(),
					// movielist, R.layout.searchitem, new String[] {
					// "moviesname" }, new int[] {
					// R.id.moviename});

					SimpleAdapter simpleAdapter = new SimpleAdapter(
							getActivity(), movielist, R.layout.allitem,
							new String[] { "hotmoviesicon", "moviesname" },
							new int[] { R.id.movieicon, R.id.moviename });

					gridView.setAdapter(simpleAdapter);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		};
	};

	// private String[] hotmoviesname = { "movie1", "movie2", "movie3",
	// "movie4",
	// "movie5", "movie6", "movie7" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.allon, container, false);

		this.gridView = (GridView) view.findViewById(R.id.gridView);

		// 设置搜索的时候的自动匹配
		auto = (AutoCompleteTextView) view.findViewById(R.id.autotext);

		// 设置搜索按钮的点击事件
		sousuo = (ImageButton) view.findViewById(R.id.sousuo);
		sousuo.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String trim = auto.getText().toString().trim();
				url = Const.HOSTIPADDRESS + Const.LIKEQUERY + Const.LIKEPARAM
						+ trim;
				url1 = url.replaceAll(" ","%20");
				Log.i(TAG, "url = " + url1);
				new Thread(new Runnable() {
					public void run() {
						response = NetUtil.get(url1);
						Log.i(TAG, "response = " + response);
						try {
							JSONArray jsonArray = new JSONArray(response);
							JSONObject jo = (JSONObject) jsonArray.get(0); //TODO：这个array就是它是否返回的电影id；0返回速度1；返回速度2
							id = jo.getInt("id");
							Intent intent = new Intent(getActivity(),
									InfoActivity.class);
							intent.putExtra("id", id);
							startActivity(intent);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}
		});
		new Thread(new Runnable() {

			public void run() {
				String url = Const.HOSTIPADDRESS + Const.MOVIE;
				Log.i(TAG, "url = " + url);
				String response = NetUtil.get(url);
				Log.d(TAG, "respnse = " + response);
				Message obtain = Message.obtain();
				obtain.what = Const.MOVIE_SUCCESS;
				obtain.obj = response;
				handler.sendMessage(obtain);
			}
		}).start();

		// 初始化水平滚动设置
		linear = (LinearLayout) view.findViewById(R.id.linear);
		inittent();

		gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), InfoActivity.class);
				int id2 = arrayList.get(position).getId();
				intent.putExtra("id", id2);
				startActivity(intent);
			}
		});
		return view;
	}

	// 水平滚动的方法
	private void inittent() {
		// TODO Auto-generated method stub
		// 开始添加数据
		for (int i = 0; i < hotmoviesicon.length; i++) {
			// 找到布局，第一个参数是行布局的ID，第二个参数是放到的那个容器
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.horizontal, linear, false);

			ImageView img = (ImageView) view.findViewById(R.id.imageView);
			TextView tv = (TextView) view.findViewById(R.id.textView);
			// 将int数组中的数据放到Imageview中
			img.setImageResource(hotmoviesicon[i]);
			// 给textview添加文字
			// tv.setText("电影" + (i + 1));
//			 tv.setText(hotmoviesname[i]);
			// 把行布局放到linear
			linear.addView(view);
			final int index = i;
			img.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(getActivity(),
							InfoActivity.class);
					int id2 = arrayList.get(index).getId();
					intent.putExtra("id", id2);
					startActivity(intent);
				}
			});
		}
	}
}