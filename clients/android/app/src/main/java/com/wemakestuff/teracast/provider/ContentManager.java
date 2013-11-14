package com.wemakestuff.teracast.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;
import com.wemakestuff.teracast.database.RssDatabase;
import com.wemakestuff.teracast.database.interfaces.DatabaseHelper;
import com.wemakestuff.teracast.rss.model.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

public class ContentManager {
	public static final Uri ENCLOSURE_URI     = getUri(RssEnclosure.ENTITY_PL);
	public static final Uri FEED_URI          = getUri(RssFeed.ENTITY_PL);
	public static final Uri GUID_URI          = getUri(RssGuid.ENTITY_PL);
	public static final Uri IMAGE_URI         = getUri(RssImage.ENTITY_PL);
	public static final Uri ITEM_URI          = getUri(RssItem.ENTITY_PL);
	public static final Uri ITUNES_IMAGE_URI  = getUri(RssITunesImage.ENTITY_PL);
	public static final Uri MEDIA_CONTENT_URI = getUri(RssMediaContent.ENTITY_PL);

	private static Map<ContentResolver, ContentManager> instances  = new HashMap<ContentResolver, ContentManager>();
	private static SimpleDateFormat                     dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DatabaseHelper helper;

	@Inject
	FeedProvider provider;

	@Inject
	RssDatabase database;

	@Inject
	public ContentManager(DatabaseHelper helper) {
		this.helper = helper;
		Log.i("ContentManager", helper == null ? "null" : "not null");
		Log.i("ContentManager", provider == null ? "null" : "not null");

	}

	public static Uri getUri(String uriPath) {
		return Uri.parse(ContentResolver.SCHEME_CONTENT + "://"
		                 + FeedProvider.AUTHORITY + "/" + uriPath);
	}

	public static ContentValues getContentValues(RssEnclosure enclosure, String[] projection) {
        ContentValues values = new ContentValues();
        Set<String> columns = new HashSet<String>(Arrays.asList(projection));
        if (columns.contains(RssEnclosure.URL))
            values.put(RssEnclosure.URL, enclosure.getUrl());
        if (columns.contains(RssEnclosure.LENGTH))
            values.put(RssEnclosure.LENGTH, enclosure.getLength());
        if (columns.contains(RssEnclosure.TYPE))
            values.put(RssEnclosure.TYPE, enclosure.getType());

        return values;
    }

    public static ContentValues getContentValues(RssFeed feed, String[] projection) {
        ContentValues values = new ContentValues();
        Set<String> columns = new HashSet<String>(Arrays.asList(projection));
        if (columns.contains(RssFeed.PUB_DATE))
            values.put(RssFeed.PUB_DATE, dateFormat.format(feed.getPubDate()));
        if (columns.contains(RssFeed.TITLE))
            values.put(RssFeed.TITLE, feed.getTitle());
        if (columns.contains(RssFeed.LINK))
            values.put(RssFeed.LINK, feed.getLink());
        if (columns.contains(RssFeed.DESCRIPTION))
            values.put(RssFeed.DESCRIPTION, feed.getDescription());
        if (columns.contains(RssFeed.COPYRIGHT))
            values.put(RssFeed.COPYRIGHT, feed.getCopyright());
        if (columns.contains(RssFeed.GENERATOR))
            values.put(RssFeed.GENERATOR, feed.getGenerator());
        if (columns.contains(RssFeed.LANGUAGE))
            values.put(RssFeed.LANGUAGE, feed.getLanguage());
        if (columns.contains(RssFeed.ITUNES_SUMMARY))
            values.put(RssFeed.ITUNES_SUMMARY, feed.getiTunesSummary());
        if (columns.contains(RssFeed.ITUNES_SUBTITLE))
            values.put(RssFeed.ITUNES_SUBTITLE, feed.getiTunesSubtitle());
        if (columns.contains(RssFeed.ITUNES_KEYWORDS))
            values.put(RssFeed.ITUNES_KEYWORDS, feed.getiTunesKeywords());
        if (columns.contains(RssFeed.ITUNES_AUTHOR))
            values.put(RssFeed.ITUNES_AUTHOR, feed.getiTunesAuthor());

//      if (columns.contains(RssFeed.ITUNES_IMAGE))
//            values.put(RssFeed.ITUNES_IMAGE, feed.getiTunesImage());
        if (columns.contains(RssFeed.ITUNES_EXPLICIT))
            values.put(RssFeed.ITUNES_EXPLICIT, feed.getiTunesExplicit());
        if (columns.contains(RssFeed.ITUNES_BLOCK))
            values.put(RssFeed.ITUNES_BLOCK, feed.getiTunesBlock());
//      if (columns.contains(RssFeed.RSS_IMAGE))
//            values.put(RssFeed.RSS_IMAGE, feed.getRssImage());
        if (columns.contains(RssFeed.LAST_BUILD_DATE))
            values.put(RssFeed.LAST_BUILD_DATE, feed.getLastBuildDate());
        if (columns.contains(RssFeed.CATEGORY))
            values.put(RssFeed.CATEGORY, feed.getCategory());
        if (columns.contains(RssFeed.TTL))
            values.put(RssFeed.TTL, feed.getTtl());
        if (columns.contains(RssFeed.DOCS))
            values.put(RssFeed.DOCS, feed.getDocs());
        if (columns.contains(RssFeed.MANAGING_EDITOR))
            values.put(RssFeed.MANAGING_EDITOR, feed.getManagingEditor());
//      if (columns.contains(RssFeed.RSS_ITEMS))
//            values.put(RssFeed.RSS_ITEMS, feed.getRssItems());

        return values;
    }

}
