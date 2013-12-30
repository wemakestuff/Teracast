package com.wemakestuff.teracast.rss.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RssItem implements Comparable<RssItem> {
    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    @JsonProperty("title")
    private String title;

    @JsonProperty("link")
    private String link;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("pubDate")
    private Date pubDate;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;

    @JsonProperty("guid")
    private RssGuid guid;

    @JsonProperty("enclosure")
    private RssEnclosure enclosure;

    @JsonProperty("itunes:image")
    private RssITunesImage iTunesImage;

    @JsonProperty("itunes:summary")
    private String iTunesSummary;

    @JsonProperty("itunes:keywords")
    private String iTunesKeywords;

    @JsonProperty("itunes:subtitle")
    private String iTunesSubtitle;

    @JsonProperty("itunes:author")
    private String iTunesAuthor;

    @JsonProperty("itunes:duration")
    private String iTunesDuration;

    @JsonProperty("itunes:explicit")
    private Boolean iTunesExplicit;

    @JsonProperty("itunes:block")
    private Boolean iTunesBlock;

    @JsonProperty("")
    private RssMediaContent mediaContent;

    @JsonProperty("")
    private String feedBurnerOrigLink;
    
    @JsonProperty("")
    private String wfwCommentRss;
    
    public RssItem copy() {
        RssItem copy = new RssItem();
        copy.title = title;
        copy.link = link;
        copy.comments = comments;
        copy.pubDate = new Date(pubDate.getTime());
        copy.category = category;
        copy.description = description;
        copy.guid = guid;
        copy.enclosure = enclosure;
        copy.iTunesSummary = iTunesSummary;
        copy.iTunesKeywords = iTunesKeywords;
        copy.iTunesSubtitle = iTunesSubtitle;
        copy.iTunesAuthor = iTunesAuthor;
        copy.iTunesDuration = iTunesDuration;
        copy.iTunesExplicit = iTunesExplicit;
        copy.iTunesBlock = iTunesBlock;
        copy.mediaContent = mediaContent;
        copy.feedBurnerOrigLink = feedBurnerOrigLink;
        copy.wfwCommentRss = wfwCommentRss;

        return copy;
    }

    public Boolean getiTunesBlock() {
        return iTunesBlock;
    }

    public void setiTunesBlock(final Boolean iTunesBlock) {
        this.iTunesBlock = iTunesBlock;
    }

    public RssMediaContent getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(final RssMediaContent mediaContent) {
        this.mediaContent = mediaContent;
    }

    public String getFeedBurnerOrigLink() {
        return feedBurnerOrigLink;
    }

    public void setFeedBurnerOrigLink(final String feedBurnerOrigLink) {
        this.feedBurnerOrigLink = feedBurnerOrigLink;
    }

    public String getWfwCommentRss() {
        return wfwCommentRss;
    }

    public void setWfwCommentRss(final String wfwCommentRss) {
        this.wfwCommentRss = wfwCommentRss;
    }

    public Boolean getiTunesExplicit() {
        return iTunesExplicit;
    }

    public void setiTunesExplicit(final Boolean iTunesExplicit) {
        this.iTunesExplicit = iTunesExplicit;
    }


    public String getiTunesDuration() {
        return iTunesDuration;
    }

    public void setiTunesDuration(final String iTunesDuration) {
        this.iTunesDuration = iTunesDuration;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

//    public void setPubDate(final Date pubDate) {
//        this.pubDate = pubDate;
//    }

    public RssGuid getGuid() {
        if (guid == null)
            guid = new RssGuid();

        return guid;
    }

    public void setGuid(final RssGuid guid) {
        this.guid = guid;
    }

    public RssEnclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(final RssEnclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getiTunesSummary() {
        return iTunesSummary;
    }

    public void setiTunesSummary(final String iTunesSummary) {
        this.iTunesSummary = iTunesSummary;
    }

    public String getiTunesKeywords() {
        return iTunesKeywords;
    }

    public void setiTunesKeywords(final String iTunesKeywords) {
        this.iTunesKeywords = iTunesKeywords;
    }

    public String getiTunesSubtitle() {
        return iTunesSubtitle;
    }

    public void setiTunesSubtitle(final String iTunesSubtitle) {
        this.iTunesSubtitle = iTunesSubtitle;
    }

    public String getiTunesAuthor() {
        return iTunesAuthor;
    }

    public void setiTunesAuthor(final String iTunesAuthor) {
        this.iTunesAuthor = iTunesAuthor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return FORMATTER.format(this.pubDate);
    }

    public void setPubDate(String date) {
        // pad the date if necessary
        while (!date.endsWith("00")) {
            date += "0";
        }
        try {
            this.pubDate = FORMATTER.parse(date.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public RssITunesImage getiTunesImage() {
        return iTunesImage;
    }

    public void setiTunesImage(final RssITunesImage iTunesImage) {
        this.iTunesImage = iTunesImage;
    }

    // sort by date
    public int compareTo(RssItem another) {
        if (another == null) return 1;
        // sort descending, most recent first
        return another.pubDate.compareTo(pubDate);
    }

}
