package com.wemakestuff.podstuff.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;
import com.squareup.picasso.Picasso;
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.rss.model.RssEnclosure;
import com.wemakestuff.podstuff.rss.model.RssFeed;
import com.wemakestuff.podstuff.rss.model.RssItem;
import com.wemakestuff.podstuff.rss.model.RssMediaContent;
import com.wemakestuff.podstuff.util.ListUtils;

public class FeedItemListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private RssFeed        mFeed;

	public FeedItemListAdapter(LayoutInflater mInflater, RssFeed mFeed) {
		this.mInflater = mInflater;
		this.mFeed = mFeed;
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

		RssItem item = mFeed.getRssItems().get(position);

		long length = 0;
		RssEnclosure enclosure = item.getEnclosure();
		RssMediaContent mediaContent = item.getMediaContent();

		if (enclosure != null && enclosure.getLength() > 0) {
			length = enclosure.getLength();
		} else if (mediaContent != null && mediaContent.getFileSize() > 0) {
			length = mediaContent.getFileSize();
		}

		holder.episodeTitle.setText(item.getTitle());
		holder.episodeDate.setText(item.getPubDate());
		holder.length.setText("Length: " + item.getiTunesDuration());

		Picasso.with(mInflater.getContext())
		       .load(mFeed.getiTunesImage().getHref())
		       .placeholder(R.drawable.ic_contact_picture)
		       .error(R.drawable.ic_contact_picture)
		       .into(holder.podcastIcon);

		return view;
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