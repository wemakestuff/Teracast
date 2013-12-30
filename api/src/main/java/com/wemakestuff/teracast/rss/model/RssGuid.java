package com.wemakestuff.teracast.rss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RssGuid {
    private static final String IS_PERMALINK = "is_permalink";
    public static final String URL = "url";

	private boolean isPermalink;

	private String  url;

    public RssGuid() {

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

}
