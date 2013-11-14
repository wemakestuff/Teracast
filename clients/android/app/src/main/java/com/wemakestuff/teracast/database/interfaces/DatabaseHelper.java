package com.wemakestuff.teracast.database.interfaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.wemakestuff.teracast.rss.model.BaseData;

public abstract class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    public abstract <T extends BaseData> String[] getColumnNames(Class<T> clazz, boolean foreignOnly);

    public abstract <T extends BaseData> String getTableName(Class<T> clazz);
}