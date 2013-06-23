package com.wemakestuff.podstuff;

import android.accounts.AccountManager;
import android.content.Context;
import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.authenticator.BootstrapAuthenticatorActivity;
import com.wemakestuff.podstuff.authenticator.LogoutService;
import com.wemakestuff.podstuff.core.TimerService;
import com.wemakestuff.podstuff.ui.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module
		(
				complete = false,

				injects = {
								  BootstrapApplication.class,
								  BootstrapAuthenticatorActivity.class,
								  CarouselActivity.class,
								  BootstrapTimerActivity.class,
								  CheckInsListFragment.class,
								  NewsActivity.class,
								  NewsListFragment.class,
								  UserActivity.class,
								  UserListFragment.class,
								  TimerService.class,
								  PlayerActivity.class
				}

		)
public class BootstrapModule {

	@Singleton
	@Provides
	Bus provideOttoBus() {
		return new Bus();
	}

	@Provides
	@Singleton
	LogoutService provideLogoutService(final Context context, final AccountManager accountManager) {
		return new LogoutService(context, accountManager);
	}

}
