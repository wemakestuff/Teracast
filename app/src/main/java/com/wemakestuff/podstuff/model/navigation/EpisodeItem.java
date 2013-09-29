package com.wemakestuff.podstuff.model.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.model.api.Episode;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class EpisodeItem implements Item {
    OnEpisodeClickListener mListener;
    Context mContext;
    Episode mEpisode;

    public EpisodeItem(Episode mEpisode, OnEpisodeClickListener mListener) {
        this.mEpisode = mEpisode;
        this.mListener = mListener;
    }

    @Override
    public View getView(Context context, View convertView, ViewGroup parent) {
        this.mContext = context;
        ViewHolder holder = null;

        if (convertView != null) {
            Object tempView = convertView.getTag();

            if (tempView instanceof ViewHolder) {
                holder = (ViewHolder) tempView;
            }
        }

        if (holder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.episode_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.title.setText(mEpisode.getTitle());
        holder.subtitle.setText(mEpisode.getDescription());

        return convertView;
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
        public void onMore(ImageButton button) {
            if (mListener != null) {
                mListener.onEpisodeClick(mEpisode, OnEpisodeClickListener.Action.MORE);
            }

            if (moreLayout.getVisibility() == View.GONE) {
                moreLayout.setVisibility(View.VISIBLE);
            } else {
                moreLayout.setVisibility(View.GONE);
            }
        }

        @OnClick(R.id.b_play)
        public void onPlay(Button button) {
            if (mListener != null) {
                mListener.onEpisodeClick(mEpisode, OnEpisodeClickListener.Action.MORE_PLAY);
            }
        }
    }
}
