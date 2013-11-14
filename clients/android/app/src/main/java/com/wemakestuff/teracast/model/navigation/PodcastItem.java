package com.wemakestuff.teracast.model.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.model.api.Podcast;
import com.wemakestuff.teracast.model.navigation.listener.OnPodcastClickListener;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class PodcastItem implements Item {
    Podcast mPodcast;
    OnPodcastClickListener mListener;

    public PodcastItem(Podcast mPodcast, OnPodcastClickListener mListener) {
        this.mPodcast = mPodcast;
        this.mListener = mListener;
    }

    public Podcast getPodcast() {
        return mPodcast;
    }

    public void setPodcast(Podcast mPodcast) {
        this.mPodcast = mPodcast;
    }

    @Override
    public View getView(Context context, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView != null) {
            Object tempView = convertView.getTag();
            if (tempView instanceof ViewHolder) {
                holder = (ViewHolder) tempView;
            }
        }

        if (holder == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.podcast_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Picasso.with(context).load(mPodcast.getIconUrl()).into(holder.icon);
        holder.title.setText(mPodcast.getTitle());
        holder.subtitle.setText(mPodcast.getDescription());

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.ib_icon)
        ImageButton icon;
        @InjectView(R.id.ib_play)
        ImageButton play;
        @InjectView(R.id.tv_title)
        TextView title;
        @InjectView(R.id.tv_subtitle)
        TextView subtitle;

        ViewHolder(View view) {
            Views.inject(this, view);
        }

        @OnClick(R.id.ib_icon)
        public void onIconClick(ImageButton button) {
            if (mListener != null) {
                mListener.onPodcastClick(PodcastItem.this, OnPodcastClickListener.Action.ICON);
            }
        }

        @OnClick(R.id.ib_play)
        public void onPlayClick(ImageButton button) {
            if (mListener != null) {
                mListener.onPodcastClick(PodcastItem.this, OnPodcastClickListener.Action.PLAY);
            }
        }
    }
}
