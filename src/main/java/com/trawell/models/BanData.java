package com.trawell.models;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
/**
 * @author Mario Paone
 * 
 */

@Entity
public class BanData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "User id can not be empty")
    private Long idUser;
    @NotEmpty(message = "Admin id can not be empty")
    private Long idAdmin;
    private Date banUntil;
    private String motivation;

    public BanData() {
    }

    public BanData(Long id, Long idUser, Long idAdmin, Date banUntil, String motivation) {
        this.id = id;
        this.idUser = idUser;
        this.idAdmin = idAdmin;
        this.banUntil = banUntil;
        this.motivation = motivation;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdAdmin() {
        return this.idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Date getBanUntil() {
        return this.banUntil;
    }

    public void setBanUntil(Date banUntil) {
        this.banUntil = banUntil;
    }

    public String getMotivation() {
        return this.motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public BanData id(Long id) {
        this.id = id;
        return this;
    }

    public BanData idUser(Long idUser) {
        this.idUser = idUser;
        return this;
    }

    public BanData idAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
        return this;
    }

    public BanData banUntil(Date banUntil) {
        this.banUntil = banUntil;
        return this;
    }

    public BanData motivation(String motivation) {
        this.motivation = motivation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BanData)) {
            return false;
        }
        BanData banData = (BanData) o;
        return Objects.equals(id, banData.id) && Objects.equals(idUser, banData.idUser) && Objects.equals(idAdmin, banData.idAdmin) && Objects.equals(banUntil, banData.banUntil) && Objects.equals(motivation, banData.motivation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, idAdmin, banUntil, motivation);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getIdUser() + "'" +
            ", idAdmin='" + getIdAdmin() + "'" +
            ", banUntil='" + getBanUntil() + "'" +
            ", motivation='" + getMotivation() + "'" +
            "}";
    }

    
    

    
}
