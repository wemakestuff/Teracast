package com.wemakestuff.podstuff.module;

import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.BaseApplication;
import com.wemakestuff.podstuff.bus.MainThreadBus;
import com.wemakestuff.podstuff.ui.ItemListActivity;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module(complete = false, injects = {BaseApplication.class, ItemListActivity.class}, library = true)
public class ApplicationModule {
	@Singleton
	@Provides
	Bus provideOttoBus() {
		return new MainThreadBus(new Bus());
	}
}