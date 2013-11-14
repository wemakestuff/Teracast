package com.wemakestuff.podstuff.rss.event;

import com.wemakestuff.podstuff.rss.model.RssFeed;

public class ProvideRssFeedEvent {
	public RssFeed rssFeed;

	public ProvideRssFeedEvent(RssFeed rssFeed) {
		this.rssFeed = rssFeed;
	}
}
