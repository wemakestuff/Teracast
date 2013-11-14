package com.wemakestuff.teracast.media.event;

import com.wemakestuff.teracast.model.api.Episode;
import com.wemakestuff.teracast.service.MediaService;

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
