package com.wemakestuff.podstuff.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "guid")
public class RssGuid implements Parcelable {

	public static final Parcelable.Creator<RssGuid> CREATOR
			= new Parcelable.Creator<RssGuid>() {
		public RssGuid createFromParcel(Parcel in) {
			return new RssGuid(in);
		}

		public RssGuid[] newArray(int size) {
			return new RssGuid[size];
		}
	};
	@DatabaseField(generatedId = true)
	private int     id;
	@DatabaseField
	private boolean isPermalink;
	@DatabaseField
	private String  url;

	public RssGuid() {

	}

	private RssGuid(Parcel in) {
		id = in.readInt();
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
		out.writeInt(id);
		out.writeByte((byte) (isPermalink ? 1 : 0));
		out.writeString(url);
	}
}
