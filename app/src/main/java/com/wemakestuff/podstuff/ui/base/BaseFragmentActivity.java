package com.wemakestuff.podstuff.ui.base;

import android.content.Intent;
import android.os.Bundle;
import butterknife.Views;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.android.debug.hv.ViewServer;
import com.github.frankiesardo.icepick.bundle.Bundles;
import com.wemakestuff.podstuff.BuildConfig;
import com.wemakestuff.podstuff.Injector;
import com.wemakestuff.podstuff.ui.FeedItemListActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Base class for all Activities that need fragments.
 */
public class BaseFragmentActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundles.restoreInstanceState(this, savedInstanceState);
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
	protected void onRestoreInstanceState(final Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Bundles.restoreInstanceState(this, savedInstanceState);
	}

}
