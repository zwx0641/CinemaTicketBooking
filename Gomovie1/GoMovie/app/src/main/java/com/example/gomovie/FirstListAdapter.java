package com.example.gomovie;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FirstListAdapter extends BaseAdapter {

	private Context context;
	private List list;

	public FirstListAdapter(Context context, List list) {
		this.context = context;
		this.list = list;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.firstitem, null);
			holder = new ViewHolder();
			holder.movieName = (TextView) convertView
					.findViewById(R.id.moviename);
			holder.datestr = (TextView) convertView.findViewById(R.id.showdate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Movie movie = (Movie) list.get(position);
		holder.datestr.setText(movie.getDatestr());
		holder.movieName.setText(movie.getMoviename());
		return convertView;
	}

	static class ViewHolder {
		TextView movieName;
		TextView datestr;
	}
}
