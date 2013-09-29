package com.wemakestuff.podstuff.model.navigation.listener;

import com.wemakestuff.podstuff.model.api.Episode;

public interface OnEpisodeClickListener {
    public enum Action {
        PLAY, MORE, MORE_PLAY;
    }

    public void onEpisodeClick(Episode episode, Action action);
}
