package com.wemakestuff.teracast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@XmlRootElement
public class Episode implements Serializable
{
    public static class Views {
        public static class DefaultView {}
        public static class DetailedView extends DefaultView {}
    }
    
    private static final long serialVersionUID = 4203758567666878057L;

    @Id
    @GeneratedValue
    @Column(name = "episode_id")
    @JsonView(value=Views.DefaultView.class)
    private Long              episodeId;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @JsonView(value=Views.DefaultView.class)
    private String            name;

    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug must only contain lowercase letters, numbers, and dashes.")
    @Column(name = "slug")
    @JsonView(value=Views.DefaultView.class)
    private String            slug;

    @NotNull
    @Column(name = "description")
    @JsonView(value=Views.DefaultView.class)
    private String            description;

    @ManyToOne
    @JoinColumn(name="podcast_id")
    @JsonView(value=Views.DetailedView.class)
    private Podcast           podcast;

    public Podcast getPodcast()
    {
        return podcast;
    }

    public void setPodcast(Podcast podcast)
    {
        this.podcast = podcast;
    }

    public Long getEpisodeId()
    {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId)
    {
        this.episodeId = episodeId;
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
