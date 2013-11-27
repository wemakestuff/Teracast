package com.wemakestuff.teracast.model;

import java.io.Serializable;

public class EpisodeKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    protected Long            episodeId;

    protected Long            podcastId;

    public Long getEpisodeId()
    {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId)
    {
        this.episodeId = episodeId;
    }

    public Long getPodcastId()
    {
        return podcastId;
    }

    public void setPodcastId(Long podcastId)
    {
        this.podcastId = podcastId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((episodeId == null) ? 0 : episodeId.hashCode());
        result = prime * result
                + ((podcastId == null) ? 0 : podcastId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EpisodeKey other = (EpisodeKey) obj;
        if (episodeId == null)
        {
            if (other.episodeId != null)
                return false;
        } else if (!episodeId.equals(other.episodeId))
            return false;
        if (podcastId == null)
        {
            if (other.podcastId != null)
                return false;
        } else if (!podcastId.equals(other.podcastId))
            return false;
        return true;
    }

}
