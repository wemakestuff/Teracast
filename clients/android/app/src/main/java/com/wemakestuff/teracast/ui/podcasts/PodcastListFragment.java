package com.wemakestuff.teracast.ui.podcasts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.squareup.otto.Bus;
import com.wemakestuff.teracast.core.Constants;
import com.wemakestuff.teracast.media.event.PlayItemEvent;
import com.wemakestuff.teracast.model.navigation.Item;
import com.wemakestuff.teracast.model.navigation.PodcastItem;
import com.wemakestuff.teracast.model.navigation.listener.OnPodcastClickListener;
import com.wemakestuff.teracast.ui.base.BaseListFragment;
import com.wemakestuff.teracast.ui.widget.adapter.ItemAdapter;
import com.wemakestuff.teracast.ui.widget.adapter.PodcastListPagerAdapter;

import javax.inject.Inject;

public class PodcastListFragment extends BaseListFragment<Item> implements OnPodcastClickListener {
    @Inject
    Bus mBus;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(getAdapter());
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Subtract one to account for the header.
                onPodcastClick((PodcastItem) getItems().get(position - 1), Action.ITEM);
            }
        });

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                onPodcastClick((PodcastItem) getItems().get(position - 1), Action.LONG_ITEM);
                return true;
            }
        });
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
    public void onPodcastClick(PodcastItem podcastItem, Action action) {
        Intent intent;
        switch (action) {
            case ITEM:
                intent = new Intent(getActivity(), PodcastDetailFragmentActivity.class);
                intent.putExtra(Constants.Intent.EXTRA_PODCAST, podcastItem.getPodcast());
                startActivity(intent);
                break;
            case ICON:
                intent = new Intent(getActivity(), PodcastDetailFragmentActivity.class);
                intent.putExtra(Constants.Intent.EXTRA_PODCAST, podcastItem.getPodcast());
                startActivity(intent);
                break;
            case PLAY:
                mBus.post(new PlayItemEvent(PodcastListPagerAdapter.getEpisodes().get(0)));
                break;
        }
    }
}
