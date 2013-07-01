package com.wemakestuff.podstuff.media.event;

/**
 * Marker class for Otto.
*/
public class SeekPlaybackEvent {
	public int seekTo;

	public SeekPlaybackEvent(int seekTo) {
		this.seekTo = seekTo;
	}
}
