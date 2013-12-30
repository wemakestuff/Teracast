package com.wemakestuff.teracast.rss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RssImage {
    public static final String  URL    = "url";
    private static final String TITLE  = "title";
    private static final String LINK   = "link";
    private static final String WIDTH  = "width";
    private static final String HEIGHT = "height";

    @JsonProperty("url")
    private String              url;

    @JsonProperty("title")
    private String              title;

    @JsonProperty("link")
    private String              link;

    @JsonProperty("width")
    private int                 width;

    @JsonProperty("height")
    private int                 height;

    public RssImage() {
        super();
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final RssImage rssImage = (RssImage) o;

        if (height != rssImage.height)
            return false;
        if (width != rssImage.width)
            return false;
        if (link != null ? !link.equals(rssImage.link) : rssImage.link != null)
            return false;
        if (title != null ? !title.equals(rssImage.title)
                : rssImage.title != null)
            return false;
        if (url != null ? !url.equals(rssImage.url) : rssImage.url != null)
            return false;

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
