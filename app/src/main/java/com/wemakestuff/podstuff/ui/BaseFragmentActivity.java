package com.wemakestuff.podstuff.ui;

import android.os.Bundle;
import butterknife.Views;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.wemakestuff.podstuff.BootstrapApplication;

/**
 * Base class for all Bootstrap Activities that need fragments.
 */
public class BaseFragmentActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BootstrapApplication.getInstance().inject(this);
	}

	@Override
	public void setContentView(int layoutResId) {
		super.setContentView(layoutResId);

		Views.inject(this);
	}

}
