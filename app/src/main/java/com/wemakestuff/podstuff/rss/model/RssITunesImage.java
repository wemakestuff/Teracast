package com.wemakestuff.podstuff.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wemakestuff.podstuff.database.annotations.ContentType;
import com.wemakestuff.podstuff.database.annotations.SortOrder;
import com.wemakestuff.podstuff.database.annotations.UriPaths;

@DatabaseTable(tableName = "itunes_image")
@UriPaths({ RssiTunesImage.ENTITY_PL, RssiTunesImage.ENTITY_PL + "/#" })
@ContentType(BaseData.MIME_TYPE_PFX + RssiTunesImage.ENTITY)
@SortOrder(RssiTunesImage.HREF + " DESC")
public class RssiTunesImage extends BaseData implements Parcelable {
    public static final String ENTITY    = "feed";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final String HREF = "href";

	@DatabaseField(columnName = HREF)
	private String href;

	public RssiTunesImage() {
	}

	private RssiTunesImage(Parcel in) {
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

		final RssiTunesImage that = (RssiTunesImage) o;

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

    public static final Parcelable.Creator<RssiTunesImage> CREATOR = new Parcelable.Creator<RssiTunesImage>() {
        public RssiTunesImage createFromParcel(Parcel in) {
            return new RssiTunesImage(in);
        }

        public RssiTunesImage[] newArray(int size) {
            return new RssiTunesImage[size];
        }
    };

}
