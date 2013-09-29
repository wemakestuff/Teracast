package com.wemakestuff.podstuff.model.api;

public class Podcast {
    String title;
    String url;
    String description;
    String imageUrl;
    String iconUrl;

    public Podcast(String title, String url, String description, String imageUrl, String iconUrl) {

        this.title = title;
        this.url = url;
        this.description = description;
        this.imageUrl = imageUrl;
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Podcast podcast = (Podcast) o;

        if (description != null ? !description.equals(podcast.description) : podcast.description != null)
            return false;
        if (iconUrl != null ? !iconUrl.equals(podcast.iconUrl) : podcast.iconUrl != null)
            return false;
        if (imageUrl != null ? !imageUrl.equals(podcast.imageUrl) : podcast.imageUrl != null)
            return false;
        if (title != null ? !title.equals(podcast.title) : podcast.title != null) return false;
        if (url != null ? !url.equals(podcast.url) : podcast.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (iconUrl != null ? iconUrl.hashCode() : 0);
        return result;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
