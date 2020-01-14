package com.trawell.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity

/**
 * @author Milione Vincent
 * class models an itinerary. An itinerary is a user's travel plan.
 */
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name="id_owner")
    private User user;
    @OneToMany(orphanRemoval=true, mappedBy = "itinerary", fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<Destination> destinations;

    public List<Destination> getDestinations() {
        return this.destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Itinerary(){}

    public Itinerary(Long id){
        this.id = id;
    }

    public Itinerary (String name, List<Destination> list) {
        setName(name);
        setDestinations(list);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Itinerary other = (Itinerary) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}