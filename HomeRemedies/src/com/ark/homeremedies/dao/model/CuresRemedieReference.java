package com.ark.homeremedies.dao.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class CuresRemedieReference implements DatabaseInsertable, Parcelable {
	public static final String TABLE_NAME = "CuresRemedieReference";

	public static enum Column {
		REMEDIE_ID("remedieId"), CURES_ID("curesId"), CURE_TITLE("title");

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
	private String curesId;

	public CuresRemedieReference(String remedieId, String title, String curesId) {
		setRemedieId(remedieId);
		setTitle(title);
		setCuresId(curesId);
	}

	public CuresRemedieReference(Parcel source) {
		setRemedieId(source.readString());
		setTitle(source.readString());
		setCuresId(source.readString());
	}

	public CuresRemedieReference(Cursor cursor) {
		setRemedieId(cursor.getString(cursor.getColumnIndex(Column.REMEDIE_ID.getIdentifier())));
		setTitle(cursor.getString(cursor.getColumnIndex(Column.CURE_TITLE.getIdentifier())));
		setCuresId(cursor.getString(cursor.getColumnIndex(Column.CURES_ID.getIdentifier())));
	}

	public String getRemedieId() {
		return remedieId;
	}

	public void setRemedieId(String remedieId) {
		this.remedieId = remedieId;
	}

	public String getCuresId() {
		return curesId;
	}

	public void setCuresId(String curesId) {
		this.curesId = curesId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public ContentValues toValues() {

		ContentValues values = new ContentValues();
		values.put(Column.REMEDIE_ID.getIdentifier(), remedieId);
		values.put(Column.CURE_TITLE.getIdentifier(), title);
		values.put(Column.CURES_ID.getIdentifier(), curesId);

		return values;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(remedieId);
		dest.writeString(title);
		dest.writeString(curesId);
	}

	public static final Parcelable.Creator<CuresRemedieReference> CREATOR = new Creator<CuresRemedieReference>() {

		@Override
		public CuresRemedieReference[] newArray(int size) {
			return new CuresRemedieReference[size];
		}

		@Override
		public CuresRemedieReference createFromParcel(Parcel source) {
			return new CuresRemedieReference(source);
		}
	};
}
