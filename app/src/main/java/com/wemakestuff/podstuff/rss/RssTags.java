package com.wemakestuff.podstuff.rss;

public interface RssTags {
	final String PUB_DATE        = "pubDate";
	final String DESCRIPTION     = "description";
	final String LINK            = "link";
	final String TITLE           = "title";
	final String ITEM            = "item";
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


	public interface iTunes {
		final String ITUNES_PREFIX = "itunes:";
		final String SUMMARY       = ITUNES_PREFIX + "summary";
		final String SUBTITLE      = ITUNES_PREFIX + "subtitle";
		final String KEYWORDS      = ITUNES_PREFIX + "keywords";
		final String OWNER         = ITUNES_PREFIX + "owner";
		final String EMAIL         = ITUNES_PREFIX + "email";
		final String NAME          = ITUNES_PREFIX + "name";
		final String CATEGORY      = ITUNES_PREFIX + "category";
		final String IMAGE         = ITUNES_PREFIX + "image";
		final String EXPLICIT      = ITUNES_PREFIX + "explicit";
		final String TEXT          = ITUNES_PREFIX + "text";
		final String HREF          = ITUNES_PREFIX + "href";
		final String DURATION      = ITUNES_PREFIX + "duration";
	}

	public interface Npr {
		final String NPR_PREFIX       = "nprml";
		final String ORG_ID           = "orgId";
		final String ORG_ABBREVIATION = "orgAbbr";
		final String ORGANIZATION     = NPR_PREFIX + "organization";
		final String WEBSITE          = NPR_PREFIX + "website";
	}
}
