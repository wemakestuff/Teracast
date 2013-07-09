package com.wemakestuff.podstuff;

import android.accounts.AccountManager;
import android.content.Context;
import android.media.AudioManager;
import com.j256.ormlite.dao.Dao;
import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.authenticator.BootstrapAuthenticatorActivity;
import com.wemakestuff.podstuff.authenticator.LogoutService;
import com.wemakestuff.podstuff.bus.MainThreadBus;
import com.wemakestuff.podstuff.core.TimerService;
import com.wemakestuff.podstuff.database.RssDatabase;
import com.wemakestuff.podstuff.rss.model.RssFeed;
import com.wemakestuff.podstuff.service.HttpService;
import com.wemakestuff.podstuff.service.MediaService;
import com.wemakestuff.podstuff.service.MusicIntentReceiver;
import com.wemakestuff.podstuff.service.RssFeedService;
import com.wemakestuff.podstuff.ui.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.sql.SQLException;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module
		(
				complete = false,

				injects = {
								  BootstrapApplication.class,
								  BootstrapAuthenticatorActivity.class,
								  FeedItemListActivity.class,
								  CarouselActivity.class,
								  BootstrapTimerActivity.class,
								  CheckInsListFragment.class,
								  NewsActivity.class,
								  NewsListFragment.class,
								  UserActivity.class,
								  UserListFragment.class,
								  TimerService.class,
								  PlayerActivity.class,
                                  MediaService.class,
                                  MusicIntentReceiver.class,
                                  HttpService.class,
								  RssFeedService.class,
								  RssFeed.class

				}

		)
public class BootstrapModule {

	@Singleton
	@Provides
	Bus provideOttoBus() {
		return new MainThreadBus(new Bus());
	}

	@Provides
	@Singleton
	LogoutService provideLogoutService(final Context context, final AccountManager accountManager) {
		return new LogoutService(context, accountManager);
	}

	@Provides
	AudioManager provideAudioManager(final Context context) {
		return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	}

	@Singleton
	@Provides
	Dao provideDao(final Context context) {
		try {
			return new RssDatabase(context).getRssDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
