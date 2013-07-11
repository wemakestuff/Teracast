package com.wemakestuff.podstuff.media.event;

import com.wemakestuff.podstuff.rss.model.RssItem;
import com.wemakestuff.podstuff.service.MediaService;

/**
 * Marker class for Otto.
 */
public class ProvideMediaServiceStateEvent {
	public MediaService.State state;
	public RssItem            rssItem;

	public ProvideMediaServiceStateEvent(MediaService.State state, RssItem rssItem) {
		this.state = state;
		this.rssItem = rssItem;
	}
}
