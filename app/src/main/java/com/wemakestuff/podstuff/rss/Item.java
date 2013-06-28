package com.wemakestuff.podstuff.rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Item implements Comparable<Item> {
	static SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

	private String       title;
	private URL          link;
	private String       comments;
	private Date         pubDate;
	private String       category;
	private String       description;
	private Guid         guid;
	private RssEnclosure enclosure;

	private iTunesImage iTunesImage;

	private String   iTunesSummary;
	private String[] iTunesKeywords;
	private String   iTunesSubtitle;
	private String   iTunesAuthor;
	private String   iTunesDuration;

	private Boolean iTunesExplicit;

	private Boolean iTunesBlock;

	private MediaContent mediaContent;
	private String       feedBurnerOrigLink;

	private String wfwCommentRss;

	public Item copy() {
		Item copy = new Item();
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

	public MediaContent getMediaContent() {
		return mediaContent;
	}

	public void setMediaContent(final MediaContent mediaContent) {
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


	public void setLink(final URL link) {
		this.link = link;
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

	public void setPubDate(final Date pubDate) {
		this.pubDate = pubDate;
	}

	public Guid getGuid() {
		if (guid == null)
			guid = new Guid();

		return guid;
	}

	public void setGuid(final Guid guid) {
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

	public String[] getiTunesKeywords() {
		return iTunesKeywords;
	}

	public void setiTunesKeywords(final String[] iTunesKeywords) {
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

	public URL getLink() {
		return link;
	}

	public void setLink(String link) {
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
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

	public iTunesImage getiTunesImage() {
		return iTunesImage;
	}

	public void setiTunesImage(final iTunesImage iTunesImage) {
		this.iTunesImage = iTunesImage;
	}

	// sort by date
	public int compareTo(Item another) {
		if (another == null) return 1;
		// sort descending, most recent first
		return another.pubDate.compareTo(pubDate);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Item item = (Item) o;

		if (category != null ? !category.equals(item.category) : item.category != null) return false;
		if (comments != null ? !comments.equals(item.comments) : item.comments != null) return false;
		if (description != null ? !description.equals(item.description) : item.description != null) return false;
		if (enclosure != null ? !enclosure.equals(item.enclosure) : item.enclosure != null) return false;
		if (feedBurnerOrigLink != null ? !feedBurnerOrigLink.equals(item.feedBurnerOrigLink) : item.feedBurnerOrigLink != null)
			return false;
		if (guid != null ? !guid.equals(item.guid) : item.guid != null) return false;
		if (iTunesAuthor != null ? !iTunesAuthor.equals(item.iTunesAuthor) : item.iTunesAuthor != null) return false;
		if (iTunesBlock != null ? !iTunesBlock.equals(item.iTunesBlock) : item.iTunesBlock != null) return false;
		if (iTunesDuration != null ? !iTunesDuration.equals(item.iTunesDuration) : item.iTunesDuration != null)
			return false;
		if (iTunesExplicit != null ? !iTunesExplicit.equals(item.iTunesExplicit) : item.iTunesExplicit != null)
			return false;
		if (iTunesImage != null ? !iTunesImage.equals(item.iTunesImage) : item.iTunesImage != null) return false;
		if (!Arrays.equals(iTunesKeywords, item.iTunesKeywords)) return false;
		if (iTunesSubtitle != null ? !iTunesSubtitle.equals(item.iTunesSubtitle) : item.iTunesSubtitle != null)
			return false;
		if (iTunesSummary != null ? !iTunesSummary.equals(item.iTunesSummary) : item.iTunesSummary != null)
			return false;
		if (link != null ? !link.equals(item.link) : item.link != null) return false;
		if (mediaContent != null ? !mediaContent.equals(item.mediaContent) : item.mediaContent != null) return false;
		if (pubDate != null ? !pubDate.equals(item.pubDate) : item.pubDate != null) return false;
		if (title != null ? !title.equals(item.title) : item.title != null) return false;
		if (wfwCommentRss != null ? !wfwCommentRss.equals(item.wfwCommentRss) : item.wfwCommentRss != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (link != null ? link.hashCode() : 0);
		result = 31 * result + (comments != null ? comments.hashCode() : 0);
		result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (guid != null ? guid.hashCode() : 0);
		result = 31 * result + (enclosure != null ? enclosure.hashCode() : 0);
		result = 31 * result + (iTunesImage != null ? iTunesImage.hashCode() : 0);
		result = 31 * result + (iTunesSummary != null ? iTunesSummary.hashCode() : 0);
		result = 31 * result + (iTunesKeywords != null ? Arrays.hashCode(iTunesKeywords) : 0);
		result = 31 * result + (iTunesSubtitle != null ? iTunesSubtitle.hashCode() : 0);
		result = 31 * result + (iTunesAuthor != null ? iTunesAuthor.hashCode() : 0);
		result = 31 * result + (iTunesDuration != null ? iTunesDuration.hashCode() : 0);
		result = 31 * result + (iTunesExplicit != null ? iTunesExplicit.hashCode() : 0);
		result = 31 * result + (iTunesBlock != null ? iTunesBlock.hashCode() : 0);
		result = 31 * result + (mediaContent != null ? mediaContent.hashCode() : 0);
		result = 31 * result + (feedBurnerOrigLink != null ? feedBurnerOrigLink.hashCode() : 0);
		result = 31 * result + (wfwCommentRss != null ? wfwCommentRss.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Item{");
		sb.append("title='").append(title).append('\'');
		sb.append(", link=").append(link);
		sb.append(", comments='").append(comments).append('\'');
		sb.append(", pubDate=").append(pubDate);
		sb.append(", category='").append(category).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", guid=").append(guid);
		sb.append(", enclosure=").append(enclosure);
		sb.append(", iTunesImage=").append(iTunesImage);
		sb.append(", iTunesSummary='").append(iTunesSummary).append('\'');
		sb.append(", iTunesKeywords=").append(Arrays.toString(iTunesKeywords));
		sb.append(", iTunesSubtitle='").append(iTunesSubtitle).append('\'');
		sb.append(", iTunesAuthor='").append(iTunesAuthor).append('\'');
		sb.append(", iTunesDuration='").append(iTunesDuration).append('\'');
		sb.append(", iTunesExplicit=").append(iTunesExplicit);
		sb.append(", iTunesBlock=").append(iTunesBlock);
		sb.append(", mediaContent=").append(mediaContent);
		sb.append(", feedBurnerOrigLink='").append(feedBurnerOrigLink).append('\'');
		sb.append(", wfwCommentRss='").append(wfwCommentRss).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
