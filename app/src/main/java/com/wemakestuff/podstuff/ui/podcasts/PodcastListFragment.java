package com.wemakestuff.podstuff.ui.podcasts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.otto.Bus;
import com.wemakestuff.podstuff.core.Constants;
import com.wemakestuff.podstuff.media.event.PlayItemEvent;
import com.wemakestuff.podstuff.model.api.Episode;
import com.wemakestuff.podstuff.model.api.Podcast;
import com.wemakestuff.podstuff.model.navigation.Item;
import com.wemakestuff.podstuff.model.navigation.listener.OnPodcastClickListener;
import com.wemakestuff.podstuff.ui.base.BaseListFragment;
import com.wemakestuff.podstuff.ui.widget.adapter.ItemAdapter;

import java.util.Date;

import javax.inject.Inject;

public class PodcastListFragment extends BaseListFragment<Item> implements OnPodcastClickListener {
    @Inject
    Bus mBus;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(getAdapter());
        hideProgressBar();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("No Podcasts Here, Sucka!");
    }

    protected ItemAdapter getAdapter() {
        return new ItemAdapter(getActivity(), getItems());
    }

    @Override
    public void onPodcastClick(Podcast podcast, Action action) {
        switch (action) {
            case ICON:
                Intent intent = new Intent(getActivity(), PodcastDetailFragmentActivity.class);
                intent.putExtra(Constants.Intent.EXTRA_PODCAST, podcast);
                startActivity(intent);
                break;
            case PLAY:
                mBus.post(new PlayItemEvent(new Episode("Episode 83 | HitTail & AuditShark: Integration Marketing, Logo Design, Survey Analysis and Leaky Funnels", "Mike and Rob discuss Integration Marketing, Logo Design, Survey Analysis and Leaky Funnels.<img src=\"http://feeds.feedburner.com/~r/StartupsForTheRestOfUs/~4/9oVMEIXh-1w\" height=\"1\" width=\"1\"/>", new Date(System.currentTimeMillis()), "http://www.project98.com/podcast/startups-for-the-rest-of-us-083.mp3", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg", 44236023L)));
                break;
        }

    }
}
