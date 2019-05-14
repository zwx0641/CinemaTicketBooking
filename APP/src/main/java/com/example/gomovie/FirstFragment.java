package com.example.gomovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FirstFragment extends Fragment {

	private List<Movie> arrayList;
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
						movie.setActors(object.getString("actors"));
						movie.setDatestr(object.getString("datestr"));
						movie.setDesc(object.getString("desc"));
						movie.setDirector(object.getString("director"));
						movie.setId(object.getInt("id"));
						movie.setIsshow(object.getString("isshow"));
						movie.setMovieicon(object.getString("movieicon"));
						movie.setMoviename(object.getString("moviename"));
						movie.setType(object.getString("type"));
						if (movie.getIsshow().equals("no")) {
							arrayList.add(movie);
						}
					}
					FirstListAdapter firstListAdapter = new FirstListAdapter(
							getActivity(), arrayList);
					firstlistview.setAdapter(firstListAdapter);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		};
	};

	private static final String TAG = MainActivity.class.getSimpleName();
	private ViewPager autoChangeViewPager;
	private boolean isExit = false;

	private ListView firstlistview;
	private int id;

	// 待映动画
	private LinearLayout viewIndicator;
	private List<View> pagers;
	private String url;
	private String response;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = inflater.inflate(R.layout.first, container, false);

		// 列表的数据设置
		firstlistview = (ListView) view.findViewById(R.id.firstlistview);
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

		// firstlistview.setAdapter(simpleAdapter);
		firstlistview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(getActivity(),
						IntroductionActivity.class);
				Log.i(TAG, "id = " + arrayList.get(position).getId());
				intent.putExtra("id", arrayList.get(position).getId());
				startActivity(intent);
			}
		});

		autoChangeViewPager = (ViewPager) view.findViewById(R.id.autoVP);
		viewIndicator = (LinearLayout) view.findViewById(R.id.vpindicator);

		initAdapter();
		autoChangeViewPager
				.setOnPageChangeListener(new ViewPageChangeListener());

		return view;
	}

	private ImageView iv;

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
					Log.i(TAG, "id = " + arrayList.get(index).getId());
					intent.putExtra("id", arrayList.get(index).getId());
					startActivityForResult(intent, 2);
				}

			});

		}

		autoChangeViewPager.setAdapter(new ViewPagerAdapter(pagers));

		LayoutParams ind_params = new LayoutParams();
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

	private class ViewPageChangeListener implements OnPageChangeListener {

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

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ToQuitTheApp();
			return false;
		} else {
			return onKeyDown(keyCode, event);
		}
	}

	private void ToQuitTheApp() {
		if (isExit) {

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			System.exit(0);
		} else {
			isExit = true;
			Toast.makeText(getActivity(), "APP", Toast.LENGTH_SHORT).show();
			mHandler2.sendEmptyMessageDelayed(0, 3000);
		}
	}

	Handler mHandler2 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};
}