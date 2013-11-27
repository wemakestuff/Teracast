package com.wemakestuff.teracast.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.wemakestuff.teracast.model.Episode;
import com.wemakestuff.teracast.model.Podcast;

@Stateless
public class PodcastManager
{

    @Inject
    private Logger         log;

    @Inject
    private EntityManager  em;

    @Inject
    private Event<Podcast> podcastEventSrc;
    
    @Inject
    private Event<Episode> episodeEventSrc;

    public void addPodcast(Podcast podcast) throws Exception
    {
        log.info("Adding Podcast " + podcast.getName());
        em.persist(podcast);
        podcastEventSrc.fire(podcast);
    }
    
    public void addEpisode(Episode episode) throws Exception {
        log.info("Adding Episode " + episode.getName() + " For Podcast " + episode.getPodcast().getName());
        em.persist(episode);
        episodeEventSrc.fire(episode);
    }
    
}
