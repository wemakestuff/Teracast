package com.wemakestuff.teracast.ui.podcasts;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.github.frankiesardo.icepick.annotation.Icicle;
import com.github.frankiesardo.icepick.bundle.Bundles;
import com.viewpagerindicator.TitlePageIndicator;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.core.Constants;
import com.wemakestuff.teracast.model.api.Podcast;
import com.wemakestuff.teracast.ui.base.BaseFragmentActivity;
import com.wemakestuff.teracast.ui.widget.adapter.PodcastDetailPagerAdapter;
import com.wemakestuff.teracast.ui.widget.view.CurrentlyPlayingView;

import butterknife.InjectView;
import butterknife.Views;

public class PodcastDetailFragmentActivity extends BaseFragmentActivity {
    @Icicle
    Podcast mPodcast;
    @InjectView(R.id.tpi_indicator)
    TitlePageIndicator indicator;
    @InjectView(R.id.vp_pages)
    ViewPager pager;
    @InjectView(R.id.rl_currently_playing)
    RelativeLayout currentlyPlayingView;
    CurrentlyPlayingView mPlayingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundles.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.view_pager);
        Views.inject(this);

        if (getIntent().hasExtra(Constants.Intent.EXTRA_PODCAST)) {
            mPodcast = getIntent().getParcelableExtra(Constants.Intent.EXTRA_PODCAST);
        }

        pager.setAdapter(new PodcastDetailPagerAdapter(this, getSupportFragmentManager(), mPodcast));
        indicator.setViewPager(pager);
        mPlayingView = new CurrentlyPlayingView(this, currentlyPlayingView);
    }

    @Override
    protected void onResume() {
        mPlayingView.register();
        mPlayingView.forceUpdateViews();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mPlayingView.unregister();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundles.saveInstanceState(this, outState);
    }
}
