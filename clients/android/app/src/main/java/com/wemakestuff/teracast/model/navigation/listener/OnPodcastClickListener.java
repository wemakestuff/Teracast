package com.wemakestuff.teracast.model.navigation.listener;


import com.wemakestuff.teracast.model.navigation.PodcastItem;

public interface OnPodcastClickListener {
    public enum Action {
        PLAY, ICON, ITEM, LONG_ITEM
    }

    public void onPodcastClick(PodcastItem podcastItem, Action action);
}
