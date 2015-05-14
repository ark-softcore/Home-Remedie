package com.ark.homeremedies.dao.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class FavouriteRemedie implements DatabaseInsertable, Parcelable {
	public static final String TABLE_NAME = "FavouriteRemedies";

	public static enum Column {
		REMEDIE_ID("remedieId"), TITLE("title");

		private String identifier;

		private Column(String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return identifier;
		}
	}

	private String remedieId;
	private String title;

	public FavouriteRemedie(String remedieId, String title) {
		this.remedieId = remedieId;
		this.title = title;
	}

	public FavouriteRemedie(Parcel source) {
		this.remedieId = source.readString();
		this.title = source.readString();
	}

	public FavouriteRemedie(Cursor cursor) {
		this.remedieId = cursor.getString(cursor.getColumnIndex(Column.REMEDIE_ID.getIdentifier()));
		this.title = cursor.getString(cursor.getColumnIndex(Column.TITLE.getIdentifier()));;
	}

	@Override
	public ContentValues toValues() {
		ContentValues values = new ContentValues();
		values.put(Column.REMEDIE_ID.getIdentifier(), remedieId);
		values.put(Column.TITLE.getIdentifier(), title);

		return values;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public String getRemedieId() {
		return remedieId;
	}

	public String getTitle() {
		return title;
	}

	public void setRemedieId(String remedieId) {
		this.remedieId = remedieId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(remedieId);
		dest.writeString(title);
	}

	public static final Parcelable.Creator<FavouriteRemedie> CREATOR = new Parcelable.Creator<FavouriteRemedie>() {

		@Override
		public FavouriteRemedie createFromParcel(Parcel source) {
			return new FavouriteRemedie(source);
		}

		@Override
		public FavouriteRemedie[] newArray(int size) {
			return new FavouriteRemedie[size];
		}
	};
}
