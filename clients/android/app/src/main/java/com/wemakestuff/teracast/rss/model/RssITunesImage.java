package com.wemakestuff.teracast.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wemakestuff.teracast.database.annotations.ContentType;
import com.wemakestuff.teracast.database.annotations.SortOrder;
import com.wemakestuff.teracast.database.annotations.UriPaths;

@DatabaseTable(tableName = "itunes_image")
@UriPaths({ RssITunesImage.ENTITY_PL, RssITunesImage.ENTITY_PL + "/#" })
@ContentType(BaseData.MIME_TYPE_PFX + RssITunesImage.ENTITY)
@SortOrder(RssITunesImage.HREF + " DESC")
public class RssITunesImage extends BaseData implements Parcelable {
    public static final String ENTITY    = "feed";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final String HREF = "href";

	@DatabaseField(columnName = HREF)
	private String href;

	public RssITunesImage() {
	}

	private RssITunesImage(Parcel in) {
		id = in.readLong();
		href = in.readString();
	}

	public String getHref() {
		return href;
	}

	public void setHref(final String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RssiTunesImage{");
		sb.append("href='").append(href).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final RssITunesImage that = (RssITunesImage) o;

		if (href != null ? !href.equals(that.href) : that.href != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return href != null ? href.hashCode() : 0;
	}

	@Override
	public int describeContents() {
		return hashCode();
	}

	@Override
	public void writeToParcel(final Parcel out, final int flags) {
		out.writeLong(id);
		out.writeString(href);
	}

    public static final Parcelable.Creator<RssITunesImage> CREATOR = new Parcelable.Creator<RssITunesImage>() {
        public RssITunesImage createFromParcel(Parcel in) {
            return new RssITunesImage(in);
        }

        public RssITunesImage[] newArray(int size) {
            return new RssITunesImage[size];
        }
    };

}
