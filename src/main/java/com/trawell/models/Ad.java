package com.trawell.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
/**
 * @author Mario Pane 
 * class models an Ad 
 */
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idOwner; 
    private String adPaymentMethod;
    private Long adCost;
    private Date adStartingDate;
    private Date adDueDate;
    private String idPhoto;

    public Ad() {
    }


    public Ad(Long id, Long idOwner, String adPaymentMethod, Long adCost, Date adStartingDate, Date adDueDate, String idPhoto) {
        this.id = id;
        this.idOwner = idOwner;
        this.adPaymentMethod = adPaymentMethod;
        this.adCost = adCost;
        this.adStartingDate = adStartingDate;
        this.adDueDate = adDueDate;
        this.idPhoto = idPhoto;
    }

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

    public String getAdPaymentMethod() {
        return this.adPaymentMethod;
    }

    public void setAdPaymentMethod(String adPaymentMethod) {
        this.adPaymentMethod = adPaymentMethod;
    }

    public Long getAdCost() {
        return this.adCost;
    }

    public void setAdCost(Long adCost) {
        this.adCost = adCost;
    }

    public Date getAdStartingDate() {
        return this.adStartingDate;
    }

    public void setAdStartingDate(Date adStartingDate) {
        this.adStartingDate = adStartingDate;
    }

    public Date getAdDueDate() {
        return this.adDueDate;
    }

    public void setAdDueDate(Date adDueDate) {
        this.adDueDate = adDueDate;
    }

    public String getIdPhoto() {
        return this.idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ad)) {
            return false;
        }
        Ad ad = (Ad) o;
        return Objects.equals(id, ad.id) && Objects.equals(idOwner, ad.idOwner) && Objects.equals(adPaymentMethod, ad.adPaymentMethod) && Objects.equals(adCost, ad.adCost) && Objects.equals(adStartingDate, ad.adStartingDate) && Objects.equals(adDueDate, ad.adDueDate) && Objects.equals(idPhoto, ad.idPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idOwner, adPaymentMethod, adCost, adStartingDate, adDueDate, idPhoto);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idOwner='" + getIdOwner() + "'" +
            ", adPaymentMethod='" + getAdPaymentMethod() + "'" +
            ", adCost='" + getAdCost() + "'" +
            ", adStartingDate='" + getAdStartingDate() + "'" +
            ", adDueDate='" + getAdDueDate() + "'" +
            ", idPhoto='" + getIdPhoto() + "'" +
            "}";
    }

    





   
}