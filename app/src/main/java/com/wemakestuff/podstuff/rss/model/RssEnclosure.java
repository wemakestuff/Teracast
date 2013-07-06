package com.wemakestuff.podstuff.rss.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rss_enclosure")
public class RssEnclosure {

	@DatabaseField(generatedId = true)
	private int    id;

	@DatabaseField
	private String url;

	@DatabaseField
	private long   length;

	@DatabaseField
	private String type;


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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final RssEnclosure enclosure = (RssEnclosure) o;

		if (length != enclosure.length) return false;
		if (type != null ? !type.equals(enclosure.type) : enclosure.type != null) return false;
		if (url != null ? !url.equals(enclosure.url) : enclosure.url != null) return false;

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
}
