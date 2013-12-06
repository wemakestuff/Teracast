package com.wemakestuff.teracast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Image implements Serializable
{
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
    @Column(name = "image_id")
    @JsonView(Views.DefaultView.class)
    private Long   imageId;

    @NotNull
    @Column(name = "width")
    @JsonView(Views.DefaultView.class)
    private int    width;

    @NotNull
    @Column(name = "height")
    @JsonView(Views.DefaultView.class)
    private int    height;

    @NotNull
    @Column(name = "webAddress")
    @JsonView(Views.DefaultView.class)
    private String webAddress;

}
