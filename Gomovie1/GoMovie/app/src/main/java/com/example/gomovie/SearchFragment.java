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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchFragment extends Fragment {
	private static final String TAG = "SearchFragment";
	private ImageButton sousuo;
	private ImageView movieicon;
	private List<Movie> arrayList;
	private List<Movie> arrayListtwo;
	private AutoCompleteTextView auto;
	private GridView gridView;
	private GridView gridViewTwo;
    private ImageButton allmore;
	private ImageButton allsoon;
	private int id;
	private String url;
	private String response;
	private String[] hotmoviesname;
	private String[] unmoviesname;
//
//	// 设置水平滚动的图片
//	private LinearLayout linear;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Handler mHandler1 = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1:
					int totalcount = pagers.size();// autoChangeViewPager.getChildCount();
					int currentItem = autoChangeViewPager.getCurrentItem();

					int toItem = currentItem + 1 == totalcount ? 0
							: currentItem + 1;

					Log.i(TAG, "totalcount: " + totalcount + "   currentItem: "
							+ currentItem + "   toItem: " + toItem);

					autoChangeViewPager.setCurrentItem(toItem, true);

					this.sendEmptyMessageDelayed(1, 2000);
			}
		}
	};
	private class ViewPageChangeListener implements ViewPager.OnPageChangeListener {

		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int arg0) {
			int len = viewIndicator.getChildCount();
			for (int i = 0; i < len; ++i)
				viewIndicator.getChildAt(i).setBackgroundResource(
						R.drawable.about);
			viewIndicator.getChildAt(arg0).setBackgroundResource(
					R.drawable.about);
		}

	}
	public class ViewPagerAdapter extends PagerAdapter {
		private List<View> mData;

		public ViewPagerAdapter(List<View> mData) {
			this.mData = mData;
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View v = mData.get(position);
			container.addView(v);
			return v;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView(mData.get(position));
		}

	}

	@Override
	public void onResume() {
		super.onResume();

		mHandler1.sendEmptyMessageDelayed(1, 6000);
	}

	@Override
	public void onStop() {
		super.onStop();

		mHandler1.removeMessages(1);
	}

	private ImageView iv;
	private LinearLayout viewIndicator;
	private List<View> pagers;
	private ViewPager autoChangeViewPager;
	private void initAdapter() {
		int[] imgs = { R.drawable.xiyouji, R.drawable.shenqidongwu,
				R.drawable.zhuoyaoji, R.drawable.direnjie,
				R.drawable.yidongmigong, R.drawable.shandianxia };

		pagers = new ArrayList<View>();
		FrameLayout.LayoutParams img_params = new FrameLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);

		for (int i = 0; i < imgs.length; ++i) {
			iv = new ImageView(getActivity());
			iv.setBackgroundResource(imgs[i]);
			iv.setLayoutParams(img_params);
			final int index = i;
			pagers.add(iv);

			iv.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),
							IntroductionActivity.class);
					Log.i(TAG, "id = " + arrayListtwo.get(index).getId());
					intent.putExtra("id", arrayListtwo.get(index).getId());
					startActivityForResult(intent, 2);
				}
			});
		}
		autoChangeViewPager.setAdapter(new ViewPagerAdapter(pagers));

		ViewPager.LayoutParams ind_params = new ViewPager.LayoutParams();
		for (int i = 0; i < imgs.length; ++i) {
			ImageView iv = new ImageView(getActivity());
			if (i == 0)
				iv.setBackgroundResource(R.drawable.ic_launcher);
			else
				iv.setBackgroundResource(R.drawable.ic_launcher);
			iv.setLayoutParams(ind_params);
			viewIndicator.addView(iv);
		}
	}


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 导航界面 添加图片 //////
	private int[] hotmoviesicon = { R.drawable.speed1, R.drawable.speed2,
			R.drawable.speed3, R.drawable.speed4, R.drawable.speed5,
			R.drawable.speed6, R.drawable.speed7, R.drawable.speed8,
			R.drawable.harry1, R.drawable.harry2, R.drawable.harry3,
			R.drawable.harry4, R.drawable.harry5, R.drawable.harry6,
			R.drawable.harry7, R.drawable.harry8 };

	// 导航界面 添加图片 //////
	private int[] soonmovieicon = { R.drawable.vxiyouji, R.drawable.vshenqidongwu,
			R.drawable.vzhuoyaoji, R.drawable.vdirenjie,
			R.drawable.vyidongmigong, R.drawable.vshandianxia };

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
					arrayListtwo = new ArrayList<Movie>();
					for (int i = 0; i < length; i++) {
						Movie movie = new Movie();
						JSONObject object = (JSONObject) jsonArray.get(i);
						movie.setMoviename(object.getString("moviename"));
						movie.setIsshow(object.getString("isshow"));
						movie.setId(object.getInt("id"));
						if (movie.getIsshow().equals("yes")) {
							arrayList.add(movie);
						}
						if (movie.getIsshow().equals("no")) {
							arrayListtwo.add(movie);
						}
					}

					hotmoviesname = new String[arrayList.size()];
					for (int i = 0; i < arrayList.size(); i++) {
						hotmoviesname[i] = arrayList.get(i).getMoviename();
					}
					unmoviesname = new String[arrayListtwo.size()];
					for (int i = 0; i < arrayListtwo.size(); i++) {
						unmoviesname[i] = arrayListtwo.get(i).getMoviename();
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getActivity(), android.R.layout.simple_list_item_1,
							hotmoviesname);
					auto.setAdapter(adapter);
					ArrayAdapter<String> adaptertwo = new ArrayAdapter<String>(
							getActivity(), android.R.layout.simple_list_item_1,
							unmoviesname);
					auto.setAdapter(adaptertwo);

					// on now网格布局的填充
					ArrayList<HashMap<String, Object>> movielist = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; i < hotmoviesname.length; i++) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("hotmoviesicon", hotmoviesicon[i]);
						map.put("moviesname", hotmoviesname[i]);
						movielist.add(map);
					}

					SimpleAdapter simpleAdapter = new SimpleAdapter(
							getActivity(), movielist, R.layout.searchitem,
							new String[] { "hotmoviesicon", "moviesname" },
							new int[] { R.id.movieicon, R.id.moviename });

					gridView.setAdapter(simpleAdapter);

					// coming soon网格布局的填充
					ArrayList<HashMap<String, Object>> unmovielist = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; i < unmoviesname.length; i++) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("soonmovieicon", soonmovieicon[i]);
						map.put("moviesname", unmoviesname[i]);
						unmovielist.add(map);
					}
					SimpleAdapter simpleAdaptertwo = new SimpleAdapter(
							getActivity(), unmovielist, R.layout.searchitem,
							new String[] { "soonmovieicon", "moviesname" },
							new int[] { R.id.movieicon, R.id.moviename });

					gridViewTwo.setAdapter(simpleAdaptertwo);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search, container, false);

		this.gridView = (GridView) view.findViewById(R.id.gridView);
		this.gridViewTwo = (GridView) view.findViewById(R.id.gridViewTwo);

        allmore = (ImageButton) view.findViewById(R.id.allmore);
        allmore.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(),
                        ViewAllActivity.class);
                startActivityForResult(intent, 2);
            }
        });
		allsoon = (ImageButton) view.findViewById(R.id.allsoon);
		allsoon.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						ViewComingActivity.class);
				startActivityForResult(intent, 2);
			}
		});
		// 设置搜索的时候的自动匹配
		auto = (AutoCompleteTextView) view.findViewById(R.id.autotext);

		// 设置搜索按钮的点击事件
		sousuo = (ImageButton) view.findViewById(R.id.sousuo);
		sousuo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String trim = auto.getText().toString().trim();
				url = Const.HOSTIPADDRESS + Const.LIKEQUERY + Const.LIKEPARAM
						+ trim;
				Log.i(TAG, "url = " + url);
				new Thread(new Runnable() {
					public void run() {
						response = NetUtil.get(url);
						Log.i(TAG, "response = " + response);
						try {
							JSONArray jsonArray = new JSONArray(response);
							JSONObject jo = (JSONObject) jsonArray.get(0);
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

        //on now click
		gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Intent intent = new Intent(getActivity(), InfoActivity.class);
				int id2 = arrayList.get(position).getId();
				intent.putExtra("id", id2);
				startActivity(intent);
			}
		});
		//come soon click
		gridViewTwo.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Intent intent = new Intent(getActivity(), IntroductionActivity.class);
				int id2 = arrayListtwo.get(position).getId();
				intent.putExtra("id", id2);
				startActivity(intent);
			}
		});
		autoChangeViewPager = (ViewPager) view.findViewById(R.id.autoVP);
		viewIndicator = (LinearLayout) view.findViewById(R.id.vpindicator);

		initAdapter();
		autoChangeViewPager
				.setOnPageChangeListener(new ViewPageChangeListener());

