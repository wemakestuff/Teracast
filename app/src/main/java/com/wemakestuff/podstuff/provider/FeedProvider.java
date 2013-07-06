package com.wemakestuff.podstuff.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import com.wemakestuff.podstuff.database.RssContract;
import com.wemakestuff.podstuff.database.RssContract.RedditData;
import com.wemakestuff.podstuff.database.RssDatabase;
import com.wemakestuff.podstuff.database.RssDatabase.Tables;

public class FeedProvider extends ContentProvider {
	private static final UriMatcher mUriMatcher = createUriMatcher();
	private static final String     TAG         = FeedProvider.class.getSimpleName();
	private RssDatabase mDatabase;

	private static UriMatcher createUriMatcher() {
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		final String authority = RssContract.CONTENT_AUTHORITY;
		matcher.addURI(authority, RssContract.BASE_CONTENT_URI.toString(), 0);
		matcher.addURI(authority, RssContract.PATH_POSTS, RssContract.POSTS);
		matcher.addURI(authority, RssContract.PATH_POSTS + "/*", RssContract.POSTS_ID);
		matcher.addURI(authority, RssContract.PATH_REDDIT_DATA, RssContract.REDDIT);
		matcher.addURI(authority, RssContract.PATH_REDDIT_DATA + "/*", RssContract.REDDIT_ID);
		matcher.addURI(authority, RssContract.PATH_LOGIN, RssContract.LOGIN);
		matcher.addURI(authority, RssContract.PATH_LOGIN + "/*", RssContract.LOGIN_ID);
		matcher.addURI(authority, RssContract.PATH_SUBREDDITS, RssContract.SUBREDDIT);
		matcher.addURI(authority, RssContract.PATH_SUBREDDITS + "/*", RssContract.SUBREDDIT_ID);

		return matcher;
	}

	@Override
	public boolean onCreate() {
		mDatabase = new RssDatabase(getContext());
		return false;
	}

	@Override
	public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
		final SQLiteDatabase db = mDatabase.getReadableDatabase();
		final int match = mUriMatcher.match(uri);
		Cursor cursor = null;

		switch (match) {
			case RssContract.REDDIT:
				cursor = db.query(Tables.FEEDS, projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case RssContract.REDDIT_ID:
				cursor = db.query(Tables.GUID, projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case RssContract.POSTS:
				cursor = db.query(Tables.ITEMS, projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case RssContract.POSTS_ID:
				cursor = db.query(Tables.ITUNES_IMAGE, projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case RssContract.LOGIN:
				cursor = db.query(Tables.MEDIA_CONTENT, projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case RssContract.LOGIN_ID:
				cursor = db.query(Tables.RSS_ENCLOSURE, projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case RssContract.SUBREDDIT:
			case RssContract.SUBREDDIT_ID:
				cursor = db.query(Tables.RSS_IMAGE, projection, selection, selectionArgs, null, null, sortOrder);
				break;

			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}

		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public String getType(final Uri uri) {
		final int match = mUriMatcher.match(uri);

		switch (match) {
			case RssContract.REDDIT:
				return RedditData.CONTENT_TYPE;
			case RssContract.REDDIT_ID:
				return RedditData.CONTENT_ITEM_TYPE;
			case RssContract.POSTS:
				return RssContract.Posts.CONTENT_TYPE;
			case RssContract.POSTS_ID:
				return RssContract.Posts.CONTENT_ITEM_TYPE;
			case RssContract.LOGIN:
				return RssContract.Login.CONTENT_TYPE;
			case RssContract.LOGIN_ID:
				return RssContract.Login.CONTENT_ITEM_TYPE;
			case RssContract.SUBREDDIT:
				return RssContract.Subreddits.CONTENT_TYPE;
			case RssContract.SUBREDDIT_ID:
				return RssContract.Subreddits.CONTENT_ITEM_TYPE;
			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}
	}

	@Override
	public Uri insert(final Uri uri, final ContentValues values) {
		final SQLiteDatabase db = mDatabase.getReadableDatabase();
		final int match = mUriMatcher.match(uri);
		ContentResolver resolver = getContext().getContentResolver();

		switch (match) {
			case RssContract.REDDIT:
			case RssContract.REDDIT_ID:
				db.insertOrThrow(Tables.RSS_ENCLOSURE, null, values);
				resolver.notifyChange(uri, null);
				return RedditData.buildPostDataUri(values.getAsString(RssContract.RedditDataColumns.MODHASH));

			case RssContract.POSTS:
			case RssContract.POSTS_ID:
				long id = db.insertOrThrow(Tables.ITUNES_IMAGE, null, values);
				resolver.notifyChange(uri, null);
				return RssContract.Posts.buildPostDataUri(id);

			case RssContract.LOGIN:
			case RssContract.LOGIN_ID:
				db.insertOrThrow(Tables.RSS_IMAGE, null, values);
				resolver.notifyChange(uri, null);
				return RssContract.Login.buildLoginUri(values.getAsString(RssContract.LoginColumns.USERNAME));

			case RssContract.SUBREDDIT:
			case RssContract.SUBREDDIT_ID:
				db.insertOrThrow(Tables.RSS_ENCLOSURE, null, values);
				resolver.notifyChange(uri, null);
				return RssContract.Subreddits.buildSubredditUri(values.getAsString(RssContract.Subreddits.DISPLAY_NAME));

			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);

		}
	}

	@Override
	public int delete(final Uri uri, final String selection, final String[] selectionArgs) {
		Log.i(TAG, "delete(uri=" + uri + ")");
		final SQLiteDatabase db = mDatabase.getWritableDatabase();
		ContentResolver resolver = getContext().getContentResolver();

		final int match = mUriMatcher.match(uri);
		switch (match) {

			case RssContract.BASE:
				resolver.notifyChange(uri, null, false);
				return 1;

			case RssContract.REDDIT:
			case RssContract.REDDIT_ID:
				int rows = db.delete(Tables.GUID, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			case RssContract.POSTS:
			case RssContract.POSTS_ID:
				rows = db.delete(Tables.MEDIA_CONTENT, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			case RssContract.LOGIN:
			case RssContract.LOGIN_ID:
				rows = db.delete(Tables.RSS_ENCLOSURE, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			case RssContract.SUBREDDIT:
			case RssContract.SUBREDDIT_ID:
				rows = db.delete(Tables.RSS_IMAGE, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);

		}
	}

	@Override
	public int update(final Uri uri, final ContentValues values, final String selection, final String[] selectionArgs) {
		Log.i(TAG, "update(uri=" + uri + ", values=" + values.toString() + ")");
		final SQLiteDatabase db = mDatabase.getWritableDatabase();
		ContentResolver resolver = getContext().getContentResolver();

		final int match = mUriMatcher.match(uri);
		switch (match) {

			case RssContract.REDDIT:
			case RssContract.REDDIT_ID:
				int rows = db.update(Tables.RSS_ENCLOSURE, values, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			case RssContract.POSTS:
			case RssContract.POSTS_ID:
				rows = db.update(Tables.GUID, values, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			case RssContract.LOGIN:
			case RssContract.LOGIN_ID:
				rows = db.update(Tables.ITEMS, values, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			case RssContract.SUBREDDIT:
			case RssContract.SUBREDDIT_ID:
				rows = db.update(Tables.ITUNES_IMAGE, values, selection, selectionArgs);
				resolver.notifyChange(uri, null);
				return rows;

			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);

		}
	}
}
