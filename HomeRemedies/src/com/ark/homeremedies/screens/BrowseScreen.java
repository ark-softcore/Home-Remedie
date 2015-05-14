package com.ark.homeremedies.screens;

import java.util.ArrayList;

import org.ark.common.support.BaseFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.ark.homeremedies.R;
import com.ark.homeremedies.RemedieDescriptionActivity;
import com.ark.homeremedies.adapter.BrowseAdapter;
import com.ark.homeremedies.apputils.AppUtil;
import com.ark.homeremedies.apputils.Sidebar;
import com.ark.homeremedies.common.StickyListHeadersListView;
import com.ark.homeremedies.dao.DatabaseHelper;
import com.ark.homeremedies.dao.model.Remedie;

public class BrowseScreen extends BaseFragment implements OnItemClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.browse_screen, container, false);

		StickyListHeadersListView listView = (StickyListHeadersListView) view.findViewById(R.id.listView);
		listView.setOnItemClickListener(this);
		listView.setAdapter(new BrowseAdapter(getActivity(), new ArrayList<Remedie>()));

		Sidebar sidebar = (Sidebar) findViewById(R.id.sidebar);
		sidebar.setListView(listView);
		
		return view;
	}

	@Override
	protected void initVar() {

	}

	@Override
	protected void bindContents() {
		ArrayList<Remedie> remedies = DatabaseHelper.getInstance(getActivity()).getAllRemedies();

		StickyListHeadersListView listView = (StickyListHeadersListView) view.findViewById(R.id.listView);
		BrowseAdapter adapter = (BrowseAdapter) listView.getAdapter();
		adapter.setList(remedies);
		listView.setAreHeadersSticky(false);
	}

	@Override
	protected void unbindContents() {
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), RemedieDescriptionActivity.class);
		intent.putExtra(AppUtil.INTENT_EXTRA_DATA, (Parcelable) adapterView.getItemAtPosition(position));
		getActivity().startActivity(intent);
	}

}
