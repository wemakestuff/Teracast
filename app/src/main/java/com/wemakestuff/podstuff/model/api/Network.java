package com.wemakestuff.podstuff.model.api;

public class Network {
    String title;
    String description;

    public Network(String title, String description) {

        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Network{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Network network = (Network) o;

        if (description != null ? !description.equals(network.description) : network.description != null)
            return false;
        if (title != null ? !title.equals(network.title) : network.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
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
}
