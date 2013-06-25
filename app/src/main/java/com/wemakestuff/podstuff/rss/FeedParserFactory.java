package com.wemakestuff.podstuff.rss;

public abstract class FeedParserFactory {
	public static FeedParser getParser(String feedUrl) {
		return new SaxFeedParser(feedUrl);
	}
}
