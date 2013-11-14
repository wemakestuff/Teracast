package com.wemakestuff.teracast.ui.widget.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wemakestuff.teracast.model.api.Podcast;
import com.wemakestuff.teracast.ui.podcasts.PodcastDetailEpisodeListFragment;
import com.wemakestuff.teracast.ui.podcasts.PodcastDetailFragment;
import com.wemakestuff.teracast.ui.podcasts.PodcastListFragment;
import com.wemakestuff.teracast.util.Ln;

public class PodcastDetailPagerAdapter extends FragmentPagerAdapter {
    Context mContext;
    Podcast mPodcast;

    public PodcastDetailPagerAdapter(Context mContext, FragmentManager fragmentManager, Podcast mPodcast) {
        super(fragmentManager);
        this.mContext = mContext;
        this.mPodcast = mPodcast;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PodcastDetailEpisodeListFragment episodeListFragment = new PodcastDetailEpisodeListFragment();
                episodeListFragment.setPodcast(mPodcast);
                return episodeListFragment;
            case 1:
                PodcastDetailFragment podcastDetailFragment = new PodcastDetailFragment();
                podcastDetailFragment.setPodcast(mPodcast);
                return podcastDetailFragment;
            case 2:
                PodcastListFragment podcastListFragment = new PodcastListFragment();
                podcastListFragment.setItems(PodcastListPagerAdapter.getPodcastItems(podcastListFragment));
                return podcastListFragment;
        }
        Ln.d("Returning Null");
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Episodes";
            case 1:
                return "Deets";
            case 2:
                return "Related Podcasts";
            default:
                return null;
        }
    }
}
