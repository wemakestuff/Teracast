package com.wemakestuff.podstuff.rss;

public class MediaContent {
	private String url;
	private long   fileSize;
	private String type;

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
		final StringBuilder sb = new StringBuilder("MediaContent{");
		sb.append("url='").append(url).append('\'');
		sb.append(", fileSize=").append(fileSize);
		sb.append(", type='").append(type).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final MediaContent that = (MediaContent) o;

		if (fileSize != that.fileSize) return false;
		if (type != null ? !type.equals(that.type) : that.type != null) return false;
		if (url != null ? !url.equals(that.url) : that.url != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = url != null ? url.hashCode() : 0;
		result = 31 * result + (int) (fileSize ^ (fileSize >>> 32));
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}
}
