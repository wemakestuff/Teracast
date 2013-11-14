package com.wemakestuff.teracast.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListFragment;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.rss.model.RssFeed;

public class FeedItemListFragment extends SherlockListFragment {
	private RssFeed mFeed;
	private LayoutInflater mInflater;

	public FeedItemListFragment(RssFeed mFeed) {
		this.mFeed = mFeed;
	}

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new FeedItemListAdapter(mInflater, mFeed));
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		mInflater = inflater;
		View view = mInflater.inflate(R.layout.list_activity, container, false);
		return view;
	}

	@Override
	public void onListItemClick(final ListView l, final View v, final int position, final long id) {

	}
}
