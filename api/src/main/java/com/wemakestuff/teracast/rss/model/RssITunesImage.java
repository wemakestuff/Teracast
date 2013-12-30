package com.wemakestuff.teracast.rss.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class RssITunesImage {
    public static final String ENTITY    = "feed";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final String HREF = "href";

    @JacksonXmlProperty(isAttribute = true)
	private String href;

	public RssITunesImage() {
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

		final RssITunesImage that = (RssITunesImage) o;

		if (href != null ? !href.equals(that.href) : that.href != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return href != null ? href.hashCode() : 0;
	}

}
