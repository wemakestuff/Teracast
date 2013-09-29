package com.wemakestuff.podstuff.module;

import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioManager;
import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.BootstrapApplication;
import com.wemakestuff.podstuff.bus.MainThreadBus;
import com.wemakestuff.podstuff.database.RssDatabase;
import com.wemakestuff.podstuff.database.interfaces.DatabaseHelper;
import com.wemakestuff.podstuff.provider.ContentManager;
import com.wemakestuff.podstuff.provider.FeedProvider;
import com.wemakestuff.podstuff.rss.model.RssFeed;
import com.wemakestuff.podstuff.service.HttpService;
import com.wemakestuff.podstuff.service.MediaService;
import com.wemakestuff.podstuff.service.MusicIntentReceiver;
import com.wemakestuff.podstuff.service.RssFeedService;
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
								  FeedItemListActivity.class,
								  FeedItemListAdapter.class,
								  PlayerActivity.class,
                                  MediaService.class,
                                  MusicIntentReceiver.class,
                                  HttpService.class,
								  RssFeedService.class,
								  RssFeed.class,
								  LoginActivity.class,
								  RegisterActivity.class,
								  PodcastsActivity.class,
                                  ContentManager.class

				}

		)
public class ApplicationModule {
//    private final Context appContext;
//
//    public BootstrapModule(Context context) {
//        this.appContext = context;
//    }
//
//    @Provides
//    Context provideContext() {
//        return appContext;
//    }

    @Singleton
    @Provides
    ContentResolver resolver(final Context context) {
        return context.getContentResolver();
    }

	@Singleton
	@Provides
	Bus provideOttoBus() {
		return new MainThreadBus(new Bus());
	}

    @Singleton
    @Provides
    FeedProvider provideFeedProvider(final ContentResolver resolver) {
        return (FeedProvider) resolver.acquireContentProviderClient(FeedProvider.AUTHORITY).getLocalContentProvider();
    }

	@Provides
	AudioManager provideAudioManager(final Context context) {
		return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	}

    @Singleton
    @Provides
    RssDatabase provideRssDatabase(final Context context) {
        return new RssDatabase(context);
    }

    @Provides
    DatabaseHelper providerDatabaseHelper(final FeedProvider provider) {
        return provider.getHelper();
    }

    @Provides
    ContentManager provideContentManager(final DatabaseHelper helper) {
        return new ContentManager(helper);
    }

//	@Singleton
//	@Provides
//    FeedProvider provideFeedProvider(ContentResolver resolver) {
//        FeedProvider provider = (FeedProvider) resolver.acquireContentProviderClient(FeedProvider.AUTHORITY).getLocalContentProvider();
//        assert (provider != null);
//
//        return provider;
//	}

//	@Singleton
//	@Provides
//    Dao<RssFeed, Integer> provideDao(final Context context) {
//		try {
//			return new RssDatabase().getRssDao(RssFeed.class);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Singleton
//	@Provides
//	Dao<RssEnclosure, Integer> provideEnclosureDao(final Context context) {
//		try {
//			return new RssDatabase(context).getRssDao(RssEnclosure.class);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Singleton
//	@Provides
//	Dao<RssGuid, Integer> provideRssGuidDao(final Context context) {
//		try {
//			return new RssDatabase(context).getRssDao(RssGuid.class);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Singleton
//	@Provides
//	Dao<RssImage, Integer> provideRssImageDao(final Context context) {
//		try {
//			return new RssDatabase(context).getRssDao(RssImage.class);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Singleton
//	@Provides
//	Dao<RssItem, Integer> provideRssItemDao(final Context context) {
//		try {
//			return new RssDatabase(context).getRssDao(RssItem.class);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Singleton
//	@Provides
//	Dao<RssMediaContent, Integer> provideRssMediaContentDao(final Context context) {
//		try {
//			return new RssDatabase(context).getRssDao(RssMediaContent.class);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Singleton
//	@Provides
//	Dao<RssITunesImage, Integer> provideITunesImage(final Context context) {
//		try {
//			return new RssDatabase(context).getRssDao(RssITunesImage.class);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
