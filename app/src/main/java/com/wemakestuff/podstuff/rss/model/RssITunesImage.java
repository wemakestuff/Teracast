package com.wemakestuff.podstuff.rss.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "itunes_image")
public class RssITunesImage {
	@DatabaseField(generatedId = true)
	private int    id;

	@DatabaseField
	private String href;

	public String getHref() {
		return href;
	}

	public void setHref(final String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RssITunesImage{");
		sb.append("href='").append(href).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final RssITunesImage that = (RssITunesImage) o;

		if (href != null ? !href.equals(that.href) : that.href != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return href != null ? href.hashCode() : 0;
	}
}
