package com.wemakestuff.podstuff.database;

import android.net.Uri;

public class RssContract {
    public static final String CONTENT_AUTHORITY = "com.wemakestuff.podstuff";
    public static final int    BASE              = 1;

    public static final int FEED    = 100;
    public static final int FEED_ID = 101;

    public static final int ENCLOSURE    = 200;
    public static final int ENCLOSURE_ID = 201;

    public static final int GUID    = 300;
    public static final int GUID_ID = 301;

    public static final int IMAGE    = 400;
    public static final int IMAGE_ID = 401;

    public static final int ITEM    = 500;
    public static final int ITEM_ID = 501;

    public static final int ITUNES_IMAGE    = 600;
    public static final int ITUNES_IMAGE_ID = 601;

    public static final int MEDIA_CONTENT    = 700;
    public static final int MEDIA_CONTENT_ID = 701;

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ENCLOSURE     = "enclosures";
    public static final String PATH_FEED          = "feeds";
    public static final String PATH_GUID          = "guids";
    public static final String PATH_IMAGE         = "images";
    public static final String PATH_ITEM          = "items";
    public static final String PATH_ITUNES_IMAGE  = "itunes_images";
    public static final String PATH_MEDIA_CONTENT = "media_contents";

    public static final String ENCLOSURES_CONTENT_TYPE      = "vnd.android.cursor.dir/vnd.podstuff.enclosures";
    public static final String ENCLOSURES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.podstuff.enclosures";

    public static final String FEED_CONTENT_TYPE               = "vnd.android.cursor.dir/vnd.podstuff.feed";
    public static final String FEED_CONTENT_ITEM_TYPE          = "vnd.android.cursor.item/vnd.podstuff.feed";

    public static final String GUID_CONTENT_TYPE               = "vnd.android.cursor.dir/vnd.podstuff.guid";
    public static final String GUID_CONTENT_ITEM_TYPE          = "vnd.android.cursor.item/vnd.podstuff.guid";

    public static final String IMAGE_CONTENT_TYPE              = "vnd.android.cursor.dir/vnd.podstuff.image";
    public static final String IMAGE_CONTENT_ITEM_TYPE         = "vnd.android.cursor.item/vnd.podstuff.image";

    public static final String ITEM_CONTENT_TYPE               = "vnd.android.cursor.dir/vnd.podstuff.item";
    public static final String ITEM_CONTENT_ITEM_TYPE          = "vnd.android.cursor.item/vnd.podstuff.item";

    public static final String ITUNES_IMAGE_CONTENT_TYPE       = "vnd.android.cursor.dir/vnd.podstuff.itunesimage";
    public static final String ITUNES_IMAGE_CONTENT_ITEM_TYPE  = "vnd.android.cursor.item/vnd.podstuff.itunesimage";

    public static final String MEDIA_CONTENT_CONTENT_TYPE      = "vnd.android.cursor.dir/vnd.podstuff.mediacontent";
    public static final String MEDIA_CONTENT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.podstuff.mediacontent";

//	public static class Posts implements PostColumns, BaseColumns {
//		public static final Uri    CONTENT_URI       = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POSTS).build();
//		public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/vnd.podstuff.postdata";
//		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.podstuff.postdata";
//
//		public static final String DEFAULT_SORT = BaseColumns._ID + " ASC";
//
//		public static final String[] GRIDVIEW_PROJECTION = new String[]{_ID, URL, THUMBNAIL};
//
//		public static Uri buildPostDataUri(long postNumber) {
//			return CONTENT_URI.buildUpon().appendPath(String.valueOf(postNumber)).build();
//		}
//	}
//
//	public static class Login implements LoginColumns, BaseColumns {
//		public static final Uri    CONTENT_URI       = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOGIN).build();
//		public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/vnd.podstuff.login";
//		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.podstuff.login";
//
//		public static final String DEFAULT_SORT = BaseColumns._ID + " ASC";
//
//		public static Uri buildLoginUri(String username) {
//			return CONTENT_URI.buildUpon().appendPath(username).build();
//		}
//	}
//
//	public static class RedditData implements RedditDataColumns, BaseColumns {
//		public static final Uri    CONTENT_URI       = BASE_CONTENT_URI.buildUpon().appendPath(PATH_REDDIT_DATA).build();
//		public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/vnd.podstuff.redditdata";
//		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.podstuff.redditdata";
//
//		public static final String DEFAULT_SORT = BaseColumns._ID + " ASC";
//
//		public static Uri buildPostDataUri(String modhash) {
//			return CONTENT_URI.buildUpon().appendPath(modhash).build();
//		}
//
//	}
//
//	public static class Subreddits implements SubredditColumns, BaseColumns {
//		public static final Uri    CONTENT_URI       = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SUBREDDITS).build();
//		public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/vnd.podstuff.subreddits";
//		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.podstuff.subreddits";
//
//		public static final String[] SUBREDDITS_PROJECTION = new String[]{_ID, DISPLAY_NAME};
//
//		public static final String DEFAULT_SORT = DISPLAY_NAME + " COLLATE NOCASE ASC";
//
//		public static Uri buildSubredditUri(String displayName) {
//			return CONTENT_URI.buildUpon().appendPath(displayName).build();
//		}
//
//	}
}
