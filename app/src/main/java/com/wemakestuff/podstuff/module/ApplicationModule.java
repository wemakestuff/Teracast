package com.wemakestuff.podstuff.module;

import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.BaseApplication;
import com.wemakestuff.podstuff.bus.MainThreadBus;
import com.wemakestuff.podstuff.ui.EpisodeListActivity;
import com.wemakestuff.podstuff.ui.LoginActivity;
import com.wemakestuff.podstuff.ui.PlayerActivity;
import com.wemakestuff.podstuff.ui.RegisterActivity;
import com.wemakestuff.podstuff.ui.podcasts.PodcastDetailEpisodeListFragment;
import com.wemakestuff.podstuff.ui.podcasts.PodcastDetailFragment;
import com.wemakestuff.podstuff.ui.podcasts.PodcastDetailFragmentActivity;
import com.wemakestuff.podstuff.ui.podcasts.PodcastListFragment;
import com.wemakestuff.podstuff.ui.podcasts.PodcastsFragmentActivity;
import com.wemakestuff.podstuff.ui.widget.view.CurrentlyPlayingView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module(complete = false, injects = {BaseApplication.class, LoginActivity.class, RegisterActivity.class, EpisodeListActivity.class, CurrentlyPlayingView.class, PodcastsFragmentActivity.class, PodcastListFragment.class, PodcastDetailFragmentActivity.class, PodcastDetailFragment.class, PodcastDetailEpisodeListFragment.class, PlayerActivity.class}, library = true)
public class ApplicationModule {
    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new MainThreadBus(new Bus());
    }
}