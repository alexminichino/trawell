package com.trawell.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author
 * 
 */

@Entity
public class TrawellGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idOwner;
    private String name;
    private String description;
    private Long idItinerary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Post> posts;
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trawellGroup")
    private List<Wallet> allWallets;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "groupmember",
            joinColumns = { @JoinColumn(name = "id_group") },
            inverseJoinColumns = { @JoinColumn(name = "id_user") })
    public Set<User> participants = new HashSet<>();

    @JsonIgnore
    public Set<User> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdItinerary() {
        return idItinerary;
    }

    public void setIdItinerary(Long id_itinerary) {
        this.idItinerary = id_itinerary;
    }

    public List<Wallet> getAllWallets() {
        return allWallets;
    }

    public void setAllWallets(List<Wallet> allWallets) {
        this.allWallets = allWallets;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
        TrawellGroup other = (TrawellGroup) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public TrawellGroup() {
    }

    public TrawellGroup(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long id_owner) {
        this.idOwner = id_owner;
    }

    @JsonIgnore
    public Wallet getPublicWallet() {
        Wallet w = allWallets.stream().filter(x -> x.getIdOwner() == null).findFirst().orElse(null);
        return w;
    }

}
