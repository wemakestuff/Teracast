package com.wemakestuff.teracast.rss;

public interface RssTags {

	final String RSS  = "rss";
	final String ITEM = "item";

	final String PUB_DATE        = "pubDate";
	final String LINK            = "link";
	final String TITLE           = "title";
	final String WIDTH           = "width";
	final String HEIGHT          = "height";
	final String MANAGING_EDITOR = "managingEditor";
	final String WEB_MASTER      = "webMaster";
	final String DESCRIPTION     = "description";
	final String LANGUAGE        = "language";
	final String CHANNEL         = "channel";
	final String COPYRIGHT       = "copyright";
	final String GENERATOR       = "generator";
	final String IMAGE           = "image";
	final String URL             = "url";
	final String LAST_BUILD_DATE = "lastBuildDate";
	final String GUID            = "guid";
	final String ENCLOSURE       = "enclosure";
	final String LENGTH          = "length";
	final String TYPE            = "type";
	final String CATEGORY        = "category";
	final String TTL             = "ttl";


	public interface Wfw {
		final String NAMESPACE   = "http://wellformedweb.org/CommentAPI/";
		final String COMMENT_RSS = "commentRss";

	}

	public interface Item {
		final String COMMENTS         = "comments";
		final String GUID             = "guid";
		final String ENCLOSURE        = "enclosure";
		final String ENCLOSURE_LENGTH = "length";
		final String ENCLOSURE_TYPE   = "type";
		final String URL              = "url";
		final String PUB_DATE         = "pubDate";
		final String LINK             = "link";
		final String TITLE            = "title";
		final String DESCRIPTION      = "description";
		final String CATEGORY         = "category";
		final String IS_PERMALINK     = "isPermaLink";
	}

	public interface Media {
		final String NAMESPACE   = "http://search.yahoo.com/mrss/";
		final String CONTENT     = "content";
		final String CREDIT      = "credit";
		final String DESCRIPTION = "description";
		final String LENGTH      = "length";
		final String RATING      = "rating";
		final String ROLE        = "role";
		final String TYPE        = "type";
	}

	public interface NPR {
		final String NAMESPACE        = "http://api.npr.org/nprml";
		final String ORG_ID           = "orgId";
		final String ORG_ABBREVIATION = "orgAbbr";
		final String ORGANIZATION     = "organization";
		final String WEBSITE          = "website";
	}

	public interface FeedBurner {
		final String NAMESPACE     = "http://rssnamespace.org/feedburner/ext/1.0";
		final String ORIGINAL_LINK = "origLink";
	}

	public interface iTunes {
		final String NAMESPACE = "http://www.itunes.com/dtds/podcast-1.0.dtd";
		final String AUTHOR    = "author";
		final String CATEGORY  = "category";
		final String DURATION  = "duration";
		final String EMAIL     = "email";
		final String EXPLICIT  = "explicit";
		final String HREF      = "href";
		final String IMAGE     = "image";
		final String BLOCK     = "block";
		final String KEYWORDS  = "keywords";
		final String NAME      = "name";
		final String OWNER     = "owner";
		final String SUBTITLE  = "subtitle";
		final String SUMMARY   = "summary";
		final String TEXT      = "text";
	}

}
