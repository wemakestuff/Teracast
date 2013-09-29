package com.wemakestuff.podstuff.ui.podcasts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.model.api.Episode;
import com.wemakestuff.podstuff.model.api.Podcast;
import com.wemakestuff.podstuff.model.navigation.Item;
import com.wemakestuff.podstuff.model.navigation.listener.OnEpisodeClickListener;
import com.wemakestuff.podstuff.ui.base.BaseListFragment;
import com.wemakestuff.podstuff.ui.widget.adapter.ItemAdapter;

import butterknife.InjectView;
import butterknife.Views;

public class PodcastDetailEpisodeListFragment extends BaseListFragment<Item> implements OnEpisodeClickListener {
    Podcast mPodcast;

    public Podcast getPodcast() {
        return mPodcast;
    }

    public void setPodcast(Podcast mPodcast) {
        this.mPodcast = mPodcast;
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
        getListView().addHeaderView(headerView);

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
    public void onEpisodeClick(Episode episode, Action action) {

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
