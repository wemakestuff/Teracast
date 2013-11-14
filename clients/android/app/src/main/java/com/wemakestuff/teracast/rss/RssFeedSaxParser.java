package com.wemakestuff.teracast.rss;

import android.sax.*;
import android.util.Log;
import android.util.Xml;
import com.wemakestuff.teracast.rss.model.*;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

import static com.wemakestuff.teracast.rss.RssTags.*;

public class RssFeedSaxParser extends BaseFeedParser {
	private static final String        RSS            = "rss";
	private static final String        TAG            = RssFeedSaxParser.class.getSimpleName();
	private              RssImage      currentImage   = new RssImage();
	private              RssFeed       feed           = new RssFeed();
	private              RssItem       currentRssItem = new RssItem();
	private              List<RssItem> rssItems       = new ArrayList<RssItem>();

	public RssFeedSaxParser(String feedUrl) {
		super(feedUrl);

	}

	public RssFeed parse() {
		RootElement root = new RootElement(RSS);
		root.setEndElementListener(getRootEndElementListener());
		Element channel = root.getChild(CHANNEL);

		channel.getChild(TITLE).setEndTextElementListener(getChannelTitleEndTextElementListener());
		channel.getChild(LINK).setEndTextElementListener(getChannelLinkEndTextElementListener());
		channel.getChild(DESCRIPTION).setEndTextElementListener(getChannelDescriptionEndTextElementListener());
		channel.getChild(LAST_BUILD_DATE).setEndTextElementListener(getChannelLastBuildDateEndTextElementListener());
		channel.getChild(GENERATOR).setEndTextElementListener(getChannelGeneratorEndTextElementListener());
		channel.getChild(COPYRIGHT).setEndTextElementListener(getChannelCopyrightEndTextElementListener());
		channel.getChild(TTL).setEndTextElementListener(getChannelTtlEndTextElementListener());
		channel.getChild(CATEGORY).setEndTextElementListener(getChannelCategoryEndTextElementListener());
		channel.getChild(LANGUAGE).setEndTextElementListener(getChannelLanguageEndTextElementListener());
		channel.getChild(MANAGING_EDITOR).setEndTextElementListener(getChannelManagingEditorEndTextElementListener());
		channel.getChild(iTunes.NAMESPACE, iTunes.SUBTITLE).setEndTextElementListener(getChannelITunesSubtitleEndTextElementListener());
		channel.getChild(iTunes.NAMESPACE, iTunes.SUMMARY).setEndTextElementListener(getChannelITunesSummaryEndTextElementListener());
		channel.getChild(iTunes.NAMESPACE, iTunes.KEYWORDS).setEndTextElementListener(getChannelITunesKeywordsEndTextElementListener());
		channel.getChild(iTunes.NAMESPACE, iTunes.AUTHOR).setEndTextElementListener(getChannelITunesAuthorEndTextElementListener());
		channel.getChild(iTunes.NAMESPACE, iTunes.BLOCK).setEndTextElementListener(getChannelITunesBlockEndTextElementListener());
		channel.getChild(iTunes.NAMESPACE, iTunes.EXPLICIT).setEndTextElementListener(getChannelITunesExplicitEndTextElementListener());
		channel.getChild(iTunes.NAMESPACE, iTunes.IMAGE).setStartElementListener(getChannelITunesImageStartElementListener());

		Element image = channel.getChild(IMAGE);
		image.setEndElementListener(getImageEndTextElementListener());
		image.setStartElementListener(getImageStartElementListener());
		image.getChild(URL).setEndTextElementListener(getImageUrlEndTextElementListener());
		image.getChild(TITLE).setEndTextElementListener(getImageTitleEndTextElementListener());
		image.getChild(LINK).setEndTextElementListener(getImageLinkEndTextElementListener());
		image.getChild(WIDTH).setEndTextElementListener(getImageWidthEndTextElementListener());
		image.getChild(HEIGHT).setEndTextElementListener(getImageHeightEndTextElementListener());

		Element item = channel.getChild(ITEM);
		item.setStartElementListener(getItemStartElementListener());
		item.setEndElementListener(getItemEndElementListener());

		// iTunes Item Tags
		item.getChild(iTunes.NAMESPACE, iTunes.IMAGE).setEndTextElementListener(getItemITunesImageEndTextElementListener());
		item.getChild(iTunes.NAMESPACE, iTunes.SUMMARY).setEndTextElementListener(getItemITunesSummaryEndTextElementListener());
		item.getChild(iTunes.NAMESPACE, iTunes.KEYWORDS).setEndTextElementListener(getItemITunesKeywordsEndTextElementListener());
		item.getChild(iTunes.NAMESPACE, iTunes.SUBTITLE).setEndTextElementListener(getItemITunesSubtitleEndTextElementListener());
		item.getChild(iTunes.NAMESPACE, iTunes.AUTHOR).setEndTextElementListener(getItemITunesAuthorEndTextElementListener());
		item.getChild(iTunes.NAMESPACE, iTunes.DURATION).setEndTextElementListener(getItemITunesDurationEndTextElementListener());
		item.getChild(iTunes.NAMESPACE, iTunes.EXPLICIT).setEndTextElementListener(getItemITunesExplicitEndTextElementListener());
		item.getChild(iTunes.NAMESPACE, iTunes.BLOCK).setEndTextElementListener(getItemITunesBlockEndTextElementListener());

		// Media Namespace
		item.getChild(Media.NAMESPACE, Media.CONTENT).setStartElementListener(getItemMediaContentStartElementListener());

		// Standard item tags
		item.getChild(RssTags.Item.CATEGORY).setEndTextElementListener(getItemCategoryEndTextElementListener());
		item.getChild(RssTags.Item.ENCLOSURE).setStartElementListener(getItemEnclosureStartElementListener());
		item.getChild(RssTags.Item.GUID).setStartElementListener(getItemGuidStartElementListener());
		item.getChild(RssTags.Item.GUID).setEndTextElementListener(getItemGuidEndTextElementListener());
		item.getChild(RssTags.Item.TITLE).setEndTextElementListener(getItemTitleEndTextElementListener());
		item.getChild(RssTags.Item.LINK).setEndTextElementListener(getItemLinkEndTextElementListener());
		item.getChild(RssTags.Item.DESCRIPTION).setEndTextElementListener(getItemDescriptionEndTextElementListener());
		item.getChild(RssTags.Item.PUB_DATE).setEndTextElementListener(getItemPubDateEndTextElementListener());
		item.getChild(RssTags.Item.COMMENTS).setEndTextElementListener(getItemCommentsEndTextElementListener());

		// WFW tags
		item.getChild(Wfw.NAMESPACE, Wfw.COMMENT_RSS).setEndTextElementListener(getItemWfwCommentRssEndTextElementListener());

		// FeedBurner tags
		item.getChild(FeedBurner.NAMESPACE, FeedBurner.ORIGINAL_LINK).setEndTextElementListener(getItemFeedBurnerOrigLinkEndTextElementListener());

		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private EndElementListener getRootEndElementListener() {
		return new EndElementListener() {
			@Override
			public void end() {
				feed.setRssItems(rssItems);
			}
		};
	}

	private EndTextElementListener getChannelLanguageEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setLanguage(body);
			}
		};
	}

	private EndTextElementListener getChannelCategoryEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setCategory(body);
			}
		};
	}

	private EndTextElementListener getChannelTtlEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				try {
					feed.setTtl(Integer.valueOf(body));
				} catch (NumberFormatException e) {
					Log.d(TAG, "Error parsing TTL, value was: " + (body == null ? "null" : body));
				}
			}
		};
	}

	private EndTextElementListener getItemFeedBurnerOrigLinkEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setFeedBurnerOrigLink(body);
			}
		};
	}

	private EndTextElementListener getItemWfwCommentRssEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setWfwCommentRss(body);
			}
		};
	}

	private EndTextElementListener getItemCommentsEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setComments(body);
			}
		};
	}

	private EndTextElementListener getItemPubDateEndTextElementListener() {
		return new EndTextElementListener() {
			public void end(String body) {
				currentRssItem.setPubDate(body);
			}
		};
	}

	private EndTextElementListener getItemDescriptionEndTextElementListener() {
		return new EndTextElementListener() {
			public void end(String body) {
				currentRssItem.setDescription(body);
			}
		};
	}

	private EndTextElementListener getItemLinkEndTextElementListener() {
		return new EndTextElementListener() {
			public void end(String body) {
				currentRssItem.setLink(body);
			}
		};
	}

	private EndTextElementListener getItemGuidEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.getGuid().setUrl(body);
			}
		};
	}

	private StartElementListener getItemGuidStartElementListener() {
		return new StartElementListener() {
			@Override
			public void start(final Attributes attributes) {
				final String value = attributes.getValue(RssTags.Item.IS_PERMALINK);
				if (value != null) {
					RssGuid guid = new RssGuid();
					guid.setIsPermalink(Deserialize.fromBoolean(value));
					currentRssItem.setGuid(guid);
				}
			}
		};
	}

	private StartElementListener getItemEnclosureStartElementListener() {
		return new StartElementListener() {
			@Override
			public void start(final Attributes attributes) {
				RssEnclosure enclosure = new RssEnclosure();
				enclosure.setUrl(attributes.getValue(RssTags.Item.URL));
				enclosure.setType(attributes.getValue(RssTags.Item.ENCLOSURE_TYPE));

				try {
					long fileSize = Long.parseLong(attributes.getValue(RssTags.Item.ENCLOSURE_LENGTH));
					enclosure.setLength(fileSize);
				} catch (NumberFormatException e) {
					// ignored
				}
				currentRssItem.setEnclosure(enclosure);
			}
		};
	}

	private EndTextElementListener getItemCategoryEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setCategory(body);
			}
		};
	}

	private StartElementListener getItemMediaContentStartElementListener() {
		return new StartElementListener() {
			@Override
			public void start(final Attributes attributes) {
				RssMediaContent mediaContent = new RssMediaContent();
				mediaContent.setUrl(attributes.getValue(RssTags.Item.URL));
				mediaContent.setType(attributes.getValue(RssTags.Item.ENCLOSURE_TYPE));

				try {
					long fileSize = Long.parseLong(attributes.getValue(RssTags.Item.ENCLOSURE_LENGTH));
					mediaContent.setFileSize(fileSize);
				} catch (NumberFormatException e) {
					// ignored
				}
				currentRssItem.setMediaContent(mediaContent);
			}

		};
	}

	private EndTextElementListener getItemITunesBlockEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setiTunesBlock(Deserialize.fromBoolean(body));
			}
		};
	}

	private EndTextElementListener getItemITunesExplicitEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setiTunesExplicit(Deserialize.fromBoolean(body));
			}
		};
	}

	private EndTextElementListener getItemITunesDurationEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setiTunesDuration(body);
			}
		};
	}

	private EndTextElementListener getItemITunesAuthorEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setiTunesAuthor(body);
			}
		};
	}

	private EndTextElementListener getItemITunesSubtitleEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setiTunesSubtitle(body);
			}
		};
	}

	private EndTextElementListener getItemITunesKeywordsEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setiTunesKeywords(body);
			}
		};
	}

	private EndTextElementListener getItemITunesSummaryEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentRssItem.setiTunesSummary(body);
			}
		};
	}

	private EndTextElementListener getItemITunesImageEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
			}
		};
	}

	private StartElementListener getItemStartElementListener() {
		return new StartElementListener() {
			@Override
			public void start(final Attributes attributes) {
				currentRssItem = new RssItem();
			}
		};
	}

	private StartElementListener getImageStartElementListener() {
		return new StartElementListener() {
			@Override
			public void start(final Attributes attributes) {
				currentImage = new RssImage();
			}
		};
	}

	private EndTextElementListener getItemTitleEndTextElementListener() {
		return new EndTextElementListener() {
			public void end(String body) {
				currentRssItem.setTitle(body);
			}
		};
	}

	private EndElementListener getItemEndElementListener() {
		return new EndElementListener() {
			public void end() {
				rssItems.add(currentRssItem);
			}
		};
	}

	private StartElementListener getChannelITunesImageStartElementListener() {
		return new StartElementListener() {
			@Override
			public void start(final Attributes attributes) {
				RssITunesImage image = new RssITunesImage();
				image.setHref(attributes.getValue(iTunes.HREF));
				feed.setiTunesImage(image);
			}
		};
	}

	private EndTextElementListener getChannelITunesExplicitEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setiTunesExplicit(Deserialize.fromBoolean(body));

			}
		};
	}

	private EndTextElementListener getChannelITunesBlockEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setiTunesBlock(Deserialize.fromBoolean(body));
			}
		};
	}

	private EndTextElementListener getChannelITunesAuthorEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setiTunesAuthor(body);
			}
		};
	}

	private EndTextElementListener getChannelITunesKeywordsEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setiTunesKeywords(body);
			}
		};
	}

	private EndTextElementListener getChannelITunesSummaryEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setiTunesSummary(body);
			}
		};
	}

	private EndTextElementListener getChannelITunesSubtitleEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setiTunesSubtitle(body);
			}
		};
	}

	private EndElementListener getImageEndTextElementListener() {
		return new EndElementListener() {
			@Override
			public void end() {
				feed.setRssImage(currentImage);
			}
		};
	}

	private EndTextElementListener getImageHeightEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				if (body != null) {
					try {
						int height = Integer.parseInt(body);
						currentImage.setHeight(height);
					} catch (NumberFormatException e) {
						// ignored
					}
				}
			}
		};
	}

	private EndTextElementListener getImageWidthEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				if (body != null) {
					try {
						int width = Integer.parseInt(body);
						currentImage.setWidth(width);
					} catch (NumberFormatException e) {
						// ignored
					}
				}
			}
		};
	}

	private EndTextElementListener getImageLinkEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentImage.setLink(body);
			}
		};
	}

	private EndTextElementListener getImageTitleEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentImage.setTitle(body);
			}
		};
	}

	private EndTextElementListener getImageUrlEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				currentImage.setUrl(body);
			}
		};
	}

	private EndTextElementListener getChannelManagingEditorEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setManagingEditor(body);
			}
		};
	}

	private EndTextElementListener getChannelCopyrightEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setCopyright(body);
			}
		};
	}

	private EndTextElementListener getChannelGeneratorEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setGenerator(body);
			}
		};
	}

	private EndTextElementListener getChannelLastBuildDateEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setLastBuildDate(body);
			}
		};
	}

	private EndTextElementListener getChannelDescriptionEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setDescription(body);
			}
		};
	}

	private EndTextElementListener getChannelLinkEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setLink(body);
			}
		};
	}

	private EndTextElementListener getChannelTitleEndTextElementListener() {
		return new EndTextElementListener() {
			@Override
			public void end(final String body) {
				feed.setTitle(body);
			}
		};
	}
}
