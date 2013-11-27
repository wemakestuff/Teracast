package com.wemakestuff.teracast.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.wemakestuff.teracast.rest.Views;

@Entity
public class Podcast implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "podcast_id")
    @JsonView(Views.PodcastView.class)
    private Long              podcastId;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @JsonView(Views.PodcastView.class)
    private String            name;

    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug must only contain lowercase letters, numbers, and dashes.")
    @Column(name = "slug")
    @JsonView(Views.PodcastView.class)
    private String            slug;

    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "description")
    @JsonView(Views.PodcastView.class)
    private String            description;

    @JsonView(Views.PodcastDetailView.class)
    @OneToMany(mappedBy = "podcast", fetch=FetchType.EAGER)
    private Set<Episode>      episodeList;

    public Set<Episode> getEpisodeList()
    {
        return episodeList;
    }

    public void setEpisodeList(Set<Episode> episodeList)
    {
        this.episodeList = episodeList;
    }

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

}
