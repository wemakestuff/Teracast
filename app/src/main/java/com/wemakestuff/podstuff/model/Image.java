package com.wemakestuff.podstuff.model;

public class Image {
	String description;
	String url;

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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Image)) {
			return false;
		}

		final Image image = (Image) o;

		if (description != null ? !description.equals(image.description) : image.description != null) {
			return false;
		}
		if (url != null ? !url.equals(image.url) : image.url != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = description != null ? description.hashCode() : 0;
		result = 31 * result + (url != null ? url.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Image{" +
		       "description='" + description + '\'' +
		       ", url='" + url + '\'' +
		       '}';
	}
}
