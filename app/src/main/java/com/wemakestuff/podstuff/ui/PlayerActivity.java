package com.wemakestuff.podstuff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.core.Constants;
import com.wemakestuff.podstuff.media.event.PlayItemEvent;
import com.wemakestuff.podstuff.media.event.ProvideMediaProgressEvent;
import com.wemakestuff.podstuff.media.event.ProvideMediaServiceStateEvent;
import com.wemakestuff.podstuff.media.event.RelativeSeekEvent;
import com.wemakestuff.podstuff.media.event.RequestMediaServiceStateEvent;
import com.wemakestuff.podstuff.media.event.SeekEvent;
import com.wemakestuff.podstuff.media.event.ToggleEvent;
import com.wemakestuff.podstuff.provider.FeedProvider;
import com.wemakestuff.podstuff.rss.model.RssItem;
import com.wemakestuff.podstuff.service.MediaService;
import com.wemakestuff.podstuff.util.ConversionUtils;

import javax.inject.Inject;

import butterknife.InjectView;

public class PlayerActivity extends BootstrapActivity {
    public static final String TAG = PlayerActivity.class.getSimpleName();
    protected RssItem      playingItem;
    @InjectView(R.id.iv_podcast_icon)
    protected ImageView    podcastIcon;
    @InjectView(R.id.tv_episode_title)
    protected TextView     episodeTitle;
    @InjectView(R.id.tv_episode_description)
    protected TextView     episodeDescription;
    @InjectView(R.id.sb_progress)
    protected SeekBar      progress;
    @InjectView(R.id.tv_current_position)
    protected TextView     currentPosition;
    @InjectView(R.id.tv_length)
    protected TextView     itemLength;
    @InjectView(R.id.ib_previous)
    protected ImageButton  previous;
    @InjectView(R.id.ib_rewind)
    protected ImageButton  rewind;
    @InjectView(R.id.ib_play_pause)
    protected ImageButton  playPause;
    @InjectView(R.id.ib_fast_forward)
    protected ImageButton  fastForward;
    @InjectView(R.id.ib_next)
    protected ImageButton  next;
    @Inject
    protected Bus          bus;
    @Inject
    protected FeedProvider feedProvider;

    private MediaService.State mMediaServiceState = MediaService.State.Stopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

        setTitle(getString(R.string.player));
        startService(new Intent(this, MediaService.class));

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (mMediaServiceState) {
                    case Playing:
                        produceToggleEvent();
                        break;
                    case Paused:
                        produceToggleEvent();
                        break;
                    default:
                        episodeTitle.setText(playingItem.getTitle());
                        episodeDescription.setText(playingItem.getDescription());
                        producePlayItemEvent(playingItem);
                        break;
                }

            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                produceRewindEvent();
            }
        });

        fastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                produceFastForwardEvent();
            }
        });

        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                //We only care about input coming from the user.
				if (fromUser) {
					produceSeekEvent(progress);
				}
			}

			@Override
			public void onStartTrackingTouch(final SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(final SeekBar seekBar) {

			}
		});
	}

	private void updateViews() {
		if (playingItem != null) {
			Picasso.with(this)
			       .load("http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_300x300.jpg")
			       .placeholder(R.drawable.ic_contact_picture)
			       .error(R.drawable.ic_contact_picture)
			       .into(podcastIcon);
			episodeTitle.setText(playingItem.getTitle());
			episodeDescription.setText(playingItem.getDescription());
		}
	}

	/**
	 * Handles a {@link com.wemakestuff.podstuff.media.event.ProvideMediaServiceStateEvent} message to the {@link Bus}
	 */
	@Subscribe
	public void onProvideMediaServiceStateEvent(ProvideMediaServiceStateEvent mediaServiceStateEvent) {
		mMediaServiceState = mediaServiceStateEvent.state;
		switch (mediaServiceStateEvent.state) {
			case Paused:
				Picasso.with(this).load(R.drawable.ic_media_play).into(playPause);
				break;
			case Playing:
				Picasso.with(this).load(R.drawable.ic_media_pause).into(playPause);
				break;
			case Preparing:
				Picasso.with(this).load(R.drawable.ic_media_play).into(playPause);
				break;
			case Stopped:
				Picasso.with(this).load(R.drawable.ic_media_play).into(playPause);
				break;
		}
		playingItem = mediaServiceStateEvent.rssItem;
	}

	/**
	 * Handles a {@link com.wemakestuff.podstuff.media.event.ProvideMediaProgressEvent} message to the {@link Bus}
	 */
	@Subscribe
	public void onProvideMediaProgressEvent(ProvideMediaProgressEvent mediaProgressEvent) {
		progress.setProgress(mediaProgressEvent.progress);
		progress.setMax(mediaProgressEvent.max);
		currentPosition.setText(ConversionUtils.formatMilliseconds(mediaProgressEvent.progress));
		itemLength.setText(ConversionUtils.formatMilliseconds(mediaProgressEvent.max));
	}

	/**
	 * Posts a {@link RequestMediaServiceStateEvent} message to the {@link Bus}
	 */
	private void produceRequestMediaServiceStateEvent() {
		bus.post(new RequestMediaServiceStateEvent());
	}

	/**
	 * Posts a {@link PlayItemEvent} message to the {@link Bus}
	 */
	private void producePlayItemEvent(RssItem rssItem) {
		bus.post(new PlayItemEvent(rssItem));
	}

	/**
	 * Posts a {@link ToggleEvent} message to the {@link Bus}
	 */
	private void produceToggleEvent() {
		bus.post(new ToggleEvent());
	}

	/**
	 * Posts a {@link RelativeSeekEvent} message to the {@link Bus} which advances the playback by {@link
	 * Constants#SEEK_AMOUNT}
	 *
	 * @see #produceRelativeSeekEvent(int)
	 */
	private void produceFastForwardEvent() {
		produceRelativeSeekEvent(Constants.SEEK_AMOUNT);
	}

	/**
	 * Posts a {@link RelativeSeekEvent} message to the {@link Bus} which reverses the playback by {@link
	 * Constants#SEEK_AMOUNT}
	 *
	 * @see #produceRelativeSeekEvent(int)
	 */
	private void produceRewindEvent() {
		produceRelativeSeekEvent(-1 * Constants.SEEK_AMOUNT);
	}

	/**
	 * Posts a {@link SeekEvent} message to the {@link Bus}
	 */
	private void produceSeekEvent(int seekTo) {
		bus.post(new SeekEvent(seekTo));
	}

	/**
	 * Posts a {@link RelativeSeekEvent} message to the {@link Bus}
	 */
	private void produceRelativeSeekEvent(int seekAmount) {
		bus.post(new RelativeSeekEvent(seekAmount));
	}

	@Override
	protected void onPause() {
		super.onPause();
		bus.unregister(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		bus.register(this);
		produceRequestMediaServiceStateEvent();
		updateViews();
	}

}
