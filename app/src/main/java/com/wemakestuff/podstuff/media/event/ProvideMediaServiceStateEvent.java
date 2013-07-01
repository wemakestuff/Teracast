package com.wemakestuff.podstuff.media.event;

import com.wemakestuff.podstuff.service.MediaService;

/**
 * Marker class for Otto.
 */
public class ProvideMediaServiceStateEvent {
	public MediaService.State state;

	public ProvideMediaServiceStateEvent(MediaService.State state) {
		this.state = state;
	}
}
