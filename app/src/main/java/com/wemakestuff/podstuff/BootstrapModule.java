package com.wemakestuff.podstuff;

import android.accounts.AccountManager;
import android.content.Context;

import com.wemakestuff.podstuff.authenticator.BootstrapAuthenticatorActivity;
import com.wemakestuff.podstuff.authenticator.LogoutService;
import com.wemakestuff.podstuff.core.CheckIn;
import com.wemakestuff.podstuff.core.TimerService;
import com.wemakestuff.podstuff.ui.BootstrapTimerActivity;
import com.wemakestuff.podstuff.ui.CarouselActivity;
import com.wemakestuff.podstuff.ui.CheckInsListFragment;
import com.wemakestuff.podstuff.ui.ItemListFragment;
import com.wemakestuff.podstuff.ui.NewsActivity;
import com.wemakestuff.podstuff.ui.NewsListFragment;
import com.wemakestuff.podstuff.ui.UserActivity;
import com.wemakestuff.podstuff.ui.UserListFragment;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
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
                TimerService.class
        }

)
public class BootstrapModule  {

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
