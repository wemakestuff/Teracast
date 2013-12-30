package com.wemakestuff.teracast.rss.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RssFeed  {
    public static final String           TAG       = RssFeed.class.getSimpleName();

    @JacksonXmlProperty(localName = "channel")
    @JacksonXmlElementWrapper(useWrapping = false)
    RssChannel channels;
//    List<RssChannel> channels;
    
    @JacksonXmlProperty(isAttribute = true)
    private String version;
    
    @JacksonXmlProperty(isAttribute = true)
    private String encoding;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public RssChannel getChannel() {
        return channels;
    }

    public void setChannel(RssChannel channel) {
        this.channels = channel;
    }


}
