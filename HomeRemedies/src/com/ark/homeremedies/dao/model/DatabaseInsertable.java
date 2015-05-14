package com.ark.homeremedies.dao.model;

import android.content.ContentValues;

public interface DatabaseInsertable {
	ContentValues toValues();
	
	String getTableName();
}
