package com.wemakestuff.teracast.model.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.model.api.Episode;
import com.wemakestuff.teracast.model.navigation.listener.OnEpisodeClickListener;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class EpisodeItem implements Item {
    OnEpisodeClickListener mListener;
    Context mContext;
    Episode mEpisode;
    ViewHolder mHolder;

    public EpisodeItem(Episode mEpisode, OnEpisodeClickListener mListener) {
        this.mEpisode = mEpisode;
        this.mListener = mListener;
    }

    public Episode getEpisode() {
        return mEpisode;
    }

    @Override
    public View getView(Context context, View convertView, ViewGroup parent) {
        this.mContext = context;
        mHolder = null;

        if (convertView != null) {
            Object tempView = convertView.getTag();

            if (tempView instanceof ViewHolder) {
                mHolder = (ViewHolder) tempView;
            }
        }

        if (mHolder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.episode_list_item, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        }

        mHolder.title.setText(mEpisode.getTitle());
        mHolder.subtitle.setText(mEpisode.getDescription());

        return convertView;
    }

    public void toggleMore() {
        if (mHolder != null) {
            if (mHolder.moreLayout.getVisibility() == View.GONE) {
                mHolder.moreLayout.setVisibility(View.VISIBLE);
            } else {
                mHolder.moreLayout.setVisibility(View.GONE);
            }
        }
    }

    class ViewHolder {
        @InjectView(R.id.tv_title)
        TextView title;
        @InjectView(R.id.tv_subtitle)
        TextView subtitle;
        @InjectView(R.id.ib_more)
        ImageButton more;
        @InjectView(R.id.ll_more)
        LinearLayout moreLayout;
        @InjectView(R.id.b_play)
        Button play;

        ViewHolder(View view) {
            Views.inject(this, view);
        }

        @OnClick(R.id.ib_more)
        public void onMore() {
            if (mListener != null) {
                mListener.onEpisodeClick(EpisodeItem.this, OnEpisodeClickListener.Action.MORE);
            }
            toggleMore();
        }

        @OnClick(R.id.b_play)
        public void onPlay() {
            if (mListener != null) {
                mListener.onEpisodeClick(EpisodeItem.this, OnEpisodeClickListener.Action.MORE_PLAY);
            }
        }
    }
}
