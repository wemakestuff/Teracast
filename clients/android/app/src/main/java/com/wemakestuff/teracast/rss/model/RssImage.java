package com.wemakestuff.teracast.rss.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wemakestuff.teracast.database.annotations.ContentType;
import com.wemakestuff.teracast.database.annotations.SortOrder;
import com.wemakestuff.teracast.database.annotations.UriPaths;

@DatabaseTable(tableName = "rss_image")
@UriPaths({ RssImage.ENTITY_PL, RssImage.ENTITY_PL + "/#" })
@ContentType(BaseData.MIME_TYPE_PFX + RssImage.ENTITY)
@SortOrder(RssImage.URL + " DESC")
public class RssImage extends BaseData implements Parcelable {
    public static final String ENTITY = "feed";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final String URL = "url";
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

	@DatabaseField(columnName = URL)
	private String url;

	@DatabaseField(columnName = TITLE)
	private String title;

	@DatabaseField(columnName = LINK)
	private String link;

	@DatabaseField(columnName = WIDTH)
	private int    width;

	@DatabaseField(columnName = HEIGHT)
	private int    height;

    public RssImage() {
        super();
    }

    public RssImage(Parcel source) {
        id = source.readLong();
        url = source.readString();
        title = source.readString();
        link = source.readString();
        width = source.readInt();
        height = source.readInt();
    }

    public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}


	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final RssImage rssImage = (RssImage) o;

		if (height != rssImage.height) return false;
		if (width != rssImage.width) return false;
		if (link != null ? !link.equals(rssImage.link) : rssImage.link != null) return false;
		if (title != null ? !title.equals(rssImage.title) : rssImage.title != null) return false;
		if (url != null ? !url.equals(rssImage.url) : rssImage.url != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = url != null ? url.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (link != null ? link.hashCode() : 0);
		result = 31 * result + width;
		result = 31 * result + height;
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RssImage{");
		sb.append("url='").append(url).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", link='").append(link).append('\'');
		sb.append(", width=").append(width);
		sb.append(", height=").append(height);
		sb.append('}');
		return sb.toString();
	}

    public static final Creator<RssImage> CREATOR = new Creator<RssImage>() {
        @Override
        public RssImage createFromParcel(Parcel source) {
            return new RssImage(source);
        }

        @Override
        public RssImage[] newArray(int size) {
            return new RssImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(url);
        dest.writeString(link);
        dest.writeInt(width);
        dest.writeInt(height);

    }
}
