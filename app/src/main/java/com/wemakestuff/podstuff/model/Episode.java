package com.wemakestuff.podstuff.model;

public class Episode {
	String name;
	String description;
	String url;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Episode{" +
		       "name='" + name + '\'' +
		       ", description='" + description + '\'' +
		       ", url='" + url + '\'' +
		       '}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Episode)) {
			return false;
		}

		final Episode episode = (Episode) o;

		if (description != null ? !description.equals(episode.description) : episode.description != null) {
			return false;
		}
		if (name != null ? !name.equals(episode.name) : episode.name != null) {
			return false;
		}
		if (url != null ? !url.equals(episode.url) : episode.url != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		return result;
	}
}
