package com.wemakestuff.podstuff.rss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RssFeed {
	public static final String           TAG       = RssFeed.class.getSimpleName();
	static              SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

	private String   title;
	private String   link;
	private String   description;
	private String   copyright;
	private String   generator;
	private String   language;
	private String   iTunesSummary;
	private String   iTunesSubtitle;
	private String[] iTunesKeywords;
	private String   iTunesAuthor;
	private iTunesImage   iTunesImage;
	private Boolean  iTunesExplicit;
	private Boolean  iTunesBlock;
	private RssImage rssImage;
	private Date     lastBuildDate;
	private Date     pubDate;
	private String   category;
	private int      ttl;
	private String   docs;
	private String   managingEditor;

	private List<Item> items;


	public static String getTag() {
		return TAG;
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

	public String[] getiTunesKeywords() {
		return iTunesKeywords;
	}

	public void setiTunesKeywords(final String[] iTunesKeywords) {
		this.iTunesKeywords = iTunesKeywords;
	}

	public String getiTunesAuthor() {
		return iTunesAuthor;
	}

	public void setiTunesAuthor(final String iTunesAuthor) {
		this.iTunesAuthor = iTunesAuthor;
	}

	public iTunesImage getiTunesImage() {
		return iTunesImage;
	}

	public void setiTunesImage(final iTunesImage iTunesImage) {
		this.iTunesImage = iTunesImage;
	}

	public Boolean getiTunesExplicit() {
		return iTunesExplicit;
	}

	public void setiTunesExplicit(final Boolean iTunesExplicit) {
		this.iTunesExplicit = iTunesExplicit;
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

	public void setRssImage(final RssImage rssImage) {
		this.rssImage = rssImage;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

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

	public void setItems(final List<Item> items) {
		this.items = items;
	}

	public RssFeed() {
	}

	public void addItem(Item item) {
		getItems().add(item);
	}

	public void addAll(List<Item> items) {
		getItems().addAll(items);
	}

	public List<Item> getItems() {
		if (items == null)
			items = new ArrayList<Item>();

		return items;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final RssFeed rssFeed = (RssFeed) o;

		if (ttl != rssFeed.ttl) return false;
		if (category != null ? !category.equals(rssFeed.category) : rssFeed.category != null) return false;
		if (copyright != null ? !copyright.equals(rssFeed.copyright) : rssFeed.copyright != null) return false;
		if (description != null ? !description.equals(rssFeed.description) : rssFeed.description != null) return false;
		if (docs != null ? !docs.equals(rssFeed.docs) : rssFeed.docs != null) return false;
		if (generator != null ? !generator.equals(rssFeed.generator) : rssFeed.generator != null) return false;
		if (iTunesAuthor != null ? !iTunesAuthor.equals(rssFeed.iTunesAuthor) : rssFeed.iTunesAuthor != null)
			return false;
		if (iTunesBlock != null ? !iTunesBlock.equals(rssFeed.iTunesBlock) : rssFeed.iTunesBlock != null) return false;
		if (iTunesExplicit != null ? !iTunesExplicit.equals(rssFeed.iTunesExplicit) : rssFeed.iTunesExplicit != null)
			return false;
		if (iTunesImage != null ? !iTunesImage.equals(rssFeed.iTunesImage) : rssFeed.iTunesImage != null) return false;
		if (!Arrays.equals(iTunesKeywords, rssFeed.iTunesKeywords)) return false;
		if (iTunesSubtitle != null ? !iTunesSubtitle.equals(rssFeed.iTunesSubtitle) : rssFeed.iTunesSubtitle != null)
			return false;
		if (iTunesSummary != null ? !iTunesSummary.equals(rssFeed.iTunesSummary) : rssFeed.iTunesSummary != null)
			return false;
		if (items != null ? !items.equals(rssFeed.items) : rssFeed.items != null) return false;
		if (language != null ? !language.equals(rssFeed.language) : rssFeed.language != null) return false;
		if (lastBuildDate != null ? !lastBuildDate.equals(rssFeed.lastBuildDate) : rssFeed.lastBuildDate != null)
			return false;
		if (link != null ? !link.equals(rssFeed.link) : rssFeed.link != null) return false;
		if (managingEditor != null ? !managingEditor.equals(rssFeed.managingEditor) : rssFeed.managingEditor != null)
			return false;
		if (pubDate != null ? !pubDate.equals(rssFeed.pubDate) : rssFeed.pubDate != null) return false;
		if (rssImage != null ? !rssImage.equals(rssFeed.rssImage) : rssFeed.rssImage != null) return false;
		if (title != null ? !title.equals(rssFeed.title) : rssFeed.title != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (link != null ? link.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (copyright != null ? copyright.hashCode() : 0);
		result = 31 * result + (generator != null ? generator.hashCode() : 0);
		result = 31 * result + (language != null ? language.hashCode() : 0);
		result = 31 * result + (iTunesSummary != null ? iTunesSummary.hashCode() : 0);
		result = 31 * result + (iTunesSubtitle != null ? iTunesSubtitle.hashCode() : 0);
		result = 31 * result + (iTunesKeywords != null ? Arrays.hashCode(iTunesKeywords) : 0);
		result = 31 * result + (iTunesAuthor != null ? iTunesAuthor.hashCode() : 0);
		result = 31 * result + (iTunesImage != null ? iTunesImage.hashCode() : 0);
		result = 31 * result + (iTunesExplicit != null ? iTunesExplicit.hashCode() : 0);
		result = 31 * result + (iTunesBlock != null ? iTunesBlock.hashCode() : 0);
		result = 31 * result + (rssImage != null ? rssImage.hashCode() : 0);
		result = 31 * result + (lastBuildDate != null ? lastBuildDate.hashCode() : 0);
		result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		result = 31 * result + ttl;
		result = 31 * result + (docs != null ? docs.hashCode() : 0);
		result = 31 * result + (managingEditor != null ? managingEditor.hashCode() : 0);
		result = 31 * result + (items != null ? items.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RssFeed{");
		sb.append("title='").append(title).append('\'');
		sb.append(", link='").append(link).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", copyright='").append(copyright).append('\'');
		sb.append(", generator='").append(generator).append('\'');
		sb.append(", language='").append(language).append('\'');
		sb.append(", iTunesSummary='").append(iTunesSummary).append('\'');
		sb.append(", iTunesSubtitle='").append(iTunesSubtitle).append('\'');
		sb.append(", iTunesKeywords=").append(Arrays.toString(iTunesKeywords));
		sb.append(", iTunesAuthor='").append(iTunesAuthor).append('\'');
		sb.append(", iTunesImage='").append(iTunesImage).append('\'');
		sb.append(", iTunesExplicit=").append(iTunesExplicit);
		sb.append(", iTunesBlock=").append(iTunesBlock);
		sb.append(", rssImage=").append(rssImage);
		sb.append(", lastBuildDate=").append(lastBuildDate);
		sb.append(", pubDate=").append(pubDate);
		sb.append(", category='").append(category).append('\'');
		sb.append(", ttl=").append(ttl);
		sb.append(", docs='").append(docs).append('\'');
		sb.append(", managingEditor='").append(managingEditor).append('\'');

		if (items != null) {
			sb.append(", items(Size=").append(items.size()).append(") : ");
			for (int i = 0; i < items.size(); i++) {
				sb.append("[ Item: ")
				  .append(i)
				  .append(" = ")
				  .append(items.get(i))
				  .append(" ]");
			}

		} else {
			sb.append("items = null ");
		}

		sb.append('}');

		return sb.toString();
	}
}
