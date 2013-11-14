package com.wemakestuff.teracast.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.wemakestuff.teracast.database.annotations.ContentType;
import com.wemakestuff.teracast.database.annotations.ProvideContentUri;
import com.wemakestuff.teracast.database.annotations.SortOrder;
import com.wemakestuff.teracast.database.annotations.UriPaths;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ProvideContentUri
@DatabaseTable(tableName = "items")
@UriPaths({ RssItem.ENTITY_PL, RssItem.ENTITY_PL + "/#" })
@ContentType(BaseData.MIME_TYPE_PFX + RssItem.ENTITY)
@SortOrder(RssItem.PUB_DATE + " DESC")
public class RssItem extends BaseData implements Comparable<RssItem>, Parcelable {
    public static final String ENTITY    = "item";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final  String PUB_DATE             = "pub_date";
    private static final String TITLE                = "title";
    private static final String LINK                 = "link";
    private static final String COMMENTS             = "comments";
    private static final String CATEGORY             = "category";
    private static final String DESCRIPTION          = "description";
    private static final String GUID                 = "guid";
    private static final String ENCLOSURE            = "enclosure";
    private static final String ITUNES_IMAGE         = "enclosure";
    private static final String ITUNES_SUMMARY       = "itunes_summary";
    private static final String ITUNES_KEYWORDS      = "itunes_keywords";
    private static final String ITUNES_SUBTITLE      = "itunes_subtitle";
    private static final String ITUNES_AUTHOR        = "itunes_author";
    private static final String ITUNES_DURATION      = "itunes_duration";
    private static final String ITUNES_EXPLICIT      = "itunes_explicit";
    private static final String ITUNES_BLOCK         = "itunes_block";
    private static final String MEDIA_CONTENT        = "media_content";
    private static final String FEEDBURNER_ORIG_LINK = "feedburner_orig_link";
    private static final String WFW_COMMENTS         = "wfw_comments";
    private static final String RSS_FEED             = "rss_feed";

    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    public static final Parcelable.Creator<RssItem> CREATOR = new Parcelable.Creator<RssItem>() {

        @Override
        public RssItem createFromParcel(Parcel source) {
        return new RssItem(source);
        }

        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };

    @DatabaseField(columnName = TITLE)
    private String title;

    @DatabaseField(columnName = LINK)
    private String link;

    @DatabaseField(columnName = COMMENTS)
    private String comments;

    @DatabaseField(columnName = PUB_DATE)
    private Date pubDate;

    @DatabaseField(columnName = CATEGORY)
    private String category;

    @DatabaseField(columnName = DESCRIPTION)
    private String description;

    @DatabaseField(columnName = GUID, canBeNull = true, foreign = true)
    private RssGuid guid;

    @DatabaseField(columnName = ENCLOSURE, canBeNull = true, foreign = true)
    private RssEnclosure enclosure;

    @DatabaseField(columnName = ITUNES_IMAGE, canBeNull = true, foreign = true)
    private RssITunesImage iTunesImage;

    @DatabaseField(columnName = ITUNES_SUMMARY)
    private String iTunesSummary;

    @DatabaseField(columnName = ITUNES_KEYWORDS, index = true)
    private String iTunesKeywords;

    @DatabaseField(columnName = ITUNES_SUBTITLE)
    private String iTunesSubtitle;

    @DatabaseField(columnName = ITUNES_AUTHOR)
    private String iTunesAuthor;

    @DatabaseField(columnName = ITUNES_DURATION)
    private String iTunesDuration;

    @DatabaseField(columnName = ITUNES_EXPLICIT)
    private Boolean iTunesExplicit;

    @DatabaseField(columnName = ITUNES_BLOCK)
    private Boolean iTunesBlock;

    @DatabaseField(columnName = MEDIA_CONTENT, canBeNull = true, foreign = true)
    private RssMediaContent mediaContent;

    @DatabaseField(columnName = FEEDBURNER_ORIG_LINK)
    private String feedBurnerOrigLink;

    @DatabaseField(columnName = WFW_COMMENTS)
    private String wfwCommentRss;

	/**
	 * Needed so that ORMLite can find the items that match a particular rssFeed
	 */
    @DatabaseField(columnName = RSS_FEED, foreign = true)
    private RssFeed rssFeed;

	public RssItem() {
	}

	private RssItem(Parcel in) {
		title = in.readString();
		link = in.readString();
		pubDate = new Date(in.readLong());
		category = in.readString();
		description = in.readString();
		guid = in.readParcelable(RssGuid.class.getClassLoader());
		enclosure = in.readParcelable(RssEnclosure.class.getClassLoader());
		iTunesImage = in.readParcelable(RssITunesImage.class.getClassLoader());
		iTunesSummary = in.readString();
		iTunesKeywords = in.readString();
		iTunesAuthor = in.readString();
		iTunesDuration = in.readString();
		iTunesExplicit = in.readByte() == 1 ? true : false;
		iTunesBlock = in.readByte() == 1 ? true : false;
		mediaContent = in.readParcelable(RssMediaContent.class.getClassLoader());
		feedBurnerOrigLink = in.readString();
		wfwCommentRss = in.readString();
	}

    public RssItem copy() {
        RssItem copy = new RssItem();
        copy.id = id;
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

    public void setPubDate(final Date pubDate) {
        this.pubDate = pubDate;
    }

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

    public RssFeed getRssFeed() {
        return rssFeed;
    }

    public void setRssFeed(final RssFeed rssFeed) {
        this.rssFeed = rssFeed;
    }

	@Override
	public int describeContents() {
		return hashCode();
	}

	@Override
	public void writeToParcel(final Parcel out, final int flags) {
		out.writeString(title);
		out.writeString(link);
		out.writeString(comments);
		out.writeLong(pubDate.getTime());
		out.writeString(category);
		out.writeString(description);
		out.writeParcelable(guid, 0);
		out.writeParcelable(enclosure, 0);
		out.writeParcelable(iTunesImage, 0);
		out.writeString(iTunesSummary);
		out.writeString(iTunesKeywords);
		out.writeString(iTunesAuthor);
		out.writeString(iTunesDuration);
		out.writeByte((byte) (iTunesExplicit ? 1 : 0));
		out.writeByte((byte) (iTunesBlock ? 1 : 0));
		out.writeParcelable(mediaContent, 0);
		out.writeString(feedBurnerOrigLink);
		out.writeString(wfwCommentRss);
	}
}
