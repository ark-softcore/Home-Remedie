package com.ark.homeremedies.main.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ark.homeremedies.R;

public class SlidingMenuOptionAdapter extends ArrayAdapter<Integer> {
	public static final String TAG = SlidingMenuOptionAdapter.class.getName();

	public SlidingMenuOptionAdapter(Context context) {
		super(context, 0);
	}
	
	public SlidingMenuOptionAdapter(Context context, Integer[] objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = (TextView) inflater.inflate(R.layout.sliding_menu_list_item, parent, false);
		}

		view.setText(getItem(position));

		return view;
	}

}
