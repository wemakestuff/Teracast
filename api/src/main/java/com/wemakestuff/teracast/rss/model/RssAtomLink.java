package com.wemakestuff.teracast.rss.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class RssAtomLink {

    @JacksonXmlProperty(localName = "rel", isAttribute = true)
    private String rel;
    
    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;
    
    @JacksonXmlProperty(localName = "href", isAttribute = true)
    private String href;
    
    @Override
    public String toString() {
        return "RssAtomLink [rel=" + rel + ", type=" + type + ", href=" + href
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((href == null) ? 0 : href.hashCode());
        result = prime * result + ((rel == null) ? 0 : rel.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RssAtomLink other = (RssAtomLink) obj;
        if (href == null) {
            if (other.href != null)
                return false;
        } else if (!href.equals(other.href))
            return false;
        if (rel == null) {
            if (other.rel != null)
                return false;
        } else if (!rel.equals(other.rel))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    public String getRel() {
        return rel;
    }
    
    public void setRel(String rel) {
        this.rel = rel;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
}
