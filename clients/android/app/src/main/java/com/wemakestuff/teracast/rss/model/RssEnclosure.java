package com.wemakestuff.teracast.rss.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wemakestuff.teracast.database.annotations.ContentType;
import com.wemakestuff.teracast.database.annotations.SortOrder;
import com.wemakestuff.teracast.database.annotations.UriPaths;

@DatabaseTable(tableName = "rss_enclosure")
@UriPaths({RssEnclosure.ENTITY_PL, RssEnclosure.ENTITY_PL + "/#"})
@ContentType(BaseData.MIME_TYPE_PFX + RssEnclosure.ENTITY)
@SortOrder(RssEnclosure.URL + " DESC")
public class RssEnclosure extends BaseData implements Parcelable {
    public static final String ENTITY    = "enclosure";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final  String URL    = "url";
    public static final String LENGTH = "length";
    public static final String TYPE   = "type";

    @DatabaseField(columnName = URL)
    private String url;

    @DatabaseField(columnName = LENGTH)
    private long length;

    @DatabaseField(columnName = TYPE)
    private String type;

    public RssEnclosure() {
        super();
    }

    public RssEnclosure(long id) {
        super(id);
    }

    private RssEnclosure(Parcel in) {
        id = in.readLong();
        url = in.readString();
        length = in.readLong();
        type = in.readString();
    }

    public static final Parcelable.Creator<RssEnclosure> CREATOR = new Parcelable.Creator<RssEnclosure>() {
        public RssEnclosure createFromParcel(Parcel in) {
            return new RssEnclosure(in);
        }

        public RssEnclosure[] newArray(int size) {
            return new RssEnclosure[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public long getLength() {
        return length;
    }

    public void setLength(final long length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public boolean equals(final Object o) {

        final RssEnclosure enclosure = (RssEnclosure) o;


        return true;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (int) (length ^ (length >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RssEnclosure{");
        sb.append("url='").append(url).append('\'');
        sb.append(", length=").append(length);
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        out.writeLong(id);
        out.writeString(url);
        out.writeLong(length);
        out.writeString(type);
    }
}
