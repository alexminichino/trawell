package com.trawell.models;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
/**
 * @author Umberto Russomando
 * This class...
 */

public class Post 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idOwner;
    private Long idGroup;
    private Long idPhoto;
    private String postDescription;

    public Post() 
    {
        super();
    }

    public Post(Long id, Long idOwner, Long idGroup, Long idPhoto, String postDescription) 
    {
        this.id = id;
        this.idOwner = idOwner;
        this.idGroup = idGroup;
        this.idPhoto = idPhoto;
        this.postDescription = postDescription;
    }

    public Long getId() 
    {
        return this.id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }


    public Long getIdOwner() 
    {
        return this.idOwner;
    }

    public void setIdOwner(Long idOwner) 
    {
        this.idOwner = idOwner;
    }

    public Long getIdGroup() 
    {
        return this.idGroup;
    }

    public void setIdGroup(Long idGroup) 
    {
        this.idGroup = idGroup;
    }

    public Long getIdPhoto() 
    {
        return this.idPhoto;
    }

    public void setIdPhoto(Long idPhoto) 
    {
        this.idPhoto = idPhoto;
    }

    public String getPostDescription() 
    {
        return this.postDescription;
    }

    public void setPostDescription(String postDescription) 
    {
        this.postDescription = postDescription;
    }

    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idOwner='" + getIdOwner() + "'" +
            ", idGroup='" + getIdGroup() + "'" +
            ", idPhoto='" + getIdPhoto() + "'" +
            ", postDescription='" + getPostDescription() + "'" +
            "}";
    }

}
