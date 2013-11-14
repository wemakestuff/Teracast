package com.wemakestuff.teracast.rss;

public abstract class FeedParserFactory {
	public static FeedParser getParser(String feedUrl) {
		return new RssFeedSaxParser(feedUrl);
	}
}
