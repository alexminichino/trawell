
package com.trawell.models;

import com.trawell.models.User;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

/**
 * @author Umberto Russomando
 * This class models a post
 */

@Entity
public class Post 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_group")
    private TrawellGroup group;
    private String postDescription;

    private boolean isReported;
   
    @ManyToOne
    @JoinColumn(name="id_owner")
    private User user;
    
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Photo> photos; 

    public List<Photo> getPhotos() {
        return this.photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Post() 
    {
        super();
    }

    public Post(Long id, Long idOwner, Long idGroup, Long idPhoto, String postDescription) 
    {
        this.id = id;
        //this.idGroup = idGroup;
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

    public String getPostDescription() 
    {
        return this.postDescription;
    }

    public void setPostDescription(String postDescription) 
    {
        this.postDescription = postDescription;
    }

    /*
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idGroup='" + getIdGroup() + "'" +
            ", idPhoto='" + getIdPhoto() + "'" +
            ", postDescription='" + getPostDescription() + "'" +
            "}";
    }
    */
    
    
    public User getUser()
    {
        return this.user;
    }


    public void setUser(User user)
    {
        this.user = user;
    }
    

    public boolean isReported()
    {
        return this.isReported;
    }

    public void setIsReported(boolean isReported)
    {
        this.isReported = isReported;
    }
}
