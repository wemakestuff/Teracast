package com.wemakestuff.teracast.rss.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RssChannel {
    static SimpleDateFormat FORMATTER = new SimpleDateFormat(
                                              "EEE, dd MMM yyyy HH:mm:ss Z");

    @JacksonXmlProperty(namespace = "", localName = "title")
    private String          title;

//    @JacksonXmlProperty(localName = "link")
//    private String          link;
//
//    @JacksonXmlProperty(namespace = "atom10", localName = "link")
//    private RssAtomLink atomLink;
    
    @JacksonXmlProperty(namespace = "", localName = "description")
    private String          description;

    @JacksonXmlProperty(namespace = "", localName = "language")
    private String          language;

    @JacksonXmlProperty(namespace = "", localName = "lastBuildDate")
    private Date            lastBuildDate;

    @JacksonXmlProperty(namespace = "", localName = "ttl")
    private int             ttl;

    @JacksonXmlProperty(localName = "image")
    private RssImage        rssImage;

    @JacksonXmlProperty(namespace = "", localName = "copyright")
    private String          copyright;

    @JacksonXmlProperty(namespace = "", localName = "generator")
    private String          generator;

    @JacksonXmlProperty(namespace = "itunes", localName = "summary")
    private String          iTunesSummary;

    @JacksonXmlProperty(namespace = "itunes", localName = "subtitle")
    private String          iTunesSubtitle;

    @JacksonXmlProperty(namespace = "itunes", localName = "keywords")
    private String          iTunesKeywords;

    @JacksonXmlProperty(namespace = "itunes", localName = "author")
    private String          iTunesAuthor;

    @JacksonXmlProperty(namespace = "itunes", localName = "image")
    private RssITunesImage  iTunesImage;

    @JacksonXmlProperty(namespace = "itunes", localName = "explicit")
    private Boolean         iTunesExplicit;

    @JacksonXmlProperty(namespace = "itunes", localName = "block")
    private Boolean         iTunesBlock;

    @JacksonXmlProperty(namespace = "itunes", localName = "pubDate")
    private Date            pubDate;

//    @JacksonXmlProperty(namespace = "", localName = "item")
//    private List<RssItem>   items;

//    private String          category;

    private String          docs;

    private String          managingEditor;

    private String          version;

    // private ForeignCollection<RssItem> RssItemsForeignCollection;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(final String link) {
//        this.link = link;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(final String copyright) {
        this.copyright = copyright;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(final String generator) {
        this.generator = generator;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getiTunesSummary() {
        return iTunesSummary;
    }

    public void setiTunesSummary(final String iTunesSummary) {
        this.iTunesSummary = iTunesSummary;
    }

    public String getiTunesSubtitle() {
        return iTunesSubtitle;
    }

    public void setiTunesSubtitle(final String iTunesSubtitle) {
        this.iTunesSubtitle = iTunesSubtitle;
    }

    public String getiTunesKeywords() {
        return iTunesKeywords;
    }

    public void setiTunesKeywords(final String iTunesKeywords) {
        this.iTunesKeywords = iTunesKeywords;
    }

    public String getiTunesAuthor() {
        return iTunesAuthor;
    }

    public void setiTunesAuthor(final String iTunesAuthor) {
        this.iTunesAuthor = iTunesAuthor;
    }

    public RssITunesImage getiTunesImage() {
        return iTunesImage;
    }
//
//    public void setiTunesImage(final RssITunesImage iTunesImage) {
//        this.iTunesImage = iTunesImage;
//    }

    public Boolean getiTunesExplicit() {
        return iTunesExplicit;
    }

//    public void setiTunesExplicit(final Boolean iTunesExplicit) {
//        this.iTunesExplicit = iTunesExplicit;
//    }
    
    public void setiTunesExplicit(final String iTunesExplicit) {
        this.iTunesExplicit = "TRUE".equalsIgnoreCase(iTunesExplicit) || "YES".equalsIgnoreCase(iTunesExplicit);
    }

    public Boolean getiTunesBlock() {
        return iTunesBlock;
    }

    public void setiTunesBlock(final Boolean iTunesBlock) {
        this.iTunesBlock = iTunesBlock;
    }

    public RssImage getRssImage() {
        return rssImage;
    }
//
//    public void setRssImage(final RssImage rssImage) {
//        this.rssImage = rssImage;
//    }

    public String getLastBuildDate() {
        return FORMATTER.format(this.lastBuildDate);
    }

    public void setLastBuildDate(String date) {
        // pad the date if necessary
        while (!date.endsWith("00")) {
            date += "0";
        }
        try {
            this.lastBuildDate = FORMATTER.parse(date.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        // pad the date if necessary
        while (!pubDate.endsWith("00")) {
            pubDate += "0";
        }
        try {
            this.pubDate = FORMATTER.parse(pubDate.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(final String category) {
//        this.category = category;
//    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(final int ttl) {
        this.ttl = ttl;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(final String docs) {
        this.docs = docs;
    }

    public String getManagingEditor() {
        return managingEditor;
    }

    public void setManagingEditor(final String managingEditor) {
        this.managingEditor = managingEditor;
    }

//    public void setRssItems(final List<RssItem> newRssItems) {
//        this.RssItems.addAll(newRssItems);
//    }
//
//    public void addRssItem(RssItem RssItem) {
//        RssItems.add(RssItem);
//    }
//
//    public List<RssItem> getRssItems() {
//        return RssItems;
//    }

//    public List<RssItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<RssItem> items) {
//        this.items = items;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RssFeed{");
        // sb.append("rssFeedDao=").append(rssFeedDao);
        sb.append(", title='").append(title).append('\'');
//        sb.append(", link='").append(link).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", copyright='").append(copyright).append('\'');
        sb.append(", generator='").append(generator).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", iTunesSummary='").append(iTunesSummary).append('\'');
        sb.append(", iTunesSubtitle='").append(iTunesSubtitle).append('\'');
        sb.append(", iTunesKeywords=").append(iTunesKeywords);
        sb.append(", iTunesAuthor='").append(iTunesAuthor).append('\'');
//        sb.append(", RssiTunesImage=").append(iTunesImage);
        sb.append(", iTunesExplicit=").append(iTunesExplicit);
        sb.append(", iTunesBlock=").append(iTunesBlock);
//        sb.append(", rssImage=").append(rssImage);
        sb.append(", lastBuildDate=").append(lastBuildDate);
        sb.append(", pubDate=").append(pubDate);
//        sb.append(", category='").append(category).append('\'');
        sb.append(", ttl=").append(ttl);
        sb.append(", docs='").append(docs).append('\'');
        sb.append(", managingEditor='").append(managingEditor).append('\'');
        // sb.append(", RssItemsForeignCollection=").append(RssItemsForeignCollection);

//        if (RssItems != null) {
//            sb.append(", RssItems(Size=").append(RssItems.size())
//                    .append(") : ");
//            int i = 0;
//            for (RssItem RssItem : RssItems) {
//                sb.append("[ RssItem: ").append(i).append(" = ")
//                        .append(RssItem).append(" ]");
//
//                i++;
//            }
//
//        } else {
//            sb.append("RssItems = null ");
//        }

        sb.append('}');
        return sb.toString();
    }
}
