package com.wemakestuff.teracast.rss.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.wemakestuff.teracast.Injector;
import com.wemakestuff.teracast.database.annotations.ContentType;
import com.wemakestuff.teracast.database.annotations.SortOrder;
import com.wemakestuff.teracast.database.annotations.UriPaths;
import com.wemakestuff.teracast.provider.FeedProvider;

import javax.inject.Inject;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = "feeds")
@UriPaths({ RssFeed.ENTITY_PL, RssFeed.ENTITY_PL + "/#" })
@ContentType(BaseData.MIME_TYPE_PFX + RssFeed.ENTITY)
@SortOrder(RssFeed.PUB_DATE + " DESC")
public class RssFeed extends BaseData implements Parcelable {
    public static final String           TAG       = RssFeed.class.getSimpleName();
    static              SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    public static final String ENTITY    = "feed";
    public static final String ENTITY_PL = ENTITY + "s";

    public static final String TITLE           = "title";
    public static final String LINK            = "link";
    public static final String DESCRIPTION     = "description";
    public static final String COPYRIGHT       = "copyright";
    public static final String GENERATOR       = "generator";
    public static final String LANGUAGE        = "language";
    public static final String ITUNES_SUMMARY  = "itunes_summary";
    public static final String ITUNES_SUBTITLE = "itunes_subtitle";
    public static final String ITUNES_KEYWORDS = "itunes_keywords";
    public static final String ITUNES_AUTHOR   = "itunes_author";
    public static final String ITUNES_IMAGE    = "itunes_image";
    public static final String ITUNES_EXPLICIT = "itunes_explicit";
    public static final String ITUNES_BLOCK    = "itunes_block";
    public static final String RSS_IMAGE       = "rss_image";
    public static final String LAST_BUILD_DATE = "last_build_date";
    public static final String CATEGORY        = "category";
    public static final String TTL             = "ttl";
    public static final String DOCS            = "docs";
    public static final String MANAGING_EDITOR = "docs";
    public static final String RSS_ITEMS       = "RssItems";

    // Needs to be public for the annotation
    public static final String PUB_DATE = "pub_date";

    @Inject
    FeedProvider provider;
//    @Inject
//    Dao<RssFeed, Integer> rssFeedDao;
//    @Inject
//    Dao<RssiTunesImage, Integer> iTunesImageDao;
//    @Inject
//    Dao<RssItem, Integer> rssItemDao;
//    @Inject
//    Dao<RssiTunesImage, Integer> rssITunesImageDao;
//    @Inject
//    Dao<RssMediaContent, Integer> rssMediaContentDao;
//    @Inject
//    Dao<RssEnclosure, Integer> rssEnclosureDao;
//    @Inject
//    Dao<RssImage, Integer> rssImageDao;
//    @Inject
//    Dao<RssGuid, Integer> rssGuidDao;

    @DatabaseField(columnName = TITLE, index = true)
    private String title;

    @DatabaseField(columnName = LINK)
    private String link;

    @DatabaseField(columnName = DESCRIPTION)
    private String description;

    @DatabaseField(columnName = COPYRIGHT)
    private String copyright;

    @DatabaseField(columnName = GENERATOR)
    private String generator;

    @DatabaseField(columnName = LANGUAGE)
    private String language;

    @DatabaseField(columnName = ITUNES_SUMMARY)
    private String iTunesSummary;

    @DatabaseField(columnName = ITUNES_SUBTITLE)
    private String iTunesSubtitle;

    @DatabaseField(columnName = ITUNES_KEYWORDS, index = true, dataType = DataType.STRING_BYTES)
    private String iTunesKeywords;

    @DatabaseField(columnName = ITUNES_AUTHOR)
    private String iTunesAuthor;

    @DatabaseField(columnName = ITUNES_IMAGE, canBeNull = true, foreign = true, foreignAutoCreate = true)
    private RssITunesImage iTunesImage;

    @DatabaseField(columnName = ITUNES_EXPLICIT)
    private Boolean iTunesExplicit;

    @DatabaseField(columnName = ITUNES_BLOCK)
    private Boolean iTunesBlock;

    @DatabaseField(columnName = RSS_IMAGE, canBeNull = true, foreign = true)
    private RssImage rssImage;

