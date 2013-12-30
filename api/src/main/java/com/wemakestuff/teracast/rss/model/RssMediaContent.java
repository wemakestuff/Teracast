package com.wemakestuff.teracast.rss.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RssMediaContent {
	
	@JsonProperty("url")
    private String url;

	@JsonProperty("fileSize")
    private long fileSize;

	@JsonProperty("file")
    private String type;

    public RssMediaContent() {

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
}
