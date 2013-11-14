package com.wemakestuff.teracast.ui.podcasts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.media.event.PlayItemEvent;
import com.wemakestuff.teracast.model.api.Episode;
import com.wemakestuff.teracast.model.api.Podcast;
import com.wemakestuff.teracast.model.navigation.EpisodeItem;
import com.wemakestuff.teracast.model.navigation.Item;
import com.wemakestuff.teracast.model.navigation.listener.OnEpisodeClickListener;
import com.wemakestuff.teracast.ui.base.BaseListFragment;
import com.wemakestuff.teracast.ui.widget.adapter.ItemAdapter;
import com.wemakestuff.teracast.ui.widget.adapter.PodcastListPagerAdapter;
import com.wemakestuff.teracast.util.Ln;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;

public class PodcastDetailEpisodeListFragment extends BaseListFragment<Item> implements OnEpisodeClickListener {
    @Inject
    Bus mBus;
    Podcast mPodcast;

    public Podcast getPodcast() {
        return mPodcast;
    }

    public void setPodcast(Podcast mPodcast) {
        this.mPodcast = mPodcast;
        List<Item> episodeList = new ArrayList<Item>();
        for (Episode episode : PodcastListPagerAdapter.getEpisodes()) {
            Ln.d(episode.getTitle());
            episodeList.add(new EpisodeItem(episode, this));
        }
        mItems = episodeList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("No Episodes Here, Sucka!");

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.podcast_detail_episode_list_header, null);
        HeaderViewHolder holder = new HeaderViewHolder(headerView);
        headerView.setTag(holder);
        holder.title.setText(mPodcast.getTitle());
        holder.description.setText(mPodcast.getDescription());
        Picasso.with(getActivity()).load(mPodcast.getImageUrl()).into(holder.icon);
        getListView().addHeaderView(headerView, null, false);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Subtract one to account for the header.
                onEpisodeClick((EpisodeItem) getItems().get(position - 1), Action.ITEM);
            }
        });

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                onEpisodeClick((EpisodeItem) getItems().get(position - 1), Action.LONG_ITEM);
                return true;
            }
        });

        setListAdapter(getAdapter());
        hideProgressBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        setListAdapter(null);
    }

    protected ItemAdapter getAdapter() {
        return new ItemAdapter(getActivity(), getItems());
    }

    @Override
    public void onEpisodeClick(EpisodeItem episodeItem, Action action) {
        Intent intent;
        switch (action) {
            case ITEM:
                mBus.post(new PlayItemEvent(episodeItem.getEpisode()));
                break;
            case LONG_ITEM:
                episodeItem.toggleMore();
                break;
            case PLAY:
                mBus.post(new PlayItemEvent(episodeItem.getEpisode()));
                break;
            case MORE_PLAY:
                mBus.post(new PlayItemEvent(episodeItem.getEpisode()));
                break;
            case MORE:
                break;
        }
    }

    class HeaderViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView icon;
        @InjectView(R.id.tv_title)
        TextView title;
        @InjectView(R.id.tv_description)
        TextView description;

        HeaderViewHolder(View view) {
            Views.inject(this, view);
        }
    }
}
