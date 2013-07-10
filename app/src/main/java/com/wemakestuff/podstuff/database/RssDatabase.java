package com.wemakestuff.podstuff.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wemakestuff.podstuff.rss.model.*;

import java.sql.SQLException;

public class RssDatabase extends OrmLiteSqliteOpenHelper {
	private static final String                        TAG                = RssDatabase.class.getSimpleName();
	private static final int                           DATABASE_VERSION   = 1;
	private static final String                        DATABASE_NAME      = "podstuff.db";
	private              Dao<RssEnclosure, Integer>    rssEnclosureDao    = null;
	private              Dao<RssFeed, Integer>         rssFeedDao         = null;
	private              Dao<RssGuid, Integer>         rssGuidDao         = null;
	private              Dao<RssImage, Integer>        rssImageDao        = null;
	private              Dao<RssItem, Integer>         rssItemDao         = null;
	private              Dao<RssITunesImage, Integer>  rssITunesImageDao  = null;
	private              Dao<RssMediaContent, Integer> rssMediaContentDao = null;

	public RssDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase sqLiteDatabase, final ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, RssFeed.class);
			TableUtils.createTable(connectionSource, RssGuid.class);
			TableUtils.createTable(connectionSource, RssItem.class);
			TableUtils.createTable(connectionSource, RssITunesImage.class);
			TableUtils.createTable(connectionSource, RssMediaContent.class);
			TableUtils.createTable(connectionSource, RssEnclosure.class);
			TableUtils.createTable(connectionSource, RssImage.class);
		} catch (SQLException e) {
			Log.e(TAG, "Unable to create database", e);
		}
	}

	@Override
	public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final ConnectionSource connectionSource, final int oldVersion, final int newVersion) {
		// TODO: Implement onUpgrade for this database
	}

	public <T, Integer> Dao<T, Integer> getRssDao(Class<T> clazz) throws SQLException {
		return getDao(clazz);
	}

	public interface Tables {
		String FEEDS         = "feeds";
		String GUID          = "guid";
		String ITEMS         = "items";
		String ITUNES_IMAGE  = "itunes_image";
		String MEDIA_CONTENT = "media_content";
		String RSS_ENCLOSURE = "rss_enclosure";
		String RSS_IMAGE     = "rss_image";
	}

}
