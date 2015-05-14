package com.ark.homeremedies.dao.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Remedie implements DatabaseInsertable, Parcelable {
	public static final String TABLE_NAME = "Remedies";

	public static enum Column {
		REMEDIE_ID("remedieId"), TITLE("title"), FAVOURITE("favourite");

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
	private boolean favourite;

	public Remedie(String remedieId, String title, boolean favourite) {
		this.remedieId = remedieId;
		this.title = title;
		this.favourite = favourite;
	}

	public Remedie(Parcel source) {
		this.remedieId = source.readString();
		this.title = source.readString();
		this.favourite = source.readInt() == 1;
	}

	public Remedie(Cursor cursor) {
		this.remedieId = cursor.getString(cursor.getColumnIndex(Column.REMEDIE_ID.getIdentifier()));
		this.title = cursor.getString(cursor.getColumnIndex(Column.TITLE.getIdentifier())).replace("\n", "");
		this.favourite = cursor.getInt(cursor.getColumnIndex(Column.FAVOURITE.getIdentifier())) == 1;
	}

	@Override
	public ContentValues toValues() {
		ContentValues values = new ContentValues();
		values.put(Column.REMEDIE_ID.getIdentifier(), remedieId);
		values.put(Column.TITLE.getIdentifier(), title);
		values.put(Column.FAVOURITE.getIdentifier(), favourite ? 1 : 0);

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

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
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
		dest.writeInt(favourite ? 1 : 0);
	}

	public static final Parcelable.Creator<Remedie> CREATOR = new Parcelable.Creator<Remedie>() {

		@Override
		public Remedie createFromParcel(Parcel source) {
			return new Remedie(source);
		}

		@Override
		public Remedie[] newArray(int size) {
			return new Remedie[size];
		}
	};
}
