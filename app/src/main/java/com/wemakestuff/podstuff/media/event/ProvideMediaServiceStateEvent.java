package com.wemakestuff.podstuff.media.event;

import com.wemakestuff.podstuff.model.api.Episode;
import com.wemakestuff.podstuff.service.MediaService;

/**
 * Marker class for Otto.
 */
public class ProvideMediaServiceStateEvent {
    public MediaService.State state;
    public Episode episode;

    public ProvideMediaServiceStateEvent(MediaService.State state, Episode episode) {
        this.state = state;
        this.episode = episode;
    }
}
