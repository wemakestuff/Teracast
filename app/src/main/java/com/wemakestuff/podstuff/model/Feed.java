package com.wemakestuff.podstuff.model;

import java.util.List;

public class Feed {
	String        name;
	String        url;
	Image         feedIcon;
	Image         networkIcon;
	List<Episode> episodeList;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Image getFeedIcon() {
		return feedIcon;
	}

	public void setFeedIcon(final Image feedIcon) {
		this.feedIcon = feedIcon;
	}

	public Image getNetworkIcon() {
		return networkIcon;
	}

	public void setNetworkIcon(final Image networkIcon) {
		this.networkIcon = networkIcon;
	}

	public List<Episode> getEpisodeList() {
		return episodeList;
	}

	public void setEpisodeList(final List<Episode> episodeList) {
		this.episodeList = episodeList;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Feed)) {
			return false;
		}

		final Feed feed = (Feed) o;

		if (episodeList != null ? !episodeList.equals(feed.episodeList) : feed.episodeList != null) {
			return false;
		}
		if (feedIcon != null ? !feedIcon.equals(feed.feedIcon) : feed.feedIcon != null) {
			return false;
		}
		if (name != null ? !name.equals(feed.name) : feed.name != null) {
			return false;
		}
		if (networkIcon != null ? !networkIcon.equals(feed.networkIcon) : feed.networkIcon != null) {
			return false;
		}
		if (url != null ? !url.equals(feed.url) : feed.url != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (feedIcon != null ? feedIcon.hashCode() : 0);
		result = 31 * result + (networkIcon != null ? networkIcon.hashCode() : 0);
		result = 31 * result + (episodeList != null ? episodeList.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Feed{" +
		       "name='" + name + '\'' +
		       ", url='" + url + '\'' +
		       ", feedIcon=" + feedIcon +
		       ", networkIcon=" + networkIcon +
		       ", episodeList=" + episodeList +
		       '}';
	}
}
