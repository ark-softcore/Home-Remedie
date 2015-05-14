package com.ark.homeremedies.adapter;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.apputils.Sidebar;
import com.ark.homeremedies.common.StickyListHeadersAdapter;
import com.ark.homeremedies.dao.model.Remedie;

public class BrowseAdapter extends ArrayAdapter<Remedie> implements SectionIndexer, StickyListHeadersAdapter {
	public BrowseAdapter(Context context, List<Remedie> list) {
		super(context, 0, list);
	}

	public void setList(List<Remedie> list) {
		clear();
		for (Remedie remedie : list) {
			add(remedie);
		}

		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		LinearLayout view = (LinearLayout) convertView;

		if (convertView == null) {
			LayoutInflater layoutInflater = LayoutInflater.from(getContext());
			view = (LinearLayout) layoutInflater.inflate(R.layout.remedie_layout, parent, false);

			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.title);
			holder.favourite = (ImageView) view.findViewById(R.id.star);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Remedie item = getItem(position);
		holder.title.setText(item.getTitle());
		holder.favourite.setVisibility(item.isFavourite() ? View.VISIBLE : View.GONE);

		return view;
	}

	private class ViewHolder {
		TextView title;
		ImageView favourite;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		int count = getCount();
		char sectionChar = (char) Sidebar.letters[sectionIndex];

		for (int i = 0; i < count; i++) {
			if (!TextUtils.isEmpty(getItem(i).getTitle())) {
				char ch = getItem(i).getTitle().toUpperCase().charAt(0);

				if (ch == sectionChar) {
					return i;
				}
			}
		}

		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	@Override
	public Object[] getSections() {
		return Sidebar.letters;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
		view.setText("" + getItem(position).getTitle().charAt(0));
		return view;
	}

	@Override
	public long getHeaderId(int position) {
		Remedie remedie = getItem(position);
		return remedie.getTitle().charAt(0);
	}
}
