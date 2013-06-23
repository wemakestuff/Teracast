package com.wemakestuff.podstuff.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.InjectView;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.core.Media;
import com.wemakestuff.podstuff.service.MusicService;

import static com.wemakestuff.podstuff.core.Constants.Extra.MEDIA_ITEM;

public class PlayerActivity extends BootstrapActivity {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_player);

		if (getIntent() != null && getIntent().getExtras() != null) {
			mediaItem = (Media) getIntent().getExtras().getSerializable(MEDIA_ITEM);
		} else {
			mediaItem = new Media("Startups For the Rest of Us", "Episode 137 | Startup Feedback, Finding Better Consulting Gigs, and More Listener Questions", "http://www.vorbis.com/music/Epoq-Lepidoptera.ogg");
		}

		getSupportActionBar().setHomeButtonEnabled(true);

		setTitle(mediaItem.getTitle());

		podcastTitle.setText(mediaItem.getTitle());
		episodeTitle.setText(mediaItem.getContent());

		playPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				Intent i = new Intent(MusicService.ACTION_STREAM);
				Uri uri = Uri.parse(mediaItem.getObjectId());
				i.setData(uri);
				startService(i);
			}
		});

	}
}
