package com.wemakestuff.podstuff.rss.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rss_image")
public class RssImage {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String url;

	@DatabaseField
	private String title;

	@DatabaseField
	private String link;

	@DatabaseField
	private int    width;

	@DatabaseField
	private int    height;

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
}
