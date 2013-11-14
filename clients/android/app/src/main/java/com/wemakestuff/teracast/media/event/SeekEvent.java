package com.wemakestuff.teracast.media.event;

/**
 * Marker class for Otto.
*/
public class SeekEvent {
	public int seekTo;

	public SeekEvent(int seekTo) {
		this.seekTo = seekTo;
	}
}
