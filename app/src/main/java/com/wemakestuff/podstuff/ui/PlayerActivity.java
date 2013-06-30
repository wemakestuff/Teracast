package com.wemakestuff.podstuff.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.InjectView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.core.Media;
import com.wemakestuff.podstuff.media.event.PlayItemPlaybackEvent;
import com.wemakestuff.podstuff.rss.Item;
import com.wemakestuff.podstuff.rss.RssFeed;
import com.wemakestuff.podstuff.service.MediaService;
import com.wemakestuff.podstuff.service.RssFeedService;

import javax.inject.Inject;

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
	@InjectView(R.id.ib_previous)
	protected ImageButton previous;
	@InjectView(R.id.ib_rewind)
	protected ImageButton rewind;
	@InjectView(R.id.ib_play_pause)
	protected ImageButton playPause;
	@InjectView(R.id.ib_forward)
	protected ImageButton forward;
	@InjectView(R.id.ib_next)
	protected ImageButton next;
	@InjectView(R.id.sp_play_speed)
	protected Spinner     playSpeed;
	@Inject
	protected Bus         BUS;
	private RssFeed feed = null;

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
				Item mediaItem = feed.getItems().get(0);

				producePlayItemEvent(mediaItem);
				podcastTitle.setText(feed.getTitle());
				episodeTitle.setText(mediaItem.getTitle());
				episodeDescription.setText(mediaItem.getDescription());
			}
		});
	}

	@Subscribe
	public void rssFeedAvailable(RssFeed feed) {
		Log.i(TAG, "Received RSS feed!");
		this.feed = feed;
	}

	/**
	 * Posts a {@link PlayItemPlaybackEvent} message to the {@link Bus}
	 */
	private void producePlayItemEvent(Item mediaItem) {
		BUS.post(new PlayItemPlaybackEvent(mediaItem));
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
	}
}
