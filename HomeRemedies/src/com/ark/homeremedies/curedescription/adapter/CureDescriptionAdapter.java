package com.ark.homeremedies.curedescription.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.dao.DatabaseHelper;
import com.ark.homeremedies.dao.model.CureDescription;
import com.ark.homeremedies.dao.model.Cures;

public class CureDescriptionAdapter extends BaseExpandableListAdapter {
	private Context context;

	private ArrayList<Cures> curesList;

	public CureDescriptionAdapter(Context context, ArrayList<Cures> curesList) {
		this.context = context;
		this.curesList = curesList;
	}

	@Override
	public CureDescription getChild(int groupPosition, int childPosition) {
		Cures group = getGroup(groupPosition);
		ArrayList<CureDescription> cureDescriptionsList = DatabaseHelper.getInstance(context).getCureDescriptions(group.getCuresId(), group.getReceipeId());
		return cureDescriptionsList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.description_layout, parent, false);
		TextView tView = (TextView) view.findViewById(R.id.title);
		tView.setText(getChild(groupPosition, childPosition).getDescription().replace("\n", ""));
		
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition < getGroupCount()) {
			Cures group = getGroup(groupPosition);
			ArrayList<CureDescription> cureDescriptionsList = DatabaseHelper.getInstance(context).getCureDescriptions(group.getCuresId(), group.getReceipeId());
			return cureDescriptionsList.size();
		}
		
		return 0;
	}

	@Override
	public Cures getGroup(int groupPosition) {
		return curesList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return curesList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView view = (TextView) inflater.inflate(R.layout.title_layout, parent, false);
		view.setBackgroundColor(context.getResources().getColor(R.color.app_color_primary_dark));
		view.setText(getGroup(groupPosition).getCureName());
		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
