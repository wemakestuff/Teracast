package com.wemakestuff.teracast.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import com.wemakestuff.teracast.Injector;
import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.media.event.PlayItemEvent;
import com.wemakestuff.teracast.rss.model.RssEnclosure;
import com.wemakestuff.teracast.rss.model.RssFeed;
import com.wemakestuff.teracast.rss.model.RssItem;
import com.wemakestuff.teracast.rss.model.RssMediaContent;
import com.wemakestuff.teracast.service.MediaService;
import com.wemakestuff.teracast.util.ListUtils;

import javax.inject.Inject;

public class FeedItemListAdapter extends BaseAdapter {
	@Inject
	protected Bus            bus;
	private   LayoutInflater mInflater;
	private   RssFeed        mFeed;

	public FeedItemListAdapter(LayoutInflater mInflater, RssFeed mFeed) {
		this.mInflater = mInflater;
		this.mFeed = mFeed;
        Injector.inject(this);
		mInflater.getContext().startService(new Intent(mInflater.getContext(), MediaService.class));
	}

	@Override
	public int getCount() {
		if (ListUtils.isEmpty(mFeed.getRssItems())) {
			return 0;
		}
		return mFeed.getRssItems().size();
	}

	@Override
	public RssItem getItem(final int position) {
		if (ListUtils.isEmpty(mFeed.getRssItems()) || ListUtils.isOutOfBounds(mFeed.getRssItems(), position)) {
			return null;
		}
		return mFeed.getRssItems().get(position);
	}

	@Override
	public long getItemId(final int position) {
		if (ListUtils.isEmpty(mFeed.getRssItems()) || ListUtils.isOutOfBounds(mFeed.getRssItems(), position)) {
			return 0;
		}
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view != null) {
			holder = (ViewHolder) view.getTag();
		} else {
			view = mInflater.inflate(R.layout.feed_item_list_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		if (ListUtils.isEmpty(mFeed.getRssItems()) || ListUtils.isOutOfBounds(mFeed.getRssItems(), position)) {
			return null;
		}

		final RssItem rssItem = mFeed.getRssItems().get(position);

		long length = 0;
		RssEnclosure enclosure = rssItem.getEnclosure();
		RssMediaContent mediaContent = rssItem.getMediaContent();

		if (enclosure != null && enclosure.getLength() > 0) {
			length = enclosure.getLength();
		} else if (mediaContent != null && mediaContent.getFileSize() > 0) {
			length = mediaContent.getFileSize();
		}

		holder.episodeTitle.setText(rssItem.getTitle());
		holder.episodeDate.setText(rssItem.getPubDate());
		holder.length.setText("Length: " + rssItem.getiTunesDuration());
		holder.play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				producePlayItemEvent(rssItem);
			}
		});

		Picasso.with(mInflater.getContext())
		       .load(mFeed.getiTunesImage().getHref())
		       .placeholder(R.drawable.ic_contact_picture)
		       .error(R.drawable.ic_contact_picture)
		       .into(holder.podcastIcon);

		return view;
	}

	/**
	 * Posts a {@link com.wemakestuff.teracast.media.event.PlayItemEvent} message to the {@link com.squareup.otto.Bus}
	 */
	private void producePlayItemEvent(RssItem rssItem) {
		bus.post(new PlayItemEvent(null));
	}

	static class ViewHolder {
		@InjectView(R.id.ib_podcast_icon)
		ImageButton podcastIcon;
		@InjectView(R.id.tv_episode_title)
		TextView    episodeTitle;
		@InjectView(R.id.tv_episode_date)
		TextView    episodeDate;
		@InjectView(R.id.tv_length)
		TextView    length;
		@InjectView(R.id.ib_play_pause)
		ImageButton play;
		@InjectView(R.id.cb_select)
		CheckBox    select;

		public ViewHolder(View view) {
			Views.inject(this, view);
		}
	}
}