package com.ark.homeremedies.main;

import org.ark.common.support.BaseActivity;
import org.ark.common.support.BaseFragment;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.dao.DatabaseCopyTask;
import com.ark.homeremedies.dao.DatabaseCopyTask.OnDBCopyCompleteListener;
import com.ark.homeremedies.main.menu.SlidingMenuOptions;
import com.ark.homeremedies.main.page.BrowseScreen;
import com.ark.homeremedies.main.page.CuresScreen;
import com.ark.homeremedies.main.page.FavouriteRemediesScreen;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity {
	private SlidingMenu menu;
	private BaseFragment currentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public SlidingMenu getMenu() {
		return menu;
	}

	public void setCurrentFragment(BaseFragment currentFragment) {
		this.currentFragment = currentFragment;
		
		TextView title = (TextView) findViewById(R.id.activity_title);
		
		if(currentFragment instanceof BrowseScreen){
			title.setText(R.string.remedies);
		} else if(currentFragment instanceof CuresScreen){
			title.setText(R.string.cures);
		} else if(currentFragment instanceof FavouriteRemediesScreen){
			title.setText(R.string.favourites);
		}
	}
	
	public BaseFragment getCurrentFragment() {
		return currentFragment;
	}

	@Override
	protected void initVar() {

		if (!DatabaseCopyTask.isDatabaseExist(getActivity())) {
			DatabaseCopyTask task = new DatabaseCopyTask(getActivity());
			task.setDBCopyCompleteListener(new OnDBCopyCompleteListener() {

				@Override
				public void onDBCopyComplete() {
					setupSlidingMenu();
				}
			});
			task.execute();
		} else {
			setupSlidingMenu();
		}
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	private void setupSlidingMenu() {
		final int width;

		Display display = getWindowManager().getDefaultDisplay();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Point size = new Point();
			display.getSize(size);

			width = size.x;
		} else {
			width = display.getWidth();
		}

		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setBehindWidth((int) (width * 0.75));
		menu.setBehindScrollScale(1.00f);
		menu.setFadeDegree(0.25f);
		menu.setSlidingEnabled(true);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setMenu(R.layout.menu_frame);
		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);

		getSupportFragmentManager().beginTransaction().replace(R.id.menu_layout, new SlidingMenuOptions()).commit();

		View imagebuttonSliderControl = findViewById(R.id.button_slider_control);
		imagebuttonSliderControl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				menu.toggle(true);
			}
		});

	}

	@Override
	protected void bindContents() {

	}

	@Override
	protected void unbindContents() {

	}

}
