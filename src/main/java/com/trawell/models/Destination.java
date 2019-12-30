package com.trawell.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")

/**
 * @author Milione Vincent
 * class models an itinerary. An itinerary is a user's travel plan.
 */
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String description;
    private java.sql.Date date;
    private boolean isVisited;
    @ManyToOne
    @JoinColumn(name="id_itinerary")
    private Itinerary itinerary;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDate() {
        return this.date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public boolean getIsVisited() {
        return this.isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

}   