package com.ark.homeremedies.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.dao.model.Remedie;

public class FavouriteAdapter extends BrowseAdapter {
	
	public FavouriteAdapter(Context context, List<Remedie> list) {
		super(context, list);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		LinearLayout view = (LinearLayout) convertView;

		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = (LinearLayout) layoutInflater.inflate(R.layout.favourite_remedie_list_item, parent, false);

			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.title);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Remedie item = getItem(position);
		holder.title.setText(item.getTitle());

		return view;
	}

	private class ViewHolder {
		TextView title;
	}
}
