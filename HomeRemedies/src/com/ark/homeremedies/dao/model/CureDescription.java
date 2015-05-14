package com.ark.homeremedies.dao.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class CureDescription implements DatabaseInsertable, Parcelable {
	public static final String TABLE_NAME = "CureDescription";

	public static enum Column {
		RECEIPE_ID("receipeId"), POSITION("position"), DESCRIPTION("description"),CURES_ID("curesId");

		private String identifier;

		private Column(String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return identifier;
		}
	}

	private String receipeId;
	private int position;
	private String description;
	private String curesId;

	public CureDescription(String receipeId, int position, String description,String curesId) {
		setreceipeId(receipeId);
		setPosition(position);
		setDescription(description);
		setCuresId(curesId);
	}

	public CureDescription(Parcel source) {
		setreceipeId(source.readString());
		setPosition(source.readInt());
		setDescription(source.readString());
		setCuresId(source.readString());
	}
	
	public CureDescription(Cursor cursor) {
		setreceipeId(cursor.getString(cursor.getColumnIndex(Column.RECEIPE_ID.getIdentifier())));
		setPosition(cursor.getInt(cursor.getColumnIndex(Column.POSITION.getIdentifier())));
		setDescription(cursor.getString(cursor.getColumnIndex(Column.DESCRIPTION.getIdentifier())));
		setCuresId(cursor.getString(cursor.getColumnIndex(Column.CURES_ID.getIdentifier())));
	}

	public String getreceipeId() {
		return receipeId;
	}

	public void setreceipeId(String receipeId) {
		this.receipeId = receipeId;
	}

	public int getPosition() {
		return position;
	}
	
	public String getCuresId() {
		return curesId;
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
	
	public void setCuresId(String curesId) {
		this.curesId = curesId;
	}

	@Override
	public ContentValues toValues() {
		ContentValues values = new ContentValues();
		values.put(Column.RECEIPE_ID.getIdentifier(), receipeId);
		values.put(Column.POSITION.getIdentifier(), position);
		values.put(Column.DESCRIPTION.getIdentifier(), description);
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
		dest.writeString(receipeId);
		dest.writeInt(position);
		dest.writeString(description);
		dest.writeString(curesId);
	}

	public static final Parcelable.Creator<CureDescription> CREATOR = new Parcelable.Creator<CureDescription>() {

		@Override
		public CureDescription createFromParcel(Parcel source) {
			return new CureDescription(source);
		}

		@Override
		public CureDescription[] newArray(int size) {
			return new CureDescription[size];
		}

	};

}
