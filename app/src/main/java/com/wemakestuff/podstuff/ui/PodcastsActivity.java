package com.wemakestuff.podstuff.ui;

import android.content.Intent;
import android.os.Bundle;
import butterknife.InjectView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.squareup.otto.Bus;
import com.viewpagerindicator.TitlePageIndicator;
import com.wemakestuff.podstuff.R;

import javax.inject.Inject;

public class PodcastsActivity extends BaseFragmentActivity {
	@Inject
	Bus                mBus;
	@InjectView(R.id.tpi_titles)
	TitlePageIndicator titlePageIndicator;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.podcasts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.register:
				Intent intent = new Intent(this, RegisterActivity.class);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
