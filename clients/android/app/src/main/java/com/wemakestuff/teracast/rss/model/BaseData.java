package com.wemakestuff.teracast.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.j256.ormlite.field.DatabaseField;

public abstract class BaseData implements BaseColumns, Parcelable {
    public static final String TABLE_NAME_PFX = "podstuff_";
    public static final String MIME_TYPE_PFX = "vnd.teracast.";

    @DatabaseField(columnName = _ID, generatedId = true)
    protected long id;

    public BaseData() {
        this.id = -1;
    }

    public BaseData(long id) {
        this.id = id;
    }

    public BaseData(Parcel in) {
        this.id = in.readLong();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
