package com.trawell.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@PrimaryKeyJoinColumn(name = "id")

/**
 * @author Milione Vincent
 * class models the user agency
 */
public class Agency extends User {
    private static final long serialVersionUID = 6675266346589634692L;
    @NotBlank( message = "Name of the agency can not be empty")
    @NotEmpty(message = "Name of the agency can not be empty")
    @Size(min = 1, max = 20, message = "Name of agency must be between 1 and 20 characters")
    private String nameAgency;
    @NotBlank(message = "URL can not be empty")
    @NotEmpty(message = "URL can not be empty")
    private String url;
    @NotBlank(message = "VAT can not be empty")
    @NotEmpty(message = "VAT can not be empty")
    @Size(max = 12, message = "Vat must be 12 characters")
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
