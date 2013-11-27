package com.wemakestuff.teracast.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonView;
import com.wemakestuff.teracast.data.PodcastRepository;
import com.wemakestuff.teracast.model.Episode;
import com.wemakestuff.teracast.model.Podcast;
import com.wemakestuff.teracast.service.PodcastManager;
import com.wemakestuff.teracast.util.Strings;

@Path("/podcasts")
@RequestScoped
public class PodcastsService
{

    @Inject
    private Logger            log;

    @Inject
    private Validator         validator;

    @Inject
    private PodcastRepository podcastRepository;

    @Inject
    private PodcastManager    podcastManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Podcast.Views.DefaultView.class)
    public List<Podcast> listAllPodcasts()
    {
        return podcastRepository.findAllOrderedByName();
    }

    @GET
    @Path("/{podcastId:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Podcast.Views.DetailedView.class)
    public Podcast getPodcast(@PathParam("podcastId") long podcastId)
    {
        Podcast podcast = podcastRepository.findById(podcastId);
        if (podcast == null)
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return podcast;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPodcast(Podcast podcast)
    {
        Response.ResponseBuilder builder = null;
        try
        {
            // Create the slug.
            podcast.setSlug(Strings.toSlug(podcast.getName()));

            // Validates using bean validation
            validateEntity(podcast);

            podcastManager.addPodcast(podcast);

            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce)
        {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e)
        {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(
                    responseObj);
        }

        return builder.build();
    }

    @POST
    @Path("/{podcastId:[0-9][0-9]*}/episodes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEpisode(@PathParam("podcastId") long podcastId,
            Episode episode)
    {
        Response.ResponseBuilder builder = null;
        try
        {
            // Make sure the podcast exists.
            Podcast podcast = getPodcast(podcastId);

            // Create the slug.
            episode.setSlug(Strings.toSlug(episode.getName()));
            episode.setPodcast(podcast);

            // Validates using bean validation
            validateEntity(episode);

            podcastManager.addEpisode(episode);

            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce)
        {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e)
        {
            log.fine("Caught an Error");
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(
                    responseObj);
        }

        return builder.build();
    }

    /**
     * <p>
     * Validates the given Podcast variable and throws validation exceptions
     * based on the type of error. If the error is standard bean validation
     * errors then it will throw a ConstraintValidationException with the set of
     * the constraints violated.
     * </p>
     * 
     * @param podcast
     *            Podcast to be validated
     * @throws ConstraintViolationException
     *             If Bean Validation errors exist
     */
    private void validateEntity(Object entity)
            throws ConstraintViolationException
    {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Object>> violations = validator
                .validate(entity);

        if (!violations.isEmpty())
        {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));
        }
    }

    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation
     * fields, and their message. This can then be used by clients to show
     * violations.
     * 
     * @param violations
     *            A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
    private Response.ResponseBuilder createViolationResponse(
            Set<ConstraintViolation<?>> violations)
    {
        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<String, String>();

        for (ConstraintViolation<?> violation : violations)
        {
            responseObj.put(violation.getPropertyPath().toString(),
                    violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

}
