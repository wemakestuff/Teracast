package com.wemakestuff.podstuff.media.event;

import com.wemakestuff.podstuff.rss.Item;

/**
 * Marker class for Otto.
 */
public class PlayItemPlaybackEvent {
	public Item mediaItem;

	public PlayItemPlaybackEvent(Item mediaItem) {
		this.mediaItem = mediaItem;
	}
}
