package com.trawell.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")

/**
 * @author Milione Vincent
 * class models the user agency
 */
public class Carsharing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private java.sql.Date departureDate;
    private String destination;
    private String departure;
    private String arrival;
    private int carsharingspot;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "list")
    private List<User> user_list; 

    public Carsharing(){}

    public java.sql.Date getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(java.sql.Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return this.departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return this.arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getCarsharingspot() {
        return this.carsharingspot;
    }

    public void setCarsharingspot(int carsharingspot) {
        this.carsharingspot = carsharingspot;
    }

}