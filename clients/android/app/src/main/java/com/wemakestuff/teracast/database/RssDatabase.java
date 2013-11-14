package com.wemakestuff.teracast.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;
import com.wemakestuff.teracast.database.interfaces.DatabaseHelper;
import com.wemakestuff.teracast.provider.FeedProvider;
import com.wemakestuff.teracast.rss.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RssDatabase extends DatabaseHelper {
	private static final String                                        TAG                = RssDatabase.class.getSimpleName();
	private static final int                                           DATABASE_VERSION   = 1;
	private static final String                                        DATABASE_NAME      = "teracast.db";
	private              HashMap<Table, Dao<? extends BaseData, Long>> tables             = null;
	private              Dao<RssEnclosure, Long>                       rssEnclosureDao    = null;
	private              Dao<RssFeed, Long>                            rssFeedDao         = null;
	private              Dao<RssGuid, Long>                            rssGuidDao         = null;
	private              Dao<RssImage, Long>                           rssImageDao        = null;
	private              Dao<RssItem, Long>                            rssItemDao         = null;
	private              Dao<RssITunesImage, Long>                     rssITunesImageDao  = null;
	private              Dao<RssMediaContent, Long>                    rssMediaContentDao = null;

	// @formatter:off
	private final Map<Class<? extends BaseData>, Dao<? extends BaseData, Long>>           daos         =
			new HashMap<Class<? extends BaseData>, Dao<? extends BaseData, Long>>();
	private final Map<Class<? extends BaseData>, DatabaseTableConfig<? extends BaseData>> tableConfigs =
			new HashMap<Class<? extends BaseData>, DatabaseTableConfig<? extends BaseData>>();

	public RssDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase db, final ConnectionSource cs) {
		Log.i(TAG, "Creating database");
		createTables(db, cs);
//        try {
//            TableUtils.createTable(connectionSource, RssFeed.class);
//            TableUtils.createTable(connectionSource, RssGuid.class);
//            TableUtils.createTable(connectionSource, RssItem.class);
//            TableUtils.createTable(connectionSource, RssITunesImage.class);
//            TableUtils.createTable(connectionSource, RssMediaContent.class);
//            TableUtils.createTable(connectionSource, RssEnclosure.class);
//            TableUtils.createTable(connectionSource, RssImage.class);
//        } catch (SQLException e) {
//            Log.e(TAG, "Unable to create database", e);
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs,
                          int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion);
        switch (oldVersion) {
            default:
                Log.i(TAG, "Version too old, deleting database contents");
                deleteTables(db, cs);
                createTables(db, cs);
                break;
        }
    }

    @Override
    public void close() {
        super.close();
        daos.clear();
        tableConfigs.clear();
    }

    private <T extends BaseData, Integer> Dao<? extends BaseData, Long> getRssDao(Table table) throws SQLException {
        Dao<? extends BaseData, Long> dao = null;
        if (tables.containsKey(table)) {
            dao = tables.get(table);
        } else {
            dao = getRssDao(table.getType());
            tables.put(table, dao);
        }

        return dao;
    }
//
    public <T, Integer> Dao<T, Long> getRssDao(Class<T> clazz) throws SQLException {
        // ORMLite creates a cache behind the scenes, so no need to cache it here
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

    public <T extends BaseData> String getTableName(Class<T> clazz) {
        DatabaseTableConfig<? extends BaseData> cfg = getTableConfig(clazz);
        return cfg.getTableName();
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseData> Dao<T, Long> getDaoEx(Class<T> clazz) {
        Dao<T, Long> result = null;
        if (daos.containsKey(clazz)) {
            result = (Dao<T, Long>) daos.get(clazz);
        } else {
            try {
                result = getDao(clazz);
            } catch (java.sql.SQLException e) {
                throw new android.database.SQLException(e.getMessage());
            }
            daos.put(clazz, result);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseData> DatabaseTableConfig<T> getTableConfig(Class<T> clazz) {
        DatabaseTableConfig<T> result = null;
        if (tableConfigs.containsKey(clazz)) {
            result = (DatabaseTableConfig<T>) tableConfigs.get(clazz);
        } else {
            try {
                result = DatabaseTableConfig.fromClass(getConnectionSource(),
                        clazz);
            } catch (java.sql.SQLException e) {
                throw new android.database.SQLException(e.getMessage());
            }
            tableConfigs.put(clazz, result);
        }
        return result;
    }

    public <T extends BaseData> String[] getColumnNames(Class<T> clazz,
                                                        boolean foreignOnly) {
        List<String> columnNames = new ArrayList<String>();
        try {
            DatabaseTableConfig<? extends BaseData> cfg = getTableConfig(clazz);
            SqliteAndroidDatabaseType dbType = new SqliteAndroidDatabaseType();
            for (FieldType fieldType : cfg.getFieldTypes(dbType)) {
                if (!foreignOnly || fieldType.isForeign()) {
                    columnNames.add(fieldType.getColumnName());
                }
            }
        } catch (java.sql.SQLException e) {
            throw new android.database.SQLException(e.getMessage());
        }
        return columnNames.toArray(new String[]{});
    }

    public <T extends BaseData> T queryById(long id, Class<T> clazz) {
        T entity = null;
        try {
            entity = getDaoEx(clazz).queryForId(id);
        } catch (java.sql.SQLException e) {
            throw new android.database.SQLException(e.getMessage());
        }
        return entity;
    }

    public <T extends BaseData> long create(BaseData entity, Class<T> clazz) {
        long id = -1;
        try {
            if (getDaoEx(clazz).create(clazz.cast(entity)) == 1) {
                id = entity.getId();
            }
        } catch (java.sql.SQLException e) {
            throw new android.database.SQLException(e.getMessage());
        }
        return id;
    }

    public <T extends BaseData> void update(BaseData entity, Class<T> clazz) {
        try {
            int count = getDaoEx(clazz).update(clazz.cast(entity));
            assert (count == 1);
        } catch (java.sql.SQLException e) {
            throw new android.database.SQLException(e.getMessage());
        }
    }

    public <T extends BaseData> void deleteById(long id, Class<T> clazz) {
        try {
            int count = getDaoEx(clazz).deleteById(id);
            assert (count == 1);
        } catch (java.sql.SQLException e) {
            throw new android.database.SQLException(e.getMessage());
        }
    }

    private void createTables(SQLiteDatabase db, ConnectionSource cs) {
        for (Class<? extends BaseData> clazz : FeedProvider.CLASSES) {
            createTable(clazz, cs);
        }

        try {
            rssFeedDao.executeRaw("CREATE VIRTUAL TABLE enrondata1 USING fts3(content TEXT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTables(SQLiteDatabase db, ConnectionSource cs) {
        for (Table t : Table.values()) {
            dropTable(t.getType(), cs);
        }
    }

    private void createTable(Class<? extends BaseData> clazz, ConnectionSource cs) {
        try {
            TableUtils.createTable(cs, clazz);
        } catch (java.sql.SQLException e) {
            throw new android.database.SQLException(e.getMessage());
        }
    }

    private void dropTable(Class<? extends BaseData> clazz, ConnectionSource cs) {
        try {
            TableUtils.dropTable(cs, clazz, false);
        } catch (java.sql.SQLException e) {
            throw new android.database.SQLException(e.getMessage());
        }
    }

}
