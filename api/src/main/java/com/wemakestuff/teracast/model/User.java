package com.wemakestuff.teracast.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class User implements Serializable
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
    @Column(name = "user_id")
    @JsonView(Views.DefaultView.class)
    private Long         userId;

    @NotNull
    @Size(min = 1, max = 100)
    @Email
    @Column(name = "email_address")
    @JsonView(Views.DefaultView.class)
    private String       emailAddress;

    @NotNull
    @Column(name = "password")
    @JsonView(Views.DefaultView.class)
    private String       password;

    @NotNull
    @Column(name = "salt")
    private String       salt;

    @ManyToMany(targetEntity = Podcast.class)
    @JoinTable(name = "user_podcast", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "podcast_id"))
    private Set<Podcast> podcastList;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Set<Podcast> getPodcastList()
    {
        return podcastList;
    }

    public void setPodcastList(Set<Podcast> podcastList)
    {
        this.podcastList = podcastList;
    }

}