//		// 初始化水平滚动设置
//		linear = (LinearLayout) view.findViewById(R.id.linear);
//		inittent();
		return view;
	}

	// 水平滚动的方法
//	private void inittent() {
//		// TODO Auto-generated method stub
//		// 开始添加数据
//		for (int i = 0; i < hotmoviesicon.length; i++) {
//			// 找到布局，第一个参数是行布局的ID，第二个参数是放到的那个容器
//			View view = LayoutInflater.from(getActivity()).inflate(
//					R.layout.horizontal, linear, false);
//
//			ImageView img = (ImageView) view.findViewById(R.id.imageView);
//			TextView tv = (TextView) view.findViewById(R.id.textView);
//			// 将int数组中的数据放到Imageview中
//			img.setImageResource(hotmoviesicon[i]);
//			// 给textview添加文字
//			// tv.setText("电影" + (i + 1));
////			 tv.setText(hotmoviesname[i]);
//			// 把行布局放到linear
//			linear.addView(view);
//			final int index = i;
//			img.setOnClickListener(new View.OnClickListener() {
//
//				@Override
//				public void onClick(View arg0) {
//					Intent intent = new Intent(getActivity(),
//							InfoActivity.class);
//					int id2 = arrayList.get(index).getId();
//					intent.putExtra("id", id2);
//					startActivity(intent);
//				}
//			});
//		}
//	}
}