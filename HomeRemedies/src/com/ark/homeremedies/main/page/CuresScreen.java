package com.ark.homeremedies.main.page;

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
import com.ark.homeremedies.apputils.AppUtil;
import com.ark.homeremedies.curedescription.CureDescriptionScreen;
import com.ark.homeremedies.dao.DatabaseHelper;
import com.ark.homeremedies.dao.model.CuresRemedieReference;
import com.ark.homeremedies.main.adapter.CuresAdapter;

public class CuresScreen extends BaseFragment implements OnItemClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.cure_browse_screen, container, false);

		ListView listView = (ListView) view.findViewById(R.id.listView);
		listView.setOnItemClickListener(this);

		ArrayList<CuresRemedieReference> list = DatabaseHelper.getInstance(getActivity()).getAllCures();
		listView.setAdapter(new CuresAdapter(getActivity(), list));

		return view;
	}

	@Override
	protected void initVar() {

	}

	@Override
	protected void bindContents() {

	}

	@Override
	protected void unbindContents() {

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), CureDescriptionScreen.class);
		intent.putExtra(AppUtil.INTENT_EXTRA_DATA, (Parcelable) adapterView.getItemAtPosition(position));
		getActivity().startActivity(intent);
	}

}
