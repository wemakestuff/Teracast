package com.wemakestuff.teracast.rss.event;

import com.wemakestuff.teracast.rss.model.RssFeed;

public class ProvideRssFeedEvent {
	public RssFeed rssFeed;

	public ProvideRssFeedEvent(RssFeed rssFeed) {
		this.rssFeed = rssFeed;
	}
}
