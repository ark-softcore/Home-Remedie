package com.ark.homeremedies;

import java.util.ArrayList;
import java.util.Iterator;

import org.ark.common.support.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ark.homeremedies.apputils.AppUtil;
import com.ark.homeremedies.dao.DatabaseHelper;
import com.ark.homeremedies.dao.model.CuresRemedieReference;
import com.ark.homeremedies.dao.model.Remedie;
import com.ark.homeremedies.dao.model.RemedieDescription;

public class RemedieDescriptionActivity extends BaseActivity {
	private Remedie remedie;
	private CuresRemedieReference cure;
	
	private MenuItem favouriteMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remedie_description);

		Intent intent = getIntent();
		remedie = intent.getParcelableExtra(AppUtil.INTENT_EXTRA_DATA);
		
		setTitle(remedie.getTitle());
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		TextView tvDesc = (TextView) findViewById(R.id.desc);

		ArrayList<RemedieDescription> test = DatabaseHelper.getInstance(this).getRemedieDescription(remedie.getRemedieId());

		StringBuilder desc = new StringBuilder();

		Iterator<RemedieDescription> iterator = test.iterator();
		while (iterator.hasNext()) {
			RemedieDescription remedieDescription = (RemedieDescription) iterator.next();
			desc.append(remedieDescription.getDescription());
		}

		tvDesc.setText(desc);
		
		cure = DatabaseHelper.getInstance(this).getCure(remedie.getRemedieId());
		
		Button cureTitle = (Button) findViewById(R.id.cureTitle);
		cureTitle.setText(cure.getTitle());
		cureTitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity(), CureDescriptionScreen.class);
				intent.putExtra(AppUtil.INTENT_EXTRA_DATA, cure);
				getActivity().startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.remedie_description, menu);
		
		favouriteMenu = menu.findItem(R.id.action_favourite);
		if(remedie.isFavourite()){
			favouriteMenu.setIcon(android.R.drawable.star_big_on);
		} else {
			favouriteMenu.setIcon(android.R.drawable.star_big_off);
		}
		
	    return super.onCreateOptionsMenu(menu);	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_favourite:
			remedie.setFavourite(!remedie.isFavourite());
			
			if(remedie.isFavourite()){
				favouriteMenu.setIcon(android.R.drawable.star_big_on);
				
				Toast.makeText(getActivity(), R.string.added_in_favourite, Toast.LENGTH_SHORT).show();
			} else {
				favouriteMenu.setIcon(android.R.drawable.star_big_off);
				
				Toast.makeText(getActivity(), R.string.removed_from_favourite, Toast.LENGTH_SHORT).show();
			}
			
			DatabaseHelper.getInstance(getActivity()).setRemedieAsFavourite(remedie);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void initVar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void bindContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void unbindContents() {
		// TODO Auto-generated method stub
		
	}

}
