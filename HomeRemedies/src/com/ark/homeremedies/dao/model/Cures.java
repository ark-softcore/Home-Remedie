package com.ark.homeremedies.dao.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Cures implements DatabaseInsertable, Parcelable {
	public static final String TABLE_NAME = "Cures";

	public static enum Column {
		CURES_ID("curesId"), CURE_NAME("cureName"), POSITION("position"), RECEIPE_ID("receipeId");

		private String identifier;

		private Column(String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return identifier;
		}
	}

	private String curesId;
	private String cureName;
	private int position;
	private String receipeId;

	public Cures(String curesId, String cureName, int position, String receipeId) {
		setCuresId(curesId);
		setCureName(cureName);
		setPosition(position);
		setRecipeId(receipeId);
	}

	private Cures(Parcel source) {
		setCuresId(source.readString());
		setCureName(source.readString());
		setPosition(source.readInt());
		setRecipeId(source.readString());
	}

	public Cures(Cursor cursor) {
		setCuresId(cursor.getString(cursor.getColumnIndex(Column.CURES_ID.getIdentifier())));
		setCureName(cursor.getString(cursor.getColumnIndex(Column.CURE_NAME.getIdentifier())));
		setPosition(cursor.getInt(cursor.getColumnIndex(Column.POSITION.getIdentifier())));
		setRecipeId(cursor.getString(cursor.getColumnIndex(Column.RECEIPE_ID.getIdentifier())));
	}

	public String getCuresId() {
		return curesId;
	}

	public void setCuresId(String curesId) {
		this.curesId = curesId;
	}

	public String getCureName() {
		return cureName;
	}

	public void setCureName(String cureName) {
		this.cureName = cureName;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getReceipeId() {
		return receipeId;
	}

	public void setRecipeId(String receipeId) {
		this.receipeId = receipeId;
	}

	@Override
	public ContentValues toValues() {
		ContentValues values = new ContentValues();
		values.put(Column.CURES_ID.getIdentifier(), curesId);
		values.put(Column.CURE_NAME.getIdentifier(), cureName);
		values.put(Column.POSITION.getIdentifier(), position);
		values.put(Column.RECEIPE_ID.getIdentifier(), receipeId);

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
		dest.writeString(curesId);
		dest.writeString(cureName);
		dest.writeInt(position);
		dest.writeString(receipeId);
	}

	public static final Parcelable.Creator<Cures> CREATOR = new Parcelable.Creator<Cures>() {

		@Override
		public Cures[] newArray(int size) {
			return new Cures[size];
		}

		@Override
		public Cures createFromParcel(Parcel source) {
			return new Cures(source);
		}
	};
}
