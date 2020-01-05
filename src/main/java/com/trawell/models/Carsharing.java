package com.trawell.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name = "id")

/**
 * @author Milione Vincent
 * class models a Carsharing add postable by any user on the platform
 */
public class Carsharing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date departureDate;
    private String description;
    private String departure;
    private String arrival;
    private int carsharingspot;
    @ManyToMany(mappedBy = "list")
    private List<User> user_list;
    @ManyToOne
    @JoinColumn(name="id_owner")
    public User user;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Carsharing(){}

    public Carsharing(Long id) {
        this.id = id;
	}

	public List<User> getUser_list() {
        return this.user_list;
    }

    public void setUser_list(List<User> user_list) {
        this.user_list = user_list;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(java.util.Date departureDate) {
        this.departureDate = departureDate;
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
        Carsharing other = (Carsharing) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

	public Long getId() {
		return id;
	}
}