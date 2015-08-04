/*
 * File: AppWebViewClient.java
 * 
 * WebViewClient class for handling web page navigation in the WebView
 */

package com.ark.homeremedies.wiki;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * AppWebViewClient is WebViewClient class for handling web page navigation in
 * the WebView
 *
 * @author optimus
 */
public class AppWebViewClient extends WebViewClient {
	private ProgressDialog dialog = null;

	public AppWebViewClient(final Activity activity) {
		dialog = new ProgressDialog(activity);
		dialog.setMessage("Loading content....");
		dialog.setCancelable(true);
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				activity.finish();
			}
		});
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);

		if(dialog != null) {
			dialog.show();
		}
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		
		if(dialog != null && dialog.isShowing()) {
			dialog.setOnCancelListener(null);
			dialog.cancel();
			dialog = null;
		}
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		return false;

	}

	public void onPause() {
	}
}
