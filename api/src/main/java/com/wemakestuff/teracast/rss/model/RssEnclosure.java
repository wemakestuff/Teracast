package com.wemakestuff.teracast.rss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RssEnclosure {
    public static final String ENTITY    = "enclosure";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final  String URL    = "url";
    public static final String LENGTH = "length";
    public static final String TYPE   = "type";

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("url")
    private String url;

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("length")
    private long length;

    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("type")
    private String type;

    public RssEnclosure() {
        super();
    }

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

        final RssEnclosure enclosure = (RssEnclosure) o;


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
