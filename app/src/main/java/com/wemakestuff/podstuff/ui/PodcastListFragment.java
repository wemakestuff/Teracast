package com.wemakestuff.podstuff.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockListFragment;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public class PodcastListFragment extends SherlockListFragment implements TitlePagerItem {
	@Inject
	Bus mBus;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public String getTitle() {
		return null;
	}
}
