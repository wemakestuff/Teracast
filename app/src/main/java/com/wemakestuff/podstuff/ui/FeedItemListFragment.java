package com.wemakestuff.podstuff.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListFragment;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.rss.RssFeed;

public class FeedItemListFragment extends SherlockListFragment {
	private RssFeed mFeed;
	private LayoutInflater mInflater;

	public FeedItemListFragment(RssFeed mFeed) {
		this.mFeed = mFeed;
	}

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new FeedItemListAdapter(mInflater, mFeed.getRssItems()));
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		mInflater = inflater;
		View view = mInflater.inflate(R.layout.feed_item_list, container, false);
		return view;
	}

	@Override
	public void onListItemClick(final ListView l, final View v, final int position, final long id) {

	}
}
