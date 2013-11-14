package com.wemakestuff.teracast.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wemakestuff.teracast.database.annotations.ContentType;
import com.wemakestuff.teracast.database.annotations.SortOrder;
import com.wemakestuff.teracast.database.annotations.UriPaths;

@DatabaseTable(tableName = "guid")
@UriPaths({ RssGuid.ENTITY_PL, RssGuid.ENTITY_PL + "/#" })
@ContentType(BaseData.MIME_TYPE_PFX + RssGuid.ENTITY)
@SortOrder(RssGuid.URL + " DESC")
public class RssGuid extends BaseData implements Parcelable {
    public static final String ENTITY = "feed";
    public static final String ENTITY_PL = ENTITY + "s";

    private static final String IS_PERMALINK = "is_permalink";
    public static final String URL = "url";

    @DatabaseField(columnName = IS_PERMALINK)
	private boolean isPermalink;

	@DatabaseField(columnName = URL)
	private String  url;

    public static final Parcelable.Creator<RssGuid> CREATOR = new Parcelable.Creator<RssGuid>() {
        public RssGuid createFromParcel(Parcel in) {
            return new RssGuid(in);
        }

        public RssGuid[] newArray(int size) {
            return new RssGuid[size];
        }
    };

    public RssGuid() {

	}

	private RssGuid(Parcel in) {
		id = in.readLong();
		isPermalink = in.readByte() == 1 ? true : false;
		url = in.readString();
	}

	public boolean isPermalink() {
		return isPermalink;
	}

	public void setIsPermalink(final boolean permalink) {
		isPermalink = permalink;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final RssGuid guid = (RssGuid) o;

		if (isPermalink != guid.isPermalink) {
			return false;
		}
		if (url != null ? !url.equals(guid.url) : guid.url != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = (isPermalink ? 1 : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RssGuid{");
		sb.append("isPermalink=").append(isPermalink);
		sb.append(", url='").append(url).append('\'');
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
		out.writeByte((byte) (isPermalink ? 1 : 0));
		out.writeString(url);
	}
}
