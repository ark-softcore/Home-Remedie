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
import android.widget.ListView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.RemedieDescriptionActivity;
import com.ark.homeremedies.adapter.FavouriteAdapter;
import com.ark.homeremedies.apputils.AppUtil;
import com.ark.homeremedies.dao.DatabaseHelper;
import com.ark.homeremedies.dao.model.Remedie;

public class FavouriteRemediesScreen extends BaseFragment implements OnItemClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.browse_screen, container, false);

		ListView listView = (ListView) view.findViewById(R.id.listView);
		listView.setOnItemClickListener(this);
		listView.setAdapter(new FavouriteAdapter(getActivity(), new ArrayList<Remedie>()));

		return view;
	}

	@Override
	protected void initVar() {

	}

	@Override
	protected void bindContents() {
		ArrayList<Remedie> remedies = DatabaseHelper.getInstance(getActivity()).getAllFavouriteRemedies();

		ListView listView = (ListView) view.findViewById(R.id.listView);
		FavouriteAdapter adapter = (FavouriteAdapter) listView.getAdapter();
		adapter.setList(remedies);
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
