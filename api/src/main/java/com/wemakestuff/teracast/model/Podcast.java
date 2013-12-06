package com.wemakestuff.teracast.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "podcast")
public class Podcast implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static class Views
    {
        public static class DefaultView
        {
        }

        public static class DetailedView extends DefaultView
        {
        }
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "podcast_id")
    @JsonView(Views.DefaultView.class)
    private Long         podcastId;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @JsonView(Views.DefaultView.class)
    private String       name;

    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug must only contain lowercase letters, numbers, and dashes.")
    @Column(name = "slug")
    @JsonView(Views.DefaultView.class)
    private String       slug;

    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "description")
    @JsonView(Views.DefaultView.class)
    private String       description;

    @NotNull
    @Column(name = "webAddress")
    @JsonView(Views.DefaultView.class)
    private String       webAddress;

    @NotNull
    @JsonView(Views.DefaultView.class)
    @OneToOne(mappedBy = "podcast")
    @JoinColumn(name = "podcast_id")
    private PodcastIcon  podcastIcon;

    @JsonView(Views.DetailedView.class)
    @OneToMany(mappedBy = "podcast")
    private Set<Episode> episodeList;

    @JsonView(Views.DetailedView.class)
    @ManyToMany(mappedBy = "podcastList", targetEntity = User.class)
    private Set<User>    userList;

    public Long getPodcastId()
    {
        return podcastId;
    }

    public void setPodcastId(Long podcastId)
    {
        this.podcastId = podcastId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getWebAddress()
    {
        return webAddress;
    }

    public void setWebAddress(String webAddress)
    {
        this.webAddress = webAddress;
    }

    public PodcastIcon getPodcastIcon()
    {
        return podcastIcon;
    }

    public void setPodcastIcon(PodcastIcon podcastIcon)
    {
        this.podcastIcon = podcastIcon;
    }

    public Set<Episode> getEpisodeList()
    {
        return episodeList;
    }

    public void setEpisodeList(Set<Episode> episodeList)
    {
        this.episodeList = episodeList;
    }

    public Set<User> getUserList()
    {
        return userList;
    }

    public void setUserList(Set<User> userList)
    {
        this.userList = userList;
    }

}
