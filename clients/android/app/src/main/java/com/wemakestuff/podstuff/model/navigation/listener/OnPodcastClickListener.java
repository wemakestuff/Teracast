package com.wemakestuff.podstuff.model.navigation.listener;


import com.wemakestuff.podstuff.model.navigation.PodcastItem;

public interface OnPodcastClickListener {
    public enum Action {
        PLAY, ICON, ITEM, LONG_ITEM
    }

    public void onPodcastClick(PodcastItem podcastItem, Action action);
}
