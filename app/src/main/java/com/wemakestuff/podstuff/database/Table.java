package com.wemakestuff.podstuff.database;

import com.wemakestuff.podstuff.database.annotations.Authority;
import com.wemakestuff.podstuff.rss.model.BaseData;
import com.wemakestuff.podstuff.rss.model.RssEnclosure;
import com.wemakestuff.podstuff.rss.model.RssFeed;
import com.wemakestuff.podstuff.rss.model.RssGuid;
import com.wemakestuff.podstuff.rss.model.RssITunesImage;
import com.wemakestuff.podstuff.rss.model.RssImage;
import com.wemakestuff.podstuff.rss.model.RssItem;
import com.wemakestuff.podstuff.rss.model.RssMediaContent;

@Authority(RssContract.CONTENT_AUTHORITY)
public enum Table {

    ENCLOSURE(RssEnclosure.class),
    FEED(RssFeed.class),
    GUID(RssGuid.class),
    IMAGE(RssImage.class),
    ITEM(RssItem.class),
    ITUNES_IMAGE(RssITunesImage.class),
    MEDIA_CONTENT(RssMediaContent.class);

    private final Class<? extends BaseData> clazz;

    Table(Class<? extends BaseData> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends BaseData> getType() {
        return clazz;
    }
}
