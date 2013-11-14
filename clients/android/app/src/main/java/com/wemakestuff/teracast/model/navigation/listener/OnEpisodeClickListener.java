package com.wemakestuff.teracast.model.navigation.listener;

import com.wemakestuff.teracast.model.navigation.EpisodeItem;

public interface OnEpisodeClickListener {
    public enum Action {
        ITEM, PLAY, MORE, LONG_ITEM, MORE_PLAY;
    }

    public void onEpisodeClick(EpisodeItem episodeItem, Action action);
}
