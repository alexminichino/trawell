package com.trawell.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "id")

/**
 * @author Milione Vincent
 * class models the user agency
 */
public class Agency extends User {
    private static final long serialVersionUID = 6675266346589634692L;
    @NotBlank
    @NotEmpty
    @Size
    private String nameAgency;
    @NotBlank
    @NotEmpty
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
}
