package com.ark.homeremedies.wiki;

import org.ark.common.support.BaseActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.ark.homeremedies.R;

public class WebviewActivity extends BaseActivity {
	public static final String TAG = WebviewActivity.class.getName();

	public static final String URL = "intent_extra_url";
	public static final String TITLE = "intent_extra_title";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		String url = getIntent().getStringExtra(URL);
		Bundle bundle = new Bundle();
		bundle.putString(WebviewActivity.URL, url);
		
		setTitle(getIntent().getStringExtra(TITLE));
		
		WebViewFragment webViewFragment = new WebViewFragment();
		webViewFragment.setArguments(bundle);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.container, webViewFragment);
		fragmentTransaction.commit();
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
		
		return super.onOptionsItemSelected(item);
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
