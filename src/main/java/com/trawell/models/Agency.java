package com.trawell.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
@PrimaryKeyJoinColumn(name = "id")

/**
 * User
 * Questa classe è solo un esempio, dovrete definire bene field e metodi
 */
public class Agency extends User {
    private String nameAgency;
    private String url;
    private String vat;

    public String getNameAgency() {
        return this.nameAgency;
    }

    public void setNameAgency(String nameAgency) {
        this.nameAgency = nameAgency;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVat() {
        return this.vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    
    @Transient
    private String transientVar;

    public Agency () {}

    /**
     * @param transientVar the transientVar to set
     */
    public void setTransientVar(String transientVar) {
        this.transientVar = transientVar;
    }
}
