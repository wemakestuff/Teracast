package com.wemakestuff.teracast.ui.widget.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wemakestuff.teracast.Injector;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.media.event.ProvideMediaProgressEvent;
import com.wemakestuff.teracast.media.event.ProvideMediaServiceStateEvent;
import com.wemakestuff.teracast.media.event.RequestMediaServiceStateEvent;
import com.wemakestuff.teracast.media.event.ToggleEvent;
import com.wemakestuff.teracast.model.api.Episode;
import com.wemakestuff.teracast.service.MediaService;
import com.wemakestuff.teracast.ui.PlayerActivity;
import com.wemakestuff.teracast.util.ConversionUtils;
import com.wemakestuff.teracast.util.Ln;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class CurrentlyPlayingView {
    Context mContext;
    Episode mEpisode;
    Integer mProgress;
    Integer mLength;
    MediaService.State mMediaServiceState = MediaService.State.Stopped;
    ViewHolder mHolder;
    @Inject
    Bus mBus;

    public CurrentlyPlayingView(Context mContext, View view) {
        this.mContext = mContext;
        Injector.inject(this);
        mHolder = new ViewHolder(view);
        forceUpdateViews();
    }

    public void register() {
        mBus.register(this);
    }

    public void unregister() {
        mBus.unregister(this);
    }

    public void forceUpdateViews() {
        produceRequestMediaServiceStateEvent();
        updateView();
        updateProgress();
    }

    /**
     * Posts a {@link com.wemakestuff.teracast.media.event.RequestMediaServiceStateEvent} message to the {@link Bus}
     */
    private void produceRequestMediaServiceStateEvent() {
        mBus.post(new RequestMediaServiceStateEvent());
    }

    /**
     * Handles a {@link com.wemakestuff.teracast.media.event.ProvideMediaServiceStateEvent} message to the {@link Bus}
     */
    @Subscribe
    public void onProvideMediaServiceStateEvent(ProvideMediaServiceStateEvent mediaServiceStateEvent) {
        mMediaServiceState = mediaServiceStateEvent.state;
        mEpisode = mediaServiceStateEvent.episode;
        if (mMediaServiceState != null && mEpisode != null) {
            Ln.d("Got Media Service State of %s for %s", mMediaServiceState.toString(), mEpisode.getTitle());
            updateView();
        }
    }

    /**
     * Handles a {@link com.wemakestuff.teracast.media.event.ProvideMediaProgressEvent} message to the {@link Bus}
     */
    @Subscribe
    public void onProvideMediaProgressEvent(ProvideMediaProgressEvent mediaProgressEvent) {
        mProgress = mediaProgressEvent.progress;
        mLength = mediaProgressEvent.max;
        if (mProgress != null && mLength != null) {
            Ln.d("Got Media Progress %s listened of %s", ConversionUtils.formatMilliseconds(mProgress), ConversionUtils.formatMilliseconds(mLength));
            updateProgress();
        }
    }

    private void updateProgress() {
        String tempLength = "";

        //When the Media Player is preparing it sends back a large number for the maximum length, want to suppress that.
        if (mProgress != null && mLength != null && mMediaServiceState != MediaService.State.Preparing) {
            tempLength = ConversionUtils.formatMilliseconds(mProgress) + "/" + ConversionUtils.formatMilliseconds(mLength);
        } else {
            tempLength += "0:00:00/0:42:42";
        }
        mHolder.length.setText(tempLength);
    }

    private void updateView() {
        if (mMediaServiceState == null || mMediaServiceState == MediaService.State.Stopped) {
            if (mHolder != null && mHolder.wrapper != null) {
                mHolder.wrapper.setVisibility(View.GONE);
            }
        } else if (mHolder != null && mMediaServiceState != MediaService.State.Preparing) {
            switch (mMediaServiceState) {
                case Paused:
                    Picasso.with(mContext).load(R.drawable.ic_media_play).into(mHolder.play);
                    break;
                case Playing:
                    Picasso.with(mContext).load(R.drawable.ic_media_pause).into(mHolder.play);
                    break;
                case Preparing:
                    Picasso.with(mContext).load(R.drawable.ic_media_play).into(mHolder.play);
                    break;
            }

            Picasso.with(mContext)
                    .load(mEpisode.getIconUrl())
                    .into(mHolder.icon);

            mHolder.title.setText(mEpisode.getTitle());
            mHolder.wrapper.setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder {
        @InjectView(R.id.rl_currently_playing)
        RelativeLayout wrapper;
        @InjectView(R.id.ib_icon)
        ImageButton icon;
        @InjectView(R.id.tv_title)
        TextView title;
        @InjectView(R.id.tv_length)
        TextView length;
        @InjectView(R.id.ib_play)
        ImageButton play;

        ViewHolder(View view) {
            Views.inject(this, view);
        }

        @OnClick(R.id.rl_currently_playing)
        void showPlayer() {
            Intent intent = new Intent(mContext, PlayerActivity.class);
            mContext.startActivity(intent);
        }

        /**
         * Posts a {@link com.wemakestuff.teracast.media.event.ToggleEvent} message to the {@link Bus}
         */
        @OnClick(R.id.ib_play)
        void produceToggleEvent() {
            mBus.post(new ToggleEvent());
        }
    }
}
