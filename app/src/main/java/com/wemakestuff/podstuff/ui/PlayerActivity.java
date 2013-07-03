package com.wemakestuff.podstuff.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.InjectView;
import com.j256.ormlite.dao.Dao;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.core.ConversionUtils;
import com.wemakestuff.podstuff.core.Media;
import com.wemakestuff.podstuff.media.event.*;
import com.wemakestuff.podstuff.rss.Item;
import com.wemakestuff.podstuff.rss.RssFeed;
import com.wemakestuff.podstuff.service.MediaService;
import com.wemakestuff.podstuff.service.RssFeedService;

import javax.inject.Inject;
import java.sql.SQLException;

public class PlayerActivity extends BootstrapActivity {
	public static final String TAG = PlayerActivity.class.getSimpleName();
	protected Media       mediaItem;
	@InjectView(R.id.iv_podcast_icon)
	protected ImageView   podcastIcon;
	@InjectView(R.id.tv_podcast_title)
	protected TextView    podcastTitle;
	@InjectView(R.id.tv_episode_title)
	protected TextView    episodeTitle;
	@InjectView(R.id.tv_episode_description)
	protected TextView    episodeDescription;
	@InjectView(R.id.sb_progress)
	protected SeekBar     progress;
	@InjectView(R.id.tv_current_position)
	protected TextView currentPosition;
	@InjectView(R.id.tv_length)
	protected TextView length;
	@InjectView(R.id.ib_previous)
	protected ImageButton previous;
	@InjectView(R.id.ib_rewind)
	protected ImageButton rewind;
	@InjectView(R.id.ib_play_pause)
	protected ImageButton playPause;
	@InjectView(R.id.ib_fast_forward)
	protected ImageButton fastForward;
	@InjectView(R.id.ib_next)
	protected ImageButton next;
	@InjectView(R.id.sp_play_speed)
	protected Spinner     playSpeed;
	@Inject
	protected Bus         BUS;
	@Inject
	protected Dao dao;
	private RssFeed feed = null;
	private MediaService.State mMediaServiceState = MediaService.State.Stopped;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_player);

		getSupportActionBar().setHomeButtonEnabled(true);

		setTitle(getString(R.string.app_name));

		final Intent i = new Intent(this, MediaService.class);
		startService(i);

		RssFeedService.getRssFeed(getApplicationContext(), Uri.parse("http://feeds.feedburner.com/StartupsForTheRestOfUs"));

		playPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				switch (mMediaServiceState) {
					case Playing:
						produceTogglePlaybackEvent();
						break;
					case Paused:
						produceTogglePlaybackEvent();
						break;
					default:
						Item mediaItem = feed.getItems().get(0);
						podcastTitle.setText(feed.getTitle());
						episodeTitle.setText(mediaItem.getTitle());
						episodeDescription.setText(mediaItem.getDescription());
						producePlayItemEvent(mediaItem);
						break;
				}

			}
		});

		rewind.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				produceRewindPlaybackEvent();
			}
		});

		fastForward.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				produceFastForwardPlaybackEvent();
			}
		});

		progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
				//We only care about input coming from the user.
				if (fromUser) {
					produceSeekPlaybackEvent(progress);
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

	@Subscribe
	public void rssFeedAvailable(RssFeed feed) {
		Log.i(TAG, "Received RSS feed!");
		this.feed = feed;

		try {
			dao.create(feed.getRssFeedForDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Item mediaItem = feed.getItems().get(0);
		Picasso.with(this)
				.load(feed.getiTunesImage().getHref())
				.placeholder(R.drawable.ic_contact_picture)
				.error(R.drawable.ic_contact_picture)
				.into(podcastIcon);
	}

	/**
	 * Handles a {@link ProvideMediaServiceStateEvent} message to the {@link Bus}
	 */
	@Subscribe
	public void onProvideMediaServiceStateEvent(ProvideMediaServiceStateEvent mediaServiceState) {
		mMediaServiceState = mediaServiceState.state;
		switch (mediaServiceState.state) {
			case Paused:
				playPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_play));
				break;
			case Playing:
				playPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_pause));
				break;
			case Preparing:
				playPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_play));
				break;
			case Stopped:
				playPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_play));
				break;
		}
	}

	/**
	 * Handles a {@link ProvideMediaProgressEvent} message to the {@link Bus}
	 */
	@Subscribe
	public void onProvideMediaProgressEvent(ProvideMediaProgressEvent mediaProgressEvent) {
		progress.setProgress(mediaProgressEvent.progress);
		progress.setMax(mediaProgressEvent.max);
		currentPosition.setText(ConversionUtils.ConvertMillisecondsToMinutesSeconds(mediaProgressEvent.progress));
		length.setText(ConversionUtils.ConvertMillisecondsToMinutesSeconds(mediaProgressEvent.max));
	}

	/**
	 * Posts a {@link RequestMediaServiceStateEvent} message to the {@link Bus}
	 */
	private void produceRequestMediaServiceStateEvent() {
		BUS.post(new RequestMediaServiceStateEvent());
	}

	/**
	 * Posts a {@link PlayItemPlaybackEvent} message to the {@link Bus}
	 */
	private void producePlayItemEvent(Item mediaItem) {
		BUS.post(new PlayItemPlaybackEvent(mediaItem));
	}

	/**
	 * Posts a {@link PlayItemPlaybackEvent} message to the {@link Bus}
	 */
	private void producePausePlaybackEvent() {
		BUS.post(new PausePlaybackEvent());
	}

	/**
	 * Posts a {@link TogglePlaybackEvent} message to the {@link Bus}
	 */
	private void produceTogglePlaybackEvent() {
		BUS.post(new TogglePlaybackEvent());
	}

	/**
	 * Posts a {@link FastForwardPlaybackEvent} message to the {@link Bus}
	 */
	private void produceFastForwardPlaybackEvent() {
		BUS.post(new FastForwardPlaybackEvent());
	}

	/**
	 * Posts a {@link RewindPlaybackEvent} message to the {@link Bus}
	 */
	private void produceRewindPlaybackEvent() {
		BUS.post(new RewindPlaybackEvent());
	}

	/**
	 * Posts a {@link SeekPlaybackEvent} message to the {@link Bus}
	 */
	private void produceSeekPlaybackEvent(int seekTo) {
		BUS.post(new SeekPlaybackEvent(seekTo));
	}

	@Override
	protected void onPause() {
		super.onPause();
		BUS.unregister(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		BUS.register(this);
		produceRequestMediaServiceStateEvent();
	}
}