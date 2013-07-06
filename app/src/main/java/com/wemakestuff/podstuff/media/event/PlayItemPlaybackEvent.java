package com.wemakestuff.podstuff.media.event;

import com.wemakestuff.podstuff.rss.RssItem;

/**
 * Marker class for Otto.
 */
public class PlayItemPlaybackEvent {
	public RssItem mediaItem;

	public PlayItemPlaybackEvent(RssItem mediaItem) {
		this.mediaItem = mediaItem;
	}
}
