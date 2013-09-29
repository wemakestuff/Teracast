package com.wemakestuff.podstuff.model.navigation.listener;

import com.wemakestuff.podstuff.model.api.Podcast;

public interface OnPodcastClickListener {
    public enum Action {
        PLAY, ICON
    }

    public void onPodcastClick(Podcast podcast, Action action);
}
