package com.wemakestuff.teracast.media.event;

/**
 * Marker class for Otto.
*/
public class RelativeSeekEvent {
	public int seekAmount;

	public RelativeSeekEvent(int seekAmount) {
		this.seekAmount = seekAmount;
	}
}
