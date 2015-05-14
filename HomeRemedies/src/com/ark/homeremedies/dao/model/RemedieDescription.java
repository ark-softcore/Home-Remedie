package com.ark.homeremedies.dao.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class RemedieDescription implements DatabaseInsertable, Parcelable {
	public static final String TABLE_NAME = "RemedieDescription";

	public static enum Column {
		REMEDIE_ID("remedieId"), POSITION("position"), DESCRIPTION("description");

		private String identifier;

		private Column(String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return identifier;
		}
	}

	private String remedieId;
	private int position;
	private String description;

	public RemedieDescription(String remedieId, int position, String description) {
		setRemedieId(remedieId);
		setPosition(position);
		setDescription(description);
	}

	public RemedieDescription(Parcel source) {
		setRemedieId(source.readString());
		setPosition(source.readInt());
		setDescription(source.readString());
	}

	public RemedieDescription(Cursor cursor) {
		setRemedieId(cursor.getString(cursor.getColumnIndex(Column.REMEDIE_ID.getIdentifier())));
		setPosition(cursor.getInt(cursor.getColumnIndex(Column.POSITION.getIdentifier())));
		setDescription(cursor.getString(cursor.getColumnIndex(Column.DESCRIPTION.getIdentifier())));
	}

	public String getRemedieId() {
		return remedieId;
	}

	public void setRemedieId(String remedieId) {
		this.remedieId = remedieId;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public ContentValues toValues() {

		ContentValues values = new ContentValues();
		values.put(Column.REMEDIE_ID.getIdentifier(), remedieId);
		values.put(Column.POSITION.getIdentifier(), position);
		values.put(Column.DESCRIPTION.getIdentifier(), description);

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
		dest.writeInt(position);
		dest.writeString(description);
	}

	public static final Parcelable.Creator<RemedieDescription> CREATOR = new Parcelable.Creator<RemedieDescription>() {

		@Override
		public RemedieDescription createFromParcel(Parcel source) {
			return new RemedieDescription(source);
		}

		@Override
		public RemedieDescription[] newArray(int size) {
			return new RemedieDescription[size];
		}
	};
}
