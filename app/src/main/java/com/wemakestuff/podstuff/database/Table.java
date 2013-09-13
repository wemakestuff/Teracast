package com.wemakestuff.podstuff.database;

import com.wemakestuff.podstuff.database.annotations.Authority;
import com.wemakestuff.podstuff.rss.model.*;

@Authority(RssContract.CONTENT_AUTHORITY)
public enum Table {

    ENCLOSURE(RssEnclosure.class),
    FEED(RssFeed.class),
    GUID(RssGuid.class),
    IMAGE(RssImage.class),
    ITEM(RssItem.class),
	ITUNES_IMAGE(RssiTunesImage.class),
	MEDIA_CONTENT(RssMediaContent.class);

	private final Class<? extends BaseData> clazz;

	Table(Class<? extends BaseData> clazz) {
		this.clazz = clazz;
	}

	public Class<? extends BaseData> getType() {
		return clazz;
	}
}
