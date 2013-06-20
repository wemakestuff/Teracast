package com.wemakestuff.podstuff.ui;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.wemakestuff.podstuff.BootstrapApplication;

import butterknife.Views;

/**
 * Base class for all Bootstrap Activities that need fragments.
 */
public class BootstrapFragmentActivity extends SherlockFragmentActivity {

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
