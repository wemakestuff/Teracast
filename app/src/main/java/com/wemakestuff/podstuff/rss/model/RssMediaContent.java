package com.wemakestuff.podstuff.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "media_content")
public class RssMediaContent implements Parcelable {

	public static final Parcelable.Creator<RssMediaContent> CREATOR
			= new Parcelable.Creator<RssMediaContent>() {
		public RssMediaContent createFromParcel(Parcel in) {
			return new RssMediaContent(in);
		}

		public RssMediaContent[] newArray(int size) {
			return new RssMediaContent[size];
		}
	};
	@DatabaseField(generatedId = true)
	private int    id;
	@DatabaseField
	private String url;
	@DatabaseField
	private long   fileSize;
	@DatabaseField
	private String type;

	public RssMediaContent() {

	}

	private RssMediaContent(Parcel in) {
		id = in.readInt();
		url = in.readString();
		fileSize = in.readLong();
		type = in.readString();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(final long fileSize) {
		this.fileSize = fileSize;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RssMediaContent{");
		sb.append("url='").append(url).append('\'');
		sb.append(", fileSize=").append(fileSize);
		sb.append(", type='").append(type).append('\'');
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

		final RssMediaContent that = (RssMediaContent) o;

		if (fileSize != that.fileSize) {
			return false;
		}
		if (type != null ? !type.equals(that.type) : that.type != null) {
			return false;
		}
		if (url != null ? !url.equals(that.url) : that.url != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = url != null ? url.hashCode() : 0;
		result = 31 * result + (int) (fileSize ^ (fileSize >>> 32));
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	@Override
	public int describeContents() {
		return hashCode();
	}

	@Override
	public void writeToParcel(final Parcel out, final int flags) {
		out.writeInt(id);
		out.writeString(url);
		out.writeLong(fileSize);
		out.writeString(type);
	}
}
