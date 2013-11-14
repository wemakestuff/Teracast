package com.wemakestuff.teracast.media.event;

import com.wemakestuff.teracast.model.api.Episode;

/**
 * Marker class for Otto.
 */
public class PlayItemEvent {
    public Episode episode;

    public PlayItemEvent(Episode episode) {
        this.episode = episode;
    }
}
