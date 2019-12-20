package com.trawell.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
/**
 * User
 * Questa classe è solo un esempio, dovrete definire bene field e metodi
 */
public class Agency extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agencyName;
    private String agencyUrl;
    private String agencyPhone;
    private String agencyVat;

    public String getAgencyName() {
        return this.agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyUrl() {
        return this.agencyUrl;
    }

    public void setAgencyUrl(String agencyUrl) {
        this.agencyUrl = agencyUrl;
    }

    public String getAgencyPhone() {
        return this.agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    public String getAgencyVat() {
        return this.agencyVat;
    }

    public void setAgencyVat(String agencyVat) {
        this.agencyVat = agencyVat;
    }

    @Transient
    private String transientVar;

    public Long getId(){
        return id;
    }

    public Agency () {}

    /**
     * @param transientVar the transientVar to set
     */
    public void setTransientVar(String transientVar) {
        this.transientVar = transientVar;
    }
}
