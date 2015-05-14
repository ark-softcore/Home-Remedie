package com.ark.homeremedies.main.menu;

import org.ark.common.support.BaseActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.ark.homeremedies.MainActivity;
import com.ark.homeremedies.R;

public class BgActivity extends BaseActivity {
	private static boolean close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		close = false;
	}

	@SuppressLint("NewApi")
	@Override
	protected void onResume() {
		super.onResume();

		if (close) {
			finish();
		} else {
			getSupportActionBar().hide();

			setContentView(R.layout.activity_bg);

			startActivity(new Intent(getActivity(), MainActivity.class));

			close = true;
		}
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

}
