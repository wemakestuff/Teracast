/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wemakestuff.teracast.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.wemakestuff.teracast.model.Podcast;

@ApplicationScoped
public class PodcastRepository
{

    @Inject
    private EntityManager em;

    public List<Podcast> findAllOrderedByName()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Podcast> criteria = cb.createQuery(Podcast.class);
        Root<Podcast> podcast = criteria.from(Podcast.class);
        criteria.select(podcast).orderBy(cb.asc(podcast.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    public Podcast findBySlug(String slug)
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Podcast> criteria = cb.createQuery(Podcast.class);
        Root<Podcast> podcast = criteria.from(Podcast.class);
        criteria.select(podcast).where(cb.equal(podcast.get("email"), slug));
        return em.createQuery(criteria).getSingleResult();
    }

    public Podcast findById(Long podcastId)
    {
        return em.find(Podcast.class, podcastId);
    }
}
