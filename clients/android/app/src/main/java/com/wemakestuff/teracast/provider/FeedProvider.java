package com.wemakestuff.teracast.provider;

import com.wemakestuff.teracast.database.RssDatabase;
import com.wemakestuff.teracast.database.interfaces.DatabaseHelper;
import com.wemakestuff.teracast.rss.model.*;

import javax.inject.Inject;

public class FeedProvider extends AbstractFeedProvider {
	//    private static final UriMatcher mUriMatcher = buildUriMatcher();
	private static final String TAG = FeedProvider.class.getSimpleName();
	private RssDatabase mDatabase;

	public static final String AUTHORITY = "com.wemakestuff.teracast.content";

	private static final String DATABASE_NAME    = "feeder.db";
	private static final int    DATABASE_VERSION = 10;

	@SuppressWarnings("rawtypes")
	public static final Class[] CLASSES = {RssEnclosure.class, RssFeed.class, RssGuid.class, RssImage.class, RssItem.class, RssITunesImage.class, RssMediaContent.class};

	private DatabaseHelper helper = null;

	@Inject
	public FeedProvider() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCreate() {
		helper = new RssDatabase(getContext());
		super.setHelper(helper);
		super.setAuthority(AUTHORITY);
		super.initialize(CLASSES);
        return true;
    }

//
//    @Override
//    public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
//        final SQLiteDatabase db = mDatabase.getReadableDatabase();
//        final int match = mUriMatcher.match(uri);
//        Cursor cursor = null;
////
////		switch (match) {
////			case RssContract.REDDIT:
////				cursor = db.query(Tables.FEEDS, projection, selection, selectionArgs, null, null, sortOrder);
////				break;
////			case RssContract.REDDIT_ID:
////				cursor = db.query(Tables.GUID, projection, selection, selectionArgs, null, null, sortOrder);
////				break;
////			case RssContract.POSTS:
////				cursor = db.query(Tables.ITEMS, projection, selection, selectionArgs, null, null, sortOrder);
////				break;
////			case RssContract.POSTS_ID:
////				cursor = db.query(Tables.ITUNES_IMAGE, projection, selection, selectionArgs, null, null, sortOrder);
////				break;
////			case RssContract.LOGIN:
////				cursor = db.query(Tables.MEDIA_CONTENT, projection, selection, selectionArgs, null, null, sortOrder);
////				break;
////			case RssContract.LOGIN_ID:
////				cursor = db.query(Tables.RSS_ENCLOSURE, projection, selection, selectionArgs, null, null, sortOrder);
////				break;
////			case RssContract.SUBREDDIT:
////			case RssContract.SUBREDDIT_ID:
////				cursor = db.query(Tables.RSS_IMAGE, projection, selection, selectionArgs, null, null, sortOrder);
////				break;
////
////			default:
////				throw new UnsupportedOperationException("Unknown uri: " + uri);
////		}
////
////		cursor.setNotificationUri(getContext().getContentResolver(), uri);
//
//        return cursor;
//    }

//    @Override
//    public Uri insert(final Uri uri, final ContentValues values) {
//        final SQLiteDatabase db = mDatabase.getReadableDatabase();
//        final int match = mUriMatcher.match(uri);
//        ContentResolver resolver = getContext().getContentResolver();
//
//        switch (match) {
//			case RssContract.REDDIT:
//			case RssContract.REDDIT_ID:
//				db.insertOrThrow(Tables.RSS_ENCLOSURE, null, values);
//				resolver.notifyChange(uri, null);
//				return RedditData.buildPostDataUri(values.getAsString(RssContract.RedditDataColumns.MODHASH));
//
//			case RssContract.POSTS:
//			case RssContract.POSTS_ID:
//				long id = db.insertOrThrow(Tables.ITUNES_IMAGE, null, values);
//				resolver.notifyChange(uri, null);
//				return RssContract.Posts.buildPostDataUri(id);
//
//			case RssContract.LOGIN:
//			case RssContract.LOGIN_ID:
//				db.insertOrThrow(Tables.RSS_IMAGE, null, values);
//				resolver.notifyChange(uri, null);
//				return RssContract.Login.buildLoginUri(values.getAsString(RssContract.LoginColumns.USERNAME));
//
//			case RssContract.SUBREDDIT:
//			case RssContract.SUBREDDIT_ID:
//				db.insertOrThrow(Tables.RSS_ENCLOSURE, null, values);
//				resolver.notifyChange(uri, null);
//				return RssContract.Subreddits.buildSubredditUri(values.getAsString(RssContract.Subreddits.DISPLAY_NAME));
//
//			default:
//				throw new UnsupportedOperationException("Unknown uri: " + uri);
//
//        }
//        return null;
//    }



//    @Override
//    public String getType(Uri uri) {
//        final int match = matcher.match(uri);
//
//        switch (match) {
//            case ENCLOSURE: return ENCLOSURES_CONTENT_TYPE;
//            case ENCLOSURE_ID: return ENCLOSURES_CONTENT_ITEM_TYPE;
//
//            case FEED: return FEED_CONTENT_TYPE;
//            case FEED_ID: return FEED_CONTENT_ITEM_TYPE;
//
//            case GUID: return GUID_CONTENT_TYPE;
//            case GUID_ID: return GUID_CONTENT_ITEM_TYPE;
//
//            case IMAGE: return IMAGE_CONTENT_TYPE;
//            case IMAGE_ID: return IMAGE_CONTENT_ITEM_TYPE;
//
//            case ITEM: return ITEM_CONTENT_TYPE;
//            case ITEM_ID: return ITEM_CONTENT_ITEM_TYPE;
//
//            case ITUNES_IMAGE: return ITUNES_IMAGE_CONTENT_TYPE;
//            case ITUNES_IMAGE_ID: return ITUNES_IMAGE_CONTENT_ITEM_TYPE;
//
//            case MEDIA_CONTENT: return MEDIA_CONTENT_CONTENT_TYPE;
//            case MEDIA_CONTENT_ID: return MEDIA_CONTENT_CONTENT_ITEM_TYPE;
//
//            default:
//                throw new UnsupportedOperationException("Unknown uri: " + uri);
//        }
//    }
    
//    @Override
//    public UriMatcher buildUriMatcher() {
//        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
//        final String authority = CONTENT_AUTHORITY;
//
//        matcher.addURI(authority, BASE_CONTENT_URI.toString(), 0);
//
//        matcher.addURI(authority, PATH_ENCLOSURE, ENCLOSURE);
//        matcher.addURI(authority, PATH_ENCLOSURE + "/*", ENCLOSURE_ID);
//
//        matcher.addURI(authority, PATH_FEED, FEED);
//        matcher.addURI(authority, PATH_FEED + "/*", FEED_ID);
//
//        matcher.addURI(authority, PATH_GUID, GUID);
//        matcher.addURI(authority, PATH_GUID + "/*", GUID_ID);
//
//        matcher.addURI(authority, PATH_IMAGE, IMAGE);
//        matcher.addURI(authority, PATH_IMAGE + "/*", IMAGE_ID);
//
//        matcher.addURI(authority, PATH_ITEM, ITEM);
//        matcher.addURI(authority, PATH_ITEM + "/*", ITEM_ID);
//
//        matcher.addURI(authority, PATH_ITUNES_IMAGE, ITUNES_IMAGE);
//        matcher.addURI(authority, PATH_ITUNES_IMAGE + "/*", ITUNES_IMAGE_ID);
//
//        matcher.addURI(authority, PATH_MEDIA_CONTENT, MEDIA_CONTENT);
//        matcher.addURI(authority, PATH_MEDIA_CONTENT + "/*", MEDIA_CONTENT_ID);
//
//        return matcher;
//    }

}
