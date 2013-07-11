package com.wemakestuff.podstuff.media.event;

import com.wemakestuff.podstuff.rss.model.RssItem;

/**
 * Marker class for Otto.
 */
public class PlayItemEvent {
	public RssItem rssItem;

	public PlayItemEvent(RssItem rssItem) {
		this.rssItem = rssItem;
	}
}
