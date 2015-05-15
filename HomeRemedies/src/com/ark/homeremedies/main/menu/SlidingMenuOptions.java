package com.ark.homeremedies.main.menu;

import org.ark.common.support.BaseFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.main.MainActivity;
import com.ark.homeremedies.main.page.BrowseScreen;
import com.ark.homeremedies.main.page.CuresScreen;
import com.ark.homeremedies.main.page.FavouriteRemediesScreen;

public class SlidingMenuOptions extends BaseFragment implements OnItemClickListener {
	public static final String TAG = SlidingMenuOptions.class.getName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.option_menu, container, false);

		return view;
	}

	@Override
	protected void initVar() {
		ListView listView = (ListView) findViewById(R.id.option_list_view);

		SlidingMenuOptionAdapter adapter = new SlidingMenuOptionAdapter(getActivity());
		adapter.add(R.string.remedies);
		adapter.add(R.string.favourites);
		adapter.add(R.string.cures);
		adapter.add(R.string.remove_ads);
		adapter.add(R.string.more_apps);

		listView.setAdapter(adapter);

		BaseFragment fragment = new BrowseScreen();
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.container, fragment).commitAllowingStateLoss();
		((MainActivity) getActivity()).setCurrentFragment(fragment);
	}

	@Override
	protected void bindContents() {
		((ListView) findViewById(R.id.option_list_view)).setOnItemClickListener(this);
	}

	@Override
	protected void unbindContents() {
		((ListView) findViewById(R.id.option_list_view)).setOnItemClickListener(null);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

		BaseFragment fragment = null;

		int item = (Integer) parent.getItemAtPosition(position);
		switch (item) {
		case R.string.remedies:
			fragment = new BrowseScreen();
			break;

		case R.string.cures:
			fragment = new CuresScreen();
			break;
			
		case R.string.favourites:
			fragment = new FavouriteRemediesScreen();
			break;
		}

		transaction.replace(R.id.container, fragment).commitAllowingStateLoss();
		((MainActivity) getActivity()).setCurrentFragment(fragment);

		((MainActivity) getActivity()).getMenu().toggle(true);

	}

}
