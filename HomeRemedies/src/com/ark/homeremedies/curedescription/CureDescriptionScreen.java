package com.ark.homeremedies.curedescription;

import java.util.ArrayList;

import org.ark.common.support.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.ark.homeremedies.R;
import com.ark.homeremedies.apputils.AppUtil;
import com.ark.homeremedies.curedescription.adapter.CureDescriptionAdapter;
import com.ark.homeremedies.dao.DatabaseHelper;
import com.ark.homeremedies.dao.model.Cures;
import com.ark.homeremedies.dao.model.CuresRemedieReference;

public class CureDescriptionScreen extends BaseActivity {

	private CuresRemedieReference curesRemedieReference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cure_description_screen);

		Intent intent = getIntent();
		curesRemedieReference = intent.getParcelableExtra(AppUtil.INTENT_EXTRA_DATA);
		setTitle(curesRemedieReference.getTitle());
		
		ArrayList<Cures> list = DatabaseHelper.getInstance(this).getCuresList(curesRemedieReference.getCuresId());
		
		ExpandableListView descListView = (ExpandableListView) findViewById(R.id.description_list);
		descListView.setAdapter(new CureDescriptionAdapter(this, list));
	}

	@Override
	protected void initVar() {

	}

	@Override
	protected void bindContents() {
	}

	@Override
	protected void unbindContents() {
	}

}
