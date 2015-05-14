package com.ark.homeremedies.dao;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ark.homeremedies.dao.model.CureDescription;
import com.ark.homeremedies.dao.model.Cures;
import com.ark.homeremedies.dao.model.CuresRemedieReference;
import com.ark.homeremedies.dao.model.DatabaseInsertable;
import com.ark.homeremedies.dao.model.FavouriteRemedie;
import com.ark.homeremedies.dao.model.Remedie;
import com.ark.homeremedies.dao.model.RemedieDescription;
import com.ark.homeremedies.dao.model.RemedieDescription.Column;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static String DB_NAME = "RemediesDB.db";
	private static int DB_VERSION = 1;

	private static DatabaseHelper dbHelper;

	private DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public static DatabaseHelper getInstance(Context context) {
		if (dbHelper == null) {
			dbHelper = new DatabaseHelper(context);
		}
		return dbHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// db.execSQL("CREATE TABLE " + Remedie.TABLE_NAME + "(" +
		// Remedie.Column.REMEDIE_ID.getIdentifier() + " text PRIMARY_KEY, " +
		// Remedie.Column.TITLE.getIdentifier() + " text)");
		//
		// db.execSQL("CREATE TABLE " + RemedieDescription.TABLE_NAME + "(" +
		// RemedieDescription.Column.REMEDIE_ID.getIdentifier() + " text, " +
		// RemedieDescription.Column.POSITION.getIdentifier()
		// + " text, " + RemedieDescription.Column.DESCRIPTION.getIdentifier() +
		// " text)");
		//
		// db.execSQL("CREATE TABLE " + CuresRemedieReference.TABLE_NAME + "(" +
		// CuresRemedieReference.Column.REMEDIE_ID.getIdentifier() + " text, "
		// + CuresRemedieReference.Column.CURES_ID.getIdentifier() + " text, " +
		// CuresRemedieReference.Column.CURE_TITLE.getIdentifier() + " text)");
		//
		// db.execSQL("CREATE TABLE " + Cures.TABLE_NAME + "(" +
		// Cures.Column.CURES_ID.getIdentifier() + " text, " +
		// Cures.Column.POSITION.getIdentifier() + " text, "
		// + Cures.Column.CURE_NAME.getIdentifier() + " text, " +
		// Cures.Column.RECEIPE_ID.getIdentifier() + " text)");
		//
		//
		// db.execSQL("CREATE TABLE " + CureDescription.TABLE_NAME + "(" +
		// CureDescription.Column.RECEIPE_ID.getIdentifier() + " text, " +
		// CureDescription.Column.POSITION.getIdentifier() + " text, "
		// + CureDescription.Column.DESCRIPTION.getIdentifier() + " text, " +
		// CureDescription.Column.CURES_ID.getIdentifier() + " text)");
		//
		// db.execSQL("CREATE TABLE " + FavouriteRemedie.TABLE_NAME + "(" +
		// FavouriteRemedie.Column.REMEDIE_ID.getIdentifier() +
		// " text PRIMARY_KEY, " + FavouriteRemedie.Column.TITLE.getIdentifier()
		// + " text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void insertRecord(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Remedie.Column.REMEDIE_ID.getIdentifier(), queryValues.get(Remedie.Column.REMEDIE_ID.getIdentifier()));
		values.put(Remedie.Column.TITLE.getIdentifier(), queryValues.get(Remedie.Column.TITLE.getIdentifier()));
		database.insert(Remedie.TABLE_NAME, null, values);
		database.close();
	}

	public void insertRecord(DatabaseInsertable insertable) {
		SQLiteDatabase database = this.getWritableDatabase();
		database.insert(insertable.getTableName(), null, insertable.toValues());
	}

	public ArrayList<Remedie> getAllRemedies() {
		SQLiteDatabase db = getReadableDatabase();
		ArrayList<Remedie> remedies = new ArrayList<Remedie>();

		try {
			Cursor cursor = db.query(Remedie.TABLE_NAME, null, null, null, null, null, null);

			while (cursor.moveToNext()) {
				remedies.add(new Remedie(cursor));
			}
		} catch (Exception e) {
		}

		return remedies;
	}
	
	public ArrayList<Remedie> getAllFavouriteRemedies() {
		SQLiteDatabase db = getReadableDatabase();
		ArrayList<Remedie> remedies = new ArrayList<Remedie>();

		try {
			Cursor cursor = db.query(Remedie.TABLE_NAME, null, Remedie.Column.FAVOURITE + "= 1", null, null, null, null);

			while (cursor.moveToNext()) {
				remedies.add(new Remedie(cursor));
			}
		} catch (Exception e) {
		}

		return remedies;
	}

	public void setRemedieAsFavourite(Remedie remedie) {
		setRemedieAsFavourite(remedie.getRemedieId(), remedie.isFavourite());
	}
	
	public void setRemedieAsFavourite(String remedieId, boolean favourite) {
		SQLiteDatabase database = this.getWritableDatabase();

		try {
			ContentValues values = new ContentValues();
			values.put(Remedie.Column.FAVOURITE.getIdentifier(), favourite ? 1 : 0);
			database.update(Remedie.TABLE_NAME, values, Remedie.Column.REMEDIE_ID.getIdentifier() + "=?", new String[] { remedieId });

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			database.close();
		}
	}

	public ArrayList<RemedieDescription> getRemedieDescription(String remedieId) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<RemedieDescription> desc = new ArrayList<RemedieDescription>();
		try {
			Cursor cursor = db.query(RemedieDescription.TABLE_NAME, null, Column.REMEDIE_ID.getIdentifier() + "=?", new String[] { remedieId }, null, null, null);
			while (cursor.moveToNext()) {
				desc.add(new RemedieDescription(cursor));
			}
		} catch (Exception e) {
		}
		return desc;
	}

	public ArrayList<Cures> getCuresList(String cureId) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Cures> cures = new ArrayList<Cures>();
		try {
			Cursor cursor = db.query(Cures.TABLE_NAME, null, Cures.Column.CURES_ID.getIdentifier() + "=?", new String[] { cureId }, null, null, Cures.Column.POSITION.getIdentifier());
			while (cursor.moveToNext()) {
				cures.add(new Cures(cursor));
			}
		} catch (Exception e) {
		}
		return cures;
	}

	public CuresRemedieReference getCure(String remedieId) {
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			Cursor cursor = db.query(CuresRemedieReference.TABLE_NAME, null, CuresRemedieReference.Column.REMEDIE_ID.getIdentifier() + "=?", new String[] { remedieId }, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				return new CuresRemedieReference(cursor);
			}

		} catch (Exception e) {
		}
		return null;
	}

	public ArrayList<CureDescription> getCureDescriptions(String cureID, String receipeId) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<CureDescription> cures = new ArrayList<CureDescription>();
		try {
			Cursor cursor = db.query(CureDescription.TABLE_NAME, null, CureDescription.Column.CURES_ID.getIdentifier() + "=?" + " and " + CureDescription.Column.RECEIPE_ID.getIdentifier() + "=?",
					new String[] { cureID, receipeId }, null, null, CureDescription.Column.POSITION.getIdentifier());

			while (cursor.moveToNext()) {
				cures.add(new CureDescription(cursor));
			}
		} catch (Exception e) {
		}
		return cures;
	}

	public ArrayList<CuresRemedieReference> getAllCures() {

		SQLiteDatabase database = this.getReadableDatabase();
		ArrayList<CuresRemedieReference> curesRemedieReferences = new ArrayList<CuresRemedieReference>();

		try {

			Cursor cursor = database.query(CuresRemedieReference.TABLE_NAME, null, null, null, null, null, null);
			while (cursor.moveToNext()) {
				curesRemedieReferences.add(new CuresRemedieReference(cursor));
			}

		} catch (Exception e) {
		}

		return curesRemedieReferences;
	}

	public ArrayList<FavouriteRemedie> getFavouriteRemedies() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<FavouriteRemedie> favouriteRemedies = new ArrayList<FavouriteRemedie>();

		try {
			Cursor cursor = db.query(FavouriteRemedie.TABLE_NAME, null, null, null, null, null, null);
			while (cursor.moveToNext())
				favouriteRemedies.add(new FavouriteRemedie(cursor));

		} catch (Exception e) {
		}
		return favouriteRemedies;
	}
}
