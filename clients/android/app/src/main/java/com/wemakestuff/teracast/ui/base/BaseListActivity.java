package com.wemakestuff.teracast.ui.base;

import android.content.Intent;
import android.os.Bundle;
import butterknife.Views;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;
import com.android.debug.hv.ViewServer;
import com.wemakestuff.teracast.BuildConfig;
import com.wemakestuff.teracast.Injector;
import com.wemakestuff.teracast.ui.FeedItemListActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Base activity for a list activity which does not use fragments.
 */
public abstract class BaseListActivity extends SherlockListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Injector.inject(this);

		if (BuildConfig.DEBUG) {
			ViewServer.get(this).addWindow(this);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (BuildConfig.DEBUG) {
			ViewServer.get(this).removeWindow(this);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (BuildConfig.DEBUG) {
			ViewServer.get(this).setFocusedWindow(this);
		}
	}

	@Override
	public void setContentView(int layoutResId) {
		super.setContentView(layoutResId);

		// Used to inject views with the Butterknife library
		Views.inject(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:  // This is the home button in the top left corner of the screen.
				// Dont call finish! Because activity could have been started by an outside activity and the home button would not operated as expected!
				Intent homeIntent = new Intent(this, FeedItemListActivity.class);
				homeIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(homeIntent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
