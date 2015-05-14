package com.ark.homeremedies.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.dao.model.CuresRemedieReference;

public class CuresAdapter extends ArrayAdapter<CuresRemedieReference> {

	public CuresAdapter(Context context, List<CuresRemedieReference> list) {
		super(context, 0, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		TextView tv = (TextView) convertView;

		if (convertView == null) {

			LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			tv = (TextView) layoutInflater.inflate(R.layout.title_layout, parent, false);

			holder = new ViewHolder();
			holder.title = tv;

			tv.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(getItem(position).getTitle());

		return tv;
	}

	private class ViewHolder {
		TextView title;
	}
}
