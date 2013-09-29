package com.wemakestuff.podstuff.model.api;

public class EpisodeCategory {
    String title;

    public EpisodeCategory(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EpisodeCategory{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EpisodeCategory that = (EpisodeCategory) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
