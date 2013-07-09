package com.wemakestuff.podstuff.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.wemakestuff.podstuff.rss.event.ProvideRssFeedEvent;
import com.wemakestuff.podstuff.rss.model.RssFeed;
import com.wemakestuff.podstuff.service.RssFeedService;

import javax.inject.Inject;

public class FeedItemListActivity extends BootstrapListActivity {
	@Inject
	Bus bus;

	RssFeed parsedFeed;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.list_activity);
		RssFeedService.getRssFeed(getApplicationContext(), Uri.parse("http://feeds.feedburner.com/StartupsForTheRestOfUs"));
		getListView().setTextFilterEnabled(true);
		getListView().setFastScrollEnabled(true);
		getListView().setClickable(true);
	}

	@Override
	protected void onDestroy() {
		bus.unregister(this);
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		bus.register(this);
	}

	@Subscribe
	public void onProvideRssFeedEvent(ProvideRssFeedEvent provideRssFeedEvent) {
		parsedFeed = provideRssFeedEvent.rssFeed;
		FeedItemListAdapter adapter = new FeedItemListAdapter(getLayoutInflater(), parsedFeed);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
		super.onListItemClick(l, v, position, id);
	}
}
