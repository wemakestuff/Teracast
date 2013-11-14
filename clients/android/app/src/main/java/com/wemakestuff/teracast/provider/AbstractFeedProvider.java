package com.wemakestuff.teracast.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.wemakestuff.teracast.database.annotations.ContentType;
import com.wemakestuff.teracast.database.annotations.SortOrder;
import com.wemakestuff.teracast.database.annotations.UriPaths;
import com.wemakestuff.teracast.database.interfaces.DatabaseHelper;
import com.wemakestuff.teracast.rss.model.BaseData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AbstractFeedProvider extends ContentProvider {
    private static final String ERR_UNKNOWN_URL   = "Unknown URL: ";
    private static final String ERR_INSERT_FAILED = "Failed to insert record at ";

    private String         authority;
    private DatabaseHelper helper;

    private UriMatcher                              matcher           = new UriMatcher(UriMatcher.NO_MATCH);
    private Map<Integer, Class<? extends BaseData>> codeClasses       = new HashMap<Integer, Class<? extends BaseData>>();
    private Set<Integer>                            allCodes          = new HashSet<Integer>();
    private Set<Integer>                            listCodes         = new HashSet<Integer>();
    private Set<Integer>                            childrenListCodes = new HashSet<Integer>();

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) {
        this.helper = helper;
    }

    protected void initialize(Class<? extends BaseData>[] classes) {
        int code = 1;
        for (Class<? extends BaseData> clazz : classes) {
            for (String path : getUriPaths(clazz)) {
                matcher.addURI(authority, path, code);
                codeClasses.put(code, clazz);
                updateCodes(code, path);
                code++;
            }
        }
    }

    private void updateCodes(int code, String path) {
        allCodes.add(code);
        if (!path.endsWith("/#")) {
            listCodes.add(code);
            if (path.contains("/#/")) {
                childrenListCodes.add(code);
            }
        }
    }

    @Override
    public String getType(Uri url) {
        int code = matcher.match(url);
        if (!isValidCode(code))
            throw new IllegalArgumentException(ERR_UNKNOWN_URL + url);
        String pfx = (isListCode(code)) ? ContentResolver.CURSOR_DIR_BASE_TYPE
                : ContentResolver.CURSOR_ITEM_BASE_TYPE;
        return pfx + "/" + getContentType(code);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri url, String[] projection, String selection,
                        String[] selectionArgs, String sort) {
        int code = matcher.match(url);
        if (!isValidCode(code))
            throw new IllegalArgumentException(ERR_UNKNOWN_URL + url);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String defaultSort = null;
        qb.setTables(getTableName(code));
        if (isListCode(code)) {
            qb.setProjectionMap(getProjectionMap(code));
            defaultSort = getDefaultSortOrder(code);
            appendWhere(qb, code, url);
        } else {
            qb.appendWhere(BaseData._ID + "=" + url.getPathSegments().get(1));
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        String orderBy = (TextUtils.isEmpty(sort)) ? defaultSort : sort;
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), url);
        return c;
    }

    private void appendWhere(SQLiteQueryBuilder qb, int code, Uri url) {
        String id = null;
        if (isChildrenListCode(code)) {
            String[] fcns = getForeignColumnNames(code);
            if (fcns.length > 0) {
                id = fcns[0];
            }
        }
        if (id != null) {
            qb.appendWhere(id + "=" + url.getPathSegments().get(1));
        }
    }

    @Override
    public Uri insert(Uri url, ContentValues initialValues) {
        int code = matcher.match(url);
        if (!isValidInsertCode(code))
            throw new IllegalArgumentException(ERR_UNKNOWN_URL + url);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = (initialValues != null) ? new ContentValues(
                initialValues) : new ContentValues();
        Uri uri = null;
        long id = db.insert(getTableName(code), null, values);
        if (id >= 0) {
            uri = ContentUris.withAppendedId(getUri(code), id);
        } else {
            throw new SQLException(ERR_INSERT_FAILED + url);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri url, String where, String[] whereArgs) {
        int code = matcher.match(url);
        if (!isValidDeleteCode(code))
            throw new IllegalArgumentException(ERR_UNKNOWN_URL + url);
        SQLiteDatabase db = helper.getWritableDatabase();
        String wherex = (isListCode(code)) ? where : getWhere(url, where);
        int count = db.delete(getTableName(code), wherex, whereArgs);
        getContext().getContentResolver().notifyChange(url, null);
        return count;
    }

    @Override
    public int update(Uri url, ContentValues values, String where,
                      String[] whereArgs) {
        int code = matcher.match(url);
        if (!isValidUpdateCode(code))
            throw new IllegalArgumentException(ERR_UNKNOWN_URL + url);
        SQLiteDatabase db = helper.getWritableDatabase();
        String wherex = (isListCode(code)) ? where : getWhere(url, where);
        int count = db.update(getTableName(code), values, wherex, whereArgs);
        getContext().getContentResolver().notifyChange(url, null);
        return count;
    }

    @Override
    public void shutdown() {
        helper.close();
    }

    private static String getWhere(Uri url, String where) {
        return BaseData._ID + "=" + url.getPathSegments().get(1)
                + (!TextUtils.isEmpty(where) ? " AND (" + where + ")" : "");
    }

    private String getTableName(int code) {
        return helper.getTableName(codeClasses.get(code));
    }

    private Map<String, String> getProjectionMap(int code) {
        Map<String, String> map = new HashMap<String, String>();
        for (String columnName : helper.getColumnNames(codeClasses.get(code),
                false)) {
            map.put(columnName, columnName);
        }
        return map;
    }

    private Uri getUri(int code) {
        Uri result = null;
        String[] uriPaths = getUriPaths(codeClasses.get(code));
        if (uriPaths.length > 0) {
            result = getUri(uriPaths[0]);
        }
        return result;
    }

    private Uri getUri(String uriPath) {
        return Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + authority
                + "/" + uriPath);
    }

    private String getContentType(int code) {
        return getContentType(codeClasses.get(code));
    }

    private String getDefaultSortOrder(int code) {
        return getDefaultSortOrder(codeClasses.get(code));
    }

    private String[] getForeignColumnNames(int code) {
        return helper.getColumnNames(codeClasses.get(code), true);
    }

    private boolean isValidCode(int code) {
        return allCodes.contains(code);
    }

    private boolean isValidInsertCode(int code) {
        return isValidCode(code);
    }

    private boolean isValidDeleteCode(int code) {
        return isValidCode(code);
    }

    private boolean isValidUpdateCode(int code) {
        return isValidCode(code);
    }

    private boolean isListCode(int code) {
        return listCodes.contains(code);
    }

    private boolean isChildrenListCode(int code) {
        return childrenListCodes.contains(code);
    }

    private String[] getUriPaths(Class<? extends BaseData> clazz) {
        UriPaths uriPaths = clazz.getAnnotation(UriPaths.class);
        return uriPaths == null ? null : uriPaths.value();
    }

    private String getContentType(Class<? extends BaseData> clazz) {
        ContentType contentType = clazz.getAnnotation(ContentType.class);
        return contentType == null ? null : contentType.value();
    }

    private String getDefaultSortOrder(Class<? extends BaseData> clazz) {
        SortOrder sortOrder = clazz.getAnnotation(SortOrder.class);
        return sortOrder == null ? null : sortOrder.value();
    }
}
