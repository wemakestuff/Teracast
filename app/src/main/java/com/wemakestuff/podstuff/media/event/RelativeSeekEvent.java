package com.wemakestuff.podstuff.media.event;

/**
 * Marker class for Otto.
*/
public class RelativeSeekEvent {
	public int seekTo;

	public RelativeSeekEvent(int seekTo) {
		this.seekTo = seekTo;
	}
}
