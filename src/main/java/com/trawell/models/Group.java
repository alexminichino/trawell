package com.trawell.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.OneToOne;

/**
 * @author
 * 
 */

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@ManyToOne
    private Long id_owner;
    private String name;
    private String description;
    // @OneToOne()
    private Long id_itinerary;
    @OneToOne
    @JoinColumn(name = "id_wallet")
    private Wallet publicWallet;
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Wallet> allWallets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId_itinerary() {
        return id_itinerary;
    }

    public void setId_itinerary(Long id_itinerary) {
        this.id_itinerary = id_itinerary;
    }

    public List<Wallet> getAllWallets() {
        return allWallets;
    }

    public void setAllWallets(List<Wallet> allWallets) {
        this.allWallets = allWallets;
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
        Group other = (Group) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Group() {
    }

    public Group(Long id) {
        this.id = id;
    }

    public Long getId_owner() {
        return id_owner;
    }

    public void setId_owner(Long id_owner) {
        this.id_owner = id_owner;
    }

    public Wallet getPublicWallet() {
        return publicWallet;
    }

    public void setPublicWallet(Wallet publicWallet) {
        this.publicWallet = publicWallet;
    }

}
