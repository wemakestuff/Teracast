package com.wemakestuff.teracast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

public class EpisodeCategory implements Parcelable {
    public static final Parcelable.Creator<EpisodeCategory> CREATOR
            = new Parcelable.Creator<EpisodeCategory>() {
        public EpisodeCategory createFromParcel(Parcel in) {
            return new EpisodeCategory(in);
        }

        public EpisodeCategory[] newArray(int size) {
            return new EpisodeCategory[size];
        }
    };
    String title;

    private EpisodeCategory(Parcel in) {
        title = in.readString();
    }

    public EpisodeCategory(String title) {
        this.title = title;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
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
