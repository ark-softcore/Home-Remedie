package com.ark.homeremedies.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.ark.homeremedies.apputils.AppUtil;
import com.ark.homeremedies.apputils.PrimitiveStorage;

public class DatabaseCopyTask extends AsyncTask<Void, Void, Boolean> {

	private final Context context;
	private ProgressDialog progressDialog;
	private OnDBCopyCompleteListener listener;

	public DatabaseCopyTask(Context context) {
		this.context = context;
		progressDialog = getProgressDialog();

	}

	public void setDBCopyCompleteListener(OnDBCopyCompleteListener listener) {
		this.listener = listener;
	}

	private ProgressDialog getProgressDialog() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Progressing");
		progressDialog.setMessage("Initializing App for first time");
		progressDialog.setCancelable(false);
		return progressDialog;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.show();
	}

	@Override
	protected Boolean doInBackground(Void... params) {

		SQLiteDatabase database = context.openOrCreateDatabase(DatabaseHelper.DB_NAME, Context.MODE_PRIVATE, null);

		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = context.getAssets().open(DatabaseHelper.DB_NAME);

			File file = context.getDatabasePath(DatabaseHelper.DB_NAME);
			outputStream = new FileOutputStream(file);

			byte[] byt = new byte[1024];
			while (inputStream.read(byt) != -1) {
				outputStream.write(byt);
			}

			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				if (outputStream != null) {
					outputStream.close();
					outputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			database.close();
		}

		return false;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);

		if (result) {
			setDatabaseExist();
		}

		if (listener != null) {
			listener.onDBCopyComplete();
		}

		progressDialog.dismiss();
	}

	public static boolean isDatabaseExist(Context context) {
		return PrimitiveStorage.getBoolean(context, AppUtil.PREFERENCE_FILE_NAME, AppUtil.KAY_DATABASE);
	}

	private void setDatabaseExist() {
		PrimitiveStorage.putBoolean(context, AppUtil.PREFERENCE_FILE_NAME, AppUtil.KAY_DATABASE, true);
	}

	public static interface OnDBCopyCompleteListener {
		public abstract void onDBCopyComplete();
	}
}
