package com.wemakestuff.teracast.ui.base;

import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragment;
import com.github.frankiesardo.icepick.bundle.Bundles;
import com.wemakestuff.teracast.Injector;

import butterknife.Views;

public abstract class BaseFragment extends SherlockFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundles.restoreInstanceState(this, savedInstanceState);
        setRetainInstance(true);
        Injector.inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundles.saveInstanceState(this, outState);
    }

    public void showProgressBar() {
        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
    }

    public void hideProgressBar() {
        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void onDestroyView() {
        Views.reset(this);
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Views.inject(this, view);
    }

    /**
     * Is this fragment still part of an activity and usable from the UI-thread?
     *
     * @return true if usable on the UI-thread, false otherwise
     */
    protected boolean isUsable() {
        return getActivity() != null;
    }
}
