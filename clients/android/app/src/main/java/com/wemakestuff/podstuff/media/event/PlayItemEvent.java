package com.wemakestuff.podstuff.media.event;

import com.wemakestuff.podstuff.model.api.Episode;

/**
 * Marker class for Otto.
 */
public class PlayItemEvent {
    public Episode episode;

    public PlayItemEvent(Episode episode) {
        this.episode = episode;
    }
}
