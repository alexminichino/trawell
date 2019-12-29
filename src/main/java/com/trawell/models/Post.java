package com.trawell.models;

import javax.persistence.Entity;


@Entity
/**
 * @author Umberto Russomando
 * This class...
 */
public class Post {

    public Post() {
    }

    private Long id;
    private Long idOwner;
    private Long idGroup;
    private Long idPhoto;
    private String postDescription;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return this.idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Long getIdGroup() {
        return this.idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public Long getIdPhoto() {
        return this.idPhoto;
    }

    public void setIdPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getPostDescription() {
        return this.postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }


}