package com.wemakestuff.podstuff.model.api;

import java.util.Date;

public class Episode {
    String title;
    String description;
    Date releaseDate;
    String url;
    String iconUrl;
    Long length;

    public Episode(String title, String description, Date releaseDate, String url, String iconUrl, Long length) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.url = url;
        this.iconUrl = iconUrl;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", url='" + url + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Episode episode = (Episode) o;

        if (description != null ? !description.equals(episode.description) : episode.description != null)
            return false;
        if (iconUrl != null ? !iconUrl.equals(episode.iconUrl) : episode.iconUrl != null)
            return false;
        if (length != null ? !length.equals(episode.length) : episode.length != null) return false;
        if (releaseDate != null ? !releaseDate.equals(episode.releaseDate) : episode.releaseDate != null)
            return false;
        if (title != null ? !title.equals(episode.title) : episode.title != null) return false;
        if (url != null ? !url.equals(episode.url) : episode.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (iconUrl != null ? iconUrl.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        return result;
    }

    public Long getLength() {

        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getIconUrl() {

        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
