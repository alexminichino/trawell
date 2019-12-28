package com.trawell.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Transient;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
/**
 * @author Milione Vincent 
 * class a user on platform
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mail;
    private String username;
    private String password;
    private String name;
    private String surname;
    private java.sql.Date birth;
    private boolean banned;
    private String bio;
    private int profilePhoto;
    private String phone;
    private boolean isAdmin;
    private boolean isBanned;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Carsharing> userAdds;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Itinerary> userItineraries; 
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "carspot", joinColumns = {@JoinColumn(name = "id_user")}, inverseJoinColumns = {@JoinColumn(name = "id_carsharing")})
    private Set<Carsharing> list;

    public List<Itinerary> getUserItineraries() {
        return this.userItineraries;
    }

    public void addItinerary(Itinerary itinerary) {
        itinerary.setUser(this);
        this.userItineraries.add(itinerary);
    }

    public void setUserItineraries(List<Itinerary> userItineraries) {
        this.userItineraries = userItineraries;
    }

    public Set<Carsharing> getList() {
        return this.list;
    }

    public void setList(Set<Carsharing> list) {
        this.list = list;
    }

    public List<Carsharing> getUserAdds() {
        return this.userAdds;
    }

    public void setUserAdds(List<Carsharing> userAdds) {
        this.userAdds = userAdds;
    }

    public java.sql.Date getBirth() {
        return this.birth;
    }

    public void setBirth(final java.sql.Date birth) {
        this.birth = birth;
    }

    public boolean getBanned() {
        return this.banned;
    }

    public void setBanned(final boolean banned) {
        this.banned = banned;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public int getProfilePhoto() {
        return this.profilePhoto;
    }

    public void setProfilePhoto(final int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(final boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsBanned() {
        return this.isBanned;
    }

    public void setIsBanned(final boolean isBanned) {
        this.isBanned = isBanned;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Transient
    private String transientVar;

    public Long getId(){
        return id;
    }

    public void setId(final Long id){
        this.id = id;
    }

    public User() {
        super();
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * @param transientVar the transientVar to set
     */
    public void setTransientVar(final String transientVar) {
        this.transientVar = transientVar;
    }
}