    @DatabaseField(columnName = LAST_BUILD_DATE)
    private Date lastBuildDate;

    @DatabaseField(columnName = PUB_DATE)
    private Date pubDate;

    @DatabaseField(columnName = CATEGORY)
    private String category;

    @DatabaseField(columnName = TTL)
    private int ttl;

    @DatabaseField(columnName = DOCS)
    private String docs;

    @DatabaseField(columnName = MANAGING_EDITOR)
    private String managingEditor;

    @ForeignCollectionField(eager = false, columnName = RSS_ITEMS)
    private ForeignCollection<RssItem> RssItemsForeignCollection;

    private List<RssItem> RssItems = new ArrayList<RssItem>();

    public RssFeed() {
        Injector.inject(this);
    }

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

    public void setiTunesImage(final RssITunesImage iTunesImage) {
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

    public RssFeed insertIntoDatabase() throws SQLException {
        Log.i("RssFeed", "provider: " + (provider == null ? "null" : "not null"));

        RssFeed copy = new RssFeed();
        copy.id = id;
        copy.title = title;
        copy.link = link;
        copy.description = description;
        copy.copyright = copyright;
        copy.generator = generator;
        copy.language = language;
        copy.RssItems = RssItems;
        copy.iTunesSummary = iTunesSummary;
        copy.iTunesSubtitle = iTunesSubtitle;
        copy.iTunesKeywords = iTunesKeywords;
        copy.iTunesAuthor = iTunesAuthor;
        copy.iTunesImage = iTunesImage;
        copy.iTunesExplicit = iTunesExplicit;
        copy.iTunesBlock = iTunesBlock;
        copy.rssImage = rssImage;
        copy.lastBuildDate = lastBuildDate;
        copy.pubDate = pubDate;
        copy.category = category;
        copy.ttl = ttl;
        copy.docs = docs;
        copy.managingEditor = managingEditor;


        /*
        iTunesImageDao.createIfNotExists(copy.iTunesImage);

        for (RssItem item : RssItems) {
            item.setRssFeed(copy);

            if (item.getGuid() != null)
                rssGuidDao.createIfNotExists(item.getGuid());

            if (item.getiTunesImage() != null)
                rssITunesImageDao.createIfNotExists(item.getiTunesImage());

            if (item.getEnclosure() != null)
                rssEnclosureDao.createIfNotExists(item.getEnclosure());

            if (item.getMediaContent() != null)
                rssMediaContentDao.createIfNotExists(item.getMediaContent());
        }

        rssFeedDao.createIfNotExists(copy);

        if (RssItems != null) {
            copy.RssItemsForeignCollection = rssFeedDao.getEmptyForeignCollection("RssItems");
            copy.RssItemsForeignCollection.addAll(RssItems);
        }

        */
        return copy;
    }

    public void setRssItems(final List<RssItem> newRssItems) {
        this.RssItems.addAll(newRssItems);
    }

    public void addRssItem(RssItem RssItem) {
        RssItems.add(RssItem);
    }

    public List<RssItem> getRssItems() {
        return RssItems;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RssFeed rssFeed = (RssFeed) o;

        if (ttl != rssFeed.ttl) return false;
        if (category != null ? !category.equals(rssFeed.category) : rssFeed.category != null)
            return false;
        if (copyright != null ? !copyright.equals(rssFeed.copyright) : rssFeed.copyright != null)
            return false;
        if (description != null ? !description.equals(rssFeed.description) : rssFeed.description != null)
            return false;
        if (docs != null ? !docs.equals(rssFeed.docs) : rssFeed.docs != null) return false;
        if (generator != null ? !generator.equals(rssFeed.generator) : rssFeed.generator != null)
            return false;
        if (iTunesAuthor != null ? !iTunesAuthor.equals(rssFeed.iTunesAuthor) : rssFeed.iTunesAuthor != null)
            return false;
        if (iTunesBlock != null ? !iTunesBlock.equals(rssFeed.iTunesBlock) : rssFeed.iTunesBlock != null)
            return false;
        if (iTunesExplicit != null ? !iTunesExplicit.equals(rssFeed.iTunesExplicit) : rssFeed.iTunesExplicit != null)
            return false;
        if (iTunesImage != null ? !iTunesImage.equals(rssFeed.iTunesImage) : rssFeed.iTunesImage != null)
            return false;
        if (!iTunesKeywords.equals(rssFeed.iTunesKeywords)) return false;
        if (iTunesSubtitle != null ? !iTunesSubtitle.equals(rssFeed.iTunesSubtitle) : rssFeed.iTunesSubtitle != null)
            return false;
        if (iTunesSummary != null ? !iTunesSummary.equals(rssFeed.iTunesSummary) : rssFeed.iTunesSummary != null)
            return false;
        if (RssItems != null ? !RssItems.equals(rssFeed.RssItems) : rssFeed.RssItems != null)
            return false;
        if (language != null ? !language.equals(rssFeed.language) : rssFeed.language != null)
            return false;
        if (lastBuildDate != null ? !lastBuildDate.equals(rssFeed.lastBuildDate) : rssFeed.lastBuildDate != null)
            return false;
        if (link != null ? !link.equals(rssFeed.link) : rssFeed.link != null) return false;
        if (managingEditor != null ? !managingEditor.equals(rssFeed.managingEditor) : rssFeed.managingEditor != null)
            return false;
        if (pubDate != null ? !pubDate.equals(rssFeed.pubDate) : rssFeed.pubDate != null)
            return false;
        if (rssImage != null ? !rssImage.equals(rssFeed.rssImage) : rssFeed.rssImage != null)
            return false;
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
        result = 31 * result + (iTunesKeywords != null ? iTunesKeywords.hashCode() : 0);
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
        result = 31 * result + (RssItems != null ? RssItems.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RssFeed{");
//        sb.append("rssFeedDao=").append(rssFeedDao);
        sb.append(", id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", copyright='").append(copyright).append('\'');
        sb.append(", generator='").append(generator).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", iTunesSummary='").append(iTunesSummary).append('\'');
        sb.append(", iTunesSubtitle='").append(iTunesSubtitle).append('\'');
        sb.append(", iTunesKeywords=").append(iTunesKeywords);
        sb.append(", iTunesAuthor='").append(iTunesAuthor).append('\'');
        sb.append(", RssiTunesImage=").append(iTunesImage);
        sb.append(", iTunesExplicit=").append(iTunesExplicit);
        sb.append(", iTunesBlock=").append(iTunesBlock);
        sb.append(", rssImage=").append(rssImage);
        sb.append(", lastBuildDate=").append(lastBuildDate);
        sb.append(", pubDate=").append(pubDate);
        sb.append(", category='").append(category).append('\'');
        sb.append(", ttl=").append(ttl);
        sb.append(", docs='").append(docs).append('\'');
        sb.append(", managingEditor='").append(managingEditor).append('\'');
        sb.append(", RssItemsForeignCollection=").append(RssItemsForeignCollection);

        if (RssItems != null) {
            sb.append(", RssItems(Size=").append(RssItems.size()).append(") : ");
            int i = 0;
            for (RssItem RssItem : RssItems) {
                sb.append("[ RssItem: ")
                        .append(i)
                        .append(" = ")
                        .append(RssItem)
                        .append(" ]");

                i++;
            }

        } else {
            sb.append("RssItems = null ");
        }

        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(description);
        dest.writeString(copyright);
        dest.writeString(generator);
        dest.writeString(language);
        dest.writeList(RssItems);
        dest.writeString(iTunesSummary);
        dest.writeString(iTunesSubtitle);
        dest.writeString(iTunesKeywords);
        dest.writeString(iTunesAuthor);
        dest.writeParcelable(iTunesImage, 0);
        dest.writeByte((byte) (iTunesExplicit ? 1 : 0));
        dest.writeByte((byte) (iTunesBlock ? 1 : 0));
        dest.writeParcelable(rssImage, 0);
        dest.writeLong(lastBuildDate == null ? null : lastBuildDate.getTime());
        dest.writeLong(pubDate == null ? null : pubDate.getTime());
        dest.writeString(category);
        dest.writeInt(ttl);
        dest.writeString(docs);
        dest.writeString(managingEditor);
    }

}
