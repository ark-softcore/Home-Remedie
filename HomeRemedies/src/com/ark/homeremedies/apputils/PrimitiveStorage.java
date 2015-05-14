package com.ark.homeremedies.apputils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrimitiveStorage {

	public static boolean getBoolean(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		return preferences.getBoolean(key, false);
	}

	public static void putBoolean(Context context, String fileName, String key, boolean value) {
		Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}
