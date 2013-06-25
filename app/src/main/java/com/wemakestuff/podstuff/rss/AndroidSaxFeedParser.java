package com.wemakestuff.podstuff.rss;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

import java.util.ArrayList;
import java.util.List;

public class AndroidSaxFeedParser extends BaseFeedParser {

	public AndroidSaxFeedParser(String feedUrl) {
		super(feedUrl);
	}

	public List<Message> parse() {
		final Message currentMessage = new Message();
		RootElement root = new RootElement("rss");
		final List<Message> messages = new ArrayList<Message>();
		Element channel = root.getChild("channel");
		Element item = channel.getChild(RssTags.ITEM);
		item.setEndElementListener(new EndElementListener() {
			public void end() {
				messages.add(currentMessage.copy());
			}
		});
		item.getChild(RssTags.TITLE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentMessage.setTitle(body);
			}
		});
		item.getChild(RssTags.LINK).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentMessage.setLink(body);
			}
		});
		item.getChild(RssTags.DESCRIPTION).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentMessage.setDescription(body);
			}
		});
		item.getChild(RssTags.PUB_DATE).setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				currentMessage.setDate(body);
			}
		});
		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8,
					root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return messages;
	}
}