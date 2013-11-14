package com.wemakestuff.teracast.ui.podcasts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.model.api.Podcast;
import com.wemakestuff.teracast.ui.base.BaseFragment;

import butterknife.InjectView;

public class PodcastDetailFragment extends BaseFragment {
    @InjectView(R.id.iv_icon)
    ImageView icon;
    @InjectView(R.id.tv_title)
    TextView title;
    @InjectView(R.id.tv_description)
    TextView description;
    Podcast mPodcast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.podcast_detail, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText(mPodcast.getTitle());
        description.setText(mPodcast.getDescription());
        Picasso.with(getActivity()).load(mPodcast.getImageUrl()).into(icon);
    }

    public Podcast getPodcast() {
        return mPodcast;
    }

    public void setPodcast(Podcast mPodcast) {
        this.mPodcast = mPodcast;
    }
}
