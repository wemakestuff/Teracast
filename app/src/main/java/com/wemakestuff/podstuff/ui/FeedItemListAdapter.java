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
import com.wemakestuff.podstuff.R;
import com.wemakestuff.podstuff.rss.model.RssMediaContent;
import com.wemakestuff.podstuff.rss.model.RssEnclosure;
import com.wemakestuff.podstuff.rss.model.RssItem;
import com.wemakestuff.podstuff.util.ConversionUtils;
import com.wemakestuff.podstuff.util.ListUtils;

import java.util.List;

public class FeedItemListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<RssItem>  mItems;

	public FeedItemListAdapter(LayoutInflater mInflater, List<RssItem> mItems) {
		this.mInflater = mInflater;
		this.mItems = mItems;
	}

	@Override
	public int getCount() {
		if (ListUtils.isEmpty(mItems)) {
			return 0;
		}
		return mItems.size();
	}

	@Override
	public RssItem getItem(final int position) {
		if (ListUtils.isEmpty(mItems) || ListUtils.isOutOfBounds(mItems, position)) {
			return null;
		}
		return mItems.get(position);
	}

	@Override
	public long getItemId(final int position) {
		if (ListUtils.isEmpty(mItems) || ListUtils.isOutOfBounds(mItems, position)) {
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

		if (ListUtils.isEmpty(mItems) || ListUtils.isOutOfBounds(mItems, position)) {
			return null;
		}

		RssItem item = mItems.get(position);

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
		holder.length.setText(ConversionUtils.formatMilliseconds(length));

		return view;
	}

	static class ViewHolder {
		@InjectView(R.id.tv_episode_title)
		TextView    episodeTitle;
		@InjectView(R.id.tv_episode_date)
		TextView    episodeDate;
		@InjectView(R.id.tv_length)
		TextView    length;
		@InjectView(R.id.ib_play)
		ImageButton play;
		@InjectView(R.id.cb_select)
		CheckBox    select;

		public ViewHolder(View view) {
			Views.inject(this, view);
		}
	}
}