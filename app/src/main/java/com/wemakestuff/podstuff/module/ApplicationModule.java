package com.wemakestuff.podstuff.module;

import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.BaseApplication;
import com.wemakestuff.podstuff.bus.MainThreadBus;
import com.wemakestuff.podstuff.ui.EpisodeListActivity;
import com.wemakestuff.podstuff.ui.PodcastListActivity;
import com.wemakestuff.podstuff.ui.podcasts.PodcastListFragment;
import com.wemakestuff.podstuff.ui.podcasts.PodcastsFragmentActivity;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module(complete = false, injects = {BaseApplication.class, EpisodeListActivity.class, PodcastListActivity.class, PodcastsFragmentActivity.class, PodcastListFragment.class}, library = true)
public class ApplicationModule {
	@Singleton
	@Provides
	Bus provideOttoBus() {
		return new MainThreadBus(new Bus());
	}
}