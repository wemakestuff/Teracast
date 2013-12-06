package com.wemakestuff.teracast.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "podcast_icon")
public class PodcastIcon extends Image
{
    private static final long serialVersionUID = 1L;
    
    @OneToOne
    @JoinColumn(name = "podcast_id")
    @JsonView(value = Views.DetailedView.class)
    private Podcast podcast;

    public Podcast getPodcast()
    {
        return podcast;
    }

    public void setPodcast(Podcast podcast)
    {
        this.podcast = podcast;
    }
    
    
}
