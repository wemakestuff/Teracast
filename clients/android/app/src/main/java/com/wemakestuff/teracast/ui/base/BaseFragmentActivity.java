package com.wemakestuff.teracast.ui.base;

import android.os.Bundle;
import butterknife.Views;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.debug.hv.ViewServer;
import com.github.frankiesardo.icepick.bundle.Bundles;
import com.wemakestuff.teracast.BuildConfig;
import com.wemakestuff.teracast.Injector;

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
