package com.ark.homeremedies.wiki;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ark.homeremedies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends org.ark.common.support.BaseFragment {
	private WebView webView;
	private AppWebViewClient webClient;
	public static String data;

	public WebViewFragment() {
	}

	@SuppressLint("SetJavaScriptEnabled")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_web_view, container,
				false);

		webView = (WebView) view.findViewById(R.id.web_view);
		webView.setInitialScale(100);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webSettings.setBuiltInZoomControls(true);
		if (Build.VERSION.SDK_INT >= 11) {
			webSettings.setDisplayZoomControls(false);
		}

		webClient = new AppWebViewClient(getActivity());

		if (savedInstanceState == null) {
			webView.setWebViewClient(webClient);
		} else {
			webView.restoreState(savedInstanceState);
		}
		
		String url = getArguments().getString(WebviewActivity.URL);
		webView.loadUrl(url);

		return view;
	}

	// Returns true if the WebView can go back to the previous page
	public boolean canGoBack() {
		return webView.canGoBack();
	}

	// Operations to be performed if the back button is pressed
	public void onBackPressed() {
		webView.stopLoading();

		if (webView.canGoBack()) {
			webView.goBack();
		}
	}

	@Override
	public void onPause() {
		super.onPause();

		webClient.onPause();
		webView.onPause();
		webView.stopLoading();
	}

	@Override
	public void onResume() {
		super.onResume();

		webView.onResume();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			webView.restoreState(savedInstanceState);
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			webView.restoreState(savedInstanceState);
		}
		super.onViewCreated(view, savedInstanceState);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		webView.saveState(outState);
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
