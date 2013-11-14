package com.wemakestuff.teracast.module;

import com.squareup.otto.Bus;
import com.wemakestuff.teracast.BaseApplication;
import com.wemakestuff.teracast.bus.MainThreadBus;
import com.wemakestuff.teracast.service.MusicIntentReceiver;
import com.wemakestuff.teracast.ui.EpisodeListActivity;
import com.wemakestuff.teracast.ui.LoginActivity;
import com.wemakestuff.teracast.ui.PlayerActivity;
import com.wemakestuff.teracast.ui.RegisterActivity;
import com.wemakestuff.teracast.ui.podcasts.PodcastDetailEpisodeListFragment;
import com.wemakestuff.teracast.ui.podcasts.PodcastDetailFragment;
import com.wemakestuff.teracast.ui.podcasts.PodcastDetailFragmentActivity;
import com.wemakestuff.teracast.ui.podcasts.PodcastListFragment;
import com.wemakestuff.teracast.ui.podcasts.PodcastsFragmentActivity;
import com.wemakestuff.teracast.ui.widget.view.CurrentlyPlayingView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module(complete = false, injects = {BaseApplication.class, LoginActivity.class, RegisterActivity.class, EpisodeListActivity.class, CurrentlyPlayingView.class, PodcastsFragmentActivity.class, PodcastListFragment.class, PodcastDetailFragmentActivity.class, PodcastDetailFragment.class, PodcastDetailEpisodeListFragment.class, PlayerActivity.class, MusicIntentReceiver.class}, library = true)
public class ApplicationModule {
    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new MainThreadBus(new Bus());
    }
}