package com.wemakestuff.podstuff.rss.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "guid")
public class RssGuid {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private boolean isPermalink;

	@DatabaseField
	private String  url;

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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final RssGuid guid = (RssGuid) o;

		if (isPermalink != guid.isPermalink) return false;
		if (url != null ? !url.equals(guid.url) : guid.url != null) return false;

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
}
